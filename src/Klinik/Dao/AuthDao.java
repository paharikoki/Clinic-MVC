package Klinik.Dao;

import Klinik.Database.Koneksi;
import Klinik.Model.Auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao implements AuthInterface{
    final String register="INSERT INTO auth (fullname,username, password) VALUES (?,?,?)";
    final String login="SELECT * FROM auth WHERE username = ? AND password = ?";
    final String changePassword="UPDATE auth SET password = ? WHERE username = ?";
    Connection connection;
    public AuthDao(){
        connection = Koneksi.connection();
    }

    @Override
    public void register(Auth auth) {
        PreparedStatement statement =null;
        try {
            statement = connection.prepareStatement(register);
            statement.setString(1,auth.getFullname());
            statement.setString(2,auth.getUsername());
            statement.setString(3,auth.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void changePassword(int id, String username, String password) {

    }
}
