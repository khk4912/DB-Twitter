package twitter;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import twitter.db.Follow;

public class FollowListController {

    @FXML
    Text sectionName;

    @FXML
    GridPane nameGridPane;

    Follow follow = new Follow(App.DB.getConnection());

    public void init(boolean isFollower, String ID) {
        if (isFollower) {
            sectionName.setText("팔로워");
        } else {
            sectionName.setText("팔로잉");
        }

        ArrayList<String> list = isFollower ? follow.getFollowListOf(ID) : follow.getFollowingListOf(ID);

        if (list.size() == 0) {
            return;
        }

        nameGridPane.getChildren().clear();

        for (String x : list) {
            Label label = new Label(x);
            nameGridPane.add(label, 0, nameGridPane.getRowCount());
        }
    }
}
