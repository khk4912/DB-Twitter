package twitter;

import java.io.IOException;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import twitter.utils.TwitterAlert;
import twitter.utils.PostContext;

public class RetweetPopupController {
    @FXML
    TextArea contentTextArea;

    @FXML
    Button submitButton;

    GridPane targetGridPane;
    PostContext postContext;

    public void setTargetGridPane(GridPane targetGridPan) {
        this.targetGridPane = targetGridPan;

    }

    public void setPostContext(PostContext postContext) {
        this.postContext = postContext;
    }

    public void initialize() {
        submitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            String content;
            if (contentTextArea.getText().equals("") || contentTextArea.getText() == null) {
                content = null;
            } else {
                content = contentTextArea.getText();
            }

            addRetweetPost(content);
            TwitterAlert.info("리트윗 완료", "리트윗이 완료되었습니다. ");

        });
    }

    public void addRetweetPost(String content) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("retweet_post.fxml"));
            AnchorPane retweetPost = fxmlLoader.load();

            RetweetPostController retweetPostController = fxmlLoader.getController();

            retweetPostController.initPost(App.loginContext.user.nickname, "@" + App.loginContext.user.userID,
                    content, 0, 0, 0, postContext.nickname, postContext.writerID, postContext.content,
                    new Date(), postContext.updateDate);

            targetGridPane.addRow(targetGridPane.getRowCount(), retweetPost);

        } catch (IOException e) {
            System.out.println("Error loading retweet_post.fxml");
            System.out.println(e.getMessage());
        }

    }

}
