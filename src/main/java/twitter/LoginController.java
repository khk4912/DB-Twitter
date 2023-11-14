package twitter;

import java.io.IOException;

import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("register_form");
    }

    @FXML
    private void switchToMainView() throws IOException {
        App.setWidth(800);
        App.setHeight(600);
        App.setRoot("main_view");
    }

}
