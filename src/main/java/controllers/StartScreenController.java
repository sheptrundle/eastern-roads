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
public class StartScreenController implements Controller {
    // Interface fields
    private DatabaseDriver db;
    private Player player;
    // FXML fields
    @FXML private AnchorPane anchorPane;
    @FXML private Label welcomeMessage;
    @FXML private Label highestRegionMessage;
    @FXML private Label currentRegionMessage;
    @FXML private VBox promptBox;
    @FXML private Label promptLabel;
    private int whichPromptBox;
    @FXML private Button myCreaturesButton;
    @FXML private Button resetButton;
    @FXML private HBox yesNoBox;
    @FXML private Button creditsButton;
    @FXML private Button exitButton;
    // Other fields
    private CreatureGroup creatureGroup;

    // Interface methods
    public DatabaseDriver getDatabaseDriver() {return db;}
    public Player getPlayer() {return player;}

    public void setDBandPlayer(DatabaseDriver db, Player player) {
        this.db = db;
        this.player = player;
        whichPromptBox = 0;
    }

    public void setCreatureGroup(CreatureGroup creatureGroup) {
        this.creatureGroup = creatureGroup;
    }

    public void setUp() {
        anchorPane.setBackground(SceneSwitcher.loadImage("/images/ERStartScreen.jpg"));
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

    // Hides prompt box
    public void hidePromptBox() {
        promptBox.setVisible(false);
        promptBox.setManaged(false);
    }

    // Shows prompt box with given text, shows buttons based on given boolean
    public void showPromptBox(String text, boolean showButtons) {
        promptBox.setVisible(true);
        promptBox.setManaged(true);
        promptLabel.setText(text);
        yesNoBox.setVisible(showButtons);
        yesNoBox.setManaged(showButtons);
    }

    @FXML
    public void handleMyCreatures(ActionEvent event) {
        try {
            SceneSwitcher.changeScene("/fxml/my-creatures.fxml", this, event, "My Creatures");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @FXML
    public void handleReset(ActionEvent event) {
        if (!promptBox.isVisible() || (promptBox.isVisible() && whichPromptBox != 0)) {
            showPromptBox("Are you sure you want to reset all your progress?\n" +
                    "This action cannot be undone.", true);
            whichPromptBox = 0;
        } else {
            hidePromptBox();
        }
    }

    @FXML public void handleYesReset(ActionEvent event) {
        try {
            db.alterHighestRegion(0);
            db.clearTables();
            showPromptBox("", false);
        } catch (Exception e) {
            welcomeMessage.setText("Error resetting progress");
        }
    }

    @FXML public void handleNoReset(ActionEvent event) {
        hidePromptBox();
    }

    @FXML
    public void handleCredits(ActionEvent event) {
        if (!promptBox.isVisible() || (promptBox.isVisible() && whichPromptBox != 1)) {
            showPromptBox("Game developed as a personal project by Shep Trundle,\n" +
                    "undergraduate student at the University of Virginia.\n\n" +
                    "Built using IntelliJ and JavaFX.\n\n" +
                    "Art by Shep Trundle, made using pixilart.com", false);
            whichPromptBox = 1;
        } else {
            hidePromptBox();
        }
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
