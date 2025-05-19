package controllers;

import creatureGroups.CreatureGroup;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import models.*;

@SuppressWarnings("StringTemplateMigration")
public class StartScreenController {
    @FXML private AnchorPane anchorPane;
    @FXML private Label welcomeMessage;
    @FXML private Label highestRegionMessage;
    @FXML private Label currentRegionMessage;
    @FXML private VBox promptBox;
    @FXML private Label promptLabel;
    @FXML private Button resetButton;
    @FXML private HBox yesNoBox;
    @FXML private Button creditsButton;
    @FXML private Button exitButton;
    private DatabaseDriver db;
    private Player player;
    private CreatureGroup creatureGroup;

    public void setDBandPlayer(DatabaseDriver db, Player player) {
        this.db = db;
        this.player = player;
    }

    public void setCreatureGroup(CreatureGroup creatureGroup) {
        this.creatureGroup = creatureGroup;
    }

    @FXML public void setUp() {
        ImageLoader imageLoader = new ImageLoader();
        BackgroundImage backgroundImage = imageLoader.loadImage("/images/ERStartScreen.jpg");
        anchorPane.setBackground(new Background(backgroundImage));
        welcomeMessage.setText("   Welcome to \n Eastern Roads!");
        updateHighestRegion();
        updateCurrentRegion("Germany");
    }

    // Automatically sets the Highest Region message to players highest
    public void updateHighestRegion() {
        highestRegionMessage.setText("Highest Region:\n" + OriginFactory.getHighestRegion(player));
    }

    // Automatically sets the Current Region messaged to whatever is given by parameter
    public void updateCurrentRegion(String region) {
        currentRegionMessage.setText("Current Region:\n" + region);
    }

    // ~~~Buttons Below~~~

    public void togglePromptBox(String text, boolean showButtons) {
        boolean show = !promptBox.isVisible();
        promptBox.setVisible(show);
        promptBox.setManaged(show);
        promptLabel.setText(text);
        yesNoBox.setVisible(showButtons);
        yesNoBox.setManaged(showButtons);
    }

    @FXML
    public void handleReset(ActionEvent event) {
        togglePromptBox("Are you sure you want to reset all your progress?\n" +
                "This action cannot be undone.", true);
    }

    @FXML public void handleYesReset(ActionEvent event) {
        try {
            db.alterHighestRegion(0);
            db.clearTables();
            togglePromptBox("", false);
        } catch (Exception e) {
            welcomeMessage.setText("Error resetting progress");
        }
    }

    @FXML public void handleNoReset(ActionEvent event) {
        togglePromptBox("", false);
    }

    @FXML
    public void handleCredits(ActionEvent event) {
        togglePromptBox("Game developed as a personal project by Shep Trundle,\n" +
                "undergraduate student at the University of Virginia.\n\n" +
                "Built using IntelliJ and JavaFX.\n\n" +
                "Art by Shep Trundle, made using pixilart.com", false);
    }

    @FXML public void handleExit(ActionEvent event) {
        try {
            db.disconnect();
            Platform.exit();
        } catch (Exception e) {
            welcomeMessage.setText("Error closing application");
        }
    }

    // One method for each item in region select dropdown menu
    @FXML public void handleSelectGermany(ActionEvent event) {setCreatureGroup(OriginFactory.getCreatureGroup("Germany")); updateCurrentRegion("Germany");}
    @FXML public void handleSelectHungary(ActionEvent event) { /*hungary implementation group here */ }
}
