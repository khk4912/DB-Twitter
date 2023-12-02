package twitter;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import twitter.db.PostSearch;
import twitter.utils.PostContext;

public class ProfileViewController extends PostViewController {
    private PostSearch postSearch = new PostSearch(App.DB.getConnection());

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

    }

    public void initialize() {

    }
}
