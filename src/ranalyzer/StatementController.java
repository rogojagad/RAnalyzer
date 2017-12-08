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
    JTextField editStatementNameTxt;
    JTextArea editStatementContentTxt;
    
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
    
    public void storeChangedStatement(String oldName, String oldContent, String newName, String newContent,JList<String> statementListParam){
        int index = statementListParam.getSelectedIndex();
        
        if(index != -1){
            this.gui.statementLstModel.remove(index);
        }
        
        for(final Statement i: this.gui.project.statementList){
            if(i.Nama_statement.equals(oldName)){
                i.Statement_content = newContent;
                i.Nama_statement = newName;
                break;
            }
        }
        
        this.gui.statementLstModel.addElement(newName);
        
    }
    
    public void editStatement(String name, String content, JTextField statName, JTextArea statContent){
        this.editStatementNameTxt = statName;
        this.editStatementContentTxt = statContent;
        
        for(final Statement i: this.gui.project.statementList){
            if(i.Nama_statement.equals(name)){
                content = i.Statement_content;
                break;
            }
        }
        
        this.editStatementNameTxt.setText(name);
        this.editStatementContentTxt.setText(content);
        this.gui.tmpStatName = name;
//        this.gui.editStatement(name, content);
    }
}
