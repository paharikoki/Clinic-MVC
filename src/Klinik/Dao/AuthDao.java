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
    final String checkUsername = "SELECT * FROM auth WHERE username = ?";
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
    public void changePassword( String username, String password) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(changePassword);
            statement.setString(1,password);
            statement.setString(2,username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Auth checkUsernameExists(String username) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(checkUsername);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Auth auth1 = new Auth();
                auth1.setFullname(result.getString(1));
                auth1.setUsername(result.getString( 2));
                auth1.setPassword(result.getString( 3));
                return auth1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
