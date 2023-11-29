package twitter;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ScrollPane;

import twitter.db.Home;
import twitter.utils.PostContext;
import twitter.utils.TwitterAlert;

public class MainViewController {

    Home home = new Home(App.DB.getConnection(), App.loginContext.user.userID);

    @FXML
    private SplitPane splitPane;

    @FXML
    private ScrollPane postScrollPane;

    /**
     * Load PostView
     * 게시글 리스트를 보여주는 화면을 postScrollPane에 로드
     */
    @FXML
    private void loadPostView() {
        try {

            ArrayList<PostContext> postList = home.getFollowingPosts();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post_view.fxml"));
            AnchorPane postView = fxmlLoader.load();

            PostViewController postViewController = fxmlLoader.getController();
            postViewController.initScrollPane(postScrollPane);

            if (postList.size() == 0) {
                postViewController.showEmptyNotify();
            } else {
                for (PostContext post : postList) {
                    postViewController.addPost(post);
                }
            }
            postScrollPane.setContent(postView);

        } catch (IOException e) {
            e.printStackTrace();
            TwitterAlert.error("게시글 로드 오류!", e.getMessage());
            return;
        }

    }

    @FXML
    private void loadBookmarkView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookmark_view.fxml"));
            AnchorPane bookmarkView = fxmlLoader.load();

            PostViewController postViewController = fxmlLoader.getController();
            postViewController.initScrollPane(postScrollPane);

            // postViewController.addPost("나", "@me", "북마크 테스트", 9999, 1, 8, new Date());
            // postViewController.addPost("나", "@me", "북마크 테스트2", 1234132, 1, 8);
            // postViewController.addPost("나", "@me", "북마크 테스트3", 13, 1, 8);
            // postViewController.addPost("나", "@me", "북마크 테스트4", 13, 1, 8);
            // postViewController.addPost("나", "@me", "북마크 테스트5", 13, 1, 8);
            // postViewController.addPost("나", "@me", "북마크 테스트5", 13, 1, 8);
            // postViewController.addPost("나", "@me", "북마크 테스트5", 13, 1, 8);

            postScrollPane.setContent(bookmarkView);

        } catch (IOException e) {
            System.out.println("Error loading bookmark_view.fxml" + e.getCause() + e.getMessage());
            return;
        }
    }

    @FXML
    private void loadWritePostView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("write.fxml"));

            AnchorPane writePostView = fxmlLoader.load();
            // WritePostController writePostController = fxmlLoader.getController();

            postScrollPane.setContent(writePostView);
        } catch (IOException e) {
            System.out.println("Error loading write_post.fxml" + e.getCause() + e.getMessage());
            return;
        }
    }

    @FXML
    private void loadSearchView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search_view.fxml"));

            AnchorPane searchView = fxmlLoader.load();
            SearchController searchController = fxmlLoader.getController();
            searchController.initScrollPane(postScrollPane);

            postScrollPane.setContent(searchView);
        } catch (IOException e) {
            System.out.println("Error loading search_view.fxml" + e.getCause() + e.getMessage());
            return;
        }

    }

    @FXML
    private void loadMyProfileView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile_view.fxml"));
            AnchorPane profileView = fxmlLoader.load();

            ProfileViewController profileViewController = fxmlLoader.getController();
            profileViewController.initScrollPane(postScrollPane);

            postScrollPane.setContent(profileView);
        } catch (IOException e) {
            System.out.println("Error loading profile_view.fxml" + e.getCause() + e.getMessage());
            return;
        }
    }

    public void initialize() {
        // 기본값은 PostView로 설정
        loadPostView();
    }

}
