package twitter.utils;

import java.util.Date;

public class PostContext {
    // TODO: Image?

    public int postID;
    public String nicnkname;
    public String writerID;
    public String image;
    public String content;
    public Date updateDate;
    public Date registrationDate;
    public int likeCnt;
    public String nickname;

    public PostContext(int postID, String nickname, String writerID, String image, String content, Date updateDate,
            Date registrationDate, int likeCnt) {
        this.nickname = nickname;
        this.postID = postID;
        this.writerID = writerID;
        this.image = image;
        this.content = content;
        this.updateDate = updateDate;
        this.registrationDate = registrationDate;
        this.likeCnt = likeCnt;

    }

}
