/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.LigneFactureDAO;
import edu.stickybit.dao.classes.MedicamentDAO;
import edu.stickybit.entity.LigneFacture;
import edu.stickybit.entity.Medicament;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
public class InterfaceBarChartmedicamntFinal {

    public InterfaceBarChartmedicamntFinal() {
    }

    LigneFactureDAO lignedao = new LigneFactureDAO();
    MedicamentDAO med = new MedicamentDAO();
    List<LigneFacture> lflist = new ArrayList<LigneFacture>();
    List<Medicament> lsitmed = new ArrayList<Medicament>();
    List<String> libelle = new ArrayList<String>();
    //List<String> years2 =new ArrayList<String>();
    BarChart chart;
    ObservableList<BarChart.Series> barChartData;

    public BorderPane RecupererInterface() {

        Group root = new Group();

        lsitmed = med.DisplayAllmed();
        lflist = lignedao.DisplayAllligne();
        for (Medicament lf : lsitmed) {
            String a = lf.getLibelle();
            libelle.add(a);
        }
        CategoryAxis xAxis = new CategoryAxis();
        double a = xAxis.getLayoutX();
        xAxis.scaleXProperty().setValue(a);
        xAxis.setCategories(FXCollections.<String>observableArrayList(libelle)); 
        NumberAxis yAxis = new NumberAxis("Nombre medicament", 0.0d, count2(), 1.0d);
        barChartData = FXCollections.observableArrayList();
        for (int i = 0; i < libelle.size(); i++) {
            barChartData.add(new BarChart.Series(libelle.get(i), FXCollections.observableArrayList(new BarChart.Data(lsitmed.get(i).getLibelle(),
                    count(lsitmed.get(i).getCode())))));
            chart = new BarChart(xAxis, yAxis, barChartData, 100.0d);
        }
        chart.setAnimated(true);
        chart.setMinHeight(500);
        chart.setMinWidth(1000);
        chart.setTitle("Medicament le plus vendus");
        root.getChildren().add(chart);
        VBox v = new VBox();
        v.getChildren().add(chart);

        BorderPane bord_med = new BorderPane();

        //bouton ajout  
        Button chart_medicament = new Button();
        chart_medicament.setCursor(Cursor.HAND);
        Image im_medic = new Image(getClass().getResourceAsStream("/cssfiles/stat2.png"));
        chart_medicament.setGraphic(new ImageView(im_medic));
        chart_medicament.setStyle("-fx-background-color: transparent;");
        chart_medicament.setTooltip(new Tooltip("Statistiques Médicament"));

        chart_medicament.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bord_med.setCenter(v);
            }
        });

        //bouton recherche/modif
        Button chart_2 = new Button();
        chart_2.setCursor(Cursor.HAND);
        Image im_modif = new Image(getClass().getResourceAsStream("/cssfiles/stat1.png"));
        chart_2.setGraphic(new ImageView(im_modif));
        chart_2.setStyle("-fx-background-color: transparent;");

        chart_2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                InterfaceServiceChartFinal interface_chart = new InterfaceServiceChartFinal();
                VBox vv = new VBox(interface_chart.RecupererInterface());
                HBox v_chart = new HBox();
                v_chart.setSpacing(90);
                v_chart.getChildren().addAll(new Label(""), new Label(""), new Label(""), vv);

                bord_med.setCenter(v_chart);
            }
        });

        //bouton dropbox
        Button chart_3 = new Button();
        chart_3.setCursor(Cursor.HAND);
        Image im_3 = new Image(getClass().getResourceAsStream("/cssfiles/dp.png"));
        chart_3.setGraphic(new ImageView(im_3));
        chart_3.setStyle("-fx-background-color: transparent;");

        chart_3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                InterfaceChartPatient interface_chart = new InterfaceChartPatient();
                VBox vv = new VBox(interface_chart.RecupererInterface());
                HBox v_chart = new HBox();
                v_chart.setSpacing(30);
                v_chart.getChildren().addAll(vv);
                bord_med.setCenter(v_chart);

            }
        });

        //regroupement des boutons
        VBox v_bouton = new VBox();
        v_bouton.setSpacing(20);
        v_bouton.getChildren().addAll(chart_medicament, chart_2, chart_3);

        bord_med.setLeft(v_bouton);
        bord_med.setCenter(v);

        return bord_med;

    }
    int aa = 0;

    public int count(int z) {
        //  LigneFactureDAO lignedao1 = new LigneFactureDAO();
        int nbr = 0;
        for (int i = 0; i < lflist.size(); i++) {
            if (lflist.get(i).getId_medicament() == z) {
                nbr = nbr + lflist.get(i).getQuantite();
            }
        }
        return nbr;
    }

    int bb = 0;

    public int count2() {
        //  LigneFactureDAO lignedao1 = new LigneFactureDAO();
        int nbr = 0;
        for (int i = 0; i < lflist.size(); i++) {
            nbr = nbr + lflist.get(i).getQuantite();
        }
        return nbr;
    }

}
