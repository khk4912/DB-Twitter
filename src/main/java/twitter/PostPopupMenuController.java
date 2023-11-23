package twitter;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PostPopupMenuController {

    static final ImageView blockImage = new ImageView(new Image(App.class.getResource("assets/block.png").toString()));
    static final ImageView deleteImage = new ImageView(
            new Image(App.class.getResource("assets/trashcan.png").toString()));
    static final ImageView editImage = new ImageView(new Image(App.class.getResource("assets/edit.png").toString()));

    @FXML
    MenuItem blockUser;

    @FXML
    MenuItem deleteTweet;

    @FXML
    MenuItem editTweet;

    // TODO: Implement ID Chedck, by adding getID in App class
    public void initlaize(boolean isMyPost) {
        if (!isMyPost) {
            deleteTweet.setVisible(false);
            editTweet.setVisible(false);
        }

        blockImage.setFitWidth(10);
        blockImage.setFitHeight(10);

        deleteImage.setFitWidth(10);
        deleteImage.setFitHeight(10);

        editImage.setFitWidth(10);
        editImage.setFitHeight(10);

        blockUser.setGraphic(blockImage);
        deleteTweet.setGraphic(deleteImage);
        editTweet.setGraphic(editImage);

    }
}
