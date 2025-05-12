/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class ImagemController implements Initializable {

@FXML
    private ProgressBar myProgressBar;

    @FXML
    private Label myLabel;

    private double progress = 0.0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myProgressBar.setStyle("-fx-accent: #00FF00;");

        // Timeline para animar a progress bar durante 3 segundos
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(30), e -> {
                if (progress < 1.0) {
                    progress += 0.01;
                    myProgressBar.setProgress(progress);
                    myLabel.setText((int)(progress * 100) + "%");
                }
            })
        );

        timeline.setCycleCount(100); // 100 * 30ms = 3000ms (3 segundos)
        timeline.play();
    }  
    
}