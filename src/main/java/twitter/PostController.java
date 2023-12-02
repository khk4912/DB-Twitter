package twitter;

import java.io.IOException;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.text.Text;
import twitter.utils.DateCalculator;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import twitter.DetailedPostController;

public class PostController {

    boolean liked = false;
    boolean retweeted = false;

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
    ImageView heartImage;

    @FXML
    ColumnConstraints userNameGridPane;

    @FXML
    ColumnConstraints userHandleGridPane;

    @FXML
    Label dateTextLabel;

    public void initialize() {
        usernameLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            loadWriterProfileView();
        });

        handleLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            loadWriterProfileView();
        });

        likeCount.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            like();
        });

        heartImage.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            like();
        });

    }

    public void initPost(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt) {

        initPost(username, handle, contentText, likeCnt, retweetCnt, replyCnt, new Date());
    }

    public void initPost(String username, String handle, String contentText, int likeCnt, int retweetCnt,
            int replyCnt, Date updateDate) {

        this.usernameLabel.setText(username);
        this.handleLabel.setText(handle);
        this.contentText.setText(contentText);

        this.likeCount.setText(Integer.toString(likeCnt));
        this.retweetCount.setText(Integer.toString(retweetCnt));
        this.commentCount.setText(Integer.toString(replyCnt));

        this.dateTextLabel.setText(DateCalculator.getDateDiffText(updateDate));

    }

    private void showPopup(double X, double Y) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post_popup_menu.fxml"));
            ContextMenu contextMenu = fxmlLoader.load();
            PostPopupMenuController postPopupMenuController = fxmlLoader.getController();

            // TODO: change isMyPost
            postPopupMenuController.initialize(true);
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

    @FXML
    private void loadDetailedPostView() {
        // Create new popup window
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("detailed_post_view.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            DetailedPostController detailedPostViewController = fxmlLoader.getController();
            detailedPostViewController.initPost(usernameLabel.getText(), handleLabel.getText(), contentText.getText(),
                    Integer.parseInt(likeCount.getText()), Integer.parseInt(retweetCount.getText()),
                    Integer.parseInt(commentCount.getText()), new Date());

            Stage stage = new Stage();
            stage.setTitle("글 보기");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadWriterProfileView() {
        // Create new popup window
        try {
            String userIDString = handleLabel.getText().substring(1);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile_view.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            ProfileViewController profileViewController = fxmlLoader.getController();
            profileViewController.initProfile(usernameLabel.getText(), userIDString, "자기소개가 설정되지 않았습니다.");

            Stage stage = new Stage();
            stage.setTitle("프로필 보기");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void like() {
        if (liked) {
            liked = false;
            heartImage.setImage(new javafx.scene.image.Image(App.class.getResource("assets/like.png").toString()));
            likeCount.setText(Integer.toString(Integer.parseInt(likeCount.getText()) - 1));
        } else {
            liked = true;
            heartImage.setImage(new javafx.scene.image.Image(App.class.getResource("assets/liked.png").toString()));
            likeCount.setText(Integer.toString(Integer.parseInt(likeCount.getText()) + 1));
        }
    }
}