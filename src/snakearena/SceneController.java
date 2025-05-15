/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakearena;

import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static snakearena.SnakeArena.scene;

public class SceneController {

    /*
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1120, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("leadboardView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1120, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SecondView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1120, 720);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene4(ActionEvent event) throws IOException {
        /* FXMLLoader loader = new FXMLLoader(getClass().getResource("arena.fxml"));
        Parent root = loader.load();
        //GameViewController controller = loader.getController();
        //controller.getScreenSize(stage);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root,858,847);
        stage.setScene(scene);
        stage.show();
        Parent root = FXMLLoader.load(getClass().getResource("arena.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 858, 847);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToScene5(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));

        scene = new Scene(root, 1120, 720);
        //scene.setFill(Color.BLACK)1;
        stage.setTitle("Snake Arena");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene6(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameOver.fxml"));

        scene = new Scene(root, 1120, 720);
        //scene.setFill(Color.BLACK)1;
        stage.setTitle("Snake Arena");
        stage.setScene(scene);
        stage.show();
    }
     */
    public void switchToScene(ActionEvent event, String s) {
        Random r = new Random();

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        int width = (int) screenBounds.getWidth();
        int height = (int) screenBounds.getHeight();
        Image img = new Image("/resources/images/background.png");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource(s)
            );
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.NONE);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Snake Arena");
            stage.getIcons().add(img);

            //stage.setX(height);
            stage.setScene(new Scene(root, 1120, 720));
            //stage.setX(r.nextInt(width - (int) stage.getWidth()));
            //stage.setY(r.nextInt(height - (int) stage.getHeight()));
                
            stage.centerOnScreen();
            stage.show();
        } catch (Exception ex) {

        }
    }
}
