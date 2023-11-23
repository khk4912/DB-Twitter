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
import javafx.scene.input.MouseEvent;

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

    private void showPopup(double X, double Y) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post_popup_menu.fxml"));
            ContextMenu contextMenu = fxmlLoader.load();
            PostPopupMenuController postPopupMenuController = fxmlLoader.getController();

            // TODO: change isMyPost
            postPopupMenuController.initlaize(true);
            contextMenu.show(menuIcon, X, Y);

        } catch (IOException e) {
            System.out.println("Error loading post_popup_menu.fxml");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void showPostPopupMenuRequested(ContextMenuEvent event) {
        showPopup(event.getScreenX(), event.getScreenY());
    }

    @FXML
    private void showPostPopupMenuClicked(MouseEvent event) {
        showPopup(event.getScreenX(), event.getScreenY());
    }
}