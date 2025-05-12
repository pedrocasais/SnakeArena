/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakearena;

import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author pedro
 */
public class SnakeArena extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Imagem.fxml"));
        
        
        scene = new Scene(root,1120,720);
        //scene.setFill(Color.BLACK)1;
        stage.setTitle("Snake Arena");
        stage.setScene(scene);
        stage.show();
        System.out.println(stage.widthProperty() + "\t" + stage.heightProperty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
