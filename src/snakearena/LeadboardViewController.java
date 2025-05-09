/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class LeadboardViewController implements Initializable {

    private BufferedReader br;
    private BufferedWriter bw;
    @FXML
    TextArea txArea;

    private void readFile() {
        try {
            File file = new File("src/resources/lead.txt");

            if (!file.exists()){
                file.createNewFile();
                System.out.println("File -> " + file.getName());
            }
            br = new BufferedReader(new FileReader(file.toString()));
            while (br.ready()) {
                System.out.println(br.readLine());
                txArea.appendText(br.readLine());
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void writeFile(String file, String s) {
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(s);
            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void handleBtn(ActionEvent event) {

        SceneController sc = new SceneController();
        try {
            sc.switchToScene1(event);
        } catch (IOException ex) {
            Logger.getLogger(LeadboardViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        readFile();

    }

}
