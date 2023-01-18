package Klinik.Controller.Auth;

import Klinik.Dao.AuthDao;
import Klinik.Dao.AuthInterface;
import Klinik.Model.Auth;
import Klinik.Utils.BCryptPasswordHasher;
import Klinik.Utils.PasswordHasher;
import Klinik.View.Auth.ChangePassword;
import Klinik.View.Auth.Login;

import javax.swing.*;
import java.util.Random;

public class ChangePasswordController {
    ChangePassword frame;
    AuthInterface authInterface;
    Auth auth;


    public ChangePasswordController(ChangePassword frame) {
        this.frame = frame;
        this.authInterface = new AuthDao();
        this.auth = auth;
    }
    public void changePassword(String username, String password) {
        if (frame.getTxtnewpassword().getText() != null) {
            String captcha = frame.getLbCaptcha().getText();
            String inputCaptcha = frame.getTxtCaptcha().getText();
            if (!inputCaptcha.equals("") ){
                if (inputCaptcha.equals(captcha)){
                    if (!frame.getTxtnewpassword().getText().equals("") ){
                        if (!password.equals(frame.getTxtnewpassword().getText())) {
                            JOptionPane.showMessageDialog(frame, "Passwords do not match, please re-enter!", "Error", JOptionPane.ERROR_MESSAGE);
                            setCaptcha();
                        }else {
                            if (frame.getTxtpasswordverification().getText().length() > 6) {
                                String passwordHash = PasswordHasher.hashPassword(password);
                                authInterface.changePassword(username, passwordHash);
                                JOptionPane.showMessageDialog(frame, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                this.frame.dispose();
                                new Login().setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(frame, "Password must be at least 8 characters long.");
                                setCaptcha();
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(frame, "Please enter your new password", "Error", JOptionPane.ERROR_MESSAGE);
                        setCaptcha();
                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Captcha value not same, Please enter the new captcha ", "Error", JOptionPane.ERROR_MESSAGE);
                    setCaptcha();
                }
            }else {
                JOptionPane.showMessageDialog(frame, "Please enter the captcha value", "Error", JOptionPane.ERROR_MESSAGE);
                setCaptcha();

            }
        }
    }
    public void setCaptcha(){
        String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHANUMERIC.charAt(rnd.nextInt(ALPHANUMERIC.length())));
        }

        String randomString = sb.toString().toLowerCase();
        frame.getTxtCaptcha().setText("");
        frame.getLbCaptcha().setText(randomString);
    }
}
