/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package snakearena;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

        scene = new Scene(root, 1120, 720);
        //scene.setFill(Color.BLACK)1;
        stage.setTitle("Snake Arena");

        Image img = new Image("/resources/images/background.png");
        stage.getIcons().add(img);
        stage.setScene(scene);

        Media sound = new Media(getClass().getResource("/resources/music.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.setAutoPlay(true);
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //mediaPlayer.play();
            
        stage.show();
        System.out.println(stage.widthProperty() + "\t" + stage.heightProperty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
