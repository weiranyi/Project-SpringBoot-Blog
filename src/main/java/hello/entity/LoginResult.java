package hello.entity;

public class LoginResult extends Result<User> {
    boolean isLogin;

    public LoginResult(String status, String msg, User user, boolean isLogin) {
        super(status, msg, user);
        this.isLogin = isLogin;
    }

    public static Result success(String msg, boolean isLogin) {
        return success(msg,isLogin,null);
    }
    public static Result success(String msg, boolean isLogin,User user) {
        return new LoginResult("ok",msg, null, isLogin);
    }


    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
