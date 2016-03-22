/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import edu.stickybit.dao.classes.DossierPatientDAO;
import edu.stickybit.entity.DossierPatient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Accordion;
import javafx.scene.layout.VBox;

/**
 * A chart that displays rectangular bars with heights indicating data values
 * for categories. Used for displaying information when at least one axis has
 * discontinuous or discrete data.
 *
 * @see javafx.scene.chart.BarChart
 * @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.Axis
 * @see javafx.scene.chart.CategoryAxis
 * @see javafx.scene.chart.NumberAxis
 *
 */
public class BarChartSample extends Application {
DossierPatientDAO dao = new DossierPatientDAO();
   List<DossierPatient> dossier =new ArrayList<DossierPatient>();
   List<String> years =new ArrayList<String>();
   List<String> years2 =new ArrayList<String>();
   BarChart chart;
   ObservableList<BarChart.Series> barChartData ;
    
           
    private void init(Stage primaryStage) {
        Group root=new Group();
        primaryStage.setScene(new Scene(root));
        
         dossier=dao.DisplayAllDossierPatient();
       for (DossierPatient dossier1 : dossier) {
         String a=  dossier1.getDate_creation_dossier().toString();
         if(years.contains(a)){
             System.out.println("  nn ");}
         else{
          years.add(a.substring(0,4));}
       }     
      
      
        
        CategoryAxis xAxis = new CategoryAxis();
        double a=  xAxis.getLayoutX();
        xAxis.setCategories(FXCollections.<String>observableArrayList(years));
        NumberAxis yAxis = new NumberAxis("Patient", 0.0d, 100.0d, 1.0d);
     
        barChartData = FXCollections.observableArrayList();
       years2=removeDuplicates((ArrayList<String>) years);
     
       Collections.sort(years2);
          
   //     barChartData.add(new BarChart.Series(null,null));
       for(int i=0;i<years2.size();i++){
        barChartData.add(new BarChart.Series(years2.get(i), FXCollections.observableArrayList(new BarChart.Data(years2.get(i),count(years.get(i))))));
        
        chart = new BarChart(xAxis, yAxis, barChartData,100.0d);}

       chart.setMinHeight(500);
       chart.setMinWidth(1000);
       VBox v =new VBox();
       v.getChildren().add(chart);
       root.getChildren().add(v);
        
      
    
    
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
   
    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
