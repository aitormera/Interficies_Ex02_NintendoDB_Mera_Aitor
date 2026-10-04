package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ControllerMobile1 {

    @FXML
    private Label title;

    @FXML
    private VBox yPane;

    public void showList() {
        try {
            title.setText(Main.categoria);
            UtilsList.fillList(yPane, Main.categoria, (index) -> { toDetail(index); });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toDetail(int index) {
        Main.index = index;
        ControllerMobile2 ctrl2 = (ControllerMobile2) UtilsViews.getController("Mobile2");
        ctrl2.showDetail();
        UtilsViews.setViewAnimating("Mobile2");
    }

    @FXML
    private void goBack(MouseEvent event) {
        UtilsViews.setViewAnimating("Mobile0");
    }
}
