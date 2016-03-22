/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.ChambreDao;
import edu.stickybit.dao.classes.FactureDAO;
import edu.stickybit.dao.classes.HospitalisationDao;
import edu.stickybit.dao.classes.LigneFactureDAO;
import edu.stickybit.dao.classes.LitDao;
import edu.stickybit.dao.classes.MedecinDAO;
import edu.stickybit.dao.classes.PatientDAO;
import edu.stickybit.dao.classes.ServicesDao;
import edu.stickybit.entity.Chambre;
import edu.stickybit.entity.Hospitalisation;
import edu.stickybit.entity.LigneFacture;
import edu.stickybit.entity.Lit;
import edu.stickybit.entity.Medecin;
import edu.stickybit.general.Prix;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Tlili Mohamed Ali 
 */
public class InterfaceHospitalisation {

    
    public InterfaceHospitalisation(){}
        
ObservableList<edu.stickybit.entity.Patient>  data =null;
ObservableList<edu.stickybit.entity.Medecin>  data_med =null;
List<Lit>  data_lit =null;
List<Chambre>  data_ch=null;
PatientDAO pdao = new PatientDAO();
List<edu.stickybit.entity.Patient> array = new ArrayList<edu.stickybit.entity.Patient>(); 
List<edu.stickybit.entity.Medecin> array_med = new ArrayList<edu.stickybit.entity.Medecin>();
List<edu.stickybit.entity.Chambre> array_ch = new ArrayList<edu.stickybit.entity.Chambre>();
List<edu.stickybit.entity.Lit> array_lit = new ArrayList<edu.stickybit.entity.Lit>();
List<edu.stickybit.entity.Patient>  ppp=new ArrayList<>();
List<edu.stickybit.entity.Medecin>  ppp_med=new ArrayList<>();


       TableView tableView2 = new TableView();        
    
List<Hospitalisation> array2 = new ArrayList<Hospitalisation>(); 
ObservableList<Hospitalisation>  data2 =null;   


    private String NomService;
    private int IdService;
    
    private String NomMedecin;
    private int IdMedecin;
      

    public BorderPane RecupererInterface() throws ParseException{
     java.util.Date dd=new java.util.Date();
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy,MM,dd");
String formattedDate = formatter.format(dd);
              String kk = String.valueOf(formattedDate);
              
                java.util.Date d_hos=new java.util.Date();
                String formattedDateSortie = formatter.format(d_hos);
                String d_hos_s = String.valueOf(formattedDateSortie);
        
      String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
       
              
        
         TableView tableView = new TableView();
         
    
        tableView.setFixedCellSize(30);
        tableView.setMaxWidth(600);
       
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
         
    
       
        tableView.getColumns().addAll(id,n,p,c,a);
        
        //bouton recherche et bouton clear de la zone de recherche
     
       TextField Boxs = new TextField();
       Button bserach = new Button("Recherche");
       Image im_search = new Image(getClass().getResourceAsStream("/cssfiles/search.png"));     
      bserach.setGraphic(new ImageView(im_search));      
      bserach.setStyle("-fx-background-color: transparent;");
       
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
   
    HBox h=new HBox();
    h.setSpacing(10);
    
    // formulaire de modification du patient
    
    Label texte=new Label("Rechercher patient : "); 
  
   Label idp=new Label( "ID PATIENT :");
   TextField idpp = new TextField();
      
   Label service=new Label( "SERVICES : ");
   
   Label id_ser=new Label( "ID SERVICE :");
   TextField id_ser_f = new TextField();
   
   
       
  Label med=new Label("MEDECINS: ");
      
  Label id_med=new Label( "ID MEDECIN :");
  TextField id_med_f = new TextField();
    
   Label chambre=new Label("CHAMBRES: ");

   Label lit_disp=new Label(" LITS DISPONIBLES : ");         
   Label id_lit=new Label( "ID LIT :");
   TextField id_lit_f = new TextField();
   
   //
   
         
  
   
  // combobox pour les services
   
   final ComboBox<String> ch = new ComboBox<>();
   final ComboBox<String> ch2 = new ComboBox<>();
   final ComboBox<String> chl = new ComboBox<>();
   final ComboBox<String> ch3 = new ComboBox<>();
   final ComboBox<String> chc = new ComboBox<>();
   
   ServicesDao serv=new ServicesDao();
   List<String> l = new ArrayList<>();
   l=serv.DisplayAllServicesName();
   ObservableList<String> data=FXCollections.observableArrayList();
   data.addAll(l);
   ch.setItems(data);
   ch.setEditable(false);
    
 
         
    

        ch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ServicesDao s=new ServicesDao();
                NomService = (String) ch.getValue();
                System.err.println(NomService);
                IdService = s.findServiceByName(NomService);
               
                id_ser_f.setText(String.valueOf(IdService));
                 MedecinDAO med2;
                try {
                    med2 = new MedecinDAO();             
                     array_med=  med2.rechercherToutCompteMedecinParService(IdService);            
                   
                   ObservableList<String>  data_med =FXCollections.observableArrayList();
                   
                     for (Medecin m : array_med) {   
                        data_med.add(m.getNom_medecin());
                        ch2.setItems(data_med);
                      
                     }  
                    
                      //affichage des chambres dispo de chaque service
                 
                 
                  ChambreDao ch_dao=new ChambreDao();
                  
                 array_ch= ch_dao.findAvailableChambreByService(IdService);
                 ObservableList<String>  data_ch =FXCollections.observableArrayList();
                 
                 for(Chambre c : array_ch){
                     data_ch.add(String.valueOf(c.getIdchambre()));
                     chc.setItems(data_ch);
                 }
                       
                      } catch (SQLException ex) {
                    Logger.getLogger(InterfaceHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
        
        });
                   
             chc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
               
                String ss=String.valueOf(chc.getValue());
                System.out.println(ss);
                
                 LitDao lit_dao=new LitDao();
                  
               array_lit= lit_dao.DisplayAllLitDisponible(Integer.parseInt(ss));
                 ObservableList<String>  data_lit =FXCollections.observableArrayList();
                 
                 for(Lit l : array_lit){
                     data_lit.add(String.valueOf(l.getIdlit()));
                     chl.setItems(data_lit);
                 }
                
                 chl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                
                
                String s=String.valueOf(chl.getValue());
                 id_lit_f.setText(s);
            }
        });
     
                
            }
        });
             
  ch2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            
                ServicesDao s=new ServicesDao();
                NomService = (String) ch.getValue();
                System.err.println(NomService);
                IdService = s.findServiceByName(NomService);
               
                id_ser_f.setText(String.valueOf(IdService));
                
                NomMedecin =(String) ch2.getValue();
                MedecinDAO ms;
                try {
                    ms = new MedecinDAO();
                    IdMedecin = ms.rechercherCompteMedecinParNom2(NomMedecin);
                    System.out.println(IdMedecin);
                    id_med_f.setText(String.valueOf(IdMedecin));
                 
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
                }
                NomMedecin = (String) ch.getValue();
                System.err.println(NomMedecin);
           
            }
        });
        
          //combo pour letat du patient
  
        ch3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            
            }
        });
       
        ch3.getItems().setAll("Critique","Urgence");
        
         ch.setPromptText("Les services");   
         ch2.setPromptText("Les medecins");
         chc.setPromptText("Les chambres");
         chl.setPromptText("Lit dispo");
   Label etat_p=new Label(" ETAT DU PATIENT : ");
  
        ch3.setPromptText("Etat patient");
    
   Label type_ad=new Label(" TYPE D'ADMISSION : ");
   TextField type_ad_f = new TextField();
   
   

   //bouton mise à jour pour la modification du patient
   
    Button bb=new Button("Sauvegarder hospitalisation");
    
    bb.getStylesheets().add(styleback);
    bb.setId("rich-blue");
    bb.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent actionEvent) {
         
         int     id_patient=Integer.parseInt(idpp.getText());
         int     id_lit_hos=Integer.parseInt(chl.getValue());
         int     id_medec=Integer.parseInt(id_med_f.getText());      
         String  etat_patient=String.valueOf(ch3.getValue());
             
         Hospitalisation hosp=new Hospitalisation(id_patient,"test", id_lit_hos,kk, id_medec,type_ad_f.getText(), etat_patient);
         HospitalisationDao hospital;
           
         try {
                    hospital = new HospitalisationDao();                    
                int xx= hospital.creerHospitalisation(hosp);
                    LitDao litdao=new LitDao();
                    Lit lili=new Lit(Integer.parseInt(id_ser_f.getText()),Integer.parseInt(id_med_f.getText()), id_lit_hos,etat_patient);
                    litdao.updateLit(id_lit_hos,"Occupé");
                   
                   if(xx==1){
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    Button sor = new Button("OK");
                    sor.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            dialog.close();                         
                       }
                    });
                    
                  // dialog.initOwner(primaryStage);
                    dialog.setTitle("Ajout de l'hospitalisation");
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().addAll(new Text("Hospitalisation ajoutée"), sor);
                    Scene dialogScene = new Scene(dialogVbox, 400, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                   }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
                }
         
            }
        });
   
  
     
   //bouton reset pour vider les champs
   Button breset=new Button("Reset"); 
   breset.getStylesheets().add(styleback);
   breset.setId("rich-blue");    
  breset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               idpp.setText("");
               id_med_f.setText("");              
               id_lit_f.setText("");
               id_ser_f.setText("");
               type_ad_f.setText("");
         
            }
        });
   
 
   //positionnement des zones du formulaire de modification
       
   VBox ver=new VBox();
   ver.setSpacing(10);
   VBox hbf=new VBox();
   hbf.setSpacing(10);
   HBox hbg=new HBox();
   hbg.setSpacing(10);
   
   VBox hbg2=new VBox();
   hbg2.setSpacing(10);
   
   HBox h1=new HBox();
   h1.setSpacing(10);
   HBox h2=new HBox();
   h2.setSpacing(10);
   HBox h3=new HBox();
   h3.setSpacing(10);
   
   VBox v1=new VBox();
   v1.setSpacing(10);
   VBox v2=new VBox();
   v2.setSpacing(10);
   VBox v3=new VBox();
   v3.setSpacing(10);
   VBox v4=new VBox();
   v4.setSpacing(100);
   
   VBox vv=new VBox();
   vv.setSpacing(10);
   
   
   v1.getChildren().addAll(service,ch,med,ch2);
   v2.getChildren().addAll(chambre,chc,lit_disp,chl);
   v3.getChildren().addAll(type_ad,type_ad_f,etat_p,ch3);
   vv.getChildren().addAll(bb,breset);
        
   h1.getChildren().addAll(v1,v2,v3,vv);
   v4.getChildren().addAll(h1,tableView2);
   
   
  // ver.getChildren().addAll(tableView,hbg,tableView2);
   
   
        tableView2.setFixedCellSize(20);
        tableView2.setMaxWidth(600);
        tableView2.setMaxHeight(70);
   
   
        TableColumn id_ho = new TableColumn();
        id_ho.setText("ID_hos ");
        id_ho.setMinWidth(80);
        id_ho.setCellValueFactory(new PropertyValueFactory("id_hos"));
        
        
        TableColumn id_patient2 = new TableColumn();
        id_patient2.setText("ID_P ");
        id_patient2.setMinWidth(80);
        id_patient2.setCellValueFactory(new PropertyValueFactory("id_patient"));
 
        TableColumn adm_login = new TableColumn();
        adm_login.setText("ADMIN");
        adm_login.setMinWidth(80);
        adm_login.setCellValueFactory(new PropertyValueFactory("adm_login"));
        
        TableColumn id_lit2 = new TableColumn();
        id_lit2.setText("LIT");
        id_lit2.setMinWidth(80);
        id_lit2.setCellValueFactory(new PropertyValueFactory("id_lit"));
        
        TableColumn date_h = new TableColumn();
        date_h.setText("DATE_E");
        date_h.setMinWidth(80);
        date_h.setCellValueFactory(new PropertyValueFactory("date_h"));
   
   
        TableColumn date_s = new TableColumn();
        date_s.setText("DATE_S");
        date_s.setMinWidth(80);
        date_s.setCellValueFactory(new PropertyValueFactory("date_s"));
   
        TableColumn id_med2 = new TableColumn();
        id_med2.setText("MEDECIN");
        id_med2.setMinWidth(80);
        id_med2.setCellValueFactory(new PropertyValueFactory("id_med"));
   
   
        TableColumn type_admission = new TableColumn();
        type_admission.setText("ADMISSION");
        type_admission.setMinWidth(80);
        type_admission.setCellValueFactory(new PropertyValueFactory("type_admission"));
   
        
        TableColumn etat_patient2 = new TableColumn();
        etat_patient2.setText("ETAT");
        etat_patient2.setMinWidth(80);
        etat_patient2.setCellValueFactory(new PropertyValueFactory("etat_patient"));
     
   
        tableView2.getColumns().addAll(id_patient2,adm_login,id_lit2,date_h,date_s,id_med2,type_admission,etat_patient2);
       
        tableView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                   
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                             
              final Stage dialog = new Stage();
              dialog.initModality(Modality.APPLICATION_MODAL);
              Button sor = new Button("Cloturer hospitalisation");              
              sor.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {                  
                  Hospitalisation ho = (Hospitalisation)tableView2.getSelectionModel().getSelectedItem();
               
              int id_lit_n=ho.getId_lit();
              int id_hospitalisation=ho.getId_hos();
              System.out.println(id_lit_n);               
              LitDao ll=new LitDao();              
              ll.updateLit(id_lit_n,"Libre");
                try {
                    HospitalisationDao h=new HospitalisationDao();
                    h.modifierHospitalisatio(id_hospitalisation,d_hos_s);
                    
                    
                int id_patient= ho.getId_patient();
                FactureDAO fact=new FactureDAO();
                int id_facture= fact.retourIdFact(id_patient);
                
                  int c=(int) h.calculeDeJours(id_hospitalisation);
                   Prix p=new Prix();
                   float x= Float.valueOf(p.prix_nuit);            
                   LigneFacture l=new LigneFacture(id_facture,c,x,2,"Hospitalisation");
                  
                   LigneFactureDAO ligne_fact=new LigneFactureDAO();
                   ligne_fact.createLigneFacture2(l);
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
                       }
                    });
              
              //
              Button annuler = new Button("Annuler");  
              
              annuler.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                   dialog.close();
                  
                       }
                    });
              
                  //  dialog.initOwner(primaryStage);
                    dialog.setTitle("Cloturer hospitalisation");
                    VBox dialogVbox = new VBox(20);
                    HBox bouton_hos=new HBox();                    
                    bouton_hos.setSpacing(20);
                    dialogVbox.setSpacing(20);
                    bouton_hos.getChildren().addAll( sor ,annuler);
                    dialogVbox.getChildren().addAll(new Text("voulez vous cloturer lhospitalisation"),bouton_hos);
                    Scene dialogScene = new Scene(dialogVbox, 400, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                   }
           
            
        }); 
     
   //remplir le formulaire de modification apres la recherche et la selection du patient à modifier)
      
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
              edu.stickybit.entity.Patient np = (edu.stickybit.entity.Patient)tableView.getSelectionModel().getSelectedItem();
             if ( np!=null)
             {
              idpp.setText(String.valueOf(np.getId_patient()));
                HospitalisationDao hos;        
                  data2= FXCollections.observableArrayList();  
                  try {
                    hos = new HospitalisationDao();
                    array2=hos.rechercherHospitalisatioParID(Integer.parseInt(idpp.getText()));
                    data2.addAll(array2);     
                    tableView2.setItems(data2);    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
                }}}}); 
     
   h.getChildren().addAll(texte,Boxs,clearB,bserach);
   

   
    //positionnement tableau+les boutons+formulaire de modification hospitalisation
   
   HBox main=new HBox();   
   main.setSpacing(10);
   main.getChildren().addAll(tableView,v4);
    BorderPane bord=new BorderPane();
    bord.setTop(h);
    bord.setCenter(main);
   return bord; 
    }
    
   
}
