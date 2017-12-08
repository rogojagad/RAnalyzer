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
public class ProjectController {
    ProjectWorksheet gui;
    
    public ProjectController(){
        
    }
    
    public ProjectController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void showProjectDescriptionWindow(){
        this.gui.showCreateProjectDescriptionWindow();
    }
    
    public void createNewProject(String name, String path, String desc, String tgl){
        this.gui.createNewProject(name, path, desc, tgl);
    }
}
