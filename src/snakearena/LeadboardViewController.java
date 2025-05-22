/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package snakearena;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView table;

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
            while (br.ready()) {

                line = br.readLine();
                System.out.println(line);
                noems.add(line);
            }
            br.close();
            return noems;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return noems;
    }

    public void writeFile(String s, String cor, String modo) {
        Calendar date = Calendar.getInstance(Locale.ENGLISH);
        StringBuilder sB = new StringBuilder();
        ArrayList<String> a = new ArrayList<>();

        try {
            a = readFile();
            File file = new File("src/resources/lead.txt");

            bw = new BufferedWriter(new FileWriter(file.toString()));
            sB.append(s);
            sB.append("," + date.getTime());
            sB.append("," + cor);
            sB.append("," + modo);
            System.out.println(sB);
            a.add(sB.toString());

            for (String string : a) {
                bw.write(string);
                bw.write("\n");
            }

            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void handleBtn(ActionEvent event) {

        SceneController sc = new SceneController();
        sc.switchToScene(event, "MainView.fxml");
        Stage thisStage = (Stage) table.getScene().getWindow();
        thisStage.close();
        thisStage = null;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        txArea.setText(string);
        //writeFile("pedro,3000", "amarelo", "easy");
        readFile();
        ArrayList<String> a = new ArrayList<>();
        a = readFile();

 


        /* for (String i : a) {
            System.out.println(i);
        }*/
        a.sort((s1, s2) -> {
            int n1 = Integer.parseInt(s1.split(",")[1]);
            int n2 = Integer.parseInt(s2.split(",")[1]);
            return Integer.compare(n2, n1);
        });

        int n = 1;

        /* for (String i : a) {
            System.out.println(i);
        }
        for (String i : a) {
            txArea.appendText(n + ".\t" + i.replace(",", "\t\t") + "\n");
            n++;
        }
        txArea.positionCaret(0);
         */
        ObservableList<Dados> dados = FXCollections.observableArrayList();

        for (int i = 0; i < a.size(); i++) {
            String linha = a.get(0).strip();  // Remove espaços no início/fim
            String[] partes = linha.split(",");

            String nome = partes[0];
            int pontuacao = Integer.parseInt(partes[1]);
            String data = partes[2];
            String corHex = partes[3];
            String dificuldade = partes[4];
            Dados d = new Dados(i,nome,pontuacao,data,corHex,dificuldade);
            dados.add(d);
        }

        ObservableList<TableColumn> colunas = table.getColumns();

        //  tc1.setCellFactory(new PropertyValueFactory<>("inteiro"));
        for (TableColumn c : colunas) {
            c.setCellValueFactory(new PropertyValueFactory<>(c.getText()));
        }
        table.setItems(dados);
          
    }

}
