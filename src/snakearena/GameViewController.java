/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.xml.transform.Source;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class GameViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private double width;
    private double height;
    private double SQUARE_SIZE;

    public void getScreenSize(Stage stage) {

        if (stage != null) {
            width = stage.getWidth();
            height = stage.getHeight();
            SQUARE_SIZE = Math.min(width / ROWS, height / ROWS);
            System.out.println("Stage width: " + width + ", height: " + height);
        } else {
            System.out.println("Stage is null in GameViewController initialize method.");
        }
    }

    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    @FXML
    private Canvas canvas;

    private void drawMap() {
        System.out.println("olbaqauqi -> " + width);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.web("aad751") : Color.web("a2d149"));
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) canvas.getScene().getWindow();
            getScreenSize(stage);
            //stage.setWidth(width - (4 * SQUARE_SIZE + SQUARE_SIZE/2));
            //stage.setHeight(height + SQUARE_SIZE);
            canvas.setWidth(width);
            canvas.setHeight(height);
            getScreenSize(stage);

            drawMap();
        });
    }

}
