public class Main {
    public static void main(String[] args) {
        try {
            DatabaseDriver db = new DatabaseDriver();
            db.connect();
            db.createTables();
            db.commit();
            db.disconnect();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }
}
