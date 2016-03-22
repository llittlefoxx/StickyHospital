/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;
import edu.stickybit.dao.classes.DossierPatientDAO;
import edu.stickybit.entity.DossierPatient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.VBox;
/**
 *
 * @author ClinicPc
 */
public class InterfaceChartPatient {
    
    public InterfaceChartPatient(){}
    
    
    DossierPatientDAO dao = new DossierPatientDAO();
   List<DossierPatient> dossier =new ArrayList<DossierPatient>();
   List<String> years =new ArrayList<String>();
   List<String> years2 =new ArrayList<String>();
   BarChart chart;
   ObservableList<BarChart.Series> barChartData ;
    
    
    
    public VBox RecupererInterface(){
    
        
    Group root=new Group();
       // primaryStage.setScene(new Scene(root));
        
         dossier=dao.DisplayAllDossierPatient();
       for (DossierPatient dossier1 : dossier) {
         String a=  dossier1.getDate_creation_dossier().toString();
         if(years.contains(a)){
             System.out.println("  nn ");}
         else{
          years.add(a.substring(0,4));}
       }     
      
      
         years2=removeDuplicates((ArrayList<String>) years);
        CategoryAxis xAxis = new CategoryAxis();
        double a=  xAxis.getLayoutX();
        xAxis.setCategories(FXCollections.<String>observableArrayList(years2));
        NumberAxis yAxis = new NumberAxis("Patient", 0.0d, 100.0d, 1.0d);
     
        barChartData = FXCollections.observableArrayList();
       //years2=removeDuplicates((ArrayList<String>) years);
     
       Collections.sort(years2);
          
   //     barChartData.add(new BarChart.Series(null,null));
       for(int i=0;i<years2.size();i++){
        barChartData.add(new BarChart.Series(years2.get(i), FXCollections.observableArrayList(new BarChart.Data(years2.get(i),count(years.get(i))))));
        
       chart = new BarChart(xAxis, yAxis, barChartData,100.0d);}
       chart.setMinHeight(500);
       chart.setMinWidth(1000);
       chart.setTitle("Nombre de patients par ans");
       VBox v =new VBox();
       v.getChildren().add(chart);
       root.getChildren().add(v);
         
    return v;
    }
    public int count(String d){
         int cpt=0;
          for(int j=0;j<years.size();j++){
            if(years.get(j).equals(d))
                    {cpt++;}}
           return cpt;}
    
    
        static ArrayList<String> removeDuplicates(ArrayList<String> list) {
	// Remove Duplicates: place them in new list (see above example).
	ArrayList<String> result = new ArrayList<>();
	HashSet<String> set = new HashSet<>();
	for (String item : list) {
	    if (!set.contains(item)) {
		result.add(item);
		set.add(item);
	    }
	}
	return result;
        }
        
        
    
    
}