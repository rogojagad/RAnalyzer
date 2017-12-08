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
public class StatementController {
    ProjectWorksheet gui;
    
    public StatementController(){
        
    }
    
    public StatementController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void showStatementForm(){
        this.gui.showStatementDescriptionWindow();
    }
    
    public void storeStatement(String name, String content){
        this.gui.storeStatement(name, content);
    }
    
    public void editStatement(String name, String content){
        this.gui.editStatement(name, content);
    }
}
