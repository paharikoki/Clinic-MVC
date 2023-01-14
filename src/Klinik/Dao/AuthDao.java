package Klinik.Dao;

import Klinik.Database.Connection;
import Klinik.Model.Auth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao implements AuthInterface{
    final String register="INSERT INTO auth (fullname,username, password) VALUES (?,?,?)";
    final String login="SELECT * FROM auth WHERE username = ? AND password = ?";
    final String changePassword="UPDATE auth SET password = ? WHERE username = ?";
    java.sql.Connection connection;
    public AuthDao(){
        connection = Connection.connection();
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
    public Auth login(String username, String password) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(login);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Auth auth1 = new Auth();
                auth1.setFullname(rs.getString(1));
                auth1.setUsername(rs.getString( 2));
                return auth1;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public void changePassword(int id, String username, String password) {

    }
}
