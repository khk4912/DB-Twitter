package twitter;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import twitter.utils.PostContext;
import twitter.db.PostSearch;

public class SearchController extends PostViewController {
    static final ImageView blockImage = new ImageView(new Image(App.class.getResource("assets/search.png").toString()));
    PostSearch search = new PostSearch(App.DB.getConnection());

    @FXML
    TextField searchInput;

    @FXML
    Button searchButton;

    public void initialize() {
        blockImage.setFitWidth(15);
        blockImage.setFitHeight(15);

        searchButton.setGraphic(blockImage);
        searchButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            search();
        });
    }

    public void search() {
        ArrayList<PostContext> postList;
        String keyword = searchInput.getText();

        postGridPane.getChildren().clear();

        if (keyword.equals("") || keyword == null) {
            return;
        }

        if (keyword.charAt(0) == '@') {
            postList = search.searchByID(keyword.substring(1));

        } else {
            postList = search.searchByContent(keyword);
        }

        if (postList.size() == 0) {
            showEmptyNotify();
        } else {
            for (PostContext post : postList) {
                addPost(post);
            }
        }
    }

}
