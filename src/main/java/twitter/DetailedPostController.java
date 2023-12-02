package twitter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.Date;
import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DetailedPostController extends PostController {

    @FXML
    TextField commentInput;

    @FXML
    Button submitCommentButton;

    @FXML
    GridPane postGridPane;

    @FXML
    Label commentCount;

    public void addComment(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt, Date updateDate) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post.fxml"));

            AnchorPane post = fxmlLoader.load();
            PostController postController = fxmlLoader.getController();

            postController.initPost(username, handle, contentText, likeCnt, retweetCnt, replyCnt, updateDate);
            postGridPane.addRow(postGridPane.getRowCount(), post);

            commentCount.setText(Integer.toString(postGridPane.getRowCount()));

        } catch (IOException e) {
            System.out.println("Error loading post.fxml");
            System.out.println(e.getMessage());
        }

    }

    public void initialize() {
        super.initialize();
        submitCommentButton.setOnAction((e) -> {
            if (commentInput.getText().equals("") || commentInput.getText() == null) {
                return;
            }

            addComment(App.loginContext.user.nickname, "@" + App.loginContext.user.userID, commentInput.getText(), 0, 0,
                    0, new Date());
        });
    }

}