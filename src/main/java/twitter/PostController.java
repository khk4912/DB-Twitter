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
    Label likeCount;

    @FXML
    Label retweetCount;

    @FXML
    Label commentCount;

    @FXML
    Text contentText;

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

}