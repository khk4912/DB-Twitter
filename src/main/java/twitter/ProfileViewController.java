package twitter;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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

    public void initProfile(String username, String handle, String bio) {
        userNameLabel.setText(username);
        userHandleLabel.setText('@' + handle);

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
