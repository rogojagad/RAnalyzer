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
public class ExistingProjectController {
    ProjectWorksheet gui;
    
    public ExistingProjectController(){
        
    }
    
    public ExistingProjectController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void showProjectDescriptionWindow(){
        this.gui.showOpenProjectDescriptionWindow();
    }
}
