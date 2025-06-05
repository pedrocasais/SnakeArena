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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
        ArrayList<String> a = readFile();

        a.sort((s1, s2) -> {
            int n1 = Integer.parseInt(s1.split(",")[1]);
            int n2 = Integer.parseInt(s2.split(",")[1]);
            return Integer.compare(n2, n1);
        });

        ObservableList<Dados> dados = FXCollections.observableArrayList();

        for (int i = 0; i < a.size(); i++) {
            String linha = a.get(i).strip();
            String[] partes = linha.split(",");
            if (partes.length < 5) continue;
            String nome = partes[0];
            int pontuacao = Integer.parseInt(partes[1]);
            String data = partes[2];
            String Cor = partes[3];
            String dificuldade = partes[4];
            Dados d = new Dados(i+1, nome, pontuacao, data, Cor, dificuldade);
            dados.add(d);
        }

        // Criação explícita das colunas para garantir que todas aparecem corretamente
        TableColumn<Dados, Integer> posicaoCol = new TableColumn<>("posição");
        posicaoCol.setCellValueFactory(new PropertyValueFactory<>("posicao"));

        TableColumn<Dados, String> nomeCol = new TableColumn<>("nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Dados, Integer> pontuacaoCol = new TableColumn<>("pontuacao");
        pontuacaoCol.setCellValueFactory(new PropertyValueFactory<>("pontuacao"));

        TableColumn<Dados, String> dataCol = new TableColumn<>("data");
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Dados, String> corCol = new TableColumn<>("cor");
        corCol.setCellValueFactory(new PropertyValueFactory<>("cor"));
        // Mostra um retângulo colorido na célula da cor
        corCol.setCellFactory(column -> new TableCell<Dados, String>() {
            private final Rectangle rect = new Rectangle(30, 16);
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    try {
                        rect.setFill(Color.web(item));
                    } catch (Exception e) {
                        rect.setFill(Color.TRANSPARENT);
                    }
                    setGraphic(rect);
                }
                setText(null);
            }
        });

        TableColumn<Dados, String> dificuldadeCol = new TableColumn<>("dificuldade");
        dificuldadeCol.setCellValueFactory(new PropertyValueFactory<>("dificuldade"));

        table.getColumns().setAll(posicaoCol, nomeCol, pontuacaoCol, dataCol, corCol, dificuldadeCol);
        table.setItems(dados);
    }

}
