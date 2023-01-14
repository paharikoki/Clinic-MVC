package Klinik.Database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.SQLException;

public class Connection {
    static java.sql.Connection conn;
    public static final java.sql.Connection connection(){
        if (conn == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("db_klinik");
            data.setUser("root");
            data.setPassword("");
            try {
                conn = data.getConnection();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return conn;
    }
}
