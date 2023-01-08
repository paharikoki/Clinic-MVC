package Klinik.Database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Koneksi {
    static Connection conn;
    public static final Connection connection(){
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
