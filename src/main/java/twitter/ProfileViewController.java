package twitter;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import twitter.db.PostSearch;
import twitter.utils.PostContext;
import twitter.db.Follow;

public class ProfileViewController extends PostViewController {
    private PostSearch postSearch = new PostSearch(App.DB.getConnection());
    Follow follow = new Follow(App.DB.getConnection());
    private boolean isfollowing = false;

    @FXML
    Text userNameLabel;

    @FXML
    Text userHandleLabel;

    @FXML
    Text bio;

    @FXML
    GridPane emptyNotifyGridPane;

    @FXML
    Button followButton;

    @FXML
    Button editProfileButton;

    @FXML
    Label followCnt;

    @FXML
    Label followerCnt;

    public void initProfile(String username, String handle, String bio) {
        userNameLabel.setText(username);
        userHandleLabel.setText('@' + handle);

        followCnt.setText(Integer.toString(follow.getFollowingCountOf(handle)));
        followerCnt.setText(Integer.toString(follow.getFollowerCountOf(handle)));

        if (bio == null || bio.equals("null")) {
            bio = "자기소개가 설정되지 않았습니다.";
        }
        this.bio.setText(bio);

        ArrayList<PostContext> posts = postSearch.searchByID(handle);

        if (posts.size() == 0) {
            System.out.println("No Posts!");
            showEmptyNotify();
        }

        for (PostContext post : posts) {
            addPost(post);
        }

        if (App.loginContext.user.userID.equals(handle)) {
            followButton.setVisible(false);
            editProfileButton.setVisible(true);
        } else {
            followButton.setVisible(true);
            editProfileButton.setVisible(false);
        }
        isfollowing = follow.checkFollowing(App.loginContext.user.userID, handle);

        if (isfollowing) {
            followButton.setText("언팔로우");
        } else {
            followButton.setText("팔로우");
        }
    }

    public void initialize() {
        followButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            handleFollowButtonClick();
        });

        followCnt.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            handleFollowingCntClick();
        });

        followerCnt.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            handleFollowerCntClick();
        });
    }

    public void handleFollowingCntClick() {
        // new popup screen
        try {
            String ID = userHandleLabel.getText().substring(1);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("followlist_view.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            FollowListController followListController = fxmlLoader.getController();
            followListController.init(false, ID);

            Stage stage = new Stage();
            stage.setTitle("팔로잉 리스트");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleFollowerCntClick() {
        // new popup screen
        try {
            String ID = userHandleLabel.getText().substring(1);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("followlist_view.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            FollowListController followListController = fxmlLoader.getController();
            followListController.init(true, ID);

            Stage stage = new Stage();
            stage.setTitle("팔로워 리스트");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleFollowButtonClick() {
        if (isfollowing) {
            followButton.setText("팔로우");
            follow.execute(App.loginContext.user.userID, userHandleLabel.getText().substring(1));
            isfollowing = false;

        } else {
            followButton.setText("언팔로우");
            follow.execute(App.loginContext.user.userID, userHandleLabel.getText().substring(1));
            isfollowing = true;
        }
    }

}
