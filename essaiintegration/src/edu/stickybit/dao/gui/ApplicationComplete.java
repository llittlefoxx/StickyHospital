package edu.stickybit.dao.gui;

/**
 *
 * @author Asma Boussabat
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 */
public class ApplicationComplete extends Application {

    private void init(Stage primaryStage) throws Exception {

        Button b = new Button();
        StackPane root = new StackPane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        Scene s = new Scene(root, 700, 700);

        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //  primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(s);
        primaryStage.setTitle("StickyHospital");
        primaryStage.setScene(s);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(20, 0, 20, 20));
        //bouton exit
        Button closeBtn = new Button("X");

        closeBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

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

        Image image = new Image(getClass().getResourceAsStream("user.png"));
        ImageView imag = new ImageView();
        imag.setImage(image);
       
       // tab0.setGraphic(imag);

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

        /*   //interface medecin         
         InterfaceMedecin interface_d=new InterfaceMedecin();
         Accordion a_medecin=new Accordion();
         a_medecin=interface_d.recupInterface();
         */
        //interface chambre
        InterfaceChambre interface_m = new InterfaceChambre();
        Accordion a_chambre = new Accordion();
//        a_chambre = interface_m.recupererinterface();

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
    /*  
         InterfaceStatistique interface_stat=new InterfaceStatistique();
         BarChart a_stat=new BarChart(null, null);
         a_stat=interface_stat.RecupererInterface();
         */
      //interface dossier administratif
        InterfaceDossierAdministratif interface_doss = new InterfaceDossierAdministratif();
        BorderPane a_dossier = new BorderPane();
        a_dossier = interface_doss.RecupererInterfcae();

        //organisation du menu
        tab1.setContent(a_patient);
//      tab2.setContent(a_medecin);
        tab3.setContent(a_chambre);
        tab5.setContent(a_medicament);
        tab6.setContent(bord);
        tab7.setContent(a_dossier);
        tab9.setContent(a_messagerie);

        //BorderPane
        BorderPane borderpane = new BorderPane();
        borderpane.setPadding(new Insets(0, 20, 32, 31));

        // image de l'entete 
        final Image i = new Image(ApplicationComplete.class.getResourceAsStream("hopital3.jpg"));
        ImageView im = new ImageView();
        im.setImage(i);

        // image de footer        
        final Image i2 = new Image(ApplicationComplete.class.getResourceAsStream("footer.jpg"));
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

        root.getChildren().add(borderpane);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
