/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class SecondViewController implements Initializable {

    @FXML
    ComboBox<String> comboBox;

    @FXML
    private void handleBtn(ActionEvent event) {

        SceneController sc = new SceneController();
        try {
            sc.switchToScene4(event);
        } catch (IOException ex) {
            Logger.getLogger(SecondViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleBtn2(ActionEvent event) {

        SceneController sc = new SceneController();
        try {
            sc.switchToScene1(event);
        } catch (IOException ex) {
            Logger.getLogger(SecondViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
