package twitter;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class PostController {

    @FXML
    Label usernameLabel;

    @FXML
    Label handleLabel;

    @FXML
    Text contentText;

    public void initalizePost(String username, String handle, String contentText) {
        this.usernameLabel.setText(username);
        this.handleLabel.setText(handle);
        this.contentText.setText(contentText);

    }

}