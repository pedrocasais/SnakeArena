/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class ImagemController implements Initializable {

   @FXML
    private StackPane stackPane;

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
    imageView.fitWidthProperty().bind(stackPane.widthProperty());
    imageView.fitHeightProperty().bind(stackPane.heightProperty());
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
