package twitter;

import java.io.IOException;

import javafx.fxml.FXML;

public class RegisterController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login_form");
    }
}
