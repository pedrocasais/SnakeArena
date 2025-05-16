/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class SecondViewController implements Initializable {

    @FXML
    ComboBox<String> comboBox;

    @FXML
    private ColorPicker ColorP;

    @FXML
    private void handleBtn(ActionEvent event) {

        SceneController sc = new SceneController();
        sc.switchToScene(event, "arena.fxml");
        Stage thisStage = (Stage) comboBox.getScene().getWindow();
        thisStage.close();
        thisStage = null;

    }

    @FXML
    private void handleBtn2(ActionEvent event) {

        SceneController sc = new SceneController();
        sc.switchToScene(event, "MainView.fxml");
        Stage thisStage = (Stage) comboBox.getScene().getWindow();
        thisStage.close();
        thisStage = null;

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox.getItems().addAll("Easy", "Hard");
        comboBox.getSelectionModel().selectFirst();
    }

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

            // Aceder ao controlador da nova cena
            GameViewController controller = fxmlLoader.getController();

            controller.setCorSelecionada(ColorP.getValue());

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
