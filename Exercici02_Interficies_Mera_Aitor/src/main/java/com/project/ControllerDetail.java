package com.project;

import java.util.Objects;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControllerDetail {

    @FXML
    private ImageView img;

    @FXML
    private Label name, info;

    @FXML
    private Rectangle colorBox;

    public void setData(String categoria, JSONObject obj) {
        String imagePath = "/assets/images/" + obj.getString("image");
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            img.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Error loading image asset: " + imagePath);
        }

        name.setText(obj.getString("name"));

        if (obj.has("color")) {
            colorBox.setFill(Color.web(obj.getString("color")));
            colorBox.setVisible(true);
        } else {
            colorBox.setVisible(false);
        }

        switch (categoria) {
            case "Personatges":
                info.setText(obj.getString("game"));
                break;
            case "Consoles":
                info.setText(obj.getString("date") + "\n" + obj.getString("procesador") + "\n" + obj.getInt("units_sold") + " unitats venudes");
                break;
            default:
                info.setText(obj.getString("plot"));
                break;
        }
    }
}
