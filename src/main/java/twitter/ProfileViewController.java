package twitter;

import java.util.ArrayList;

import twitter.db.PostSearch;
import twitter.utils.PostContext;

public class ProfileViewController extends PostViewController {
    private PostSearch postSearch = new PostSearch(App.DB.getConnection());

    public void initialize() {
        ArrayList<PostContext> posts = postSearch.searchByID(App.loginContext.user.userID);

        if (posts.size() == 0) {
            showEmptyNotify();
            return;
        }

        for (PostContext post : posts) {
            addPost(post);
        }

    }
}
