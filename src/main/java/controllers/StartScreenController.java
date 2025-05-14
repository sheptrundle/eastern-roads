package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import models.DatabaseDriver;
import models.ImageLoader;
import models.SceneSwitcher;

public class StartScreenController {
    @FXML private AnchorPane anchorPane;
    @FXML private Label message;
    private DatabaseDriver db;

    public void setDatabaseDriver(DatabaseDriver db) {
        this.db = db;
    }

    @FXML public void initialize() {
        ImageLoader imageLoader = new ImageLoader();
        BackgroundImage backgroundImage = imageLoader.loadImage("/images/ERStartScreen.jpg");
        anchorPane.setBackground(new Background(backgroundImage));
    }

    @FXML public void handleMyCreatures(ActionEvent event) {
        try {
            SceneSwitcher.changeScene("my-creature.fxml", event, "My Creature");
        } catch (Exception e) {
            message.setText("Error loading new scene");
        }
    }
}
