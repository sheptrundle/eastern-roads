package models;

import controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneSwitcher {
    public static void changeScene(String fxmlPath, Controller currentController, ActionEvent event, String title) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.setDBandPlayer(currentController.getDatabaseDriver(), currentController.getPlayer());
            controller.setUp();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public static Background loadImage(String path) {
        ImageLoader imageLoader = new ImageLoader();
        return new Background(imageLoader.loadImage(path));
    }
}