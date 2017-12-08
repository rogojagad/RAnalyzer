/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranalyzer;

import java.io.*;
import javax.swing.*;

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
    
    public void showProjectDescriptionWindow(JFileChooser projectOpener){
        int returnVal = projectOpener.showOpenDialog(this.gui);
        File file;
        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = projectOpener.getSelectedFile();
            
            this.gui.project = new Project();
            this.gui.project = this.gui.project.open(file.getAbsolutePath());
            this.gui.projectName = this.gui.project.Nama_project;
            this.gui.setTitle(file.getName());
        }
        
        this.gui.changeEnable(true);
        this.gui.initListDoc();
        
        this.gui.initListStatement();
    }
}
