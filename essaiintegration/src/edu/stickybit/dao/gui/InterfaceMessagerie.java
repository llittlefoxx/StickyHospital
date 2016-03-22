/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.PatientDAO;
import edu.stickybit.dao.classes.SendEmail;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

/**
 *
 * @author Asma Boussabat
 */
public class InterfaceMessagerie {
 
    public InterfaceMessagerie(){}
    
    ObservableList<edu.stickybit.entity.Patient>  data =null;
PatientDAO pdao = new PatientDAO();
List<edu.stickybit.entity.Patient> array = new ArrayList<edu.stickybit.entity.Patient>(); 
List<edu.stickybit.entity.Patient>  ppp=new ArrayList<>();
      


public StringBuffer stripHTMLTags(String htmlText) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer sb = new StringBuffer(htmlText.length());
        while(matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
      return  matcher.appendTail(sb);
    }
       
  
    public HBox RecupererInterface(){
  
        final HTMLEditor htmlEditor = new HTMLEditor();
      //  htmlEditor.setPrefHeight(125);
        htmlEditor.setMaxWidth(500);
        htmlEditor.setMinHeight(100);
        htmlEditor.setStyle(
    "-fx-font: 12 cambria;"
    + "-fx-border-color: brown; "
    + "-fx-border-style: dotted;"
    + "-fx-border-width: 2;"
);
        
        //tableau
        
         TableView tableView = new TableView();
         
    
        tableView.setFixedCellSize(20);
        tableView.setMaxWidth(400);
        tableView.setMaxHeight(400);
        array=pdao.DisplayAllPatient();
        
        data= FXCollections.observableArrayList();
        data.addAll(array);
        tableView.setItems(data);
        
        TableColumn id = new TableColumn();
        id.setText("ID ");
        id.setMinWidth(80);
        id.setCellValueFactory(new PropertyValueFactory("id_patient"));
 
        TableColumn n = new TableColumn();
        n.setText("Nom");
        n.setMinWidth(80);
        n.setCellValueFactory(new PropertyValueFactory("nom_patient"));
        
        TableColumn p = new TableColumn();
        p.setText("Prenom");
        p.setMinWidth(80);
        p.setCellValueFactory(new PropertyValueFactory("prenom_patient"));
        
        TableColumn c = new TableColumn();
        c.setText("CIN");
        c.setMinWidth(80);
        c.setCellValueFactory(new PropertyValueFactory("cin_patient"));
        
        TableColumn a = new TableColumn();
        a.setText("Adresse");
        a.setMinWidth(80);
        a.setCellValueFactory(new PropertyValueFactory("adresse"));
         
        TableColumn t = new TableColumn();
        t.setText("telephone");
        t.setMinWidth(80);
        t.setCellValueFactory(new PropertyValueFactory("tel_patient"));
  
        
        TableColumn ma = new TableColumn();
        ma.setText("Mail");
        ma.setMinWidth(80);
        ma.setCellValueFactory(new PropertyValueFactory("mail_patient"));
        
        
        TableColumn u = new TableColumn();
        u.setText("URL");
        u.setMinWidth(80);
        u.setCellValueFactory(new PropertyValueFactory("url_photo_patient"));
        
        
        TableColumn se = new TableColumn();
        se.setText("Sexe");
        se.setMinWidth(80);
        se.setCellValueFactory(new PropertyValueFactory("sex_patient"));
        
        TableColumn dat = new TableColumn();
        dat.setText("date n");
        dat.setMinWidth(80);
        dat.setCellValueFactory(new PropertyValueFactory("date_n_patient"));
        
        TableColumn dt = new TableColumn();
        dt.setText("date d");
        dt.setMinWidth(80);
        dt.setCellValueFactory(new PropertyValueFactory("date_d_patient"));
        
       
        tableView.getColumns().addAll(id,n,p,c,a,t,ma,u,se,dat,dt);
        
        TextField Boxs = new TextField();
       Button bserach = new Button("Recherche");
       Button clearB = new Button("clear");
        
       clearB.setVisible(false);
         
       bserach.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                array.removeAll(data);
                data.clear();
                
                PatientDAO pd=null;
                pd=new PatientDAO();
                try {
                     ppp = pd.findPatientByName(Boxs.getText());
                    data = FXCollections.observableArrayList();
                    data.addAll(ppp);
                    tableView.setItems(data);
                                      
                } catch (Exception e) {
                }
               
            }
        }));

       Boxs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clearB.setVisible(Boxs.getText().length() != 0);
            }
        });
       
       clearB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
           
            public void handle(ActionEvent actionEvent) {
                
                Boxs.setText("");
              //  Boxs.requestFocus();
        array=pdao.DisplayAllPatient();
        data= FXCollections.observableArrayList();
        data.addAll(array);
        tableView.setItems(data);
               
            }
        });

     Label texte=new Label("Rechercher patient : "); 
      
    
    
    HBox h=new HBox();
    h.setSpacing(20);
    h.getChildren().addAll(texte,Boxs,clearB,bserach);
    
    VBox v=new VBox();
    v.setSpacing(30);
    v.getChildren().addAll(h,tableView);
    
    // formulaire de modification du patient
  VBox vertical=new VBox();
  vertical.setSpacing(20);
  
  Button b_envoyer=new Button("ENVOYER");
  Button b_reset=new Button("RESET");
  
 
   
   //
  
  
  
  TextField mail_dest=new TextField();
  mail_dest.setMaxHeight(TextField.USE_PREF_SIZE);
  Label mail_destinataire=new Label("Mail du Patient");
  
  
  
       tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
              edu.stickybit.entity.Patient np = (edu.stickybit.entity.Patient)tableView.getSelectionModel().getSelectedItem();
              mail_dest.setText(String.valueOf(np.getMail_patient()));
                       
            }
            
        }); 
       
        
  //bouton envoyer mail
  
   b_envoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
           
            public void handle(ActionEvent actionEvent) {
                
              SendEmail s=new SendEmail();
              String bb = stripHTMLTags(htmlEditor.getHtmlText()).toString();
              s.send(mail_dest.getText(), bb);
               
            }
        });
 
  
  vertical.getChildren().addAll(mail_destinataire,mail_dest,htmlEditor,b_envoyer,b_reset);

        
        HBox hbox_mess=new HBox();
        hbox_mess.setSpacing(30);
        
        hbox_mess.getChildren().addAll(v,vertical);
       
    return hbox_mess;
    }
}