package twitter;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import twitter.db.LoginHandler;
import twitter.utils.FieldChecker;
import twitter.utils.TwitterAlert;

public class LoginController {
    LoginHandler loginHandler = new LoginHandler(App.DB.getConnection());

    @FXML
    TextField idField;

    @FXML
    PasswordField passwordField;

    @FXML
    private void switchToRegister() throws IOException {
        App.setHeight(450);
        App.setRoot("register_form");
    }

    @FXML
    private void switchToMainView() throws IOException {
        App.setWidth(800);
        App.setHeight(600);
        App.setRoot("main_view");
    }

    @FXML
    private void login() {
        try {

            String userID = idField.getText();
            String password = passwordField.getText();

            if (!FieldChecker.isAllFilled(userID, password)) {
                return;
            }
            App.loginContext = loginHandler.loginUser(userID, password);
            switchToMainView();

        } catch (Exception e) {
            e.printStackTrace();
            TwitterAlert.error("로그인 오류!", e.getMessage());
        }

    }

}
