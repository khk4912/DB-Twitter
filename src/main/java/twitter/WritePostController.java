package twitter;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import twitter.db.UploadPost;
import twitter.utils.TwitterAlert;

public class WritePostController {
    UploadPost uploadPost = new UploadPost(App.DB.getConnection(), App.loginContext.user.userID);

    @FXML
    TextArea contentTextArea;

    private void uploadPost(String userID, String content) {
        uploadPost.uploadPost(userID, content);
    }

    @FXML
    private void upload() {
        String content = contentTextArea.getText();

        if (content.length() == 0 || content == null) {
            TwitterAlert.info("트윗 내용이 없음", "트윗 내용을 입력해주세요.");
            return;
        }

        uploadPost(App.loginContext.user.userID, content);
        TwitterAlert.info("트윗 업로드 완료", "트윗이 업로드되었습니다.");
        contentTextArea.setText("");
    }
}
