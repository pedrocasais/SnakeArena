/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.W;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private Timeline timeline;

    private double x = 300;
    private double y = 300;
    private ArrayList<Point> corpo = new ArrayList<>();
    Snake s = new Snake(x, y, corpo);

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    private void drawMap() {
        //System.out.println("olbaqauqi -> " + width);
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.web("aad751") : Color.web("a2d149"));
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawSnake(String Cor, ArrayList<Point> c) {

        for (Point p : c) {
            gc.setFill(Color.web(Cor));
            gc.fillOval(p.x * (SQUARE_SIZE), p.y * (SQUARE_SIZE), SQUARE_SIZE, SQUARE_SIZE);
        }

        /*        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 1; j++) {
                gc.setFill(Color.web(Cor));
                gc.fillOval(i * (SQUARE_SIZE / 2), j * (SQUARE_SIZE / 2), SQUARE_SIZE / 2, SQUARE_SIZE / 2);

            }
        }
         */
    }

    private ArrayList<Point> firstSnake() {
        ArrayList<Point> c = new ArrayList<>();
        int x = 10;
        int y = 10;

        for (int i = 0; i < 4; i++) {
            Point p = new Point(x, y);
            c.add(p);
            x -= 1;
        }
        return c;
    }

    private int currentDirection = RIGHT;

    private void updateSnakePosition() {
        ArrayList<Point> body = s.getCorpo();
        Point head = body.get(0);
        int x = head.x;
        int y = head.y;

        // Move based on direction
        switch (currentDirection) {
            case RIGHT ->
                x += 1;
            case LEFT ->
                x -= 1;
            case UP ->
                y -= 1;
            case DOWN ->
                y += 1;
        }

        // Add new head at new position
        Point newHead = new Point(x, y);
        body.add(0, newHead);

        // Remove last segment to simulate movement
        body.remove(body.size() - 1);
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        switch (code) {
            case RIGHT, D -> {
                System.out.println("here");
                if (currentDirection != LEFT) {
                    currentDirection = RIGHT;
                }
            }
            case LEFT, A -> {
                if (currentDirection != RIGHT) {
                    currentDirection = LEFT;
                }
            }
            case UP, W -> {
                if (currentDirection != DOWN) {
                    currentDirection = UP;
                }
            }
            case DOWN, S -> {
                if (currentDirection != UP) {
                    currentDirection = DOWN;
                }
            }
            default -> {
                // Ignore other keys
            }
        }
    }

    private boolean gameOver() {
        Point head = s.getCorpo().get(0);

        // Check if snake hits the wall
        if (head.x < 0 || head.x >= COLUMNS || head.y < 0 || head.y >= ROWS) {
            return true;
        }

        // Check if snake hits itself
        ArrayList<Point> body = s.getCorpo();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            Stage stage = (Stage) canvas.getScene().getWindow();
            getScreenSize(stage);
            //stage.setWidth(width - (4 * SQUARE_SIZE + SQUARE_SIZE/2));
            //stage.setHeight(height + SQUARE_SIZE);
            canvas.setFocusTraversable(true);
            canvas.requestFocus();

            canvas.setWidth(width);
            canvas.setHeight(height);
            getScreenSize(stage);

            //ArrayList<Point> c = new ArrayList<>();
            //c = firstSnake();
            //drawSnake("FFFFFF", c);
            s.setCorpo(firstSnake());

            gc = canvas.getGraphicsContext2D();

            canvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.setOnKeyPressed(this::handleKeyPress);
                }
            });

            timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run()));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            /*Scene sc = stage.getScene();
            sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    move(e, s.getCorpo());

                }
            });
             */
        });

        // save on file
        //LeadboardViewController lc = new LeadboardViewController();
        //lc.writeFile("pedro,1555000", "red", "hard");
    }

    private void run() {
        if (gameOver()) {
            System.out.println("gameover");
            timeline.stop();
            SceneController sc = new SceneController();
            try {
                sc.switchToScene6((Stage) canvas.getScene().getWindow());
            } catch (IOException ex) {
                Logger.getLogger(SecondViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        drawMap();
        updateSnakePosition();
        drawSnake("FFFFFF", s.getCorpo());
        //s.setCorpo(c);
    }

}
