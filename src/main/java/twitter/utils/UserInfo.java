package twitter.utils;

import java.util.Date;

public class UserInfo {
    public String userID;
    public String nickname;
    public String gender;
    public Date birthday;
    public String introduce; // bio

    public UserInfo(String userID, String nickname, String gender, Date birthday, String introduce) {
        this.userID = userID;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.introduce = introduce;
    }

}