package controllers;

import models.DatabaseDriver;
import models.Player;

public interface Controller {
    DatabaseDriver getDatabaseDriver();
    Player getPlayer();
    void setDBandPlayer(DatabaseDriver db, Player player);
    void setUp();
}
