package twitter;

import java.util.Date;

import javafx.fxml.FXML;

import javafx.scene.text.Text;
import twitter.utils.DateCalculator;
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

    @FXML
    Label retweetDateTextLabel;

    public void initPost(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt, String retweetUsername, String retweetHandle, String retweetContentText, Date updateDate,
            Date retweetDate) {

        super.initPost(username, handle, contentText, likeCnt, retweetCnt, replyCnt, updateDate);

        this.retweetUserName.setText(retweetUsername);
        this.retweetUserHandleLabel.setText(retweetHandle);
        this.retweetContentText.setText(retweetContentText);

        this.retweetDateTextLabel.setText(DateCalculator.getDateDiffText(retweetDate));

        if (contentText == null || contentText.equals("")) {
            retweetGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == null);

        } else {
            this.contentText.setText(contentText);
        }
    }

}
