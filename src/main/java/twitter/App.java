package twitter;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import atlantafx.base.theme.PrimerLight;

import twitter.db.DB;
import twitter.utils.LoginContext;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    public static DB DB;
    public static LoginContext loginContext;

    @Override
    public void start(Stage stage) throws IOException {
        // DB Initalize
        DB = new DB();
        DB.makeConnection("user", "mypassword", "Twitter");

        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        Font.loadFont(App.class.getResourceAsStream("assets/Pretendard-Regular.ttf").toString(), 10);
        Font.loadFont(App.class.getResourceAsStream("assets/Pretendard-Black.ttf").toString(), 10);

        scene = new Scene(loadFXML("login_form"), 400, 300);

        stage.setTitle("Twitter");
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
        launch();
    }

    public static void setWidth(int width) {
        scene.getWindow().setWidth(width);
    }

    public static void setHeight(int height) {
        scene.getWindow().setHeight(height);
    }

}