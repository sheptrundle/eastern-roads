package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import models.DatabaseDriver;
import models.Player;
import models.SceneSwitcher;

public class MyCreaturesController implements Controller {
    // Interface fields
    private DatabaseDriver db;
    private Player player;
    // FXML fields
    @FXML private AnchorPane anchorPane;
    // Other fields



    // Interface methods
    public DatabaseDriver getDatabaseDriver() {return db;}
    public Player getPlayer() {return player;}

    public void setDBandPlayer(DatabaseDriver db, Player player) {
        this.db = db;
        this.player = player;
    }

    public void setUp() {
        anchorPane.setBackground(SceneSwitcher.loadImage("/images/ERMyCreatures.jpg"));
    }
}
