package twitter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SearchController extends PostViewController {
    static final ImageView blockImage = new ImageView(new Image(App.class.getResource("assets/search.png").toString()));

    @FXML
    Button searchButton;

    public void initialize() {
        blockImage.setFitWidth(15);
        blockImage.setFitHeight(15);
        searchButton.setGraphic(blockImage);

    }

}
