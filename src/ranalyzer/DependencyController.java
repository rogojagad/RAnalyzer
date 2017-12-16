/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranalyzer;

import javax.swing.*;
import info.debatty.java.stringsimilarity.JaroWinkler;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
/**
 *
 * @author 5115100168
 */
public class DependencyController {
    ProjectWorksheet gui;
    JDialog dependencyCheckWindow;
    JProgressBar checkDependencyProgressBar;
    
    public DependencyController(){
        
    }
    
    public DependencyController(ProjectWorksheet paramGui){
        this.gui = paramGui;
    }
    
    public void loading(JDialog dependencyCheckWindow, JProgressBar checkDependencyProgressBar){
        this.dependencyCheckWindow = dependencyCheckWindow;
        this.checkDependencyProgressBar = checkDependencyProgressBar;
        
        Task task = new Task();
        task.start();
        
        this.dependencyCheckWindow.setVisible(true);
        this.dependencyCheckWindow.setEnabled(true);
        this.checkDependencyProgressBar.setVisible(true);
        this.checkDependencyProgressBar.setEnabled(true);
    }
    
    private class Task extends Thread{
        public Task(){
            
        }
        
        public void run(){
            for(int i = 0; i <= 100; i++){
                final int progress = i;
                
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        checkDependencyProgressBar.setValue(progress);
                    }
                });
                try {
                    Thread.sleep(20);
                 } catch (InterruptedException e) {}
            }
            
            dismissProgressDialog();
        }
    }
    
    public void dismissProgressDialog(){
        this.checkDependencyProgressBar.setEnabled(false);
        this.checkDependencyProgressBar.setVisible(false);
        
        this.dependencyCheckWindow.setEnabled(false);
        this.dependencyCheckWindow.setVisible(false);
    }
    
    public void viewDependency(List<Component> components, List<Statement> statements, List<Relation> relations){
        for(final Statement i: statements){
            i.Statement_content = preprocessStatement(i.Statement_content);
        }
        
        Map<String, Double> similarityResultToComponent = new HashMap();
        Map<String, String> statementToComponent = new HashMap();
        
        for(final Statement i: statements){
            
            for(final Component j: components){
                if(j.umlType.equals("Actor")) continue;
                double rs = similarityCheck(j.umlName.toLowerCase().split(" "), i.Statement_content.split(" "));
                similarityResultToComponent.put(j.umlName, new Double(rs));
            }
            
            Double pcg = -2.0;
            String selectedComponent = "";
            System.out.println(i.Statement_content);
            for(Map.Entry<String, Double> entry: similarityResultToComponent.entrySet()){
                System.out.println(entry.getKey() + " --- " + entry.getValue());
            }
            System.out.println("===========================================");
            for(Map.Entry<String, Double> entry: similarityResultToComponent.entrySet()){
                if(entry.getValue() > pcg){
                    pcg = entry.getValue();
                    selectedComponent = entry.getKey();
                }
            }
            
//            System.out.println(selectedComponent + " ++++++ " + i.Statement_content);
            statementToComponent.put(i.Statement_content, selectedComponent);
            similarityResultToComponent.clear();
        }
        System.out.println("\n");
        
        for(Map.Entry<String, String> entry: statementToComponent.entrySet()){
            System.out.println(entry.getKey() + " ----> " + entry.getValue());
        }
        
        
        generateGraph(statementToComponent, relations, components);
    }
    
    public void generateGraph(Map<String, String> statementToComponent, List<Relation> relations, List<Component> components){
        Graph graph = new Graph("Requirement Dependency Graph", statementToComponent, relations, components);
    }
    
    public double similarityCheck(String[] listStrComponent, String[] listStrStatement){
        JaroWinkler jw = new JaroWinkler();
        double means;
        double sum_means = 0;
        double n = (double) listStrComponent.length;
        double statLength = (double) listStrStatement.length;
        
        for(int i=0; i<listStrStatement.length; i++){
            means = 0;
            
            for(int j=0; j<listStrComponent.length; j++){
                means += jw.similarity(listStrStatement[i], listStrComponent[j]);
//                System.out.println(listStrStatement[i]);
            }
            
            sum_means += means/n;
//            System.out.println(means/n);
        }
        
        return sum_means / statLength;
    }
    
    public String preprocessStatement(String statementContent){
        
        Analyzer analyzer = new StopAnalyzer(Version.LUCENE_36);
        
        TokenStream tokenStream  = analyzer.tokenStream(LuceneConstants.CONTENTS, new StringReader(statementContent));
        
        tokenStream = new StopFilter(Version.LUCENE_36, tokenStream, StandardAnalyzer.STOP_WORDS_SET);
        
        List<String> stopWords = new ArrayList<String>(Arrays.asList("a","can", "may", "should", "shall", "will"));
        
        tokenStream = new StopFilter(Version.LUCENE_36, tokenStream, StopFilter.makeStopSet(Version.LUCENE_36, stopWords));
        
        TermAttribute term = tokenStream.addAttribute(TermAttribute.class);
        
        String pre = "";
        
        try {
            while(tokenStream.incrementToken()){
//            System.out.println("[" + term.term() + "]");
//            pre.add(term.term());
                pre = pre + term.term() + " ";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return pre;
    }
    
}
