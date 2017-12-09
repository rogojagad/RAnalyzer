/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ranalyzer;
import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
 *
 * @author 5115100168
 */
public class RequirementDocument {
    public String Id_document;
    public String Nama_document;
    public String Document_path;
    public String Tanggal_document;
    public String content;
    public String deskripsi;
    
    public List<Component> componentList = new ArrayList<Component>(1000);
    public List<Relation> relationList = new ArrayList<Relation>(1000);
    
    public RequirementDocument(){
        
    }
    
    public void addDeskripsi(String newDeskripsi){
        this.deskripsi = newDeskripsi;
    }
    
    public RequirementDocument(String id, String nama, String path, String content){
        this.Id_document = id;
        this.Nama_document = nama;
        this.Document_path = path;
        this.content = content;
        
        readComponents();
    }
    
    public void readComponents(){
        try{
            File inputFile = new File(this.Document_path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("packagedElement");
            
            for(int i=1;i<nList.getLength();i++){
                Node node = nList.item(i);
                Element curr = (Element) node;
                
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    String id = curr.getAttribute("xmi:id");
                    String name = curr.getAttribute("name");
                    String type = curr.getAttribute("xmi:type");
                    
                    String[] stripType = type.split(":");
                    
                    componentList.add(new Component(id, stripType[1], name));
                }
            }
            
            for(int i = 1; i<nList.getLength(); i++){
                Node node = nList.item(i);
                
                Element curr = (Element) node;
                
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    NodeList assoc = element.getElementsByTagName("ownedEnd");
                    
                    if(assoc.getLength() != 0){
                        for(int j =0 ; j<assoc.getLength();j+=2){
                            Node currentChild = assoc.item(j);
                            Node nextChild = assoc.item(j+1);
                            
                            if(currentChild.getNodeType() == Node.ELEMENT_NODE){
                                Element now = (Element) currentChild;
                                Element next = (Element) nextChild;
                                
                                String src = findNameById(now.getAttribute("type"), this);
                                String dest = findNameById(next.getAttribute("type"),this);
                                String relation = "association";
                                
                                relationList.add(new Relation(src, dest, relation));
                                
                            }
                        }
                    }
                    
                    NodeList include = element.getElementsByTagName("include");
                    
                    if(include.getLength() != 0){
                        for(int j = 0; j<include.getLength(); j++){
                            Node currentChild = include.item(j);
                            
                            if(currentChild.getNodeType() == Node.ELEMENT_NODE){
                                Element now = (Element) currentChild;
                                
                                String src = curr.getAttribute("name");
                                String dest = findNameById(now.getAttribute("addition"),this);
                                String rel = "includes";
                                
                                relationList.add(new Relation(src, dest, rel));
                            }
                        }
                    }
                    
                    NodeList extend = element.getElementsByTagName("extend");
                    
                    if(extend.getLength() != 0){
                        for(int j = 0; j<extend.getLength(); j++){
                            Node currentChild = extend.item(j);
                            
                            if(currentChild.getNodeType() == Node.ELEMENT_NODE){
                                Element now = (Element) currentChild;
                                
                                String src = curr.getAttribute("name");
                                String dest = findNameById(now.getAttribute("addition"),this);
                                String rel = "extends";
                                
                                relationList.add(new Relation(src, dest, rel));
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        for(final Relation i: relationList){
            System.out.println(i.idSrc + " " + i.relation + " " + i.idDestination + "\n");
        }
        
        System.out.println("========================\n");
    }
    
    public void addDocument(){
        
    }
    
    public void removeDocument(){
        
    }
    
    public static String findNameById(String id, RequirementDocument e){
        String res="";
        for (Component i : e.componentList) {
            if(i.xmiId.equals(id)){
                res = i.umlName;
                break;
            }
        }
        return res;
    }
    
    public String getContent(){
        String content = "Component list : \n";
        
        for(final Component i: componentList){
            content = content + "- " + i.umlName + " as " + i.umlType + "\n";
        }
        
        content = content + "\nRelation list : \n";
        
        for(final Relation i: relationList){
            content = content + "- " + i.idSrc + " " + i.relation + " " + i.idDestination + "\n";
        }
        
        content = content + "\n";
        
        return content;
    }
}
