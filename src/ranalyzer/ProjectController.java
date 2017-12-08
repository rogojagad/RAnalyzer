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
public class ProjectController {
    ProjectWorksheet gui;
    
    public ProjectController(){
        
    }
    
    public ProjectController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void showProjectDescriptionWindow(JDialog projectDescriptionWindow){
        projectDescriptionWindow.setEnabled(true);
        projectDescriptionWindow.setVisible(true);
//        this.gui.showCreateProjectDescriptionWindow();
    }
    
    public void createNewProject(String name, String path, String desc, String tgl){
        String fullNama = path+"\\"+name+".ran";
        this.gui.project = new Project("01", fullNama, "private", tgl);
        this.gui.project.create(this.gui.project, fullNama);
        this.gui.projectName = name;
//        this.gui.createNewProject(name, path, desc, tgl);
    }
}
