package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ScrollPane;

public class PostViewController {

    @FXML
    private GridPane postGridPane;

    private ScrollPane postScrollPane;

    public void initScrollPane(ScrollPane postScrollPane) {
        this.postScrollPane = postScrollPane;
    }

    public void addPost(String username, String handle, String contentText) {
        addPost(username, handle, contentText, 0, 0, 0);
    }

    public void addPost(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post.fxml"));

            AnchorPane post = fxmlLoader.load();
            PostController postController = fxmlLoader.getController();

            postController.initPost(username, handle, contentText, likeCnt, retweetCnt, replyCnt);
            postGridPane.addRow(postGridPane.getRowCount(), post);

        } catch (IOException e) {
            System.out.println("Error loading post.fxml");
            System.out.println(e.getMessage());
        }

    }

    public void addRetweetPost(String username, String handle, String contentText, String retweetUsername,
            String retweetHandle, String retweetContentText) {
        addRetweetPost(username, handle, contentText, retweetUsername, retweetHandle, retweetContentText, 0, 0, 0);
    }

    public void addRetweetPost(String username, String handle, String contentText, String retweetUsername,
            String retweetHandle, String retweetContentText, int likeCnt, int retweetCnt, int replyCnt) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("retweet_post.fxml"));

            AnchorPane post = fxmlLoader.load();
            RetweetPostController retweetPostController = fxmlLoader.getController();

            retweetPostController.initPost(username, handle, contentText, likeCnt, retweetCnt, replyCnt,
                    retweetUsername, retweetHandle, retweetContentText);
            postGridPane.addRow(postGridPane.getRowCount(), post);

        } catch (IOException e) {
            System.out.println("Error loading retweet_post.fxml");
            System.out.println(e.getMessage());
        }

    }

    // Scroll이 거의 다 되었으면, Vvalue 확인하여 새로운 post 삽입
    @FXML
    private void scrollFinished() {

        double scrollHeight = postScrollPane.getVvalue();

        if (scrollHeight > 0.85) {
            // TODO: Add more posts!

        }

    }

}
