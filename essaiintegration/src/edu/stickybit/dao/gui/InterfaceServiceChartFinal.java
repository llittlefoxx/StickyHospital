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
import java.util.Set;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;

/**
 *
 * @author Asma Boussabat
 */
public class InterfaceServiceChartFinal {
    
    public InterfaceServiceChartFinal(){}
    
     public static final int GIRTH = 25;
 public static final String TITLE = "SERVICES HOSPITALISATION";
     PieChart chart;
    ObservableList<PieChart.Data> barChartData ;
  LitDao ld = new LitDao();
  ServicesDao ser=new ServicesDao();
  List<Integer> Services =new ArrayList<Integer>();
  List<Services> Services2 =new ArrayList<Services>();
    
    
    public HBox RecupererInterface(){
        
        
        final PieChart chart = new PieChart(getData());
    PieChart[] fatBack = new PieChart[GIRTH];
    for (int i = 0; i < fatBack.length; i++) {
      fatBack[GIRTH - (i + 1)] = makeFatBack();
    }
    chart.setTitle(TITLE);
 
    // layout the scene as a large stack of charts.
    final StackPane fatPieChart = new StackPane();
    fatPieChart.getChildren().addAll(fatBack);
    fatPieChart.getChildren().add(chart);
 
    // show the scene.
    StackPane layout = new StackPane();
    layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 30");
    layout.getChildren().add(fatPieChart);
    final Scene scene = new Scene(layout, 1500,1500);
    scene.setCamera(new PerspectiveCamera());
    
    // rotate a stack of charts to give a 3d back effect.
    for (int i = 0; i < fatBack.length; i++) {
      rotateBack(fatBack[i], GIRTH - i);
    }
 
    // rotate top chart and labels.
    final Region chartContent = (Region) chart.lookup(".chart-content");
    chartContent.setRotationAxis(Rotate.X_AXIS);
    chartContent.setRotate(-65);
 
    // adjust the lower text tabs so that they look better and don't overflow.
    for (final Node text : chartContent.lookupAll("Text")) {
      text.setStyle("-fx-font-size: 18;");
      hideLower(text);
    }
 
     // adjust the chart-pie-label-line color to make it stand out more.
    chartContent.lookup(".chart-pie-label-line").setStyle("-fx-stroke: dimgray;");
 
    // adjust the title and legend positions.
    chart.lookup("Label.chart-title").translateYProperty().bind(chartContent.heightProperty().divide(50));
    chart.lookup(".chart-legend").translateYProperty().bind(chartContent.heightProperty().divide(-8.5));
    fatPieChart.translateYProperty().bind(chartContent.heightProperty().divide(-300));
   
     HBox v=new HBox();
     v.getChildren().add(fatPieChart);
    return v;
  }
   
    
    private void hideLower(Node node) {
    final double my = node.getBoundsInParent().getMinY();
    final double cy = ((Region) node.getParent()).getHeight() / 2;
    if (my > cy) {
      makeTrulyInvisible(node);
    }
  }
 
  // hide the node if it is in the upper half of the chart.
  private void hideUpper(Node node) {
    final double my = node.getBoundsInParent().getMinY();
    final double cy = ((Region) node.getParent()).getHeight() / 2;
    if (my <= cy) {
      makeTrulyInvisible(node);
    }
  }
 
  // rotate a backing part of a pie chart stack.
  private void rotateBack(PieChart chart, int i) {
    // remove the stuff we don't need.
    makeTrulyInvisible(chart.lookup(".chart-title"));
    makeTrulyInvisible(chart.lookup(".chart-legend"));
 
    // position the chart.
    final Region chartBackContent = (Region) chart.lookup(".chart-content");
    chartBackContent.setRotationAxis(Rotate.X_AXIS);
    chartBackContent.setRotate(-65);
    chartBackContent.setTranslateY(i + 1);
 
    // we only want labels for the the chart on the bottom of the stack.
    Set<Node> chartBackText = chartBackContent.lookupAll("Text");
    for (final Node text : chartBackText) {
      text.setStyle("-fx-font-size: 18;");
      if (i + 1 != GIRTH) {
        makeTrulyInvisible(text);
      } else {
        hideUpper(text);
      }
    }
  }
 
  // ensure that what is hidden, remains hidden.
  private void makeTrulyInvisible(final Node text) {
    text.setVisible(false);
    text.visibleProperty().addListener(new InvalidationListener() {
      @Override public void invalidated(Observable observable) {
        text.setVisible(false);
      }
    });
  }
 
  // construct a pie chart to put in the fat stack.
  private PieChart makeFatBack() {
    final PieChart chartBack = new PieChart(getData());
    chartBack.setTitle(TITLE);
    return chartBack;
  }
 
  // get the pie chart data.
  private ObservableList<PieChart.Data> getData() {
     
      barChartData = FXCollections.observableArrayList();
         for(int i=0;i<ld.DisplatAllLitbyetat().size();i++){
                 Services.add(ld.DisplatAllLitbyetat().get(i).getIdservice());
         }
         Services2=ser.DisplayAllServices();
          for(int i=0;i<Services2.size();i++){
              int a=100-(100-count(Services2.get(i).getIdService())*100/Services.size()+1);
              String b=String.valueOf(a);
             
                 barChartData.add(new PieChart.Data(Services2.get(i).getNomService().toString()+"\n"+a+ "%" ,
                         
                 count(Services2.get(i).getIdService())));   
                 chart =new PieChart(barChartData); 
          }
    
        // setup chart
        chart.setId("BasicPie");
      //  chart.setTitle("SERVICES HOSPITALISATION "); 
        return barChartData;
  }
   public int count(int d){
         int cpt=0;
          for(int j=0;j<Services.size();j++){
            if(Services.get(j)==(d))
                    {cpt++;}}
          
           return cpt;}
}
