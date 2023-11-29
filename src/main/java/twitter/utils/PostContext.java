package twitter.utils;

import java.util.Date;

public class PostContext {
    // TODO: Image?

    public int postID;
    public String wrtierID;
    public String image;
    public String content;
    public Date updateDate;
    public Date registrationDate;
    public int likeCnt;
    public String nickname;

    public PostContext(int postID, String wrtierID, String image, String content, Date updateDate,
            Date registrationDate, int likeCnt, String nickname) {
        this.postID = postID;
        this.wrtierID = wrtierID;
        this.image = image;
        this.content = content;
        this.updateDate = updateDate;
        this.registrationDate = registrationDate;
        this.likeCnt = likeCnt;
        this.nickname = nickname;

    }

}
