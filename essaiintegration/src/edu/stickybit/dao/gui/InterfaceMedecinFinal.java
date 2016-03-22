/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;

import com.dropbox.core.DbxException;
import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import edu.stickybit.dao.classes.MedecinDAO;
import edu.stickybit.dao.classes.PatientDAO;
import edu.stickybit.dao.classes.ServicesDao;
import static edu.stickybit.dao.gui.InterfaceMedecin.isValidEmailAddress;
import edu.stickybit.entity.Medecin;
import edu.stickybit.general.SendEmail;
import edu.stickybit.general.SmsSender;
import edu.stickybit.technique.JavaDropbox;
import edu.stickybit.technique.MyConnection;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import rapport.Medecinjasper;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class InterfaceMedecinFinal {

    public InterfaceMedecinFinal() {
    }

    private static final String DROP_BOX_APP_KEY = "bn44b3748vknwjw";
    private static final String DROP_BOX_APP_SECRET = "drybmfd7zlh2yn7";
    private String choixRecherche;
    private String choixSpecialiteMedecin;
    private int choixServiceMedecin;
    private String choixServiceMedecinMod;
    private int IDnvCH;
    ObservableList<Medecin> data2 = null;
    TextInputValidatorPane<TextField> pane = new TextInputValidatorPane<TextField>();
    TextInputValidatorPane<TextField> paneCin = new TextInputValidatorPane<TextField>();
    List<Medecin> array = new ArrayList<Medecin>();
    TableView tableView = new TableView();
    ProgressIndicator p2 = new ProgressIndicator();
    public int testTelValidation = 0;
    public int testCinValidation = 0;
    public int testMailValidation = 0;
    public String emailExpo;
   

    public BorderPane RecupererInterface() throws SQLException {

        MedecinDAO dao = new MedecinDAO();

        final ComboBox<String> cbspecialite = new ComboBox();
        final ComboBox<String> cbservice = new ComboBox();
        final ComboBox<String> cbservice1 = new ComboBox();
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        HBox formulaireAj = new HBox();
        String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
        

        formulaireAj.setSpacing(10);
        formulaireAj.setPadding(new Insets(5, 10, 20, 20));
        //labels formulaire d'ajout
        Label nomLabel = new Label("Nom");
        Label pLabel = new Label("Prenom");
        Label telLabel = new Label("N° Tel");
        Label cinLab = new Label("C.I.N");
        Label specialiteLabel = new Label("Spécialité Medecin");
        Label serviceLabel = new Label("Service");
        Label mailLabel = new Label("Adresse mail");
        Label adresseLabel = new Label("Adresse");

        final Label lll = new Label();
        final Label lllCin = new Label();
        final Label lllMail = new Label();
        //champs de text formulaire d'ajout
        final TextField nom = new TextField();
        nom.setPromptText("Nom du medecin ICI");
        nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.20F);
            }

        });
        final TextField prenom = new TextField();
        prenom.setPromptText("Prenom du medecin ICI");
        prenom.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.40F);
            }

        });
        final TextField tel = new TextField();
        String validatorCss = InterfaceMedecin.class.getResource("/cssfiles/Validators.css").toExternalForm();

        tel.setMaxHeight(TextField.USE_PREF_SIZE);
        pane.setContent(tel);

        tel.setPromptText("Numéro de téléphone ICI");
        pane.setValidator(new Validator<TextField>() {
            public ValidationResult validate(TextField tel) {
                try {
                    String text = tel.getText();
                    if (text == null || text.trim().equals("")) {
                        lll.setText("Veuillez taper un numero");
                        lll.setTextFill(Color.web("#D22525"));
                        testTelValidation = 0;
                        return null;
                    } else {
                        lll.setText("Veuillez taper un numero");
                        lll.setTextFill(Color.web("#D22525"));
                        testTelValidation = 0;
                    }

                    int d = Integer.parseInt(text);
                    if (String.valueOf(d).length() < 8 | String.valueOf(d).length() > 8) {
                        lll.setText("Should be > 8");
                        lll.setTextFill(Color.web("#E1D60C"));
                        testTelValidation = 0;
                        return new ValidationResult("Should be > 8", ValidationResult.Type.WARNING);

                    } else {
                        lll.setText("numéro de téléphone conforme");
                        lll.setTextFill(Color.web("#24F462"));
                        testTelValidation = 1;
                    }
                    return null; // succeeded
                } catch (Exception e) {
                    // failed
                    lll.setText("Faux numéro");
                    lll.setTextFill(Color.web("#D22525"));
                    testTelValidation = 0;
                    return new ValidationResult("Bad number", ValidationResult.Type.ERROR);
                }

            }

        });

        tel.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.50F);

            }

        });

        final TextField cin = new TextField();
        cin.setPromptText("CIN du medecin ICI");
        paneCin.setContent(cin);

        paneCin.setValidator(new Validator<TextField>() {
            public ValidationResult validate(TextField cin) {
                try {
                    String text = cin.getText();
                    if (text == null || text.trim().equals("")) {
                        lllCin.setText("Veuillez taper un numero");
                        lllCin.setTextFill(Color.web("#D22525"));
                        testCinValidation = 0;
                        return null;
                    } else {
                        lllCin.setText("Veuillez taper un numero");
                        lllCin.setTextFill(Color.web("#D22525"));
                        testCinValidation = 0;
                    }
                    //double d = Double.parseDouble(text);
                    if (text.length() < 8 | text.length() > 8) {
                        lllCin.setText("Should be > 8");
                        lllCin.setTextFill(Color.web("#E1D60C"));
                        testCinValidation = 0;
                        return new ValidationResult("Should be > 1000", ValidationResult.Type.WARNING);
                    } else {
                        lllCin.setText("numéro de téléphone conforme");
                        lllCin.setTextFill(Color.web("#24F462"));
                        testCinValidation = 1;
                    }
                    return null; // succeeded
                } catch (Exception e) {
                    // failed
                    lllCin.setText("Faux numéro");
                    lllCin.setTextFill(Color.web("#D22525"));
                    testCinValidation = 0;
                    return new ValidationResult("Bad number", ValidationResult.Type.ERROR);
                }
            }
        });

        cin.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.60F);
            }

        });

        ///////////////
        MedecinDAO d = new MedecinDAO();
        List<Medecin> l = new ArrayList<>();
        l = d.rechercherToutCompteMedecin();
        List<String> ln = new ArrayList<>();
        for (Medecin medecin : l) {
            String nomMd = medecin.getSpecialite_medecin();
            //System.err.println(nomMd);
            ln.add(nomMd);
        }
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(ln);
        cbspecialite.setItems(data);
        cbspecialite.setEditable(true);

        cbspecialite.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                choixSpecialiteMedecin = (String) cbspecialite.getValue();
                System.err.println(choixSpecialiteMedecin);
            }
        });

        ServicesDao sevDao = new ServicesDao();
        List<String> ls = new ArrayList<>();
        ls = sevDao.DisplayAllServicesName();
        for (String spec : ls) {

            System.err.println(spec);

        }
        ObservableList<String> data3 = FXCollections.observableArrayList();
        data3.addAll(ls);
        cbservice.setItems(data3);
        cbservice.setEditable(false);

        cbservice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                ServicesDao sdao11 = new ServicesDao();
                choixServiceMedecin = sdao11.findServiceByName((String) cbservice.getValue());
                System.err.println(choixServiceMedecin);

            }
        });

        final TextField mail = new TextField();
        mail.setPromptText("Adresse mail ICI");

        mail.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.80F);
                if (isValidEmailAddress(mail.getText())) {
                    lllMail.setText("Adresse conforme");
                    lllMail.setTextFill(Color.web("#24F462"));
                    testMailValidation = 1;
                } else {
                    lllMail.setText("Fausse Adresse");
                    lllMail.setTextFill(Color.web("#D22525"));
                    testMailValidation = 0;
                }
                // System.out.println(isValidEmailAddress(mail.getText()));

            }

        });

        final TextField adresse = new TextField();
        adresse.setPromptText("Adresse ICI");
        adresse.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.99F);
            }

        });

        //conteneur 1
        VBox hb = new VBox();
        hb.getChildren().addAll(nomLabel, nom, pLabel, prenom, telLabel, pane, lll, cinLab, paneCin, lllCin);
        hb.setSpacing(10);
        //conteneur 2
        VBox hb1 = new VBox();
        hb1.getChildren().addAll(specialiteLabel, cbspecialite, serviceLabel, cbservice, mailLabel, mail, lllMail, adresseLabel, adresse);
        hb1.setSpacing(10);
        //boutton reset
        Button rzt = new Button("Reset");
        rzt.getStylesheets().add(styleback);
        rzt.setId("rich-blue");
        
        VBox vb2 = new VBox();
        vb2.getChildren().addAll(rzt);
        //ajout des hbox 
//modification compte medecin

        array = dao.rechercherToutCompteMedecin();
        data2 = FXCollections.observableArrayList();
        data2.addAll(array);
        tableView.setItems(data2);

        TableColumn id_medecin = new TableColumn();
        id_medecin.setText("ID medecin");
        id_medecin.setCellValueFactory(new PropertyValueFactory("id_medecin"));

        TableColumn nom_m = new TableColumn();
        nom_m.setText("Nom ");
        nom_m.setMinWidth(100);
        nom_m.setCellValueFactory(new PropertyValueFactory("nom_medecin"));

        TableColumn prenom_m = new TableColumn();
        prenom_m.setText("Prenom");
        prenom_m.setMinWidth(100);
        prenom_m.setCellValueFactory(new PropertyValueFactory("prenom_medecin"));

        TableColumn addr_m = new TableColumn();
        addr_m.setText("Adresse");
        addr_m.setMinWidth(100);
        addr_m.setCellValueFactory(new PropertyValueFactory("adresse_medecin"));

        TableColumn mail_m = new TableColumn();
        mail_m.setText("Mail");
        mail_m.setMinWidth(100);
        mail_m.setCellValueFactory(new PropertyValueFactory("mail_medecin"));

        TableColumn tel_m = new TableColumn();
        tel_m.setText("Telephone");
        tel_m.setMinWidth(100);
        tel_m.setCellValueFactory(new PropertyValueFactory("tel_medecin"));

        TableColumn specialite_m = new TableColumn();
        specialite_m.setText("Spécialite");
        specialite_m.setMinWidth(100);
        specialite_m.setCellValueFactory(new PropertyValueFactory("specialite_medecin"));

        TableColumn service_m = new TableColumn();
        service_m.setText("Service");
        service_m.setMinWidth(100);
        service_m.setCellValueFactory(new PropertyValueFactory("id_service"));

        TableColumn cin_m = new TableColumn();
        cin_m.setText("C.I.N");
        cin_m.setMinWidth(100);
        cin_m.setCellValueFactory(new PropertyValueFactory("cin_medecin"));

        tableView.getColumns().addAll(nom_m, prenom_m, tel_m, specialite_m, service_m, cin_m, mail_m, addr_m);

        //recherche 
        final TextField textBox = new TextField();
        final Button clearButton = new Button();
        clearButton.getStylesheets().add(styleback);
        clearButton.setId("rich-blue");
        
        final Button search = new Button("Rechercher");
        
       search.setCursor(Cursor.HAND);
      Image im_search = new Image(getClass().getResourceAsStream("/cssfiles/search.png"));     
      search.setGraphic(new ImageView(im_search));      
      search.setStyle("-fx-background-color: transparent;");
        
        final Button refresh = new Button("Refresh");      
        
      Image im_refresh = new Image(getClass().getResourceAsStream("/cssfiles/refresh.png"));     
      refresh.setGraphic(new ImageView(im_refresh));      
      refresh.setStyle("-fx-background-color: transparent;");
        
        
        
      final Button Imprimer = new Button("Imprimer la liste des medecins");
      Imprimer.setCursor(Cursor.HAND);
      Image im_imp = new Image(getClass().getResourceAsStream("/cssfiles/Printer.png"));     
      Imprimer.setGraphic(new ImageView(im_imp));      
      Imprimer.setStyle("-fx-background-color: transparent;");
        
        

        Imprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    MyConnection cnx = new MyConnection();
                    Connection conn = cnx.dbCnx();
                    // la connexion à la base de données
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("", conn);
                    Medecinjasper medecinjasper;
                    medecinjasper = new Medecinjasper("Cherry", conn, "Aperçu", params, null);
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    MedecinDAO medAo = new MedecinDAO();
                    array.removeAll(data2);
                    data2.clear();
                    array = medAo.rechercherToutCompteMedecin();
                    data2.addAll(array);
                    tableView.setItems(data2);
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                array.removeAll(data2);
                data2.clear();
                MedecinDAO dao1 = null;
                try {
                    dao1 = new MedecinDAO();
                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                }
                if ("".equals(choixRecherche)) {
                    System.out.println("pas de choix");
                } else if ("Nom".equals(choixRecherche)) {
                    array = dao1.rechercherCompteMedecinParNom(textBox.getText());
                    data2.addAll(array);
                    tableView.setItems(data2);
                } else if ("Prenom".equals(choixRecherche)) {
                    array.removeAll(data2);
                    data2.clear();
                    array = dao1.rechercherCompteMedecinParPrenom(textBox.getText());
                    data2.addAll(array);
                    tableView.setItems(data2);
                } else if ("Spécialité".equals(choixRecherche)) {
                    array.removeAll(data2);
                    data2.clear();
                    array = dao1.rechercherCompteMedecinParSpecialite(textBox.getText());
                    data2.addAll(array);
                    tableView.setItems(data2);

                } else if ("Téléphone".equals(choixRecherche)) {
                    array.removeAll(data2);
                    data2.clear();
                    array = dao1.rechercherCompteMedecinParNumTel(textBox.getText());
                    data2.addAll(array);
                    tableView.setItems(data2);

                } else {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    Button sor = new Button("OK");
                    sor.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            dialog.close();
                        }
                    });
                    //  dialog.initOwner(primaryStage);
                    dialog.setTitle("Choisissez une methode de recherche !");
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().addAll(new Text("ERREUR NUM-004 : Formulaire de recherche (champs obligatoires manquants) !"), sor);
                    Scene dialogScene = new Scene(dialogVbox, 400, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();
                    System.out.println("choisissez une methode de recherche");
                }
            }
        });

        final HBox hh = new HBox();
        hh.setSpacing(8);
        textBox.setPromptText("Search");
        clearButton.setVisible(false);
        Label lab2 = new Label("Rechercher par");
        //combo de choix de recherche
        final ComboBox<String> ch = new ComboBox<>();
        ch.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                choixRecherche = (String) ch.getValue();
                System.err.println(choixRecherche);
            }
        });
        ch.getItems().addAll(
                "Nom",
                "Prenom",
                "Spécialité",
                "Téléphone"
        );
        ch.setPromptText("Methode de recherche");

        hh.getChildren().addAll(textBox, clearButton, lab2, ch, search, refresh, Imprimer);

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textBox.setText("");
                textBox.requestFocus();
            }
        });
        textBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clearButton.setVisible(textBox.getText().length() != 0);
            }
        });
        //formulaire modification
        VBox formulaireModif = new VBox();
        formulaireModif.setSpacing(5);
        formulaireModif.setPadding(new Insets(5, 20, 10, 20));
        VBox formulaireModif1 = new VBox();
        formulaireModif1.setSpacing(5);
        formulaireModif1.setPadding(new Insets(5, 20, 10, 20));

        //labels formulaire d'ajout
        Label idLabelModif = new Label("ID");
        Label nomLabelModif = new Label("Nom");
        Label pLabelModif = new Label("Prenom");
        Label telLabelModif = new Label("N° Tel");
        Label cinLabModif = new Label("C.I.N");
        Label specialiteLabelModif = new Label("Spécialité Medecin");
        Label serviceLabelModif = new Label("Numero Service");
        Label NomserviceLabelModif = new Label("Nom Service actuel");
        Label NomserviceLabelModif1 = new Label("Nouveau service");
        Label mailLabelModif = new Label("Adresse mail");
        Label adresseLabelModif = new Label("Adresse");
        final Label SendE = new Label("Envoyer un email au medecin : ");
        //champs de text formulaire d'ajout
        final TextField idModif = new TextField();
        final TextField nomModif = new TextField();
        final TextField prenomModif = new TextField();
        final TextField telModif = new TextField();
        final TextField cinModif = new TextField();
        final TextField spModif = new TextField();
        final TextField servModif = new TextField();
        final TextField mailModif = new TextField();
        final TextField addModif = new TextField();
        final TextField NomservModif = new TextField();

        NomservModif.setEditable(false);
        cbservice1.setItems(data3);
        cbservice1.setEditable(false);
        cbservice1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {

                choixServiceMedecinMod = (String) cbservice1.getValue();
                System.err.println(choixServiceMedecinMod);
                ServicesDao serda;
                serda = new ServicesDao();
                IDnvCH = serda.findServiceByName(choixServiceMedecinMod);
                System.err.println(IDnvCH);

            }
        });
        Button mo = new Button("Modifier Medecin ");
        mo.getStylesheets().add(styleback);
        mo.setId("rich-blue");
        
        Button SEND_email = new Button("Envoyer un E-Mail au medecin");
        
      Image im_mail = new Image(getClass().getResourceAsStream("/cssfiles/Mail.png"));     
      SEND_email.setGraphic(new ImageView(im_mail));      
      SEND_email.setStyle("-fx-background-color: transparent;");
        
        
        Button supp_med = new Button("Supprimer Medecin ");
        supp_med.getStylesheets().add(styleback);
        supp_med.setId("rich-blue"); 
        
        formulaireModif.getChildren().addAll(nomLabelModif, nomModif, pLabelModif, prenomModif, telLabelModif, telModif, cinLabModif, cinModif, specialiteLabelModif, spModif, serviceLabelModif, servModif);
        formulaireModif1.getChildren().addAll(NomserviceLabelModif, NomservModif, NomserviceLabelModif1, cbservice1, mailLabelModif, mailModif, adresseLabelModif, addModif, idLabelModif, idModif, mo, supp_med, SEND_email);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                Medecin med_g = (Medecin) tableView.getSelectionModel().getSelectedItem();
               if(med_g!=null){
                idModif.setText(String.valueOf(med_g.getId_medecin()));
                nomModif.setText(med_g.getNom_medecin());
                prenomModif.setText(med_g.getPrenom_medecin());
                telModif.setText(med_g.getTel_medecin());
                cinModif.setText(med_g.getCin_medecin());
                spModif.setText(med_g.getSpecialite_medecin());
                servModif.setText(String.valueOf(med_g.getId_service()));
                mailModif.setText(med_g.getMail_medecin());
                emailExpo = mailModif.getText();
                addModif.setText(med_g.getAdresse_medecin());
                ServicesDao sdao = new ServicesDao();
                NomservModif.setText(sdao.findById(Integer.valueOf(servModif.getText())).getNomService());
                }else{
                    System.out.println("Objet medecin null");
                }
            }
        });
        SEND_email.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
//                dialog.initOwner(primaryStage);
                dialog.setTitle("EMAIL");
                TextField txmail = new ComboBoxListViewSkin.FakeFocusTextField();
                txmail.setText(emailExpo);

                final HTMLEditor hedit = new HTMLEditor();
                Button sor = new Button("ENVOYER");
                sor.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String corps = hedit.getHtmlText();
                        SendEmail sendEmail = new SendEmail();
                        sendEmail.send(emailExpo, corps);
                    }
                });
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().addAll(txmail, hedit, sor);
                Scene dialogScene = new Scene(dialogVbox, 500, 500);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });

        supp_med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                Button sor = new Button("Oui");
                Button annu = new Button("Non");
                annu.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        dialog.close();
                    }
                });
                sor.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            MedecinDAO dao3 = new MedecinDAO();
                            dao3.supprimerCompteMedecin(Integer.valueOf(idModif.getText()));
                            array.removeAll(data2);
                            data2.clear();
                            array = dao3.rechercherToutCompteMedecin();
                            data2.addAll(array);
                            tableView.setItems(data2);

                        } catch (SQLException ex) {
                            Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dialog.close();
                    }
                });
                // dialog.initOwner(primaryStage);
                dialog.setTitle("Verification de suppression");
                VBox dialogVbox = new VBox(20);
                HBox hb = new HBox();
                hb.getChildren().addAll(sor, annu);
                dialogVbox.getChildren().addAll(new Text("Attention vous allez supprimer un compte medecin !\n êtes-vous sûr ?"), hb);
                Scene dialogScene = new Scene(dialogVbox, 400, 100);
                dialog.setScene(dialogScene);
                dialog.show();

            }
        });
        mo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    MedecinDAO dao3 = new MedecinDAO();
                    if (IDnvCH == 0) {
                        Medecin medecin_mo = new Medecin(Integer.valueOf(idModif.getText()), nomModif.getText(), prenomModif.getText(), addModif.getText(), mailModif.getText(), telModif.getText(), spModif.getText(), Integer.valueOf(servModif.getText()), cinModif.getText());

                        dao3.modifierCompteMedecin(medecin_mo);
                        array.removeAll(data2);
                        data2.clear();
                        array = dao3.rechercherToutCompteMedecin();
                        data2.addAll(array);
                        tableView.setItems(data2);
                    } else {
                        Medecin medecin_mo = new Medecin(Integer.valueOf(idModif.getText()), nomModif.getText(), prenomModif.getText(), addModif.getText(), mailModif.getText(), telModif.getText(), spModif.getText(), IDnvCH, cinModif.getText());
                        dao3.modifierCompteMedecin(medecin_mo);
                        array.removeAll(data2);
                        data2.clear();
                        array = dao3.rechercherToutCompteMedecin();
                        data2.addAll(array);
                        tableView.setItems(data2);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        HBox t = new HBox();
        t.getChildren().addAll(tableView, formulaireModif, formulaireModif1);
        VBox vbmodif = new VBox();
        vbmodif.setSpacing(10);
        vbmodif.setPadding(new Insets(0, 20, 10, 20));
        vbmodif.getChildren().addAll(new Label(""),hh, t);

// fin modification        
        Button btnAdd = new Button("Ajouter compte");
        btnAdd.getStylesheets().add(styleback);
        btnAdd.setId("rich-blue");
        
        btnAdd.setCursor(Cursor.HAND);
        formulaireAj.getChildren().addAll(hb, hb1, vb2, btnAdd, p2);
      /*
        TitledPane t1 = new TitledPane("Ajouter Compte Medecin", formulaireAj);
        TitledPane t2 = new TitledPane("Rechercher / Modifier / Supprimer Compte Medecin", vbmodif);

        Accordion accordion = new Accordion();
        accordion.getPanes().addAll(t1, t2);
       
     */   
        Button Archiver = new Button("Archiver sur DROPBOX");
        Archiver.getStylesheets().add(styleback);
        Archiver.setId("rich-blue");

        Button testNav = new Button("Consulter l'archive sur DROPBOX");
        testNav.getStylesheets().add(styleback);
        testNav.setId("rich-blue");
        testNav.setCursor(Cursor.HAND);
        
        testNav.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                String DEFAULT_URL = "www.dropbox.com";
                WebView webView = new WebView();

                final WebEngine webEngine = webView.getEngine();
                webEngine.load(DEFAULT_URL);

                final TextField locationField = new TextField(DEFAULT_URL);
                webEngine.locationProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        locationField.setText(newValue);
                    }
                });
                EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        webEngine.load(locationField.getText().startsWith("http://")
                                ? locationField.getText()
                                : "http://" + locationField.getText());
                    }
                };
                locationField.setOnAction(goAction);

                Button goButton = new Button("Go");
                goButton.setDefaultButton(true);
                goButton.setOnAction(goAction);

                // Layout logic
                HBox hBox = new HBox(5);
                hBox.getChildren().setAll(locationField, goButton);
                HBox.setHgrow(locationField, Priority.ALWAYS);

                VBox vBox = new VBox(5);
                vBox.getChildren().setAll(hBox, webView);
                VBox.setVgrow(webView, Priority.ALWAYS);
                final Stage nav = new Stage();
                nav.initModality(Modality.APPLICATION_MODAL);
                // nav.initOwner(primaryStage);

                Group root1 = new Group();
                root1.getChildren().add(vBox);
                // primaryStage.setScene(new Scene(root1));

                Scene dialogScene = new Scene(root1, 600, 800);
                nav.setScene(dialogScene);
                nav.show();
            }
        });
        
        Archiver.setCursor(Cursor.HAND);
        Archiver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                Button sor = new Button("Commencer");
                sor.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        dialog.close();
                    }
                });
                //  dialog.initOwner(primaryStage);
                dialog.setTitle("Sauvgarde");
                VBox dialogVbox = new VBox(20);
                JavaDropbox javaDropbox = new JavaDropbox();
                TextField textfie = new ComboBoxListViewSkin.FakeFocusTextField();
                try {
                    javaDropbox.authDropbox(DROP_BOX_APP_KEY, DROP_BOX_APP_SECRET);
                    textfie.setText(JavaDropbox.lien);

                    System.out.println("Dropbox Size: " + javaDropbox.getDropboxSize()
                            + " GB");
                    javaDropbox.createFolder("testDropbox");
                    javaDropbox.listDropboxFolders("/");
                    File[] files = new File("E://test//").listFiles();
                    for (File file : files) {
                        System.out.println(file.getName());
                        System.out.println(file.toURI());
                        String te = file.toURI().toString();
                        String et = te.substring(6);
                        System.out.println(et);
                        javaDropbox.uploadToDropbox(et);
                    }
                } catch (IOException | DbxException ex) {
                    Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                }

                dialogVbox.getChildren().addAll(new Text(""), sor);
                Scene dialogScene = new Scene(dialogVbox, 400, 100);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });

        Label archive_lab=new Label();
        archive_lab.setText("Archivage des documents des patients ");
        archive_lab.setStyle("-fx-font-size: 24;");
        
        TextField cle_drop=new TextField();
        
        
       //boutton de manip
       
        //conteneur menu dropbox
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(5, 20, 10, 20));
        vbButtons.getChildren().addAll(new Label(""),archive_lab,Archiver,cle_drop,testNav);
     
        //handler du boutton reset
        rzt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                nom.clear();
                tel.clear();
                adresse.clear();
                mail.clear();
                prenom.clear();
                cin.clear();
            }
        });

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ("".equals(nom.getText()) | "".equals(prenom.getText()) | "".equals(choixSpecialiteMedecin) | "".equals(cin.getText()) | testTelValidation == 0 | testCinValidation == 0 | testMailValidation == 0) {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    Button sor = new Button("OK");
                    sor.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            dialog.close();
                        }
                    });
                    //   dialog.initOwner(primaryStage);
                    dialog.setTitle("Champs Obligatoires manquant");
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().addAll(new Text("ERREUR NUM-003 : Formulaire d'ajout (champs obligatoires manquants) !"), sor);
                    Scene dialogScene = new Scene(dialogVbox, 400, 100);
                    dialog.setScene(dialogScene);
                    dialog.show();

                } else {

                    Medecin medecin = new Medecin(nom.getText(), prenom.getText(), adresse.getText(), mail.getText(), tel.getText(), choixSpecialiteMedecin, Integer.valueOf(choixServiceMedecin), cin.getText());
                    try {
                        MedecinDAO medDao = new MedecinDAO();

                        int res = medDao.ajouterCompteMedecin(medecin);
                        SmsSender ss = new SmsSender();
                        ss.testSMS("216" + medecin.getTel_medecin(), "Bienvenu Mr/Mme " + medecin.getNom_medecin());
                        //test sur la valeur du retour de la fonction d'ajout de medecin
                        if (res == 0) {
                            System.err.println("Medecin existant");
                            //popup
                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            // dialog.initOwner(primaryStage);
                            dialog.setTitle("MEDECIN EXISTANT");
                            Button sor = new Button("OK");
                            sor.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    dialog.close();
                                }
                            });
                            VBox dialogVbox = new VBox(20);
                            dialogVbox.getChildren().addAll(new Text("ERREUR NUM-001 : MEDECIN DEJA EXISTANT !"), sor);
                            Scene dialogScene = new Scene(dialogVbox, 250, 250);
                            dialog.setScene(dialogScene);
                            dialog.show();
                        }
                        if (res == 2) {
                            System.err.println("ERREUR NUM-000 : erreur d'ajout de compte medecin");
                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            // dialog.initOwner(primaryStage);
                            dialog.setTitle("ERREUR NUM-001 : erreur générale");
                            VBox dialogVbox = new VBox(20);
                            Button sor = new Button("OK");
                            sor.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    dialog.close();
                                }
                            });
                            dialogVbox.getChildren().addAll(new Text("ERREUR NUM-002 : Erreur d'ajout de compte medecin. consultez votre assitance !"), sor);
                            Scene dialogScene = new Scene(dialogVbox, 250, 250);
                            dialog.setScene(dialogScene);
                            dialog.show();
                        }
                        if (res == 1) {
                            p2.setProgress(1.00F);
                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            //      dialog.initOwner(primaryStage);
                            dialog.setTitle("MEDECIN AJOUTE AVEC SUCCE");
                            VBox dialogVbox = new VBox(20);
                            Button sor = new Button("OK");
                            sor.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    dialog.close();
                                }
                            });
                            dialogVbox.getChildren().addAll(new Text("MEDECIN AJOUTE AVEC SUCCE !"), sor);
                            Scene dialogScene = new Scene(dialogVbox, 250, 250);
                            dialog.setScene(dialogScene);
                            dialog.show();
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(InterfaceMedecin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        //interface borderpane
        //borderpane boutons+interface à droite
      BorderPane bord_med=new BorderPane();
  
        
       //bouton ajout  
      Button ajout_med=new Button();
      ajout_med.setCursor(Cursor.HAND);
      Image im_aj = new Image(getClass().getResourceAsStream("/cssfiles/ajout_med.png"));     
      ajout_med.setGraphic(new ImageView(im_aj));      
      ajout_med.setStyle("-fx-background-color: transparent;");
       Tooltip toolB_aj = new Tooltip("Ajouter Compte Médecin");
        toolB_aj.setContentDisplay(ContentDisplay.BOTTOM);
        toolB_aj.setStyle("");
        ajout_med.setTooltip(toolB_aj);
      
        ajout_med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_med.setCenter(formulaireAj);
            }
        });
      
      //bouton recherche/modif
      Button modif_med=new Button();
      modif_med.setCursor(Cursor.HAND);
      Image im_modif = new Image(getClass().getResourceAsStream("/cssfiles/modif_med.png"));     
      modif_med.setGraphic(new ImageView(im_modif));      
      modif_med.setStyle("-fx-background-color: transparent;");
      Tooltip toolB = new Tooltip("Modifier et supprimer Compte médecin");
      toolB.setContentDisplay(ContentDisplay.BOTTOM);
      toolB.setStyle("");
      modif_med.setTooltip(toolB);
      
      modif_med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_med.setCenter(vbmodif);
            }
        });
      
      //bouton dropbox
      Button drop_med=new Button();
      drop_med.setCursor(Cursor.HAND);
      Image im_drop = new Image(getClass().getResourceAsStream("/cssfiles/dp.png"));     
      drop_med.setGraphic(new ImageView(im_drop));      
      drop_med.setStyle("-fx-background-color: transparent;");
      Tooltip toolB_ar = new Tooltip("Arichiver dans dropbox");
      toolB_ar.setContentDisplay(ContentDisplay.BOTTOM);
      toolB_ar.setStyle("");
      drop_med.setTooltip(toolB_ar);
      
      drop_med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_med.setCenter(vbButtons);
            }
            
        });
      
      
      //regroupement des boutons
      VBox v_bouton=new VBox();
      v_bouton.setSpacing(20);
      v_bouton.getChildren().addAll(ajout_med,modif_med,drop_med);     
      bord_med.setLeft(v_bouton);
      bord_med.setCenter(formulaireAj);
      
       return bord_med;
    }
    
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    
    
    
    
    
    
    
    

}
