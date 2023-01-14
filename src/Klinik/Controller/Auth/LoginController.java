package Klinik.Controller.Auth;

import Klinik.Dao.AuthDao;
import Klinik.Dao.AuthInterface;
import Klinik.Model.Auth;
import Klinik.View.Auth.Login;

public class LoginController {
    Login frame;
    static AuthInterface authInterface;
    Auth auth;

    public LoginController(Login frame, Auth auth) {
        this.frame = frame;
        this.authInterface = new AuthDao();
        this.auth = auth;
    }

    public static boolean login(String username, String password) {
        Auth auth =authInterface.login(username,password);
        if (auth != null) {
            return true;
        }
        return false;
    }
}
