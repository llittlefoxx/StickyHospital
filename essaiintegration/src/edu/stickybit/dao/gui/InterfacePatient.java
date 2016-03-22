/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.PatientDAO;
import static edu.stickybit.dao.gui.InterfaceMedecinFinal.isValidEmailAddress;
import edu.stickybit.entity.Patient;
import edu.stickybit.general.SmsSender;
import java.awt.Desktop;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level; 
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;

/**
 *
 * @author Asma Boussabat
 */
public class InterfacePatient {

    public InterfacePatient() {
    }
    private DatePicker DatePicker;
    private Desktop desktop = Desktop.getDesktop();
    Button bb2 = new Button("supprimer patient");
    ObservableList<edu.stickybit.entity.Patient> data = null;
    PatientDAO pdao = new PatientDAO();
    List<edu.stickybit.entity.Patient> array = new ArrayList<edu.stickybit.entity.Patient>();
    List<edu.stickybit.entity.Patient> ppp = new ArrayList<>();
    final Label lll = new Label();
    TextInputValidatorPane<TextField> pane = new TextInputValidatorPane<TextField>();
    TextInputValidatorPane<TextField> paneCin = new TextInputValidatorPane<TextField>();
    public int testTelValidation = 0;
    public int testMailValidation = 0;
    public int testCinValidation = 0;
    final Label lllMail = new Label();
    final Label lllCin = new Label();

    String et = "";
    String te = "";
    String sexe_p = "";

    public BorderPane RecupererInterface() {

        String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
        /*
        Button b = new Button();
        b.getStylesheets().add(styleback);
        b.setId("rich-blue");
*/
        StackPane root = new StackPane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Stage primaryStage = new Stage();

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));

        //formulaire 
        HBox form = new HBox();
        form.setSpacing(110);
        form.setPadding(new Insets(5, 20, 10, 20));

        // labels
        Label nom = new Label("Nom");
        Label prenom = new Label("Prenom");
        Label cin = new Label("CIN");
        Label adresse = new Label("Adresse");
        Label tel = new Label("Telephone");
        Label mail = new Label("Adresse mail");
        Label url = new Label(" Photo");
        Label sexe = new Label("Sexe");
        Label dn = new Label("Date de naissance");

        //textfields
        TextField nomf = new TextField();
        TextField prenomf = new TextField();
        TextField cinf = new TextField();

        paneCin.setContent(cinf);

        paneCin.setValidator(new Validator<TextField>() {
            public ValidationResult validate(TextField cin) {
                try {
                    String text = cinf.getText();
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

        TextField adressef = new TextField();
        TextField telf = new TextField();

        String validatorCss = InterfaceMedecin.class.getResource("/cssfiles/Validators.css").toExternalForm();
        pane.setContent(telf);

        telf.setPromptText("Numéro de téléphone ICI");
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

        TextField mailf = new TextField();
        //  TextField sexf = new TextField();
        mailf.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {

                if (isValidEmailAddress(mailf.getText())) {
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

        ToggleGroup tg = new ToggleGroup();
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        RadioButton b1 = new RadioButton("Femme");
        b1.setToggleGroup(tg);
        b1.setSelected(true);
        RadioButton b2 = new RadioButton("Homme");
        b2.setToggleGroup(tg);
        vbox.getChildren().addAll(b1, b2);

        //date 
        DatePicker = new DatePicker(LocalDate.of(1998, 10, 8));
        DatePicker.setShowWeekNumbers(true);

        //image du patient essai pour recuperer l url 
        final Button openB = new Button("Choose a Picture");
        openB.getStylesheets().add(styleback);
        openB.setId("rich-blue");
        openB.setCursor(Cursor.HAND);

        openB.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {

                        JFileChooser chooser = new JFileChooser();
                        chooser.setCurrentDirectory(new java.io.File("."));
                        chooser.setDialogTitle("choosertitle");
                        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                        chooser.setAcceptAllFileFilterUsed(false);
                        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
                            System.out.println("getSelectedFile()uri : " + chooser.getSelectedFile().toURI());

                            String te = chooser.getSelectedFile().toURI().toString();
                            et = te.substring(6);

                        }
                    }
                });

        final Button B = new Button("ajouter");
        B.getStylesheets().add(styleback);
        B.setId("rich-blue");
        B.setCursor(Cursor.HAND);

        B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    String naissance = DatePicker.getValue().toString();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date;
                    date = sdf1.parse(naissance);
                    if (b1.isSelected()) {
                        sexe_p = "femme";
                    } else {
                        sexe_p = "homme";
                    }
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    Patient patient = new Patient(nomf.getText(), prenomf.getText(), Integer.parseInt(cinf.getText()), adressef.getText(), telf.getText(), mailf.getText(), et, sexe_p, sqlDate);
                    PatientDAO pdao = new PatientDAO();
                    int xx = 0;
                    if (testMailValidation == 1 | testTelValidation == 1 | testCinValidation == 1) {
                        xx = pdao.insertPatient(patient);
                        SmsSender ss = new SmsSender();
                        ss.testSMS("216" + patient.getTel_patient(), "Bienvenu Mr/Mme " + patient.getNom_patient());

                        if (xx == 1) {
                            final Stage dialog = new Stage();

                            dialog.initModality(Modality.APPLICATION_MODAL);
                            Button sor = new Button("OK");
                            sor.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                    dialog.close();
                                }
                            });
                            dialog.initOwner(primaryStage);
                            dialog.setTitle("Ajout du patient");
                            dialog.initStyle(StageStyle.UNDECORATED);

                            VBox dialogVbox = new VBox(20);
                            dialogVbox.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                            dialogVbox.setAlignment(Pos.CENTER);

                            Text t = new Text("Patient Ajouté");
                            t.setFill(Color.WHITE);
                            t.setFont(Font.font(java.awt.Font.SERIF, 25));
                            dialogVbox.getChildren().addAll(t, sor);
                            Scene dialogScene = new Scene(dialogVbox, 250, 150);

                            dialog.setScene(dialogScene);
                            dialog.show();
                        } else {

                            System.out.println("NON");

                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(InterfacePatient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // bouton reset pour vider les champs
        final Button R = new Button("Reset");
        R.getStylesheets().add(styleback);
        R.setId("rich-blue");
        R.setCursor(Cursor.HAND);

        R.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nomf.setText("");
                prenomf.setText("");
                cinf.setText("");
                adressef.setText("");
                telf.setText("");
                mailf.setText("");

            }
        });

        VBox hb = new VBox();
        hb.getChildren().addAll(nom, nomf, prenom, prenomf, cin, paneCin, lllCin, adresse, adressef, tel, pane, lll, mail, mailf, lllMail);
        hb.setSpacing(15);

        VBox hb1 = new VBox();
        hb1.getChildren().addAll(sexe, vbox, dn, DatePicker, openB, B, R);
        hb1.setSpacing(15);

        VBox x = new VBox();
        x.getChildren().add(new Label(""));
        form.getChildren().addAll(x, hb, hb1);

        //partie 2 de laccordion (recherche/modification/suppression du patient)
        TableView tableView = new TableView();
        tableView.setFixedCellSize(30);
        tableView.setMaxWidth(800);
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

        tableView.getColumns().addAll(n, p, c, a, t, ma);

        //bouton recherche et bouton clear de la zone de recherche
        TextField Boxs = new TextField();
        Button bserach = new Button("Recherche");
        bserach.setCursor(Cursor.HAND);
        Image im_search = new Image(getClass().getResourceAsStream("/cssfiles/search.png"));
        bserach.setGraphic(new ImageView(im_search));
        bserach.setStyle("-fx-background-color: transparent;");

        Button clearB = new Button("clear");
        clearB.setCursor(Cursor.HAND);
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

        HBox h = new HBox();
        h.setSpacing(15);

        // formulaire de modification du patient
        Label texte = new Label("rechercher patient : ");

        Label idp = new Label(" id ");
        TextField idpp = new TextField();

        Label lab = new Label(" nom: ");
        TextField nomlab = new TextField();

        Label prenlab = new Label(" prenom: ");
        TextField pren = new TextField();

        Label cinx = new Label(" CIN: ");
        TextField cinxx = new TextField();

        Label adrx = new Label(" Adresse: ");
        TextField adrxx = new TextField();

        Label telt = new Label(" telephone : ");
        TextField teltt = new TextField();

        Label maillab = new Label(" mail: ");
        TextField maillabb = new TextField();
        Label vide = new Label("  ");

        //bouton mise à jour pour la modification du patient
        Button bb = new Button("Mettre à jour");
        bb.getStylesheets().add(styleback);
        bb.setId("rich-blue");
        bb.setCursor(Cursor.HAND);

        bb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PatientDAO d_mo = new PatientDAO();
                edu.stickybit.entity.Patient p_mo = new edu.stickybit.entity.Patient(Integer.valueOf(idpp.getText()), nomlab.getText(), pren.getText(), Integer.valueOf(cinxx.getText()), adrxx.getText(), teltt.getText(), maillabb.getText());
                int x = 0;
                x = d_mo.updatePatientG(p_mo);
                if (x == 1) {
                    final Stage dialog = new Stage();

                    dialog.initModality(Modality.APPLICATION_MODAL);
                    Button sor = new Button("OK");
                    sor.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            dialog.close();
                        }
                    });
                    dialog.initOwner(primaryStage);
                    dialog.initStyle(StageStyle.UNDECORATED);

                    VBox dialogVbox = new VBox(20);
                    dialogVbox.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                    dialogVbox.setAlignment(Pos.CENTER);

                    Text t = new Text("Mise à jour avec succès");
                    t.setFill(Color.WHITE);
                    t.setFont(Font.font(java.awt.Font.SERIF, 25));
                    dialogVbox.getChildren().addAll(t, sor);
                    Scene dialogScene = new Scene(dialogVbox, 250, 150);

                    dialog.setScene(dialogScene);
                    dialog.show();

                    array.removeAll(data);
                    data.clear();
                    array = d_mo.DisplayAllPatient();
                    data.addAll(array);
                    tableView.setItems(data);
                }
            }
        });

        //bouton supprimer patient
        Button suppb = new Button("supprimer patient");
        suppb.getStylesheets().add(styleback);
        suppb.setId("rich-blue");
        suppb.setCursor(Cursor.HAND);

        suppb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PatientDAO pdn = new PatientDAO();
                int x_supp = 0;
                x_supp = pdn.deletePatient(Integer.valueOf(idpp.getText()));

                if (x_supp == 0) {
                    final Stage dialog = new Stage();

                    dialog.initModality(Modality.APPLICATION_MODAL);
                    Button sor = new Button("OK");
                    sor.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            PatientDAO pdn = new PatientDAO();

                            array.removeAll(data);
                            data.clear();
                            array = pdn.DisplayAllPatient();
                            data.addAll(array);
                            tableView.setItems(data);
                            dialog.close();
                        }
                    });
                    dialog.initOwner(primaryStage);
                    dialog.setTitle("Suppression du patient");
                    dialog.initStyle(StageStyle.UNDECORATED);

                    VBox dialogVbox = new VBox(20);
                    dialogVbox.setBackground(new Background(new BackgroundFill(Color.DARKGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                    dialogVbox.setAlignment(Pos.CENTER);

                    Text t = new Text("Patient Supprimé");
                    t.setFill(Color.WHITE);
                    t.setFont(Font.font(java.awt.Font.SERIF, 25));
                    dialogVbox.getChildren().addAll(t, sor);
                    Scene dialogScene = new Scene(dialogVbox, 250, 150);

                    dialog.setScene(dialogScene);
                    dialog.show();

                }

            }
        });

        //bouton refresh
        Button brefresh = new Button();
        Image im_refresh = new Image(getClass().getResourceAsStream("/cssfiles/refresh.png"));
        brefresh.setGraphic(new ImageView(im_refresh));
        brefresh.setStyle("-fx-background-color: transparent;");
        brefresh.setCursor(Cursor.HAND);

        brefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PatientDAO pdn_ref = new PatientDAO();

                array.removeAll(data);
                data.clear();
                array = pdn_ref.DisplayAllPatient();
                data.addAll(array);
                tableView.setItems(data);
            }
        });

        //bouton reset pour vider les champs
        Button breset = new Button("Reset");
        breset.getStylesheets().add(styleback);
        breset.setId("rich-blue");
        breset.setCursor(Cursor.HAND);

        breset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                idpp.setText("");
                nomlab.setText("");
                pren.setText("");
                cinxx.setText("");
                adrxx.setText("");
                teltt.setText("");
                maillabb.setText("");
            }
        });

        //positionnement des zones du formulaire de modification
        HBox ver = new HBox();
        VBox hbf = new VBox();
        VBox hbg = new VBox();

        hbf.setSpacing(10);
        hbg.setSpacing(10);
        ver.setSpacing(10);

        hbg.getChildren().addAll(idp, idpp, lab, nomlab, prenlab, pren, cinx, cinxx, telt, teltt);
        hbf.getChildren().addAll(adrx, adrxx, maillab, maillabb, bb, breset);

        ver.getChildren().addAll(hbg, hbf);

        //remplir le formulaire de modification apres la recherche et la selection du patient à modifier)
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                edu.stickybit.entity.Patient np = (edu.stickybit.entity.Patient) tableView.getSelectionModel().getSelectedItem();
//                System.out.println(np.getId_patient());
                if (np!=null){
                System.out.println(np.getId_patient());
                idpp.setText(String.valueOf(np.getId_patient()));
                nomlab.setText(String.valueOf(np.getNom_patient()));
                pren.setText(String.valueOf(np.getPrenom_patient()));
                adrxx.setText(String.valueOf(np.getAdresse()));
                cinxx.setText(String.valueOf(np.getCin_patient()));
                teltt.setText(String.valueOf(np.getTel_patient()));
                maillabb.setText(String.valueOf(np.getMail_patient()));
                }else{
                    System.out.println("objet nul ! ");
                }
                }
            
        });

        HBox hbox=new HBox(new Label(""));
        VBox vbox2=new VBox();
        vbox2.setSpacing(20);
        HBox pos=new HBox(new Label(""));
        
        h.getChildren().addAll(texte, Boxs, clearB, bserach, suppb, brefresh);
        
        vbox2.getChildren().addAll(hbox,h,pos);
        vbox2.setSpacing(20);
        
         HBox pos2=new HBox(new Label(""));
         pos2.setSpacing(50);
        //positionnement de la 2éme partie de l'accordion  (tableau+les boutons+formulaire de modification)   
        HBox main = new HBox();
        main.setSpacing(100);
        main.getChildren().addAll(pos2,tableView, ver);

        BorderPane bord = new BorderPane();
        bord.setTop(vbox2);

        bord.setCenter(main);

        BorderPane border_af = new BorderPane();

        //bouton menu ajout
        Button ajout_p = new Button();
        ajout_p.setCursor(Cursor.HAND);
        Image im_aj = new Image(getClass().getResourceAsStream("/cssfiles/ajout_p.png"));
        ajout_p.setGraphic(new ImageView(im_aj));
        ajout_p.setStyle("-fx-background-color: transparent;");
        Tooltip toolB = new Tooltip("Ajouter Compte Patient");
        toolB.setContentDisplay(ContentDisplay.BOTTOM);
        toolB.setStyle("");
        ajout_p.setTooltip(toolB);

        ajout_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                border_af.setCenter(form);
            }
        });

        //bouton menu recherche/modif
        Button modif_p = new Button();
        modif_p.setCursor(Cursor.HAND);
        Image im_modif = new Image(getClass().getResourceAsStream("/cssfiles/supp_p.png"));
        modif_p.setGraphic(new ImageView(im_modif));
        modif_p.setStyle("-fx-background-color: transparent;");
        Tooltip toolB_p = new Tooltip("Modifier et supprimer Patient");
        toolB_p.setContentDisplay(ContentDisplay.BOTTOM);
        toolB_p.setStyle("");
        modif_p.setTooltip(toolB);

        modif_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                border_af.setCenter(bord);
            }
        });

        VBox v = new VBox();
        v.getChildren().addAll(ajout_p, modif_p);

        border_af.setLeft(v);
        border_af.setCenter(form);

        root.getChildren().add(border_af);
        return border_af;
    }

}
