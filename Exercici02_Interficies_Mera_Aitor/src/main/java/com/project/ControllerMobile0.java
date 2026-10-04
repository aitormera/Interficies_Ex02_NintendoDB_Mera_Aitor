package com.project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControllerMobile0 implements Initializable {

    @FXML
    private VBox yPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] categories = { "Personatges", "Jocs", "Consoles" };
        for (String categoria : categories) {
            Label label = new Label(categoria);
            label.setMaxWidth(Double.MAX_VALUE);
            label.setStyle("-fx-padding: 12; -fx-border-color: transparent transparent #cccccc transparent;");
            label.setOnMouseClicked((event) -> { toList(categoria); });
            yPane.getChildren().add(label);
        }
    }

    private void toList(String categoria) {
        Main.categoria = categoria;
        ControllerMobile1 ctrl1 = (ControllerMobile1) UtilsViews.getController("Mobile1");
        ctrl1.showList();
        UtilsViews.setViewAnimating("Mobile1");
    }
}
