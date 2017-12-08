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
import javax.swing.*;

public class StatementController {
    ProjectWorksheet gui;
    JDialog statementDescriptionWindow;
    
    public StatementController(){
        
    }
    
    public StatementController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void showStatementForm(JDialog statementDescriptionWindow){
        this.statementDescriptionWindow = statementDescriptionWindow;
        this.statementDescriptionWindow.setEnabled(true);
        this.statementDescriptionWindow.setVisible(true);
    }
    
    public void storeStatement(String name, String content){
        this.gui.project.addStatement(new Statement(content, name));
        this.gui.statementLstModel.addElement(name);
        this.statementDescriptionWindow.setEnabled(false);
        this.statementDescriptionWindow.setVisible(false);
    }
    
    public void editStatement(String name, String content){
        this.gui.editStatement(name, content);
    }
}
