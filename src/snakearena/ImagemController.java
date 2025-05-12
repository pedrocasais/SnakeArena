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

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ImagemController implements Initializable {

    @FXML
    private ProgressBar myProgressBar;

    private double progress = 0.0;

    
    private void switchScene(Stage stage) {

        SceneController sc = new SceneController();
        try {
            sc.switchToScene5(stage);
        } catch (IOException ex) {
            Logger.getLogger(SecondViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        

        // Timeline para animar a progress bar durante 3 segundos
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(30), e -> {
                    if (progress < 1.0) {
                        progress += 0.01;
                        myProgressBar.setProgress(progress);
                        System.out.println("progress -> "+myProgressBar.getProgress());
                        if (myProgressBar.getProgress() >= 1.0) {
                            Stage stage = (Stage) myProgressBar.getScene().getWindow();
                            switchScene(stage);
                        }
                        //myLabel.setText((int)(progress * 100) + "%");
                    }
                })
        );

        timeline.setCycleCount(100); // 100 * 30ms = 3000ms (3 segundos)
        timeline.play();
    }

}
