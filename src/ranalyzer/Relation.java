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
public class Relation {
    public String idSrc;
    public String idDestination;
    public String relation;
    
    public Relation(){
        
    }
    
    public Relation(String src, String destination, String rel){
        this.idSrc = src;
        this.idDestination = destination;
        this.relation = rel;
    }
    
}
