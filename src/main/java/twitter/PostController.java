package twitter;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;

public class PostController {

    @FXML
    Label usernameLabel;

    @FXML
    Label handleLabel;

    @FXML
    Text contentText;

    @FXML
    ColumnConstraints userNameGridPane;

    @FXML
    ColumnConstraints userHandleGridPane;

    public void initalizePost(String username, String handle, String contentText) {

        this.usernameLabel.setText(username);
        this.handleLabel.setText(handle);
        this.contentText.setText(contentText);

        this.userNameGridPane.setPrefWidth(username.length() * 10);
        this.userHandleGridPane.setPrefWidth(handle.length() * 10);

    }

}