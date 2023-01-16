package Klinik.Controller.Auth;

import Klinik.Dao.AuthDao;
import Klinik.Dao.AuthInterface;
import Klinik.Model.Auth;
import Klinik.Utils.BCryptPasswordHasher;
import Klinik.Utils.PasswordHasher;
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
        String hashedPassword = PasswordHasher.hashPassword(password);
        Auth auth =authInterface.login(username,hashedPassword);
        if (auth != null) {
            return true;
        }
        return false;
    }
    public boolean checkUsername(String username) {
        if (authInterface.checkUsernameExists(username)!= null){
            return true;
        }
        return false;
    }
}
