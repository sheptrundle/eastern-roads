import controllers.StartScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.DatabaseDriver;
import models.Player;

public class EasternRoadsApplication extends Application {
    public static void main(String[] args) { launch(args);}

    @Override
    public void start(Stage stage) throws Exception {

        // Set database and connect
        DatabaseDriver db = new DatabaseDriver();

        db.connect();
        db.createTables();
        Player player = db.getPlayer();
        db.addPlayer(player);
        db.commit();

        // Link database to controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start-screen.fxml"));
        Parent root = fxmlLoader.load();
        StartScreenController controller =  fxmlLoader.getController();
        controller.setDBandPlayer(db, player);
        controller.setUp();

        // Set scene
        Scene scene = new Scene(root);
        stage.setTitle("Eastern Roads");
        stage.setScene(scene);
        stage.show();
    }
}
