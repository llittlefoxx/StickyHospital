/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.MedicamentDAO;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tlili Mohamed Ali 
 */
public class InterfaceMedicament {

    public InterfaceMedicament() {
    }

    ProgressIndicator p2 = new ProgressIndicator();
    ObservableList<Medicament> data = null;
    MedicamentDAO dao = new MedicamentDAO();
    List<Medicament> array = new ArrayList<Medicament>();
    ObservableList<Medicament> data3 = null;
    List<Medicament> array5 = new ArrayList<Medicament>();
    ObservableList<Medicament> data5 = null;
    List<Medicament> array3 = new ArrayList<Medicament>();
    ObservableList<Medicament> data4 = null;
    List<Medicament> array4 = new ArrayList<Medicament>();
    int testTelValidation = 0;
    TextInputValidatorPane<TextField> pane = new TextInputValidatorPane<>();
    TextInputValidatorPane<TextField> pane2 = new TextInputValidatorPane<>();
    TextInputValidatorPane<TextField> pane3 = new TextInputValidatorPane<>();
    TextInputValidatorPane<TextField> pane4 = new TextInputValidatorPane<>();
    TextInputValidatorPane<TextField> pane5 = new TextInputValidatorPane<>();
    Label lll = new Label();
    Label labelquan = new Label();
    Label labeleffet = new Label();
    Label labelmin = new Label();
    Label labelprix = new Label();

    public void newMessage() {

        final Stage newConnDialog = new Stage();
        newConnDialog.initStyle(StageStyle.UNDECORATED);
        newConnDialog.initModality(Modality.WINDOW_MODAL);

        // Set pisition
        newConnDialog.setX(1050); //secondStage.setX(primaryStage.getX() + 250);
        newConnDialog.setY(150);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // text
        Text productName = new Text("Test");
        productName.setFont(Font.font("Verdana", 12));
        grid.add(productName, 0, 2);

        // Configure dialog size and background color
        Scene aboutDialogScene = new Scene(grid, 200, 100, Color.WHITESMOKE);
        newConnDialog.setScene(aboutDialogScene);
        newConnDialog.show();
    }

    public BorderPane RecupererAccordion() {
        
        String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
        Label ajout_m=new Label("Modifier un médicament :");
        ajout_m.setStyle("-fx-font-size: 24;");
        
        final TableView tableView = new TableView();
        
        tableView.setFixedCellSize(20);
        tableView.setMaxWidth(600);
        tableView.setMaxHeight(400);
      
        p2.setPrefSize(50, 50);
        //interface dajout
        Button btn = new Button();
        btn.setText("Ajouter");
        
      btn.getStylesheets().add(styleback);
      btn.setId("rich-blue");
      btn.setCursor(Cursor.HAND);

        VBox vb = new VBox();
        vb.setSpacing(10);
        VBox vb2 = new VBox();
        vb2.setSpacing(10);

        Label Libelle = new Label("Libelle:");
        final TextField Libellef = new TextField();
        Libellef.setMinWidth(200);

        Label Quantite = new Label("Quantite:");
        final TextField Quantitef = new TextField();
        Quantitef.setMinWidth(200);
        Label Effet = new Label("Effet:");
        final TextField Effetf = new TextField();
        Effetf.setMinWidth(200);
        Label Prix = new Label("Prix:");
        final TextField prixf = new TextField();
        prixf.setMinWidth(200);
        Label min = new Label("Quantite minimale:");
        final TextField minf = new TextField();
        minf.setMinWidth(200);

        String validatorCss = InterfaceMedicament.class.getResource("/cssfiles/Validators.css").toExternalForm();

        Libellef.setMaxHeight(TextField.USE_PREF_SIZE);
        pane.setContent(Libellef);
        pane.setValidator(new Validator<TextField>() {

            public ValidationResult validate(TextField tel) {
                try {
                    String text = tel.getText();

                    if (!text.isEmpty()) {
                        if (text.contains("0") | text.contains("1") | text.contains("2")
                                | text.contains("3") | text.contains("4") | text.contains("5")
                                | text.contains("6") | text.contains("7") | text.contains("8") | text.contains("9")) {
                            lll.setText("libelle invalide");
                            lll.setTextFill(Color.web("#FF0000"));
                            testTelValidation = 0;
                            return new ValidationResult("Should be > 8", ValidationResult.Type.WARNING);

                        } else {
                            lll.setText("Libelle conforme");
                            lll.setTextFill(Color.web("#24F462"));
                            testTelValidation = 1;
                        }
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

        Quantitef.setMaxHeight(TextField.USE_PREF_SIZE);
        pane2.setContent(Quantitef);
        pane2.setValidator(new Validator<TextField>() {
            public ValidationResult validate(TextField tel) {
                try {
                    String text1 = tel.getText();

                    if (!text1.isEmpty()) {
                        if (text1.contains("0") | text1.contains("1") | text1.contains("2")
                                | text1.contains("3") | text1.contains("4") | text1.contains("5")
                                | text1.contains("6") | text1.contains("7") | text1.contains("8") | text1.contains("9")) {
                            labelquan.setText("Quantite Conforme");
                            labelquan.setTextFill(Color.web("#E1D60C"));
                            testTelValidation = 1;
                            return new ValidationResult("Should be > 8", ValidationResult.Type.WARNING);

                        } else {
                            labelquan.setText("Quantite invalide");
                            labelquan.setTextFill(Color.web("#FF0000"));
                            testTelValidation = 0;
                        }
                    }
                    return null; // succeeded
                } catch (Exception e) {
                    // failed
                    labelquan.setText("Faux numéro");
                    labelquan.setTextFill(Color.web("#D22525"));
                    testTelValidation = 0;
                    return new ValidationResult("Bad number", ValidationResult.Type.ERROR);
                }
            }
        });

        Effetf.setMaxHeight(TextField.USE_PREF_SIZE);
        pane3.setContent(Effetf);

        pane3.setValidator(new Validator<TextField>() {

            public ValidationResult validate(TextField tel) {
                try {
                    String text = tel.getText();

                    if (!text.isEmpty()) {
                        if (text.contains("0") | text.contains("1") | text.contains("2")
                                | text.contains("3") | text.contains("4") | text.contains("5")
                                | text.contains("6") | text.contains("7") | text.contains("8") | text.contains("9")) {
                            labeleffet.setText("Effet invalide");
                            labeleffet.setTextFill(Color.web("#FF0000"));
                            testTelValidation = 0;
                      //  return new ValidationResult("Should be > 8", ValidationResult.Type.WARNING);

                        } else {
                            labeleffet.setText("Effet conforme");
                            labeleffet.setTextFill(Color.web("#24F462"));
                            testTelValidation = 1;
                        }
                    }
                    return null; // succeeded
                } catch (Exception e) {
                    // failed
                    labeleffet.setText("Faux numéro");
                    labeleffet.setTextFill(Color.web("#D22525"));
                    testTelValidation = 0;
                    return new ValidationResult("Bad number", ValidationResult.Type.ERROR);
                }
            }
        });
        minf.setMaxHeight(TextField.USE_PREF_SIZE);
        pane4.setContent(minf);
        pane4.setValidator(new Validator<TextField>() {
            public ValidationResult validate(TextField tel) {
                try {
                    String text1 = tel.getText();

                    if (!text1.isEmpty()) {
                        if (text1.contains("0") | text1.contains("1") | text1.contains("2")
                                | text1.contains("3") | text1.contains("4") | text1.contains("5")
                                | text1.contains("6") | text1.contains("7") | text1.contains("8") | text1.contains("9")) {
                            labelmin.setText("Quantite Conforme");
                            labelmin.setTextFill(Color.web("#E1D60C"));
                            testTelValidation = 1;
                     //   return new ValidationResult("Should be > 8", ValidationResult.Type.WARNING);

                        } else {
                            labelmin.setText("Quantite invalide");
                            labelmin.setTextFill(Color.web("#FF0000"));
                            testTelValidation = 0;
                        }
                    }
                    return null; // succeeded
                } catch (Exception e) {
                    // failed
                    labelmin.setText("Faux numéro");
                    labelmin.setTextFill(Color.web("#D22525"));
                    testTelValidation = 0;
                    return new ValidationResult("Bad number", ValidationResult.Type.ERROR);
                }
            }
        });

        prixf.setMaxHeight(TextField.USE_PREF_SIZE);
        pane5.setContent(prixf);
        pane5.setValidator(new Validator<TextField>() {
            public ValidationResult validate(TextField tel) {
                try {
                    String text1 = tel.getText();

                    if (!text1.isEmpty()) {
                        if (text1.contains("0") | text1.contains("1") | text1.contains("2")
                                | text1.contains("3") | text1.contains("4") | text1.contains("5")
                                | text1.contains("6") | text1.contains("7") | text1.contains("8") | text1.contains("9")) {
                            labelprix.setText("Prix Conforme");
                            labelprix.setTextFill(Color.web("#E1D60C"));
                            testTelValidation = 1;
                            return new ValidationResult("Should be > 8", ValidationResult.Type.WARNING);

                        } else {
                            labelprix.setText("Prix invalide");
                            labelprix.setTextFill(Color.web("#FF0000"));
                            testTelValidation = 0;
                        }
                    }
                    return null; // succeeded
                } catch (Exception e) {
                    // failed
                    labelprix.setText("Faux numéro");
                    labelprix.setTextFill(Color.web("#D22525"));
                    testTelValidation = 0;
                    return new ValidationResult("Bad number", ValidationResult.Type.ERROR);
                }
            }
        });
        Libellef.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.20F);
            }

        });
        Quantitef.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.40F);
            }

        });
        Effetf.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.60F);
            }

        });
        prixf.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(0.80F);
            }

        });
        minf.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                p2.setProgress(1F);
            }

        });

        vb.getChildren().addAll(Libelle, pane, lll, Quantite, pane2, labelquan, Effet, pane3, labeleffet);
        vb2.getChildren().addAll(Prix, pane5, labelprix, min, pane4, labelmin, p2, btn);
      
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MedicamentDAO dao = new MedicamentDAO();
                Medicament med2 = new Medicament(Libellef.getText(), Integer.parseInt(Quantitef.getText()), Effetf.getText(), Integer.parseInt(prixf.getText()), Integer.parseInt(minf.getText()));
                dao.insertmed(med2);
            }
        });
        
        VBox vv=new VBox(new Label(""));
                
        HBox h = new HBox();
        h.setSpacing(20);
        h.getChildren().addAll(vb,vb2);
        
        HBox hh=new HBox();
        hh.setSpacing(70);
        hh.getChildren().addAll(vv,h);
        

  //interface recher/modif
        final TableView tv = new TableView();
        array5 = dao.DisplayAllmed();
        data5 = FXCollections.observableArrayList();
        data5.addAll(array5);
        tv.setItems(data5);
        tv.setEditable(true);
         tv.setFixedCellSize(20);
        tv.setMaxWidth(600);
        tv.setMaxHeight(400);

        TableColumn Libelle2 = new TableColumn();
        Libelle2.setText("Libelle");
        Libelle2.setMinWidth(100);
        Libelle2.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Libelle"));
        TableColumn Quantite2 = new TableColumn();
        Quantite2.setText("Quantite");
        Quantite2.setMinWidth(100);
        Quantite2.setCellValueFactory(new PropertyValueFactory("Quantite"));

        TableColumn Effet2 = new TableColumn();
        Effet2.setText("Effet");
        Effet2.setMinWidth(100);
        Effet2.setCellValueFactory(new PropertyValueFactory("Effet"));

        TableColumn Prix2 = new TableColumn();
        Prix2.setText("Prix");
        Prix2.setMinWidth(100);
        Prix2.setCellValueFactory(new PropertyValueFactory("Prix"));

        TableColumn quantite_min = new TableColumn();
        quantite_min.setText("Quantite minimale");
        quantite_min.setMinWidth(100);
        quantite_min.setCellValueFactory(new PropertyValueFactory("quantite_min"));

        final TextField code = new TextField();
        final TextField lib = new TextField();
        final TextField qua = new TextField();
        final TextField eff = new TextField();
        final TextField prix = new TextField();
        final TextField min2 = new TextField();
        final Button bt = new Button("Modifier");
        /*  tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
         @Override
         public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
         //Check whether item is selected and set value of selected item to Label
         if (tv.getSelectionModel().getSelectedItem() != null) {
         TableView.TableViewSelectionModel selectionModel = tv.getSelectionModel();
         ObservableList selectedCells = selectionModel.getSelectedCells();
         TablePosition tablePosition = (TablePosition) selectedCells.get(0);
         final Object val = tablePosition.getTableColumn().getCellData(newValue);
            
         if(val.toString()!="confirmer"){*/
        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                Medicament med_g1 = (Medicament) tv.getSelectionModel().getSelectedItem();

                final Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(new Scene(VBoxBuilder.create().
                        children(new Text(), bt, lib, qua, eff, prix, min2).alignment(Pos.CENTER).padding(new Insets(5)).build()));
                if (med_g1 != null) {
                    code.setText(String.valueOf(dao.findmedBylibelle((String) med_g1.getLibelle()).getCode()));
                    lib.setText(dao.findmedBylibelle((String) med_g1.getLibelle()).getLibelle());
                    qua.setText(String.valueOf(dao.findmedBylibelle((String) med_g1.getLibelle()).getPrix()));
                    eff.setText(dao.findmedBylibelle((String) med_g1.getLibelle()).getEffet());
                    prix.setText(String.valueOf(dao.findmedBylibelle((String) med_g1.getLibelle()).getPrix()));
                    min2.setText(String.valueOf(dao.findmedBylibelle((String) med_g1.getLibelle()).getQuantite_min()));

                } else {
                    System.out.println("objet vide");
                }
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Medicament med = new Medicament();
                        med.setCode(Integer.parseInt(code.getText()));

                        med.setLibelle(lib.getText());
                        med.setQuantite((int) Float.parseFloat(qua.getText()));
                        med.setEffet(eff.getText());
                        med.setPrix(Float.parseFloat(prix.getText()));
                        med.setQuantite_min(Integer.parseInt(min2.getText()));

                        dao.updatemed(med);
                        array5.removeAll(data5);
                        data5.clear();
                        array5 = dao.DisplayAllmed();
                        data5.addAll(array5);
                        tv.setItems(data5);
                        dialogStage.hide();
                    }
                });
                dialogStage.show();
                dialogStage.setWidth(400);
                dialogStage.setHeight(400);
            }
        });
        tv.getColumns().addAll(Libelle2, Quantite2, Effet2, Prix2, quantite_min);

  // interface tableau supprimer
        final TableView tableView3 = new TableView();

        array3 = dao.DisplayAllmed();

        data3 = FXCollections.observableArrayList();

        data3.addAll(array3);

        tableView3.setItems(data3);
        tableView3.setEditable(true);
        tableView3.setFixedCellSize(20);
        tableView3.setMaxWidth(600);
        tableView3.setMaxHeight(400);

        TableColumn Libelle3 = new TableColumn();
        Libelle3.setText("Libelle");
        Libelle3.setMinWidth(100);
        Libelle3.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Libelle"));

        TableColumn Quantite3 = new TableColumn();
        Quantite3.setText("Quantite");
        Quantite3.setMinWidth(100);
        Quantite3.setCellValueFactory(new PropertyValueFactory("Quantite"));

        TableColumn Effet3 = new TableColumn();
        Effet3.setText("Effet");
        Effet3.setMinWidth(100);
        Effet3.setCellValueFactory(new PropertyValueFactory("Effet"));

        TableColumn Prix3 = new TableColumn();
        Prix3.setText("Prix");
        Prix3.setMinWidth(100);
        Prix3.setCellValueFactory(new PropertyValueFactory("Prix"));

        TableColumn quantite_min3 = new TableColumn();
        quantite_min3.setText("quantite_min");
        quantite_min3.setMinWidth(100);
        quantite_min3.setCellValueFactory(new PropertyValueFactory("quantite_min"));

       // tableView3.setEditable(true);

        final Button bt2 = new Button("supprimer");

        tableView3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {

                Medicament med_g = (Medicament) tableView3.getSelectionModel().getSelectedItem();

                /*            tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                 @Override
                 public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                 //Check whether item is selected and set value of selected item to Label
                 if (tableView.getSelectionModel().getSelectedItem() != null) {
                 TableView.TableViewSelectionModel selectionModel = tableView.getSelectionModel();
                 ObservableList selectedCells = selectionModel.getSelectedCells();
                 TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                 final Object val = tablePosition.getTableColumn().getCellData(newValue);
                 if(val!=null)
                 if(val.toString()!="confirmer"){
                 */
                final Stage dialogStage = new Stage();
                if (med_g != null) {
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.setScene(new Scene(VBoxBuilder.create().
                            children(new Text(" voulez vous supprimer le medicament \t" + med_g.getLibelle()), bt2).alignment(Pos.CENTER).padding(new Insets(5)).build()));
                    dialogStage.show();

                    dialogStage.setWidth(400);
                    dialogStage.setHeight(400);
                } else {
                    System.out.println("vide");
                }
                bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (med_g.getLibelle() != null) {
                            dao.deletemedlib(med_g.getLibelle());
                            array3.removeAll(data3);
                            data3.clear();
                            array3 = dao.DisplayAllmed();
                            data3.addAll(array3);
                            tableView3.setItems(data3);
                            tableView3.getSelectionModel().clearSelection();
                            dialogStage.hide();

                        } else {
                            System.out.println("objet medic null");
                        }

                    }
                });
            }
        });

        tableView3.getColumns().addAll(Libelle3, Quantite3, Effet3, Prix3, quantite_min3);

        // tbaleau stock Medicament
        final TableView tableView4 = new TableView();
         tableView4.setFixedCellSize(20);
        tableView4.setMaxWidth(600);
        tableView4.setMaxHeight(400);
        //tableView4.setStyle("-fx-background-color: red");
        array4 = dao.Displaystock();
        data4 = FXCollections.observableArrayList();
        data4.addAll(array4);
        tableView4.setItems(data4);
        tableView4.setEditable(true);
        TableColumn Libelle5 = new TableColumn();
        Libelle5.setText("Libelle");
        Libelle5.setMinWidth(100);
        Libelle5.setCellValueFactory(new PropertyValueFactory<Medicament, String>("Libelle"));

        TableColumn Quantite5 = new TableColumn();
        Quantite5.setText("Quantite");
        Quantite5.setMinWidth(100);
        Quantite5.setCellValueFactory(new PropertyValueFactory("Quantite"));

        TableColumn Effet5 = new TableColumn();
        Effet5.setText("Effet");
        Effet5.setMinWidth(100);
        Effet5.setCellValueFactory(new PropertyValueFactory("Effet"));

        TableColumn Prix5 = new TableColumn();
        Prix5.setText("Prix");
        Prix5.setMinWidth(100);
        Prix5.setCellValueFactory(new PropertyValueFactory("Prix"));

        TableColumn quantite_min5 = new TableColumn();
        quantite_min5.setText("Quantite minimale");
        quantite_min5.setMinWidth(100);
        quantite_min5.setCellValueFactory(new PropertyValueFactory("quantite_min"));
        tableView4.setEditable(true);
        tableView4.getColumns().addAll(Libelle5, Quantite5, Effet5, Prix5, quantite_min5);

        //accordion
        /*
        Accordion accordion_med = new Accordion();
        TitledPane t1 = new TitledPane("Ajout d'un nouveau medicament", h);
        TitledPane t2 = new TitledPane("Modification des medicaments", tv);
        TitledPane t3 = new TitledPane("Suppression des medicaments", tableView3);
        TitledPane t4 = new TitledPane("Etat du Stock des médicaments", tableView4);
        accordion_med.getPanes().add(t1);
        accordion_med.getPanes().add(t2);
        accordion_med.getPanes().add(t3);
        accordion_med.getPanes().add(t4);
         */
        
      //interface borderpane
        //borderpane boutons+interface à droite
        
        Label sup_m=new Label("Supprimer un médicament :");
        sup_m.setStyle("-fx-font-size: 24;");
        
        Label stock_m=new Label("Consuler le stock des médicaments :");
        stock_m.setStyle("-fx-font-size: 24;");
        
        
        
        HBox tab_mod=new HBox();
        tab_mod.setSpacing(50);        
        tab_mod.getChildren().addAll(ajout_m,tv);
        
        HBox tab_sup=new HBox();
        tab_sup.setSpacing(50);        
        tab_sup.getChildren().addAll(sup_m,tableView3);
        
        HBox tab_stoc=new HBox();
        tab_stoc.setSpacing(50);        
        tab_stoc.getChildren().addAll(stock_m,tableView4);
        
        
        
        
        BorderPane bord_medic=new BorderPane();
  
        
       //bouton ajout  
      Button ajout_medic=new Button();
     
      Image im_ajm = new Image(getClass().getResourceAsStream("/cssfiles/ajout_medicament.png"));     
      ajout_medic.setGraphic(new ImageView(im_ajm));      
      ajout_medic.setStyle("-fx-background-color: transparent;");
      Tooltip toolB_aj = new Tooltip("Ajouter des médicaments");
      toolB_aj.setContentDisplay(ContentDisplay.BOTTOM);
      toolB_aj.setStyle("");
      ajout_medic.setTooltip(toolB_aj);
      
      ajout_medic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_medic.setCenter(hh);
            }
        });
      
      //bouton recherche/modif
      Button modif_medic=new Button();
      modif_medic.setCursor(Cursor.HAND);
      Image im_modifm = new Image(getClass().getResourceAsStream("/cssfiles/modif_medicament.png"));     
      modif_medic.setGraphic(new ImageView(im_modifm));      
      modif_medic.setStyle("-fx-background-color: transparent;");
      Tooltip toolB = new Tooltip("Modifier stock des médicaments");
      toolB.setContentDisplay(ContentDisplay.BOTTOM);
      toolB.setStyle("");
      modif_medic.setTooltip(toolB);
      
      modif_medic.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_medic.setCenter(tab_mod);
            }
        });
      
      //bouton dropbox
      Button supp_med=new Button();
      supp_med.setCursor(Cursor.HAND);
      Image im_sup = new Image(getClass().getResourceAsStream("/cssfiles/supp_medicament.png"));     
      supp_med.setGraphic(new ImageView(im_sup));      
      supp_med.setStyle("-fx-background-color: transparent;");
      Tooltip toolB_ar = new Tooltip("Supprimer des médicaments");
      toolB_ar.setContentDisplay(ContentDisplay.BOTTOM);
      toolB_ar.setStyle("");
      
      supp_med.setTooltip(toolB_ar);      
      supp_med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_medic.setCenter(tab_sup);
            }
            
        });
      
      
      
       //bouton dropbox
      Button stock_med=new Button();
      stock_med.setCursor(Cursor.HAND);
      Image im_stock = new Image(getClass().getResourceAsStream("/cssfiles/shelf.png"));     
      stock_med.setGraphic(new ImageView(im_stock));      
      stock_med.setStyle("-fx-background-color: transparent;");
      Tooltip toolB_stock = new Tooltip("Stock des médicaments");
      toolB_stock.setContentDisplay(ContentDisplay.BOTTOM);
      toolB_stock.setStyle("");
      
      stock_med.setTooltip(toolB_ar);      
      stock_med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              bord_medic.setCenter(tab_stoc);
            }
            
        });
      
      
      
      
      
      //regroupement des boutons
      VBox v_bouton=new VBox();
      v_bouton.setSpacing(20);
      v_bouton.getChildren().addAll(ajout_medic,modif_medic,supp_med,stock_med);     
      bord_medic.setLeft(v_bouton);
     // bord_medic.setCenter(formulaireAj);
      
       return bord_medic;

    }

}
