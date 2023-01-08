package Klinik.Dao;

import Klinik.Model.Auth;

public interface AuthInterface {
    public void register(Auth auth);
    public void login(String username,String password);
    public void changePassword(int id,String username,String password);
}
