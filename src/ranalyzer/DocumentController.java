/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranalyzer;

import javax.swing.JFileChooser;

/**
 *
 * @author 5115100168
 */
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentController {
    private ProjectWorksheet gui;
    private JFileChooser fileExplorer;
    private JDialog openDocLoadingDialog;
    private JProgressBar loadingBar;
    
    public DocumentController(){
        
    }
    
    public DocumentController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void openFileExplorer(JFileChooser fileExplorer, JDialog openDocLoadingDialog, JProgressBar loadingBar){
        this.fileExplorer = fileExplorer;
        this.openDocLoadingDialog = openDocLoadingDialog;
        this.loadingBar = loadingBar;
        
        int returnVal = this.fileExplorer.showOpenDialog(gui);
        
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = this.fileExplorer.getSelectedFile();
            String content = "";
            
            try{
                content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            } catch (IOException ex) {
                Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Task task = new Task();
            
            task.start();
            this.loadingBar.setEnabled(true);
            this.loadingBar.setVisible(true);
            this.openDocLoadingDialog.setEnabled(true);
            this.openDocLoadingDialog.setVisible(true);
            
            this.openDocLoadingDialog.setEnabled(false);
            this.openDocLoadingDialog.setVisible(false);
            
            RequirementDocument newDoc = new RequirementDocument("01", file.getName(), file.getAbsolutePath(), content);
            
            this.gui.project.addDocument(newDoc);
            
            this.gui.model.addElement(file.getName());
            
        }
    }
    
    public void loadUseCaseDescriptionForm(){
        this.gui.showUseCaseDescriptionForm();
    }
    
    public void submitUseCaseDescription(String description){
        this.gui.saveUseCaseDescription(description);
    }
    
    public void showDeleteConfirmation(String name){
        this.gui.showConfirmDocumentDelete(name);
    }
    
    public void deleteDocument(String name){
        this.gui.deleteSelectedDocument(name);
    }
    
    private class Task extends Thread{
        public Task(){
            
        }
        
        public void run(){
            for(int i = 0; i <= 100; i++){
                final int progress = i;
                
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        loadingBar.setValue(progress);
                    }
                });
                try {
                    Thread.sleep(10);
                 } catch (InterruptedException e) {}
            }
            
            dismissLoadingDialog();
        }
    }
    
    public void dismissLoadingDialog(){
        this.openDocLoadingDialog.setEnabled(false);
        this.openDocLoadingDialog.setVisible(false);
    }
}

