package com.project;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONArray;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    final int WINDOW_WIDTH = 800;
    final int WINDOW_HEIGHT = 600;
    final int MIN_WIDTH = 300;
    final int MIN_HEIGHT = 500;

    static JSONArray personatges;
    static JSONArray jocs;
    static JSONArray consoles;
    static String categoria = "Jocs";
    static int index = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        personatges = loadJSON("/assets/data/characters.json");
        jocs = loadJSON("/assets/data/games.json");
        consoles = loadJSON("/assets/data/consoles.json");

        UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
        UtilsViews.addView(getClass(), "Desktop", "/assets/layout_desktop.fxml");
        UtilsViews.addView(getClass(), "Mobile0", "/assets/layout_mobile0.fxml");
        UtilsViews.addView(getClass(), "Mobile1", "/assets/layout_mobile1.fxml");
        UtilsViews.addView(getClass(), "Mobile2", "/assets/layout_mobile2.fxml");

        Scene scene = new Scene(UtilsViews.parentContainer);

        scene.widthProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldWidth, Number newWidth) {
                _setLayout(newWidth.intValue());
            }
        });

        stage.setScene(scene);
        stage.setTitle("NintendoDB");
        stage.setMinWidth(MIN_WIDTH);
        stage.setWidth(WINDOW_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setHeight(WINDOW_HEIGHT);
        stage.show();

        if (!System.getProperty("os.name").contains("Mac")) {
            Image icon = new Image("file:/icons/icon.png");
            stage.getIcons().add(icon);
        }
    }

    private void _setLayout(int width) {
        String active = UtilsViews.getActiveView();
        if (width < 600) {
            if ("Desktop".equals(active)) {
                UtilsViews.setView("Mobile0");
            }
        } else {
            if (!"Desktop".equals(active)) {
                UtilsViews.setView("Desktop");
            }
        }
    }

    private JSONArray loadJSON(String resource) throws Exception {
        URL jsonFileURL = getClass().getResource(resource);
        Path path = Paths.get(jsonFileURL.toURI());
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        return new JSONArray(content);
    }

    static JSONArray getDades(String categoria) {
        switch (categoria) {
            case "Personatges":
                return personatges;
            case "Consoles":
                return consoles;
            default:
                return jocs;
        }
    }
}
