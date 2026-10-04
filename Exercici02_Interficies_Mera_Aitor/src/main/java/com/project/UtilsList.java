package com.project;

import java.net.URL;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class UtilsList {

    public static void fillList(Pane pane, String categoria, Consumer<Integer> onSelect) throws Exception {

        URL resource = UtilsList.class.getResource("/assets/listItem.fxml");

        pane.getChildren().clear();

        JSONArray dades = Main.getDades(categoria);
        for (int i = 0; i < dades.length(); i++) {
            JSONObject obj = dades.getJSONObject(i);

            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();

            itemController.setTitle(obj.getString("name"));
            itemController.setImatge("/assets/images/" + obj.getString("image"));

            final int index = i;
            itemTemplate.setOnMouseClicked((event) -> { onSelect.accept(index); });

            pane.getChildren().add(itemTemplate);
        }
    }

    public static ControllerDetail loadDetail(Pane pane) throws Exception {
        URL resource = UtilsList.class.getResource("/assets/detail.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent detail = loader.load();
        pane.getChildren().add(detail);
        return loader.getController();
    }
}
