package twitter;

import java.util.Date;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.DatePicker;

import twitter.utils.TwitterAlert;
import twitter.utils.FieldChecker;
import twitter.db.SignUp;

// TODO: ID 중복 입력 끝나자마자 확인?
public class RegisterController {

    SignUp SignUpHandler = new SignUp(App.DB.getConnection());

    @FXML
    private ComboBox<String> genderSelectComboBox;
    ObservableList<String> genderData = FXCollections.observableArrayList("남성", "여성", "기타");

    @FXML
    TextField idField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField nicknameField;

    @FXML
    DatePicker birthdayPicker;

    public void initialize() {
        genderSelectComboBox.setItems(genderData);
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login_form");
        App.setWidth(400);
        App.setHeight(300);

    }

    @FXML
    private void register() {
        String userID = idField.getText();
        String password = passwordField.getText();
        String nickname = nicknameField.getText();
        String gender = genderSelectComboBox.getValue();

        LocalDate pickedDate = birthdayPicker.getValue();

        // Check if all fields are filled
        if (!FieldChecker.isAllFilled(userID, password, nickname, gender) || pickedDate == null) {
            TwitterAlert.error("주의!", "모든 항목을 입력해주세요.");
            return;
        }

        Date birthday = Date.from(birthdayPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        try {
            SignUpHandler.signUp(userID, password, nickname, gender, birthday);

            TwitterAlert.info("회원가입 성공", "회원가입이 완료되었어요.");
            App.setRoot("login_form");
        } catch (Exception e) {
            TwitterAlert.error("회원가입 실패", e.getMessage());
        }

    }
}
