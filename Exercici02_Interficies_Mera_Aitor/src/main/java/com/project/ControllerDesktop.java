package com.project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class ControllerDesktop implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private VBox yPane, detailPane;

    private ControllerDetail detailController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            detailController = UtilsList.loadDetail(detailPane);
            detailPane.setVisible(false);

            choiceBox.getItems().addAll("Personatges", "Jocs", "Consoles");
            choiceBox.setValue(Main.categoria);
            choiceBox.setOnAction((event) -> { showList(choiceBox.getValue()); });

            showList(Main.categoria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showList(String categoria) {
        try {
            Main.categoria = categoria;
            detailPane.setVisible(false);
            UtilsList.fillList(yPane, categoria, (index) -> { showDetail(index); });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDetail(int index) {
        Main.index = index;
        detailController.setData(Main.categoria, Main.getDades(Main.categoria).getJSONObject(index));
        detailPane.setVisible(true);
    }
}
