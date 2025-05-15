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
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class MainViewController implements Initializable {

    @FXML
    private void startBtn(ActionEvent event) {

        SceneController sc = new SceneController();
        sc.switchToScene(event, "SecondView.fxml");

    }

    @FXML
    private void leadBtn(ActionEvent event) {

        SceneController sc = new SceneController();
        sc.switchToScene(event, "LeadboardView.fxml");
    }
    
    
    @FXML
    private void exitBtn() {
        exit();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
