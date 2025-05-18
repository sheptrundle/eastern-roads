package models;

import creatureGroups.GermanyCreatures;

public class DatabaseRunnable {
    public static void main(String[] args) {
        try {
            DatabaseDriver db = new DatabaseDriver();
            db.connect();
            db.deleteTables();
            db.createTables();
            GermanyCreatures germanyCreatures = new GermanyCreatures();
            db.addCreature(germanyCreatures.getCreature("drude"));
            Player player = new Player();
            db.addPlayer(player);
            db.commit();
            db.disconnect();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
