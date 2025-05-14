package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import models.DatabaseDriver;
import models.ImageLoader;
import models.Player;

public class StartScreenController {
    @FXML private AnchorPane anchorPane;
    @FXML private Label message;
    @FXML private VBox creditsBox;
    @FXML private Label creditsLabel;
    @FXML private Button creditsButton;
    @FXML private Button exitButton;
    private DatabaseDriver db;
    private Player player;

    public void setDBandPlayer(DatabaseDriver db, Player player) {
        this.db = db;
        this.player = player;
    }

    @FXML public void initialize() {
        ImageLoader imageLoader = new ImageLoader();
        BackgroundImage backgroundImage = imageLoader.loadImage("/images/ERStartScreen.jpg");
        anchorPane.setBackground(new Background(backgroundImage));
        message.setText("   Welcome to \n Eastern Roads!");
    }

    @FXML
    public void handleCredits(ActionEvent event) {
        boolean show = !creditsBox.isVisible();
        creditsBox.setVisible(show);
        creditsBox.setManaged(show);
        creditsLabel.setText("Game developed as a personal project by Shep Trundle,\n" +
                "undergraduate student at the University of Virginia.\n\n" +
                "Built using IntelliJ and JavaFX.\n\n" +
                "Art by Shep Trundle, made using pixilart.com");
    }

    @FXML public void handleExit(ActionEvent event) {
        try {
            db.disconnect();
            Platform.exit();
        } catch (Exception e) {
            message.setText("Error closing application");
        }
    }
}
