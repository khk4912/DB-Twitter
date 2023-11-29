package twitter.utils;

import twitter.App;
import twitter.db.YourProfile;

public class LoginContext {
    public UserInfo user;
    YourProfile profile;

    public LoginContext(String userID) {
        profile = new YourProfile(App.DB.getConnection(), userID);

        try {
            user = profile.getUserInfo();

        } catch (Exception e) {
            e.printStackTrace();
            TwitterAlert.error("오류", "유저 정보를 가져오는데 실패했습니다.");
        }
    }

}