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
public class DocumentController {
    GUI gui;
    
    public DocumentController(){
        
    }
    
    public DocumentController(GUI guiParam){
        this.gui = guiParam;
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
}
