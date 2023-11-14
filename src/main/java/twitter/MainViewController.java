package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class MainViewController {

    @FXML
    private AnchorPane postPane;

    public void initialize() {
        // Add another anchorpane, using FXML 'post.fxml'
        try {
            System.out.println("Loading post.fxml");
            AnchorPane post = FXMLLoader.load(getClass().getResource("post.fxml"));
            postPane.getChildren().add(post);

        } catch (IOException e) {
            System.out.println("Error loading post.fxml");
        }

    }

}
