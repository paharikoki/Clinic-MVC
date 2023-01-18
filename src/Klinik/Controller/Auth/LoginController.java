package Klinik.Controller.Auth;

import Klinik.Dao.AuthDao;
import Klinik.Dao.AuthInterface;
import Klinik.Model.Auth;
import Klinik.Utils.BCryptPasswordHasher;
import Klinik.Utils.PasswordHasher;
import Klinik.View.Auth.Login;

import javax.swing.*;

public class LoginController {
    Login frame;
    static AuthInterface authInterface;
    Auth auth;

    public LoginController(Login frame) {
        this.frame = frame;
        this.authInterface = new AuthDao();
        this.auth = auth;
    }

    public void login() {
        String username,password;
        username = frame.getTxtUsername().getText().toString();
        password = frame.getTxtPassword().getText().toString();
        String hashedPassword = PasswordHasher.hashPassword(password);
        Auth auth =authInterface.login(username,hashedPassword);
        if (auth != null) {
            JOptionPane.showMessageDialog(frame, "Login successful. Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);
            frame.clearFields();
            frame.viewMain();
        }else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.", "Failed", JOptionPane.WARNING_MESSAGE);
            frame.getTxtPassword().setText("");
        }
    }
    public boolean checkUsername(String username) {
        if (authInterface.checkUsernameExists(username)!= null){
            return true;
        }
        return false;
    }
}
