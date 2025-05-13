package models;

public class DatabaseRunnable {
    public static void main(String[] args) {
        try {
            DatabaseDriver db = new DatabaseDriver();
            db.connect();
            db.createTables();
            GermanyCreatures germanyCreatures = new GermanyCreatures();
            db.addCreature(germanyCreatures.getCreature("drude"));
            db.commit();
            db.disconnect();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
