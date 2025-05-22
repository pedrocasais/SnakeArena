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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
    private TextField tF;
    
    
    
    
    @FXML
    private void handleBtn(ActionEvent event) {

        if (tF.getText() == null) {
            tF.setText("Nome Obrigatório!");
        }
        
        Color cor = ColorP.getValue();
        System.out.println(cor.toString());

        SceneController sc = new SceneController();
        sc.switchToScene2(event, "arena.fxml", cor,tF.getText(),comboBox.getValue());
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

}
