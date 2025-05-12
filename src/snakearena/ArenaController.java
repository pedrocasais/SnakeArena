package snakearena;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.Initializable;

public class ArenaController  {    
    
    @FXML
    private Canvas gameCanvas;
    public static final int WIDTH = 616;
    private static final int HEIGHT = 439;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final double SQUARE_SIZE = WIDTH / ROWS;
    private static final String[] FOODS_IMAGE = new String[]{"/resources/images/ic_orange.png", "/resources/images/ic_apple.png", "/resources/images/ic_cherry.png",
            "/resources/images/ic_berry.png", "/resources/images/ic_coconut_.png", "/resources/images/ic_peach.png", "/resources/images/ic_watermelon.png", "/resources/images/ic_orange.png",
            "/resources/images/ic_pomegranate.png"};

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private Image foodImage;
    private int foodX, foodY;
    private boolean gameOver;
    private int currentDirection;
    private int score = 0;
    private Timeline timeline;

    @FXML
    public void initialize() {

        gc = gameCanvas.getGraphicsContext2D();

        gameCanvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
        if (newScene != null) {
        newScene.setOnKeyPressed(this::handleKeyPress);
            }
        });


        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        generateFood();

        timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        switch (code) {
            case RIGHT, D -> { if (currentDirection != LEFT) currentDirection = RIGHT; }
            case LEFT, A -> { if (currentDirection != RIGHT) currentDirection = LEFT; }
            case UP, W -> { if (currentDirection != DOWN) currentDirection = UP; }
            case DOWN, S -> { if (currentDirection != UP) currentDirection = DOWN; }
        }
    }

    private void run() {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
            return;
        }

        drawBackground();
        drawFood();
        drawSnake();
        drawScore();

        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

        switch (currentDirection) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }

        checkGameOver();
        eatFood();
    }

    private void drawBackground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.web("AAD751") : Color.web("A2D149"));
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawSnake() {
        gc.setFill(Color.web("4674E9"));
        gc.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);
        for (int i = 1; i < snakeBody.size(); i++) {
            gc.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }

    private void drawFood() {
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUp() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }

    private void checkGameOver() {
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x >= ROWS || snakeHead.y >= COLUMNS) {
            gameOver = true;
        }

        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).x && snakeHead.y == snakeBody.get(i).y) {
                gameOver = true;
                break;
            }
        }
    }

    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            generateFood();
            score += 5;
        }
    }

    private void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + score, 10, 35);
    }
}
