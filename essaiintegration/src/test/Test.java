/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Asma Boussabat
 */
public class Test extends Application {
    
    @Override
    public void start(Stage primaryStage) {      
       
        TextField tf=new TextField();    
       tf.setAlignment(Pos.CENTER);
        tf.setMaxSize(150,10);
       
        //bouton
        
          
        Button b = new Button("insert");
        b.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        b.setMaxSize(140, 10);
        b.setAlignment(Pos.BASELINE_CENTER);
        
        
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField,tf,b);
        hb.setSpacing(10);
        
        
        
        b.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
       
    }
});
            
     /*   
        StackPane root = new StackPane();      
        root.getChildren().add(tf);
        root.getChildren().add(b);
      */
             
        Scene scene = new Scene(hb, 500, 200, Color.CORAL);
       
 
        
        primaryStage.setTitle("Test application");
        primaryStage.setScene(scene);
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
