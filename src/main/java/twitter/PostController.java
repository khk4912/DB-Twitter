package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;

public class PostController {

    @FXML
    Label usernameLabel;

    @FXML
    Label handleLabel;

    @FXML
    Label likeCount;

    @FXML
    Label retweetCount;

    @FXML
    Label commentCount;

    @FXML
    Text contentText;

    @FXML
    ImageView menuIcon;

    @FXML
    ColumnConstraints userNameGridPane;

    @FXML
    ColumnConstraints userHandleGridPane;

    public void initalizePost(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt) {

        this.usernameLabel.setText(username);
        this.handleLabel.setText(handle);
        this.contentText.setText(contentText);

        this.likeCount.setText(Integer.toString(likeCnt));
        this.retweetCount.setText(Integer.toString(retweetCnt));
        this.commentCount.setText(Integer.toString(replyCnt));

    }

    @FXML
    private void showPostPopupMenu(ContextMenuEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post_popup_menu.fxml"));

            ContextMenu contextMenu = fxmlLoader.load();
            contextMenu.show(menuIcon, event.getScreenX(), event.getScreenY());

        } catch (IOException e) {
            System.out.println("Error loading post_popup_menu.fxml");
            System.out.println(e.getMessage());
        }

    }
}