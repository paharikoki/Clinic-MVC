package Klinik.Controller.Auth;

import Klinik.Dao.AuthDao;
import Klinik.Dao.AuthInterface;
import Klinik.Model.Auth;
import Klinik.Utils.BCryptPasswordHasher;
import Klinik.Utils.PasswordHasher;
import Klinik.View.Auth.Register;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    Register frame;
    AuthInterface authInterface;
    Auth auth;

    public RegisterController(Register frame, Auth auth) {
        this.frame = frame;
        this.authInterface = new AuthDao();
        this.auth = auth;
    }
    public void register(){
        String hashedPassword=null;
        try {
            String fullName,userName,password;
            fullName = frame.getTxtFullname().getText();
            userName = frame.getTxtUsername().getText();
            password = frame.getTxtPassword().getText();
            if (userName.length() <6){
                JOptionPane.showMessageDialog(frame, "Username must be at least 6 characters long.");
                frame.captcha();
                return;
            }
            if (password.length() <8){
                JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long.");
                frame.getTxtPassword().setText("");
                frame.captcha();
                return;
            }
            if (authInterface.checkUsernameExists(userName)!=null) {
                JOptionPane.showMessageDialog(frame, "User already taken, please choose another one!", "Error", JOptionPane.INFORMATION_MESSAGE);
                frame.getTxtPassword().setText("");
                frame.captcha();
            }else {
                hashedPassword = PasswordHasher.hashPassword(password);
                Auth auth1 = new Auth();
                auth1.setFullname(fullName);
                auth1.setUsername(userName);
                auth1.setPassword(hashedPassword);
                authInterface.register(auth1);
                JOptionPane.showMessageDialog(frame, "Your account has been successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.clearField();
                frame.viewLogin();
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Error : "+e, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}
