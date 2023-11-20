package twitter;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import twitter.utils.DB;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(App.class.getResource("primer-light.css").toString());

        scene = new Scene(loadFXML("login_form"), 400, 300);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // DB Initalize

        DB.makeConnection("user", "mypassword", "twitter");

        launch();
    }

    public static void setWidth(int width) {
        scene.getWindow().setWidth(width);
    }

    public static void setHeight(int height) {
        scene.getWindow().setHeight(height);
    }

}