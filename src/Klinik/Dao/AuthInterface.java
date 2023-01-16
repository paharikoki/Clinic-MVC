package Klinik.Dao;

import Klinik.Model.Auth;

import javax.swing.*;

public interface AuthInterface {
    public void register(Auth auth);
    public Auth login(String username, String password);
    public void changePassword(String username,String password);
    public Auth checkUsernameExists(String username);
}
