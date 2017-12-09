/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranalyzer;

/**
 *
 * @author 5115100168
 */
import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;

public class Graph {
    MultiGraph dependency;
    Map<String, String> statementToComponent;
    List<Relation> relations;
    List<Component> components;
    int idx = 0;
    
    public Graph(String graphTitle, Map<String, String> statementToComponent, List<Relation> relations, List<Component> components){
        this.dependency = new MultiGraph(graphTitle);
        this.statementToComponent = statementToComponent;
        this.relations = relations;
        this.components = components;
        
        for(Map.Entry<String, String> entry: statementToComponent.entrySet()){
            String src = entry.getValue();
            String statement1 = entry.getKey();
            generateGraph(src, statement1);
        }
        
        Viewer viewer = dependency.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        
        
        for(Node x: dependency){
            x.addAttribute("ui.style", "text-size: 20;");
            x.addAttribute("ui.label", x.getId());
        }
    }
    
    public void generateGraph(String componentSrc, String statementSrc){
        String componentDst = "";
        for(final Relation i: relations){
            if(i.idSrc.equals(componentSrc)){
                componentDst = i.idDestination;
                
                for(Map.Entry<String, String> entry: statementToComponent.entrySet()){
                    if(entry.getValue().equals(componentDst)){
//                        componentDst = entry.getKey();
                        
                        if(dependency.getNode(statementSrc) == null) dependency.addNode(statementSrc);
                        
                        if(dependency.getNode(entry.getKey()) == null) dependency.addNode(entry.getKey());
                        
                        dependency.addEdge(Integer.toString(idx++), statementSrc, entry.getKey(), true);
                        System.out.println(statementSrc + " to " + entry.getKey() + "success");
                        break;
                    }
                }
            }
        }
    }
}
