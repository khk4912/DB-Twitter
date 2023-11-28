package twitter;

import javafx.fxml.FXML;

import javafx.scene.text.Text;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

public class RetweetPostController extends PostController {

    @FXML
    Label retweetUserName;

    @FXML
    Label retweetUserHandleLabel;

    @FXML
    Text retweetContentText;

    @FXML
    GridPane retweetGridPane;

    public void initPost(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt, String retweetUsername, String retweetHandle, String retweetContentText) {

        super.initPost(username, handle, contentText, likeCnt, retweetCnt, replyCnt);

        this.retweetUserName.setText(retweetUsername);
        this.retweetUserHandleLabel.setText(retweetHandle);
        this.retweetContentText.setText(retweetContentText);

        if (contentText == null || contentText.equals("")) {
            retweetGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == null);

        } else {
            this.contentText.setText(contentText);
        }
    }

}
