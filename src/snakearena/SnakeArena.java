/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakearena;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author pedro
 */
public class SnakeArena extends Application {

    public static Scene scene;

    // Add this as a class field
private MediaPlayer mediaPlayer;

@Override
public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("Imagem.fxml"));

    scene = new Scene(root, 1120, 720);
    //scene.setFill(Color.BLACK)1;
    stage.setTitle("Snake Arena");

    Image img = new Image("/resources/images/background.png");
    stage.getIcons().add(img);
    stage.setScene(scene);

    // Improved music setup
    try {
        URL resource = getClass().getResource("/resources/music.mp3");
        if (resource == null) {
            System.err.println("Arquivo de música não encontrado: /resources/music.mp3");
        } else {
            Media sound = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            
            // Set volume (0.0 to 1.0)
            mediaPlayer.setVolume(0.5);
            
            // Configure looping
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            
            // Play when ready
            mediaPlayer.setOnReady(() -> mediaPlayer.play());
            
            // Stop music when application closes
            stage.setOnCloseRequest(event -> {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                }
            });
        }
    } catch (Exception e) {
        System.err.println("Erro ao carregar ou reproduzir música: " + e.getMessage());
    }

    stage.show();
    System.out.println(stage.widthProperty() + "\t" + stage.heightProperty());
}
    public static void main(String[] args) {
        launch(args);
    }
}
