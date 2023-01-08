package Klinik.Controller.Auth;

import Klinik.Dao.AuthDao;
import Klinik.Dao.AuthInterface;
import Klinik.Model.Auth;
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

    public  void register(){
        try {
            Auth auth1 = new Auth();
            auth1.setFullname(frame.getTxtFullname().getText());
            auth1.setUsername(frame.getTxtUsername().getText());
            auth1.setPassword(frame.getTxtPassword().getText());
            authInterface.register(auth);
            JOptionPane.showMessageDialog(frame, "Your account has been successfully registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Error : "+e, "Error", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
