package compulsory.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private String configFile;
    private HikariConfig cfg;
    private static HikariDataSource ds;
    private static ConnectionPool con;

    private ConnectionPool() {
       configFile  = new String("src/main/java/compulsory/connection/db.properties");
       cfg = new HikariConfig(configFile);
       ds = new HikariDataSource(cfg);
    }
    public static Connection getConnection() throws  SQLException {
        if(con == null)
            con = new ConnectionPool();
        return ds.getConnection();
    }

}
