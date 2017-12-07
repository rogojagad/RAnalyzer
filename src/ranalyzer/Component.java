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
public class Component {
    public String xmiId;
    public String umlType;
    public String umlName;
    
    public Component(){
        
    }
    
    public Component(String paramId, String paramType, String paramName){
        this.xmiId = paramId;
        this.umlType = paramType;
        this.umlName = paramName;
    }
    
    
}
