/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.gui;

import edu.stickybit.dao.classes.Authentification;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tlili Mohamed Ali 
 */
public class Conn extends Application {

    String login_val = "";
    String pass_val = "";

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene s = new Scene(root, 700, 700);
        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(s);
        primaryStage.setScene(s);
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        String styleback = Conn.class.getResource("/cssfiles/style.css").toExternalForm();
        root.getStylesheets().add(styleback);
        root.setId("pane");

        final Label label = new Label("Authentification");
        label.setStyle("-fx-text-fill: rgb(255,0,0); -fx-font-size: 24; -fx-font-weight: bold;");
        StackPane glass = new StackPane();
        StackPane.setAlignment(glass, Pos.CENTER);
        glass.setStyle("-fx-background-color: rgb(255,255,255); -fx-background-radius: 10; -fx-opacity:0.9;");
        glass.setMaxWidth(400);
        glass.setMaxHeight(200);
  //  glass.setPadding(new Insets(200,500, 500, 100));
        //bouton exit
        Button closeBtn = new Button("X");
        closeBtn.setBorder(Border.EMPTY);
        closeBtn.setTextFill(Color.RED);

        closeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
//
        Button Minbt = new Button("-");
        closeBtn.setBorder(Border.EMPTY);
        closeBtn.setTextFill(Color.RED);
        Minbt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                primaryStage.toBack();
            }
        });

        Label login = new Label("LOGIN :");
        Label pass = new Label("PASS :");

        login.setTextFill(Color.web("#070719"));
        login.setFont(Font.font("Cambria", 25));
        TextField log_f = new TextField();
        log_f.setPromptText("login");

        pass.setTextFill(Color.web("#070719"));
        pass.setFont(Font.font("Cambria", 25));
        PasswordField pass_f = new PasswordField();
        pass_f.setPromptText("password");

        Label test = new Label();
        test.setTextFill(Color.web("#070719"));

        Button connecter = new Button("log in");

        connecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println("okkkkkkkk");

                    login_val = log_f.getText();
                    pass_val = pass_f.getText();

                    Authentification auth = new Authentification();
                    if (auth.auth(login_val, pass_val) == 1) {

                        Stage dialog = new Stage();

                        Button b = new Button();
                        StackPane roott = new StackPane();
                        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

                        //the main screen
                        dialog.setX(primaryScreenBounds.getMinX());
                        dialog.setY(primaryScreenBounds.getMinY());
                        dialog.setWidth(primaryScreenBounds.getWidth());
                        dialog.setHeight(primaryScreenBounds.getHeight());

                        final Scene ss = new Scene(roott, 700, 700);

                        // dialog.setResizable(true);
                        dialog.initStyle(StageStyle.UNDECORATED);
                        //  primaryStage.initStyle(StageStyle.TRANSPARENT);
                        dialog.setScene(ss);
                        dialog.setTitle("StickyHospital");

                        BorderPane border = new BorderPane();
                        border.setPadding(new Insets(20, 0, 20, 20));
                        //bouton exit

                        // tabPane
                        final TabPane tabPane = new TabPane();
                        //  tabPane.setPrefSize(400, 400);
                        tabPane.setPrefWidth(Double.MAX_VALUE);
                        //tabPane.setMaxHeight(560);
                        tabPane.setSide(Side.TOP);

                        // tabPane.setSide(Side.TOP);
                        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

                        final Tab tab0 = new Tab();
                        tab0.setText(" Acceuil ");
                        final Tab tab1 = new Tab();
                        tab1.setText("Gestion des patients");
                        final Tab tab2 = new Tab();
                        tab2.setText("Gesion des médecins");
                        final Tab tab3 = new Tab();
                        tab3.setText("Gestion des chambres");
                        final Tab tab4 = new Tab();
                        tab4.setText("Gestion des factures");
                        final Tab tab5 = new Tab();
                        tab5.setText("Stock des médicaments");
                        final Tab tab6 = new Tab();
                        tab6.setText("Hospitalisation");
                        final Tab tab7 = new Tab();
                        tab7.setText("Dossiers administratifs");
                        final Tab tab8 = new Tab();
                        tab8.setText("Statistiques");
                        final Tab tab9 = new Tab();
                        tab9.setText("Messagerie");

                        tabPane.getTabs().addAll(tab0, tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9);

                        //interface patient
                        InterfacePatient interface_patient = new InterfacePatient();
                        BorderPane a_patient = new BorderPane();
                        a_patient = interface_patient.RecupererInterface();

                        //interface medecin
                        InterfaceMedecinFinal interface_d = new InterfaceMedecinFinal();
                        BorderPane a_medecin = new BorderPane();
                        a_medecin = interface_d.RecupererInterface();

                        //interface chambre
                        InterfaceChambreFinal interface_m = new InterfaceChambreFinal();
                        BorderPane a_chambre = new BorderPane();
                        a_chambre = interface_m.RecupererInterface();

                        //interface medicament
                        InterfaceMedicament interface_med = new InterfaceMedicament();
                        BorderPane a_medicament = new BorderPane();
                        a_medicament = interface_med.RecupererAccordion();

                        //interface hospitalisation
                        InterfaceHospitalisation interface_hosp = new InterfaceHospitalisation();
                        BorderPane bord = new BorderPane();
                        bord = interface_hosp.RecupererInterface();

                        //interface messagerie
                        InterfaceMessagerie interface_mess = new InterfaceMessagerie();
                        HBox a_messagerie = new HBox();
                        a_messagerie = interface_mess.RecupererInterface();

                        //interface des statistiques
                        InterfaceBarChartmedicamntFinal interface_stat = new InterfaceBarChartmedicamntFinal();
                        BorderPane a_stat = new BorderPane();
                        a_stat = interface_stat.RecupererInterface();

                        //interface facture
                        InterfaceFactureFinal interface_fact = new InterfaceFactureFinal();
                        BorderPane a_facture = new BorderPane();
                        a_facture = interface_fact.RecupererInterface();

                        //interface dossier administratif
                        InterfaceDossierAdministratif interface_doss = new InterfaceDossierAdministratif();
                        BorderPane a_dossier = new BorderPane();
                        a_dossier = interface_doss.RecupererInterfcae();

                 
                        final Image image_acc = new Image(ApplicationComplete.class.getResourceAsStream("acceuil.jpg"));
                        ImageView im21 = new ImageView();
                        im21.setImage(image_acc);
                        
                        VBox vbox_acc=new VBox();                        
                        vbox_acc.getChildren().addAll(im21);                        
                        VBox h=new VBox(new Label("")); 
                        
                        HBox m=new HBox();
                        m.setSpacing(90);
                                               
                        m.getChildren().addAll(h,vbox_acc);
                        
                        
                        //organisation du menu
                        tab0.setContent(m);
                        tab1.setContent(a_patient);
                        tab2.setContent(a_medecin);
                        tab3.setContent(a_chambre);
                        tab4.setContent(a_facture);
                        tab5.setContent(a_medicament);
                        tab6.setContent(bord);
                        tab7.setContent(a_dossier);
                        tab8.setContent(a_stat);
                        tab9.setContent(a_messagerie);

                        //BorderPane
                        BorderPane borderpane = new BorderPane();
                        borderpane.setPadding(new Insets(0, 20, 32, 31));

                        // image de l'entete 
                        final Image i = new Image(ApplicationComplete.class.getResourceAsStream("entete.png"));
                        ImageView im = new ImageView();
                        im.setImage(i);

                        // image de footer        
                        final Image i2 = new Image(ApplicationComplete.class.getResourceAsStream("footer2.png"));
                        ImageView im2 = new ImageView();
                        im2.setImage(i2);

                        HBox hbx2 = new HBox();
                        Label lab_aff = new Label("Système de gestion de l'hopital");
                        hbx2.getChildren().add(lab_aff);
                        HBox hh = new HBox();
                        hh.getChildren().addAll(im, closeBtn);

                        borderpane.setTop(hh);
                        borderpane.setCenter(tabPane);
                        borderpane.setBottom(im2);

                        roott.getChildren().add(borderpane);

                        dialog.setScene(ss);
                        primaryStage.close();
                        dialog.show();

                    } else {
                        test.setText("login ou mot de passse incorrect");
                        test.setTextFill(Color.RED);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Conn.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        double y = 40;
        final double SPACING = 40;

        y += SPACING;
        ProgressBar p3 = new ProgressBar();
        p3.setMaxHeight(400);
        p3.setMaxWidth(1400);
        p3.setLayoutY(y);

        HBox h1 = new HBox();
        HBox h2 = new HBox();
        HBox h3 = new HBox();
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        HBox x = new HBox();

        h1.setSpacing(10);
        h2.setSpacing(25);
        v1.setSpacing(10);
        x.setSpacing(90);

        h1.getChildren().addAll(new Label(""), new Label(""), login, log_f);
        h2.getChildren().addAll(new Label(""), pass, pass_f);
        x.getChildren().addAll(new Label(""), new Label(""), connecter);

        h3.getChildren().addAll(closeBtn, Minbt);
        h3.setAlignment(Pos.TOP_RIGHT);

        v1.getChildren().addAll(label, new Label(""), h1, h2, x, test);
        glass.getChildren().add(v1);

        root.setTop(h3);
        root.setCenter(glass);
        root.setBottom(p3);

        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
