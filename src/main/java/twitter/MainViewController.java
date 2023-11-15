package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;

public class MainViewController {
    @FXML
    private GridPane postGridPane;

    @FXML
    private ScrollPane postScrollPane;

    public void initialize() {

        // Add another anchorpane, using FXML 'post.fxml'
        addPost("Demo", "@Demo", "This is a demo!");

        addPost("Demo2", "@random",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ac interdum nisl. Maecenas rhoncus rhoncus libero, ac lacinia nibh elementum nec. Donec tempus, lacus id porta cursus, erat lectus condimentum tortor, sed tincidunt dui est a erat. Nunc vel tellus eget mi gravida lacinia tincidunt ut nibh. Nam vitae enim in elit consequat pretium. Suspendisse porttitor nisi quis massa egestas commodo. Integer ultricies vel massa a iaculis. Donec porta, diam ac tincidunt malesuada, ipsum eros consequat sapien, pretium pharetra mi risus vitae tellus. Aliquam varius at eros vel luctus.");

    }

    private void addPost(String username, String handle, String contentText) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post.fxml"));

            AnchorPane post = fxmlLoader.load();
            PostController postController = fxmlLoader.getController();

            postController.initalizePost(username, handle, contentText);
            postGridPane.addRow(postGridPane.getRowCount(), post);

        } catch (IOException e) {
            System.out.println("Error loading post.fxml");
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void scrollFinished() {

        double scrollHeight = postScrollPane.getHeight();

        if (scrollHeight > 0.95) {
            // TODO: Add more posts!
        }

    }

}
