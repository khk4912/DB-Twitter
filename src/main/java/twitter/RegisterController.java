package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.control.ComboBox;

public class RegisterController {

    @FXML
    private ComboBox<String> genderSelectComboBox;

    ObservableList<String> genderData = FXCollections.observableArrayList("남성", "여성", "기타");

    public void initialize() {
        genderSelectComboBox.setItems(genderData);
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login_form");
        App.setWidth(400);
        App.setHeight(300);

    }
}
