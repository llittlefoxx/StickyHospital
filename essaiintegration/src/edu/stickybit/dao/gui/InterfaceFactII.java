/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;


import edu.stickybit.dao.classes.FactureDAO;
import edu.stickybit.dao.classes.InterventionDAO;
import edu.stickybit.dao.classes.LigneFactureDAO;
import edu.stickybit.dao.classes.PatientDAO;
import edu.stickybit.dao.classes.MedicamentDAO; 
import edu.stickybit.entity.Facture;
import edu.stickybit.entity.Intervention; 
import edu.stickybit.entity.LigneFacture;
import edu.stickybit.entity.Patient;
import edu.stickybit.entity.Medicament;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Dontey
 */
public class InterfaceFactII extends Application {

    //java.util.Date utilDate = new java.util.Date();
    // java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    // Date d = new Date();
    List<Facture> arrayFact = new ArrayList<>();
    List<Facture> arrayFact2 = new ArrayList<>();
    List<LigneFacture> arrayLigne = new ArrayList<>();
    List<Patient> arrayPatient = new ArrayList<>();
    List<Medicament> arrayMed = new ArrayList<>();
    List<edu.stickybit.entity.Intervention> arrayInter = new ArrayList<>();
    FactureDAO daoFact = new FactureDAO();
    LigneFactureDAO daoLigne = new LigneFactureDAO();
    PatientDAO daoPatient = new PatientDAO();
    MedicamentDAO daomed = new MedicamentDAO();
    InterventionDAO daointer = new InterventionDAO();
    ObservableList<Facture> dataFact = null;
    ObservableList<Facture> dataFact2 = null;
    ObservableList<LigneFacture> dataLigne = null;
    ObservableList<Patient> dataPatient = null;
    ObservableList<Medicament> dataMedicament = null;
    ObservableList<Intervention> dataIntervention = null;
    final TableView tableFact = new TableView();
    final TableView tableLigne = new TableView();
    final TableView tablePatient = new TableView();
    final TableView tablemed = new TableView();
    final TableView tabinter = new TableView();
    int idfact = 0;
    int idl = 0;
    int cinpat;
    int idpatientt;
    float tot = 0;
    Boolean et = false;
    Date factdate;
    int idm = 0;
    int idi = 0;
    int qu = 0;
    float prixx = 0;
    String libnom = null;
    int idmedic;
    String nm = null;
    int qm = 0;
    float pm = 0;
    int idintervention;
    float print;
    String libinterv = null;
    int factid;
    float facttot;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(final Stage stage) throws SQLException {
        Accordion A = new Accordion();
        A.setMinSize(200, 200);
        Group g = new Group();
        Scene scene = new Scene(g);
        stage.setTitle("Gestion des factures");
        // stage.setWidth(900);
        //  stage.setHeight(700);

        final VBox vbox = new VBox();
        final VBox formajout = new VBox();
        
        final HBox hbox1 = new HBox();
        hbox1.setSpacing(5);
        hbox1.setPadding(new Insets(5, 20, 10, 20));
        
        final HBox hbox2 = new HBox();
        hbox2.setSpacing(5);
        hbox2.setPadding(new Insets(5, 20, 10, 20));
        
        final HBox hbox3 = new HBox();
        hbox3.setSpacing(5);
        hbox3.setPadding(new Insets(5, 20, 10, 20));
        
        final HBox hbox4 = new HBox();
        hbox4.setSpacing(5);
        hbox4.setPadding(new Insets(5, 20, 10, 20));
        
        final HBox hbox5 = new HBox();
        hbox5.setSpacing(5);
        hbox5.setPadding(new Insets(5, 20, 10, 20));
        
        final HBox hbox6 = new HBox();
        hbox6.setSpacing(5);
        hbox6.setPadding(new Insets(5, 20, 10, 20));
        final HBox hbox7 = new HBox();
        hbox7.setSpacing(5);
        hbox7.setPadding(new Insets(5, 20, 10, 20));
        final HBox hbox8 = new HBox();
        hbox8.setSpacing(5);
        hbox8.setPadding(new Insets(5, 20, 10, 20));
        
        arrayPatient = daoPatient.DisplayAllPatient();
        dataPatient = FXCollections.observableArrayList();
        dataPatient.addAll(arrayPatient);
        tablePatient.setItems(dataPatient);
        tablePatient.setEditable(true);
        tablePatient.setMaxSize(1000, 150);
        
        TableColumn idp = new TableColumn("Id Patient");
        idp.setMinWidth(100);
        idp.setCellValueFactory(new PropertyValueFactory<Patient, String>("id_patient"));
        
        TableColumn np = new TableColumn("Nom Patient");
        np.setMinWidth(100);
        np.setCellValueFactory(new PropertyValueFactory<Patient, String>("nom_patient"));
        
        TableColumn pp = new TableColumn("Prénom Patient");
        pp.setMinWidth(100);
        pp.setCellValueFactory(new PropertyValueFactory<Patient, String>("prenom_patient"));
        
        TableColumn cinp = new TableColumn("CIN Patient");
        cinp.setMinWidth(100);
        cinp.setCellValueFactory(new PropertyValueFactory<Patient, String>("cin_patient"));
        
        tablePatient.getColumns().addAll(idp, np, pp, cinp);
        
        final VBox ajoutFact = new VBox();
        Label idpatient = new Label("Id Patient");
        final TextField txt_id_pa = new TextField();
        final Label idadmin = new Label("Id Admin");
        final TextField txt_id_admin = new TextField();
        txt_id_admin.setText("1");
        // final Label date = new Label("Date");
        //final TextField txt_date = new TextField();

        arrayFact2 = daoFact.DisplayAllFactures();
        System.out.println(arrayFact2.size());
        dataFact2 = FXCollections.observableArrayList();
        dataFact2.addAll(arrayFact2);
        tableFact.setItems(dataFact2);
        tableFact.setEditable(true);
        tableFact.setMaxSize(1000, 150);
        
        TableColumn idfactureCol = new TableColumn("Id Facture");
        idfactureCol.setMinWidth(100);
        idfactureCol.setCellValueFactory(new PropertyValueFactory("id_facture"));
        
        TableColumn idpatientCol = new TableColumn("Id Patient");
        idpatientCol.setMinWidth(100);
        idpatientCol.setCellValueFactory(new PropertyValueFactory("id_patient"));
        
        TableColumn idadminCol = new TableColumn("Id Admin");
        idadminCol.setMinWidth(100);
        idadminCol.setCellValueFactory(new PropertyValueFactory("id_dadmin"));
        
        TableColumn dateCol = new TableColumn("Date Facturation");
        dateCol.setMinWidth(150);
        dateCol.setCellValueFactory(new PropertyValueFactory("date_facturation"));
        
        TableColumn etatCol = new TableColumn("Etat Facture");
        etatCol.setMinWidth(100);
        etatCol.setCellValueFactory(new PropertyValueFactory<Facture, String>("etat"));
        
        TableColumn totalCol = new TableColumn("Total Facture");
        totalCol.setMinWidth(100);
        totalCol.setCellValueFactory(new PropertyValueFactory<Facture, String>("total_facture"));
        
        tableFact.getColumns().addAll(idfactureCol, dateCol, etatCol, totalCol);
        final TextField Sbox = new TextField();
        final Label recherche = new Label("Rechercher Patient   :");
        tablePatient.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Patient pa = new Patient();
                pa = (Patient) tablePatient.getSelectionModel().getSelectedItem();
                cinpat = pa.getCin_patient();
                idpatientt = pa.getId_patient();

                //  System.err.println(cinpat);
                    try {
                    arrayFact.removeAll(dataFact);
                    dataFact.clear();
                    dataFact.addAll(daoFact.FindFactureParCin(cinpat));
                    tableFact.setItems(dataFact);
                    // textmodifEtat.setText(String.valueOf(et));
                    // textmodifTotal.setText(String.valueOf(tot));

                } catch (Exception e) {
                    
                }
                
            }
            
        });
        
        Button rech = new Button();
        rech.setText("Rechercher");
        rech.setDefaultButton(true);
        rech.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                arrayPatient.removeAll(dataPatient);
                dataPatient.clear();
                List<Patient> i = new ArrayList<>();
                try {
                    daoLigne = new LigneFactureDAO();
                    i = (List<Patient>) daoPatient.findPatientByCin((Integer.valueOf(Sbox.getText())));
                    dataPatient.addAll(i);
                    tablePatient.setItems(dataPatient);
                    Sbox.clear();
                    
                } catch (Exception e) {
                }
                
            }
        }));
        
        Button act = new Button();
        act.setText("Actualiser");
        act.setDefaultButton(true);
        act.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent actionEvent) {
                arrayPatient.removeAll(dataPatient);
                dataPatient.clear();
                arrayPatient = daoPatient.DisplayAllPatient();
                dataPatient.addAll(arrayPatient);
                tablePatient.setItems(dataPatient);
                
            }
        });
        Button modiffact = new Button();
        modiffact.setText("Modifier facture");
        modiffact.setDefaultButton(true);
        
        tableFact.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                Facture fa = new Facture();
                fa = (Facture) tableFact.getSelectionModel().getSelectedItem();
                factid = Integer.valueOf(fa.getId_facture());
                facttot = fa.getTotal_facture();
                factdate = fa.getDate_facturation();

                 System.err.println(cinpat);
                try {
                     arrayFact.removeAll(dataFact);
                    dataFact.clear();
                arrayFact=daoFact.FindFactureParCin(cinpat);
                    System.out.println(arrayFact.size());
        dataFact = FXCollections.observableArrayList();
        dataFact.addAll(arrayFact);
                    
                    tableFact.setItems(dataFact);
                    // textmodifEtat.setText(String.valueOf(et));
                    // textmodifTotal.setText(String.valueOf(tot));

                } catch (Exception e) {
                    
                }
                
            }
            
        });
        
        modiffact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                FactureDAO factdao = new FactureDAO();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

               // Facture fact2 = new Facture(idpatientt, 1, sqlDate, 0, true); 
                // final int xx = factdao.ajoutFacture2(fact2);
                final Stage stage = new Stage();
                
                Group rootGroup = new Group();
                Scene scene = new Scene(rootGroup, 500, 500, Color.WHITESMOKE);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                final VBox v1 = new VBox();
                final VBox v2 = new VBox();
                final HBox h1 = new HBox();
                h1.setSpacing(5);
                h1.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h2 = new HBox();
                h2.setSpacing(5);
                h2.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h3 = new HBox();
                h3.setSpacing(5);
                h3.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox menu = new HBox();
                menu.setSpacing(5);
                menu.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h6 = new HBox();
                h6.setSpacing(5);
                h6.setPadding(new Insets(5, 20, 10, 20));
                final HBox h7 = new HBox();
                h7.setSpacing(5);
                h7.setPadding(new Insets(5, 20, 10, 20));
                final HBox h8 = new HBox();
                h8.setSpacing(5);
                h8.setPadding(new Insets(5, 20, 10, 20));
                final HBox h9 = new HBox();
                h9.setSpacing(5);
                h9.setPadding(new Insets(5, 20, 10, 20));
                final HBox h10 = new HBox();
                h10.setSpacing(5);
                h10.setPadding(new Insets(5, 20, 10, 20));
                final HBox h11 = new HBox();
                h11.setSpacing(5);
                h11.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h12 = new HBox();
                h12.setSpacing(5);
                h12.setPadding(new Insets(5, 20, 10, 20));
                
                final Label RechMed = new Label("Recherche");
                final TextField rmed = new TextField();
                Button remed = new Button();
                remed.setText("Rechercher");
                remed.setDefaultButton(true);
                remed.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        try {
                            arrayMed.removeAll(dataMedicament);
                            dataMedicament.clear();
                            Medicament m = new Medicament();
                            daomed = new MedicamentDAO();
                            m = daomed.findmedBylibelle((rmed.getText()));
                            
                            dataMedicament.addAll(m);
                            tablemed.setItems(dataMedicament);
                            rmed.clear();
                            
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                
                arrayMed = daomed.DisplayAllmed();
                //System.out.println(arrayMed.size());
                dataMedicament = FXCollections.observableArrayList();
                dataMedicament.addAll(arrayMed);
                tablemed.setItems(dataMedicament);
                tablemed.setEditable(true);
                tablemed.setMaxSize(1000, 150);
                
                TableColumn idmed = new TableColumn("Id");
                idmed.setMinWidth(100);
                idmed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("code"));
                
                TableColumn libmed = new TableColumn("Nom");
                libmed.setMinWidth(100);
                libmed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("libelle"));
                
                TableColumn qumed = new TableColumn("Quantité");
                qumed.setMinWidth(100);
                qumed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("quantite"));
                
                TableColumn prmed = new TableColumn("Prix");
                prmed.setMinWidth(100);
                prmed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("prix"));
                
                final TextField txt_choix_med = new TextField();
                tablemed.getColumns().addAll(idmed, libmed, qumed, prmed);
                //saisie des champs du tableau
                tablemed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    
                    @Override
                    public void changed(ObservableValue ov, Object t, Object t1) {
                        Medicament m = new Medicament();
                        m = (Medicament) tablemed.getSelectionModel().getSelectedItem();
                        idmedic = m.getCode();
                        nm = m.getLibelle();
                        qm = m.getQuantite();
                        pm = m.getPrix();
                        txt_choix_med.setText(String.valueOf(idmedic));
                        //  System.err.println(idmedic);
                    }
                    
                });
                
                arrayInter = daointer.DisplayAllInterventions();
                dataIntervention = FXCollections.observableArrayList();
                dataIntervention.addAll(arrayInter);
                tabinter.setItems(dataIntervention);
                tabinter.setEditable(true);
                tabinter.setMaxSize(1000, 150);
                
                TableColumn idinter = new TableColumn("Id");
                idinter.setMinWidth(100);
                idinter.setCellValueFactory(new PropertyValueFactory<Intervention, String>("id_intervention"));
                
                TableColumn libinter = new TableColumn("Nom");
                libinter.setMinWidth(100);
                libinter.setCellValueFactory(new PropertyValueFactory<Intervention, String>("lib_intervention"));
                
                TableColumn printer = new TableColumn("Prix");
                printer.setMinWidth(100);
                printer.setCellValueFactory(new PropertyValueFactory<Intervention, String>("prix_interv"));
                
                TableColumn dinter = new TableColumn("Date");
                dinter.setMinWidth(100);
                dinter.setCellValueFactory(new PropertyValueFactory<Intervention, String>("date_interv"));
                
                tabinter.getColumns().addAll(idinter, libinter, printer);
                
                final TextField txt_choix_int = new TextField();

                //saisie des champs du tableau
                tabinter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    
                    @Override
                    public void changed(ObservableValue ov, Object t, Object t1) {
                        Intervention inte = new Intervention();
                        inte = (Intervention) tabinter.getSelectionModel().getSelectedItem();
                        idintervention = inte.getId_intervention();
                        print = inte.getPrix_interv();
                        libinterv = inte.getLib_intervention();
                        txt_choix_int.setText(String.valueOf(idintervention));
                        // System.err.println(idmedic);
                    }
                    
                });
                
                final TableColumn q = new TableColumn("Quantite");
                q.setMinWidth(150);
                q.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("quantite"));
                
                final TableColumn prix = new TableColumn("Prix");
                prix.setMinWidth(150);
                prix.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("prix"));
                
                TableColumn l = new TableColumn("Libelle Ligne");
                l.setMinWidth(150);
                l.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("lib_ligne"));
                
                tableLigne.getColumns().addAll(l, q, prix);
                
                final Button actu = new Button();
                actu.setText("Actualiser");
                actu.setDefaultButton(true);
                
                dataLigne = FXCollections.observableArrayList();
                dataLigne.addAll(arrayLigne);
                tableLigne.setItems(dataLigne);
                tableLigne.setEditable(true);
                tableLigne.setMaxSize(1000, 150);
                
                actu.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        arrayLigne = daoLigne.FindLigneFacture(factid);
                        
                        arrayLigne.removeAll(dataLigne);
                        dataLigne.clear();
                        dataLigne.addAll(arrayLigne);
                        tableLigne.setItems(dataLigne);
                        
                        arrayMed.removeAll(dataMedicament);
                        dataMedicament.clear();
                        arrayMed = daomed.DisplayAllmed();
                        dataMedicament.addAll(arrayMed);
                        tablemed.setItems(dataMedicament);
                        
                        arrayInter.removeAll(dataIntervention);
                        dataIntervention.clear();
                        arrayInter = daointer.DisplayAllInterventions();
                        dataIntervention.addAll(arrayInter);
                        tabinter.setItems(dataIntervention);
                        
                    }
                });
                h1.getChildren().addAll(RechMed, rmed, remed, actu);
                h2.getChildren().addAll(tablemed);
                
                final TextField tx_id_ad = new TextField();
                tx_id_ad.setText("1");
                final TextField tx_id_fact = new TextField();
                tx_id_fact.setText(String.valueOf(factid));
                final TextField tx_date = new TextField();
                tx_date.setText(String.valueOf(factdate));
                h3.getChildren().addAll(tx_id_ad, tx_id_fact, tx_date);
                
                final Label choix_med = new Label();
                choix_med.setText("Choix du médicament");
                
                final Label choix_interve = new Label();
                choix_interve.setText("Choix de l'intérvention");
                
                final Label choix_quan = new Label();
                choix_quan.setText("choix de la quantité");
                final TextField txt_choix_quan = new TextField();
                
                final Label Rechint = new Label("Recherche");
                final TextField rint = new TextField();
                final Button rin = new Button();
                rin.setText("Rechercher");
                rin.setDefaultButton(true);
                rin.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        try {
                            arrayInter.removeAll(dataIntervention);
                            dataIntervention.clear();
                            Intervention in = new Intervention();
                            daointer = new InterventionDAO();
                            in = daointer.findIntervBylibelle((rint.getText()));
                            //   System.err.println(rint.getText());

                            dataIntervention.addAll(in);
                            tabinter.setItems(dataIntervention);
                            rint.clear();
                            
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                h10.getChildren().addAll(Rechint, rint, rin);
                
                Button addL = new Button();
                addL.setDefaultButton(true);
                addL.setText("Ajouter Medicament");
                addL.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            if (Integer.valueOf(txt_choix_quan.getText()) > qm) {
                                System.err.println("ajout impossible");
                                txt_choix_med.clear();
                                txt_choix_int.clear();
                                txt_choix_quan.clear();
                            } else {
                                LigneFacture l1 = new LigneFacture(factid, Integer.valueOf(txt_choix_med.getText()), Integer.valueOf(txt_choix_quan.getText()), Float.valueOf(pm), nm);
                                Medicament med = new Medicament(Integer.valueOf(txt_choix_med.getText()), nm, qm - Integer.valueOf(txt_choix_quan.getText()), pm);
                                daoLigne = new LigneFactureDAO();
                                daoLigne.ajouterMedicament(l1);
                                daomed.updatemed(med);
                                
                                txt_choix_med.clear();
                                txt_choix_int.clear();
                                txt_choix_quan.clear();
                            }
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                
                Button addI = new Button();
                addI.setDefaultButton(true);
                addI.setText("Ajouter Intervention");
                addI.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            LigneFacture l2 = new LigneFacture(factid, 1, Float.valueOf(print), idintervention, libinterv);
                            daoLigne = new LigneFactureDAO();
                            daoLigne.ajouterIntervention(l2);
                            
                            txt_choix_med.clear();
                            txt_choix_int.clear();
                            txt_choix_quan.clear();
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                
                final TextField calcultot = new TextField();
                Button calcul = new Button();
                calcul.setText("Total Facture hors Taxe");
                calcul.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        float total = 0;
                        
                        try {
                            
                            for (int i = 0; i < arrayLigne.size(); i++) {
                                total += arrayLigne.get(i).getPrix() * arrayLigne.get(i).getQuantite();
                            }

                            // System.err.println(total);
                        } catch (Exception e) {
                        }
                        calcultot.setText(String.valueOf(total));
                    }
                    
                }));
                HBox tva = new HBox();
                tva.setSpacing(5);
                tva.setPadding(new Insets(5, 20, 10, 20));
                
                final TextField tva_tx = new TextField();
                Button caltva = new Button();
                caltva.setText("Total Facture");
                caltva.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        tva_tx.setText(String.valueOf((Float.valueOf(calcultot.getText()) / 100) * 105));
                        Facture f = new Facture(Integer.valueOf(factid), Float.valueOf(tva_tx.getText()));
                        FactureDAO fd = new FactureDAO();
                        fd.updateTotalFacture(f);
                    }
                    
                }));
                
                tva.getChildren().addAll(caltva, tva_tx);

                //   calcultot.setText(String.valueOf(total));
                h11.getChildren().addAll(choix_interve, txt_choix_int);
                h12.getChildren().addAll(calcul, calcultot);
                h9.getChildren().addAll(tabinter);
                h6.getChildren().addAll(choix_med, txt_choix_med);
                h7.getChildren().addAll(addL, addI);
                h8.getChildren().addAll(choix_quan, txt_choix_quan);
                v1.getChildren().addAll(h1, h2, h6, h8, h10, h9, h11, h7);
                v2.getChildren().addAll(h3, tableLigne, h12, tva);
                menu.getChildren().addAll(v1, v2);
                rootGroup.getChildren().addAll(menu);
            }
        });
        
        hbox1.getChildren().addAll(recherche, Sbox, rech, act);
        hbox2.getChildren().addAll(tableFact, modiffact);
        // hbox3.getChildren().addAll(tableLigne);
        hbox4.getChildren().addAll(tablePatient, formajout);
        
        final Label lab1 = new Label("Id Admin");
        final TextField tx1 = new TextField("1");
        tx1.setEditable(false);
        int lastId = daoFact.retourId() + 1;
        final Label lab2 = new Label("Id Facture");
        final TextField tx2 = new TextField("");
        tx2.setEditable(false);
        tx2.setText(String.valueOf(lastId));
        
        final Label lab3 = new Label("Date Facturation");
        final TextField tx3 = new TextField();
        tx3.setEditable(false);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        tx3.setText(String.valueOf(sqlDate));
        
        Button aj = new Button();
        aj.setText("Ajouter Facture");
        aj.setDefaultButton(true);
        
        aj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                
                FactureDAO factdao = new FactureDAO();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                
                Facture fact2 = new Facture(idpatientt, 1, sqlDate, 0, true);
                
                final int xx = factdao.ajoutFacture2(fact2);
                
                final Stage stage = new Stage();
                
                Group rootGroup = new Group();
                Scene scene = new Scene(rootGroup, 500, 500, Color.WHITESMOKE);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                final VBox v1 = new VBox();
                final VBox v2 = new VBox();
                final HBox h1 = new HBox();
                h1.setSpacing(5);
                h1.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h2 = new HBox();
                h2.setSpacing(5);
                h2.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h3 = new HBox();
                h3.setSpacing(5);
                h3.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox menu = new HBox();
                menu.setSpacing(5);
                menu.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h6 = new HBox();
                h6.setSpacing(5);
                h6.setPadding(new Insets(5, 20, 10, 20));
                final HBox h7 = new HBox();
                h7.setSpacing(5);
                h7.setPadding(new Insets(5, 20, 10, 20));
                final HBox h8 = new HBox();
                h8.setSpacing(5);
                h8.setPadding(new Insets(5, 20, 10, 20));
                final HBox h9 = new HBox();
                h9.setSpacing(5);
                h9.setPadding(new Insets(5, 20, 10, 20));
                final HBox h10 = new HBox();
                h10.setSpacing(5);
                h10.setPadding(new Insets(5, 20, 10, 20));
                final HBox h11 = new HBox();
                h11.setSpacing(5);
                h11.setPadding(new Insets(5, 20, 10, 20));
                
                final HBox h12 = new HBox();
                h12.setSpacing(5);
                h12.setPadding(new Insets(5, 20, 10, 20));
                
                final Label RechMed = new Label("Recherche");
                final TextField rmed = new TextField();
                Button remed = new Button();
                remed.setText("Rechercher");
                remed.setDefaultButton(true);
                remed.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        try {
                            arrayMed.removeAll(dataMedicament);
                            dataMedicament.clear();
                            Medicament m = new Medicament();
                            daomed = new MedicamentDAO();
                            m = daomed.findmedBylibelle((rmed.getText()));
                            
                            dataMedicament.addAll(m);
                            tablemed.setItems(dataMedicament);
                            rmed.clear();
                            
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                
                arrayMed = daomed.DisplayAllmed();
                //System.out.println(arrayMed.size());
                dataMedicament = FXCollections.observableArrayList();
                dataMedicament.addAll(arrayMed);
                tablemed.setItems(dataMedicament);
                tablemed.setEditable(true);
                tablemed.setMaxSize(1000, 150);
                
                TableColumn idmed = new TableColumn("Id");
                idmed.setMinWidth(100);
                idmed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("code"));
                
                TableColumn libmed = new TableColumn("Nom");
                libmed.setMinWidth(100);
                libmed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("libelle"));
                
                TableColumn qumed = new TableColumn("Quantité");
                qumed.setMinWidth(100);
                qumed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("quantite"));
                
                TableColumn prmed = new TableColumn("Prix");
                prmed.setMinWidth(100);
                prmed.setCellValueFactory(new PropertyValueFactory<Medicament, String>("prix"));
                
                final TextField txt_choix_med = new TextField();
                tablemed.getColumns().addAll(idmed, libmed, qumed, prmed);
                //saisie des champs du tableau
                tablemed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    
                    @Override
                    public void changed(ObservableValue ov, Object t, Object t1) {
                        Medicament m = new Medicament();
                        m = (Medicament) tablemed.getSelectionModel().getSelectedItem();
                        idmedic = m.getCode();
                        nm = m.getLibelle();
                        qm = m.getQuantite();
                        pm = m.getPrix();
                        txt_choix_med.setText(String.valueOf(idmedic));
                        //  System.err.println(idmedic);
                    }
                    
                });
                
                arrayInter = daointer.DisplayAllInterventions();
                dataIntervention = FXCollections.observableArrayList();
                dataIntervention.addAll(arrayInter);
                tabinter.setItems(dataIntervention);
                tabinter.setEditable(true);
                tabinter.setMaxSize(1000, 150);
                
                TableColumn idinter = new TableColumn("Id");
                idinter.setMinWidth(100);
                idinter.setCellValueFactory(new PropertyValueFactory<Intervention, String>("id_intervention"));
                
                TableColumn libinter = new TableColumn("Nom");
                libinter.setMinWidth(100);
                libinter.setCellValueFactory(new PropertyValueFactory<Intervention, String>("lib_intervention"));
                
                TableColumn printer = new TableColumn("Prix");
                printer.setMinWidth(100);
                printer.setCellValueFactory(new PropertyValueFactory<Intervention, String>("prix_interv"));
                
                TableColumn dinter = new TableColumn("Date");
                dinter.setMinWidth(100);
                dinter.setCellValueFactory(new PropertyValueFactory<Intervention, String>("date_interv"));
                
                tabinter.getColumns().addAll(idinter, libinter, printer);
                
                final TextField txt_choix_int = new TextField();

                //saisie des champs du tableau
                tabinter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    
                    @Override
                    public void changed(ObservableValue ov, Object t, Object t1) {
                        Intervention inte = new Intervention();
                        inte = (Intervention) tabinter.getSelectionModel().getSelectedItem();
                        idintervention = inte.getId_intervention();
                        print = inte.getPrix_interv();
                        libinterv = inte.getLib_intervention();
                        txt_choix_int.setText(String.valueOf(idintervention));
                        // System.err.println(idmedic);
                    }
                    
                });
                
                final TableColumn q = new TableColumn("Quantite");
                q.setMinWidth(150);
                q.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("quantite"));
                
                final TableColumn prix = new TableColumn("Prix");
                prix.setMinWidth(150);
                prix.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("prix"));
                
                TableColumn l = new TableColumn("Libelle Ligne");
                l.setMinWidth(150);
                l.setCellValueFactory(new PropertyValueFactory<LigneFacture, String>("lib_ligne"));
                
                tableLigne.getColumns().addAll(l, q, prix);
                
                final Button actu = new Button();
                actu.setText("Actualiser");
                actu.setDefaultButton(true);
                
                dataLigne = FXCollections.observableArrayList();
                dataLigne.addAll(arrayLigne);
                tableLigne.setItems(dataLigne);
                tableLigne.setEditable(true);
                tableLigne.setMaxSize(1000, 150);
                
                actu.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        arrayLigne = daoLigne.FindLigneFacture(xx);
                        
                        arrayLigne.removeAll(dataLigne);
                        dataLigne.clear();
                        dataLigne.addAll(arrayLigne);
                        tableLigne.setItems(dataLigne);
                        
                        arrayMed.removeAll(dataMedicament);
                        dataMedicament.clear();
                        arrayMed = daomed.DisplayAllmed();
                        dataMedicament.addAll(arrayMed);
                        tablemed.setItems(dataMedicament);
                        
                        arrayInter.removeAll(dataIntervention);
                        dataIntervention.clear();
                        arrayInter = daointer.DisplayAllInterventions();
                        dataIntervention.addAll(arrayInter);
                        tabinter.setItems(dataIntervention);
                        
                    }
                });
                h1.getChildren().addAll(RechMed, rmed, remed, actu);
                h2.getChildren().addAll(tablemed);
                
                final TextField tx_id_ad = new TextField();
                tx_id_ad.setText(tx1.getText());
                final TextField tx_id_fact = new TextField();
                tx_id_fact.setText(tx2.getText());
                final TextField tx_date = new TextField();
                tx_date.setText(tx3.getText());
                h3.getChildren().addAll(tx_id_ad, tx_id_fact, tx_date);
                
                final Label choix_med = new Label();
                choix_med.setText("Choix du médicament");
                
                final Label choix_interve = new Label();
                choix_interve.setText("Choix de l'intérvention");
                
                final Label choix_quan = new Label();
                choix_quan.setText("choix de la quantité");
                final TextField txt_choix_quan = new TextField();
                
                final Label Rechint = new Label("Recherche");
                final TextField rint = new TextField();
                final Button rin = new Button();
                rin.setText("Rechercher");
                rin.setDefaultButton(true);
                rin.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        try {
                            arrayInter.removeAll(dataIntervention);
                            dataIntervention.clear();
                            Intervention in = new Intervention();
                            daointer = new InterventionDAO();
                            in = daointer.findIntervBylibelle((rint.getText()));
                            //   System.err.println(rint.getText());

                            dataIntervention.addAll(in);
                            tabinter.setItems(dataIntervention);
                            rint.clear();
                            
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                h10.getChildren().addAll(Rechint, rint, rin);
                
                Button addL = new Button();
                addL.setDefaultButton(true);
                addL.setText("Ajouter Medicament");
                addL.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            if (Integer.valueOf(txt_choix_quan.getText()) > qm) {
                                System.err.println("ajout impossible");
                                txt_choix_med.clear();
                                txt_choix_int.clear();
                                txt_choix_quan.clear();
                            } else {
                                LigneFacture l1 = new LigneFacture(xx, Integer.valueOf(txt_choix_med.getText()), Integer.valueOf(txt_choix_quan.getText()), Float.valueOf(pm), nm);
                                Medicament med = new Medicament(Integer.valueOf(txt_choix_med.getText()), nm, qm - Integer.valueOf(txt_choix_quan.getText()), pm);
                                daoLigne = new LigneFactureDAO();
                                daoLigne.ajouterMedicament(l1);
                                daomed.updatemed(med);
                                
                                txt_choix_med.clear();
                                txt_choix_int.clear();
                                txt_choix_quan.clear();
                            }
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                
                Button addI = new Button();
                addI.setDefaultButton(true);
                addI.setText("Ajouter Intervention");
                addI.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            LigneFacture l2 = new LigneFacture(xx, 1, Float.valueOf(print), idintervention, libinterv);
                            daoLigne = new LigneFactureDAO();
                            daoLigne.ajouterIntervention(l2);
                            
                            txt_choix_med.clear();
                            txt_choix_int.clear();
                            txt_choix_quan.clear();
                        } catch (Exception e) {
                        }
                        
                    }
                }));
                
                final TextField calcultot = new TextField();
                Button calcul = new Button();
                calcul.setText("Total Facture hors Taxe");
                calcul.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        float total = 0;
                        
                        try {
                            
                            for (int i = 0; i < arrayLigne.size(); i++) {
                                total += arrayLigne.get(i).getPrix() * arrayLigne.get(i).getQuantite();
                            }

                            // System.err.println(total);
                        } catch (Exception e) {
                        }
                        calcultot.setText(String.valueOf(total));
                    }
                    
                }));
                
                HBox tva = new HBox();
                tva.setSpacing(5);
                tva.setPadding(new Insets(5, 20, 10, 20));
                
                final TextField tva_tx = new TextField();
                Button caltva = new Button();
                caltva.setText("Total Facture");
                caltva.setOnAction((new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        
                        tva_tx.setText(String.valueOf((Float.valueOf(calcultot.getText()) / 100) * 105));
                        Facture f = new Facture(Integer.valueOf(tx2.getText()), Float.valueOf(tva_tx.getText()));
                        FactureDAO fd = new FactureDAO();
                        fd.updateTotalFacture(f);
                    }
                    
                }));
                tva.getChildren().addAll(caltva, tva_tx);

                //   calcultot.setText(String.valueOf(total));
                h11.getChildren().addAll(choix_interve, txt_choix_int);
                h12.getChildren().addAll(calcul, calcultot);
                h9.getChildren().addAll(tabinter);
                h6.getChildren().addAll(choix_med, txt_choix_med);
                h7.getChildren().addAll(addL, addI);
                h8.getChildren().addAll(choix_quan, txt_choix_quan);
                v1.getChildren().addAll(h1, h2, h6, h8, h10, h9, h11, h7);
                v2.getChildren().addAll(h3, tableLigne, h12, tva);
                menu.getChildren().addAll(v1, v2);
                rootGroup.getChildren().addAll(menu);
            }
        });
        
        hbox5.getChildren().addAll(lab1, tx1);
        hbox6.getChildren().addAll(lab2, tx2);
        hbox7.getChildren().addAll(lab3, tx3);
        hbox8.getChildren().addAll(aj);
        formajout.getChildren().addAll(hbox5, hbox6, hbox7, hbox8);
        
        vbox.getChildren().addAll(hbox1, hbox4, hbox2, hbox3);
        TitledPane t1 = new TitledPane("Gestion Facture & Ligne Facture", vbox);
        //  TitledPane t2 = new TitledPane("Ajouter Facture", hbox10);

        A.getPanes().addAll(t1);
        g.getChildren().add(A);
        
        stage.setScene(scene);
        stage.show();
        
    }
}
