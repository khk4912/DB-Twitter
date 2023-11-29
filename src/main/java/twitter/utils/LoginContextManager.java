package twitter.utils;

public class LoginContextManager {
    private static LoginContext loginContext;

    public static void setLoginContext(String userID) {
        loginContext = new LoginContext(userID);
    }

    public static LoginContext getLoginContext() {
        return loginContext;
    }
}
