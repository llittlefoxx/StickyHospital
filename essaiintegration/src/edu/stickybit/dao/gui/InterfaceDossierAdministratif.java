/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;
import edu.stickybit.dao.classes.FactureDAO;
import edu.stickybit.dao.classes.PatientDAO;
import edu.stickybit.entity.Facture;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 *
 * @author Asma Boussabat
 */
public class InterfaceDossierAdministratif {
    
    public InterfaceDossierAdministratif(){}
    
    
    ObservableList<edu.stickybit.entity.Patient> data = null;
    ObservableList<Facture> data_fact = null;
    
    PatientDAO pdao = new PatientDAO();
    List<edu.stickybit.entity.Patient> array = new ArrayList<edu.stickybit.entity.Patient>();
    List<Facture> array_fact = new ArrayList<Facture>();
    List<edu.stickybit.entity.Patient> ppp = new ArrayList<>();
     BorderPane border=new BorderPane();
    public BorderPane RecupererInterfcae(){
    
          String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
     
         TableView tableView = new TableView();
        //   tableView.setPrefSize(600,600);
        tableView.setFixedCellSize(30);
        tableView.setMaxWidth(400);
        array = pdao.DisplayAllPatient();
        data = FXCollections.observableArrayList();
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
        tableView.getColumns().addAll(n,p,c,a,t);
        //bouton recherche et bouton clear de la zone de recherche
        TextField Boxs = new TextField();
        Button bserach = new Button("Recherche");
      
        Button clearB = new Button("clear");
        clearB.setVisible(false);
        bserach.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                array.removeAll(data);
                data.clear();
                PatientDAO pd = null;
                pd = new PatientDAO();
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
                array = pdao.DisplayAllPatient();
                data = FXCollections.observableArrayList();
                data.addAll(array);
                tableView.setItems(data);
            }
        });
     
        Label l=new Label("");
        Label l2=new Label("");
        HBox h = new HBox();
        h.setSpacing(15);
       
    
        VBox v=new VBox();
        v.setSpacing(30);
        v.getChildren().addAll(l,h,tableView);
        
        
        HBox h2 = new HBox();
        h2.setSpacing(15);
        h2.getChildren().addAll(l2,v);
                
        //affichage du dossier du patient
        BorderPane bord2=new BorderPane();
          
         TableView tableView_fact = new TableView();
        //   tableView.setPrefSize(600,600);
        tableView_fact.setFixedCellSize(40);
        tableView_fact.setMaxWidth(400);
        tableView_fact.setMaxHeight(500);
        
          
        TableColumn id_fact = new TableColumn();
        id_fact.setText("ID_facture ");
        id_fact.setMinWidth(80);
  
        TableColumn date_fact = new TableColumn();
        date_fact.setText("Date_facture");
        date_fact.setMinWidth(80);
    
        TableColumn total_fact = new TableColumn();
        total_fact.setText("Total_facture");
        total_fact.setMinWidth(80);
        
        
        id_fact.setCellValueFactory(new PropertyValueFactory("id_facture"));
        date_fact.setCellValueFactory(new PropertyValueFactory("date_facturation"));
        total_fact.setCellValueFactory(new PropertyValueFactory("total_facture"));    
         
     
          tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
               
                String style_label = "-fx-font-family: \"Arial\";"
              + "-fx-text-fill: #654b00;";
                
                    Label nom=new Label();
                    nom.setStyle(style_label);
                    
                    Label pren=new Label();
                    Label cin=new Label();
                    Label tel=new Label();
                    Label mail=new Label();
                    Label v=new Label("");
                    Label v2=new Label("");
                    
                    Button b_imprim=new Button("imprimer");
                    
                    edu.stickybit.entity.Patient np = (edu.stickybit.entity.Patient) tableView.getSelectionModel().getSelectedItem();
                    
                 
                  /* 
                   final Image i = new Image(ApplicationComplete.class.getResourceAsStream("user.png"));
                   im.setImage(i);
                 */
                    final File file = new File(np.getUrl_photo_patient());  
                    final Image image = new Image(file.toURI().toString());              
                    ImageView im_v=new ImageView();    
                    im_v.setEffect(new DropShadow(20, Color.ORANGE));
                    
                    String cssBordering = "-fx-border-color:#FF8000 ; \n" //#090a0c
            + "-fx-border-insets:3;\n"
            + "-fx-border-radius:7;\n"
            + "-fx-border-width:3.0";
              im_v.setImage(image);
              HBox top = new HBox();
              top.getChildren().add(im_v);
              top.setStyle(cssBordering);
                
                    
                    nom.setText("Nom      : "+np.getNom_patient());
                    pren.setText("Prenom     : "+np.getPrenom_patient());
                    cin.setText("Carte d'identité  :"+String.valueOf(np.getCin_patient()));
                    tel.setText("Telephone    : "+String.valueOf(np.getTel_patient()));
                    mail.setText("Adresse Mail   :"+String.valueOf(np.getMail_patient()));
                    
                    tableView_fact.getColumns().addAll(id_fact,date_fact,total_fact);
                    
                    FactureDAO fact=new FactureDAO();
                    array_fact= fact.FindFactureParCin(np.getCin_patient());
                    data_fact = FXCollections.observableArrayList();
                    data_fact.addAll(array_fact);
                    tableView_fact.setItems(data_fact);
                    
                    VBox table_bouton=new VBox();
                    table_bouton.setSpacing(20);
                    table_bouton.getChildren().addAll(tableView_fact,b_imprim);
                    
                   
                    HBox h_patient=new HBox();
                    VBox v_patient=new VBox();
                    VBox vert=new VBox();
                    vert.setSpacing(30);
                    v_patient.setSpacing(15);
                    v_patient.getChildren().addAll(v,v2,nom,pren,cin,tel,mail);
                    h_patient.setSpacing(50);
                    h_patient.getChildren().addAll(top,v_patient);
                    
                    vert.getChildren().addAll(new Label(""),h_patient,table_bouton);
                    
                    bord2.setTop(new Label(""));
                    bord2.setCenter(vert);
                  
            }  });
                  
           Label texte = new Label("rechercher patient : ");
           Button b_imprim=new Button("Imprimer");
           b_imprim.setCursor(Cursor.HAND);
           Image im_imp = new Image(getClass().getResourceAsStream("/cssfiles/Printer.png"));     
           b_imprim.setGraphic(new ImageView(im_imp));      
           b_imprim.setStyle("-fx-background-color: transparent;");
           
           
           Button brefresh = new Button("Refresh");
            Image im_refresh = new Image(getClass().getResourceAsStream("/cssfiles/refresh.png"));     
           brefresh.setGraphic(new ImageView(im_refresh));      
           brefresh.setStyle("-fx-background-color: transparent;");
      
      
           brefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                
                 bord2.setTop(new Label(""));
                 bord2.setCenter(new Label(""));
               
            }
            
        });
        
       
      h.getChildren().addAll(texte, Boxs, clearB, bserach,brefresh);
      
      String style_outter = "-fx-border-color:#FF8000;"
              + "-fx-border-width:1.0;";
      border.setStyle(style_outter);
                    
        border.setLeft(h2);
        border.setCenter(bord2);
     
   
 return border;
    }
}