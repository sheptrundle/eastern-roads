package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import models.DatabaseDriver;
import models.ImageLoader;

public class StartScreenController {
    @FXML private AnchorPane anchorPane;
    private DatabaseDriver db;

    public void setDatabaseDriver(DatabaseDriver db) {
        this.db = db;
    }

    @FXML public void initialize() {
        ImageLoader imageLoader = new ImageLoader();
        BackgroundImage backgroundImage = imageLoader.loadImage("/images/ERStartScreen.jpg");
        anchorPane.setBackground(new Background(backgroundImage));
    }
}
