/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranalyzer;

import javax.swing.JFileChooser;

/**
 *
 * @author 5115100168
 */
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentController {
    private ProjectWorksheet gui;
    private JFileChooser fileExplorer;
    private JDialog openDocLoadingDialog;
    private JProgressBar loadingBar;
    private JDialog progressInformation;
    private JProgressBar progressBar;
    
    public DocumentController(){
        
    }
    
    public DocumentController(ProjectWorksheet guiParam){
        this.gui = guiParam;
    }
    
    public void openFileExplorer(JFileChooser fileExplorer, JDialog openDocLoadingDialog, JProgressBar loadingBar){
        this.fileExplorer = fileExplorer;
        this.openDocLoadingDialog = openDocLoadingDialog;
        this.loadingBar = loadingBar;
        
        int returnVal = this.fileExplorer.showOpenDialog(gui);
        
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = this.fileExplorer.getSelectedFile();
            String content = "";
            
            try{
                content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            } catch (IOException ex) {
                Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Task task = new Task();
            
            task.start();
            this.loadingBar.setEnabled(true);
            this.loadingBar.setVisible(true);
            this.openDocLoadingDialog.setEnabled(true);
            this.openDocLoadingDialog.setVisible(true);
            
            this.openDocLoadingDialog.setEnabled(false);
            this.openDocLoadingDialog.setVisible(false);
            
            RequirementDocument newDoc = new RequirementDocument("01", file.getName(), file.getAbsolutePath(), content);
            
            this.gui.project.addDocument(newDoc);
            
            this.gui.model.addElement(file.getName());
            
        }
    }
    
    public void loadUseCaseDescriptionForm(String docName, JLabel label, JDialog descriptionWindow){
        label.setText("Description for use case : " + docName);
        descriptionWindow.setEnabled(true);
        descriptionWindow.setVisible(true);
    }
    
    public void submitUseCaseDescription(String description, JList<String> docList, JDialog progressInformation, JProgressBar progressBar){
        this.progressInformation = progressInformation;
        this.progressBar = progressBar;
        
        for(final RequirementDocument i: this.gui.project.docList){
            if(i.Nama_document.equals(docList.getSelectedValue())){
                i.addDeskripsi(description);
                break;
            }
        }
        ProgressTask countTask = new ProgressTask();
        countTask.start();
        
        this.progressInformation.setEnabled(true);
        this.progressInformation.setVisible(true);
        this.progressBar.setEnabled(true);
        this.progressBar.setVisible(true);
//        this.gui.saveUseCaseDescription(description);
    }
    
    public void showDeleteConfirmation(String name, JLabel msg, JDialog deleteDocConfirm){
        msg.setText("Are you sure want to delete document " + name + " ?");
        deleteDocConfirm.setEnabled(true);
        deleteDocConfirm.setVisible(true);
    }
    
    public void deleteDocument(String name, int index){
        for(final RequirementDocument i: this.gui.project.docList){
            if(i.Nama_document.equals(name)){
                this.gui.project.docList.remove(i);
                break;
            }
        }
        
        if(index != -1){
            this.gui.model.remove(index);
        }
    }
    
    private class Task extends Thread{
        public Task(){
            
        }
        
        public void run(){
            for(int i = 0; i <= 100; i++){
                final int progress = i;
                
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        loadingBar.setValue(progress);
                    }
                });
                try {
                    Thread.sleep(10);
                 } catch (InterruptedException e) {}
            }
            
            dismissLoadingDialog();
        }
    }
    
    private class ProgressTask extends Thread{
        public ProgressTask(){
            
        }
        
        public void run(){
            for(int i = 0; i <= 100; i++){
                final int progress = i;
                
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        progressBar.setValue(progress);
                    }
                });
                try {
                    Thread.sleep(5);
                 } catch (InterruptedException e) {}
            }
            
            dismissProgressDialog();
        }
    }
    
    public void dismissProgressDialog(){
        this.progressBar.setEnabled(false);
        this.progressBar.setVisible(false);
        
        this.progressInformation.setEnabled(false);
        this.progressInformation.setVisible(false);
    }
    
    public void dismissLoadingDialog(){
        this.openDocLoadingDialog.setEnabled(false);
        this.openDocLoadingDialog.setVisible(false);
    }
}

