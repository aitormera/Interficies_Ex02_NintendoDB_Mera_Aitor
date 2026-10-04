package com.project;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ControllerMobile2 implements Initializable {

    @FXML
    private Label title;

    @FXML
    private VBox detailPane;

    private ControllerDetail detailController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            detailController = UtilsList.loadDetail(detailPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDetail() {
        JSONObject obj = Main.getDades(Main.categoria).getJSONObject(Main.index);
        title.setText(obj.getString("name"));
        detailController.setData(Main.categoria, obj);
    }

    @FXML
    private void goBack(MouseEvent event) {
        UtilsViews.setViewAnimating("Mobile1");
    }
}
