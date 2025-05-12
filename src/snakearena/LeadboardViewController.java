/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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

    private ArrayList<String> readFile() {

        ArrayList<String> noems = new ArrayList<>();
        try {
            File file = new File("src/resources/lead.txt");

            if (!file.exists()) {

                file.createNewFile();
                System.out.println("File -> " + file.getName());
            }
            br = new BufferedReader(new FileReader(file.toString()));
            String line;
            int n = 1;
            while (br.ready()) {

                line = br.readLine();
                System.out.println(line);
                noems.add(line);
                n++;
            }
            br.close();
            return noems;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return noems;
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
//        txArea.setText(string);
        ArrayList<String> a = new ArrayList<>();
        a = readFile();
            
        for(String i : a){
            System.out.println(i);
        }
        
        a.sort((s1, s2) -> {
            int n1 = Integer.parseInt(s1.split(",")[1]);
            int n2 = Integer.parseInt(s2.split(",")[1]);
            return Integer.compare(n2, n1);
        });

        int n = 1;
        
        
            
        for(String i : a){
            System.out.println(i);
        }
        for(String i: a){
           txArea.appendText(n + ".\t" + i.replace(",", "\t\t") + "\n");
            n++;
        }
        
        
        
    }
    

}
