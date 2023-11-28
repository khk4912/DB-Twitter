package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ScrollPane;

public class MainViewController {

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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("post_view.fxml"));

            AnchorPane postView = fxmlLoader.load();

            PostViewController postViewController = fxmlLoader.getController();
            postViewController.initScrollPane(postScrollPane);

            // Demo retweet posts
            postViewController.addRetweetPost("나", "@kosame___", "돈까스 ㄱㄱ ", "너", "@you", "점심 뭐 먹지?", 13, 1, 8);

            postViewController.addRetweetPost("나", "@kosame___", "", "너", "@you", "리트윗 하면 복이 와요", 13, 1, 8);

            // Demo posts
            postViewController.addPost("Demo", "@Demo", "This is a demo!", 1, 10, 100);

            postViewController.addPost("Demo2", "@random",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ac interdum nisl. Maecenas rhoncus rhoncus libero, ac lacinia nibh elementum nec. Donec tempus, lacus id porta cursus, erat lectus condimentum tortor, sed tincidunt dui est a erat. Nunc vel tellus eget mi gravida lacinia tincidunt ut nibh. Nam vitae enim in elit consequat pretium. Suspendisse porttitor nisi quis massa egestas commodo. Integer ultricies vel massa a iaculis. Donec porta, diam ac tincidunt malesuada, ipsum eros consequat sapien, pretium pharetra mi risus vitae tellus. Aliquam varius at eros vel luctus.");

            postViewController.addPost("나", "@kosame___", "오늘 점심 뭐 먹지?", 13, 1, 8);
            postScrollPane.setContent(postView);
        } catch (IOException e) {
            System.out.println("Error loading post_view.fxml" + e.getCause() + e.getMessage());
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

            postViewController.addPost("나", "@me", "북마크 테스트", 9999, 1, 8);
            postViewController.addPost("나", "@me", "북마크 테스트2", 1234132, 1, 8);
            postViewController.addPost("나", "@me", "북마크 테스트3", 13, 1, 8);
            postViewController.addPost("나", "@me", "북마크 테스트4", 13, 1, 8);
            postViewController.addPost("나", "@me", "북마크 테스트5", 13, 1, 8);
            postViewController.addPost("나", "@me", "북마크 테스트5", 13, 1, 8);
            postViewController.addPost("나", "@me", "북마크 테스트5", 13, 1, 8);

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
            // WritePostController writePostController = fxmlLoader.getController();

            postScrollPane.setContent(searchView);
        } catch (IOException e) {
            System.out.println("Error loading search_view.fxml" + e.getCause() + e.getMessage());
            return;
        }

    }

    public void initialize() {
        // 기본값은 PostView로 설정
        loadPostView();
    }

}
