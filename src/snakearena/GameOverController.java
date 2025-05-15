/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class GameOverController implements Initializable {

    @FXML
    private Button btn1;

    @FXML
    private void playAgain(ActionEvent event) {

        SceneController sc = new SceneController();
        sc.switchToScene(event, "arena.fxml");
        Stage thisStage = (Stage) btn1.getScene().getWindow();
        thisStage.close();
        thisStage = null;
    }

    @FXML
    private void exitBtn() {
        Stage thisStage = (Stage) btn1.getScene().getWindow();
        thisStage.close();
        thisStage = null;
        exit();
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
