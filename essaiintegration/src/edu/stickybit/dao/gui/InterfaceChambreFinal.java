/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.ChambreDao;
import edu.stickybit.dao.classes.LitDao;
import edu.stickybit.dao.classes.ServicesDao;
import edu.stickybit.entity.Chambre;
import edu.stickybit.entity.Lit;
import edu.stickybit.entity.Medicament;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class InterfaceChambreFinal {

    public InterfaceChambreFinal() {
    }

    List<Lit> listeLits;
    List<String> listService;
    List<Chambre> ListChambreSupp;
    ObservableList<String> dataServiceAjout;
    final ChambreDao daoChambre2 = new ChambreDao();
    int idCh;
    final LitDao daoLit = new LitDao();

    public BorderPane RecupererInterface() {

        
        
         ServicesDao daoService = new ServicesDao();
         listService = new ArrayList<>();
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
        //formulaire Ajouter Chambre et Lits
        //Accordion 1
        HBox grid1 = new HBox();
        grid1.setSpacing(10);
        grid1.setPadding(new Insets(5, 10, 20, 20));
        //Les Labels 
        Label Service = new Label("Services");
        Label capacite = new Label("Capacité");
        Label type = new Label("Type");

        listService = daoService.DisplayAllServicesName();
        //textfields
         dataServiceAjout = FXCollections.observableArrayList();
        final ComboBox<String> combxServiceAjout = new ComboBox();
        dataServiceAjout.addAll(listService);
        combxServiceAjout.setId("second-editable");
        combxServiceAjout.setPromptText("Edit or Choose...");
        combxServiceAjout.setItems(dataServiceAjout);
        combxServiceAjout.setEditable(true);
        final TextField CapaciteText = new TextField();
        final TextField TypeText = new TextField();

        
        String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
        
        //Button pout ajouter les chambres et les lits 
        final Button BtnACham = new Button("ajouter");
        BtnACham.getStylesheets().add(styleback);
        BtnACham.setId("rich-blue");
        BtnACham.setCursor(Cursor.HAND);
        /*
         tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
         @Override
         public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
         RadioButton chk = new RadioButton();
                         
         //String  chk=tg.getSelectedToggle().getUserData().toString();
         chk = (RadioButton)t1.getToggleGroup().getSelectedToggle();
         }
         });        
         */
        BtnACham.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eventAdd) {

                
                ServicesDao daoservice = new ServicesDao();
                final ChambreDao daochambre = new ChambreDao();
                final int idService = daoservice.findServiceByName(combxServiceAjout.getValue());
               String chk = "Libre";
                Chambre newch = new Chambre(idService, Integer.parseInt(CapaciteText.getText()), TypeText.getText(), chk);
                daochambre.insertChambre(newch);

            }

        });

        VBox hb1 = new VBox();
        hb1.getChildren().addAll(Service, combxServiceAjout, capacite, CapaciteText,type,TypeText);
        hb1.setSpacing(15);
        VBox hb2 = new VBox();
        hb2.getChildren().addAll(BtnACham);
        hb2.setSpacing(15);
        grid1.getChildren().addAll(hb1, hb2);
        //Formulaire Modifier Chambre et Lit
        //Accordion 2
        HBox grid2 = new HBox();
        grid2.setSpacing(15);
        grid2.setPadding(new Insets(5, 20, 10, 20));
        //Declaration
        Label ServiceMod = new Label("Services:");
        //Liste des Services 
        //textfields
        ObservableList<String> dataServiceMod = FXCollections.observableArrayList();
        final ComboBox<String> comboServiceMod = new ComboBox();
        dataServiceMod.addAll(listService);
        comboServiceMod.setId("second-editable");
        comboServiceMod.setPromptText("Edit or Choose...");
        comboServiceMod.setItems(dataServiceAjout);
        comboServiceMod.setEditable(true);

        TableColumn id_chambre = new TableColumn();
        id_chambre.setMinWidth(80);
        id_chambre.setText("ID Chambre");
        id_chambre.setCellValueFactory(new PropertyValueFactory("idchambre"));
        TableColumn id_service = new TableColumn();
        id_service.setMinWidth(80);
        id_service.setText("ID Service");
        id_service.setCellValueFactory(new PropertyValueFactory("idservice"));
        TableColumn getcapacite = new TableColumn();
        getcapacite.setMinWidth(80);
        getcapacite.setText("Capacité");
        getcapacite.setCellValueFactory(new PropertyValueFactory("capacite"));
        TableColumn gettype = new TableColumn();
        gettype.setMinWidth(80);
        gettype.setText("Type");
        gettype.setCellValueFactory(new PropertyValueFactory("type"));
        TableColumn etat = new TableColumn("Disponibilité");
        etat.setMinWidth(80);
        etat.setText("Disponibilité");
        etat.setCellValueFactory(new PropertyValueFactory("etat"));
        //Importation des Services de la Base 
        final TableView tableChambre = new TableView();
        tableChambre.setMaxWidth(400);
        comboServiceMod.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                String choixRecherche = (String) comboServiceMod.getValue();
                System.err.println(choixRecherche);
                ServicesDao daoS = new ServicesDao();
                int idServ = daoS.findServiceByName(choixRecherche);
                List<Chambre> ListChambre = new ArrayList<>();
                ChambreDao daoChambre = new ChambreDao();
                ListChambre = daoChambre.DisplayAllChambreByService(idServ);
                ObservableList<Chambre> dataChambre = null;
                dataChambre = FXCollections.observableArrayList();

                dataChambre.addAll(ListChambre);

                tableChambre.setItems(dataChambre);
            }
        });
        tableChambre.getColumns().addAll(id_chambre, id_service, getcapacite, gettype, etat);

        //Table View des Lits de la chambre sélectionné 
        ///List Dao 
        final TableView tableLits = new TableView();

        //Table View Column
        TableColumn idlit = new TableColumn();
        idlit.setMinWidth(80);
        idlit.setText("ID Lit");
        idlit.setCellValueFactory(new PropertyValueFactory("idlit"));
        final TableColumn idservice = new TableColumn();
        idservice.setMinWidth(80);
        idservice.setText("ID Service");
        idservice.setCellValueFactory(new PropertyValueFactory("idservice"));
        TableColumn idchambre = new TableColumn();
        idchambre.setMinWidth(80);
        idchambre.setText("ID Chambre");
        idchambre.setCellValueFactory(new PropertyValueFactory("idchambre"));
        TableColumn dispo = new TableColumn("Disponibiité");
        dispo.setMinWidth(80);
        dispo.setText("Disponibiité");
        dispo.setCellValueFactory(new PropertyValueFactory("etat"));

        tableLits.getColumns().addAll(idlit,idservice,idchambre,dispo);
         
         final ObservableList<Lit> dataLits = FXCollections.observableArrayList();
        tableChambre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Chambre cham = (Chambre) tableChambre.getSelectionModel().getSelectedItem();
               
                int idCh = cham.getIdchambre();
                listeLits = new ArrayList<Lit>();
                listeLits = daoLit.FindLitByIdChambre(idCh);

                dataLits.addAll(listeLits);
                tableLits.setItems(dataLits);
                tableLits.setMaxWidth(400);

            }

        });
        ToggleGroup tg2 = new ToggleGroup();
        final RadioButton rbModif1 = new RadioButton("Libre");
        rbModif1.setToggleGroup(tg2);
        final RadioButton rbModif2 = new RadioButton("Occupé");
        rbModif2.setToggleGroup(tg2);
        final Button modif_etat = new Button("Modifier");
        modif_etat.setStyle("-fx-background-color:#F20D3E;-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
      
        final Button refresh = new Button("Actualiser");
        modif_etat.setStyle("-fx-background-color:#F20D3E;-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        tableLits.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (tableLits.getSelectionModel().getSelectedItem() != null) {
                    TableView.TableViewSelectionModel selectionModel = tableLits.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//                    final Object valCh = tablePosition.getTableColumn().getCellData(t1);
                    final Lit lit_g = (Lit) tableLits.getSelectionModel().getSelectedItem();
                    if (lit_g.getEtat().equals("Occupé")) {
                        rbModif2.setSelected(true);
                    } else {
                        rbModif1.setSelected(true);
                    }
                    final Stage dialogStage = new Stage();
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.setScene(new Scene(VBoxBuilder.create().children(rbModif1, rbModif2, modif_etat).alignment(Pos.CENTER).padding(new Insets(5)).build()));

                    modif_etat.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String chked="";
                             if(rbModif1.isArmed()){
                                  chked = rbModif1.getText();
                             }
                            else{
                                  chked = rbModif2.getText();
                             }
                             Lit newlit = new Lit();
                            idCh = lit_g.getIdlit();
                             daoLit.updateLit(idCh,chked);
                             
                                  dialogStage.hide();
                        }
                    });
                     
                    dialogStage.show();
                    dialogStage.setWidth(400);
                    dialogStage.setHeight(400);
                }
                refresh.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        LitDao daoLit = new LitDao();
                         listeLits.removeAll(dataLits);
                            dataLits.clear();
                            listeLits=daoLit.DisplayAllLitByRoom(idCh);
                             dataLits.addAll(listeLits);
                             tableLits.setItems(dataLits);
                       }
                     
                            
                });
            }
        });

        //Emplacement
        VBox hspace2 = new VBox();
        //hspace2.setSpacing(15);
        //hspace2.setPadding(new Insets(5, 5, 5, 5));
        //hspace2.setPrefWidth(300);
        HBox h1 = new HBox();
        h1.setSpacing(15);
        h1.setPadding(new Insets(5, 5, 5, 5));
        h1.setPrefWidth(300);
        h1.getChildren().addAll(ServiceMod, comboServiceMod,refresh);
        HBox hTable = new HBox();
        hTable.getChildren().addAll(tableChambre);
        hspace2.getChildren().addAll(h1,hTable);
        
        //placer tableu+bouton
        VBox main2 = new VBox();
        //main2.setPadding(new Insets(5, 5, 5, 5));
        //main2.setSpacing(4);
        //main2.setPrefSize(800, 300);
        main2.getChildren().addAll(tableLits);

        grid2.getChildren().addAll(hspace2, main2);

        ///Suppression
        //3éme Grid pour suppression
        final GridPane grid3 = new GridPane();
        grid3.setVgap(4);
        grid3.setPadding(new Insets(50, 50, 50, 50));
        
        Label sericeSup = new Label("Service:");
        
       final ComboBox<String> comService = new ComboBox();
        grid3.add(sericeSup, 0, 0);
        grid3.add(comService, 1, 0);
        
         listService = daoService.DisplayAllServicesName();
        //textfields
         dataServiceAjout = FXCollections.observableArrayList();
        dataServiceAjout.addAll(listService);
        comService.setId("second-editable");
        comService.setPromptText("Edit or Choose...");
        comService.setItems(dataServiceAjout);
        comService.setEditable(true);
        
       ListChambreSupp = new ArrayList<>();
        ListChambreSupp = daoChambre2.DisplayAllChambre();
        final ObservableList<Chambre> dataChambreSupp = FXCollections.observableArrayList();

        dataChambreSupp.addAll(ListChambreSupp);
       final TableView tableViewmSupp = new TableView();
        tableViewmSupp.setItems(dataChambreSupp);
       //Table de chambre .

        final TableColumn idcham = new TableColumn();
        idcham.setMinWidth(100);
        idcham.setText("ID Chambre");
        idcham.setCellValueFactory(new PropertyValueFactory("idchambre"));
       final  TableColumn idserv = new TableColumn();
        idserv.setMinWidth(100);
        idserv.setText("ID Service");
        idserv.setCellValueFactory(new PropertyValueFactory("idservice"));
        final TableColumn capacitem = new TableColumn();
        capacitem.setMinWidth(100);
        capacitem.setText("Capacité");
        capacitem.setCellValueFactory(new PropertyValueFactory("capacite"));
       final  TableColumn typem = new TableColumn();
        typem.setMinWidth(100);
        typem.setText("Type");
        typem.setCellValueFactory(new PropertyValueFactory("type"));
       final  TableColumn etatm = new TableColumn("Etat");
        etatm.setMinWidth(100);
        etatm.setText("Etat");
        etatm.setCellValueFactory(new PropertyValueFactory("etat"));
      
        //formulaire modification
        VBox formulaireModif = new VBox();
        formulaireModif.setSpacing(5);
        formulaireModif.setPadding(new Insets(5, 20, 10, 20));
        final Button supp_chambre = new Button("Supprimer");
        supp_chambre.setStyle("-fx-background-color:#F20D3E;-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 );");
        comService.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                 String choixRecherche = (String) comService.getValue();
                System.err.println(choixRecherche);
                ServicesDao daoS = new ServicesDao();
                int idServ = daoS.findServiceByName(choixRecherche);
                List<Chambre> ListChambre = new ArrayList<>();
                ChambreDao daoChambre = new ChambreDao();
                ListChambre = daoChambre.DisplayAllChambreByService(idServ);
                ObservableList<Chambre> dataChambre = null;
                dataChambre = FXCollections.observableArrayList();

                dataChambre.addAll(ListChambre);

                 tableViewmSupp.setItems(dataChambre);
                
            }
        });
          tableViewmSupp.getColumns().addAll(idcham, idservice, capacitem, typem, etatm);
          grid3.add(tableViewmSupp, 1, 2);
          /*tableViewmSupp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
          @Override
          public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
          if (tableViewmSupp.getSelectionModel().getSelectedItem() != null) {
          TableView.TableViewSelectionModel selectionModel = tableViewmSupp.getSelectionModel();
          ObservableList selectedCells = selectionModel.getSelectedCells();
          TablePosition tablePosition = (TablePosition) selectedCells.get(0);
          final Object valCh = tablePosition.getTableColumn().getCellData(newValue);*/
                   tableViewmSupp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                Chambre hc = (Chambre) tableViewmSupp.getSelectionModel().getSelectedItem();
                    final Stage dialogStage = new Stage();
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    if (hc!=null){
                    dialogStage.setScene(new Scene(VBoxBuilder.create().
                    children(new Text(" voulez vous supprimer cet chambre\t" + hc.getIdchambre()), supp_chambre).alignment(Pos.CENTER).padding(new Insets(5)).build()));
                    supp_chambre.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            daoChambre2.deleteChambre(hc.getIdchambre());

                             ListChambreSupp.removeAll(dataChambreSupp );
                            dataChambreSupp .clear();
                            //List<Lit> newList = new ArrayList<>();
                            ListChambreSupp=daoChambre2.DisplayAllChambre();
                            dataChambreSupp.addAll(ListChambreSupp);
                           tableViewmSupp.setItems(dataChambreSupp);
                            dialogStage.hide();
                        }
                    });
                    }
                    dialogStage.show();
                    dialogStage.setWidth(300);
                    dialogStage.setHeight(300);
                }
            
        });
 /*
        // --- GridPane container
        TitledPane gridTitlePane1 = new TitledPane("Ajouter Chambre", grid1);

        TitledPane gridTitlePane2 = new TitledPane("Modifier Chambre", grid2);
        TitledPane gridTitlePane3 = new TitledPane("Supprimer Chambre", grid3);
        // --- Accordion
        final Accordion accordion = new Accordion();
        accordion.getPanes().add(gridTitlePane1);
        accordion.getPanes().add(gridTitlePane2);
        accordion.getPanes().add(gridTitlePane3);
       
        return accordion;
         
         */
        //borderpane boutons+interface à droite
      BorderPane bord_ch=new BorderPane();
  
        
       //bouton ajout  
      Button ajout_ch=new Button();
      ajout_ch.setCursor(Cursor.HAND);
      Image im_aj = new Image(getClass().getResourceAsStream("/cssfiles/ajout_ch.png"));     
      ajout_ch.setGraphic(new ImageView(im_aj));      
      ajout_ch.setStyle("-fx-background-color: transparent;");
       Tooltip toolB_aj = new Tooltip("Ajouter des chambres");
        toolB_aj.setContentDisplay(ContentDisplay.BOTTOM);
        toolB_aj.setStyle("");
        ajout_ch.setTooltip(toolB_aj);
      
        ajout_ch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
             bord_ch.setCenter(grid1);
            }
        });
      
      //bouton recherche/modif
      Button modif_ch=new Button();
      modif_ch.setCursor(Cursor.HAND);
      Image im_modif = new Image(getClass().getResourceAsStream("/cssfiles/rech_ch.png"));     
      modif_ch.setGraphic(new ImageView(im_modif));      
      modif_ch.setStyle("-fx-background-color: transparent;");
      Tooltip toolB = new Tooltip("Modifier chambres");
      toolB.setContentDisplay(ContentDisplay.BOTTOM);
      toolB.setStyle("");
      modif_ch.setTooltip(toolB);
      
      modif_ch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
             bord_ch.setCenter(grid2);
            }
        });
      
      //bouton dropbox
      Button sup_ch=new Button();
      sup_ch.setCursor(Cursor.HAND);
      Image im_drop = new Image(getClass().getResourceAsStream("/cssfiles/supp_ch.png"));     
      sup_ch.setGraphic(new ImageView(im_drop));      
      sup_ch.setStyle("-fx-background-color: transparent;");
      Tooltip toolB_ar = new Tooltip("Arichiver dans dropbox");
      toolB_ar.setContentDisplay(ContentDisplay.BOTTOM);
      toolB_ar.setStyle("");
      sup_ch.setTooltip(toolB_ar);
      
      sup_ch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_ch.setCenter(grid3);
            }
            
        });
      
      //regroupement des boutons
      VBox v_bouton=new VBox();
      v_bouton.setSpacing(20);
      v_bouton.getChildren().addAll(ajout_ch,modif_ch,sup_ch);  
      
      bord_ch.setLeft(v_bouton);
      bord_ch.setCenter(grid1);
      
      
       return bord_ch;           
         
                   
                   
                   
                   
                   
                   
                   
                   
    }

}