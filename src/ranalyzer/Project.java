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
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Project {
    public String Id_project;
    public String Nama_project;
    public String Jenis_project;
    public String Tanggal_project;
    public List<RequirementDocument> docList = new ArrayList<RequirementDocument>(1000);
    public List<Statement> statementList = new ArrayList<Statement>(1000);
    
    public Project(String id, String nama, String jenis, String tanggal){
        this.Nama_project = nama;
        this.Tanggal_project = tanggal;
    }
    
    public Project(){
    
    }
    
    public void save(Project params, String namaProject){
        XMLEncoder encoder = null;
        
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(namaProject)));
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: While Creating or Opening the File");
        }
        
        encoder.writeObject(params);
        encoder.close();
    }
    
    public Project open(String params){
        XMLDecoder decoder = null;
        
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(params)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Project project = (Project)decoder.readObject();
        
        return project;
    }
    
    public void addDocument(RequirementDocument e){
        this.docList.add(e);
    }
    
    public void addStatement(Statement e){
        this.statementList.add(e);
    }
    
    public void create(Project params, String namaProject){
        XMLEncoder encoder = null;
        
        try{
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(namaProject)));
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: While Creating or Opening the File");
        }
        
        encoder.writeObject(params);
        encoder.close();
    }
    
    public void show(){
        
    }
}
