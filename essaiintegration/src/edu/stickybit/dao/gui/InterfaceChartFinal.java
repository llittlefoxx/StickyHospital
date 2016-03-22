/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.LitDao;
import edu.stickybit.dao.classes.ServicesDao;
import edu.stickybit.entity.Services;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class InterfaceChartFinal {
    
    
    public InterfaceChartFinal(){}
    
    
      private int itemNameIndex = 1;
    PieChart chart;
    ObservableList<PieChart.Data> barChartData;
    LitDao ld = new LitDao();
    ServicesDao ser = new ServicesDao();
    List<Integer> Services = new ArrayList<Integer>();
    List<Services> Services2 = new ArrayList<Services>();
    Label caption = new Label("");
    
    public HBox RecupererInterface(){
    
         Group root = new Group();

        //root.setTitle("Imported Fruits");
         PieChart p=new PieChart();
        p=createChart();
      
        HBox chart_h=new HBox();
       chart_h.getChildren().add(p);
        
        
        return chart_h;
   
    }
   

    protected PieChart createChart() {
        caption.setTextFill(Color.DARKORANGE);

        caption.setStyle("-fx-font: 24 arial;");
        barChartData = FXCollections.observableArrayList();
        for (int i = 0; i < ld.DisplatAllLitbyetat().size(); i++) {
            Services.add(ld.DisplatAllLitbyetat().get(i).getIdservice());
        }
        Services2 = ser.DisplayAllServices();
        for (int i = 0; i < Services2.size(); i++) {
            int a = count(Services2.get(i).getIdService()) * 100 / 3;
            String b = String.valueOf(a);
            caption.setText(b);
            barChartData.add(new PieChart.Data(Services2.get(i).getNomService().toString() + "\n" + caption.getText() + "%",
                    count(Services2.get(i).getIdService())));
            chart = new PieChart(barChartData);
        }

        // setup chart
        chart.setId("BasicPie");
        chart.setTitle("SERVICES HOSPITALISATION ");
     
     return chart;
    }
    
    
    public int count(int d) {
        int cpt = 0;
        for (int j = 0; j < Services.size(); j++) {
            if (Services.get(j) == (d)) {
                cpt++;
            }
        }
        return cpt;
    }
    
}
