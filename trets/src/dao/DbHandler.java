package dao;

import static bases.Const.LOG;
import static bases.Const.PROPERTIES;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbHandler {
    private static Connection dbConnection;
    public static Connection getDbConnection() {
        try {
            if(dbConnection == null || dbConnection.isClosed()) setDbConnection();
        } catch (SQLException ex) {
            LOG.info(ex.getMessage());
        }
        return dbConnection;
    }

    
    private static void setDbConnection() {
        
        String host = PROPERTIES.getString("cargo.host");
        String port = PROPERTIES.getString("cargo.port");
        String dbname = PROPERTIES.getString("cargo.sid");
        String user = PROPERTIES.getString("cargo.user");
        String password = PROPERTIES.getString("cargo.password");
        final String ConnectionString = PROPERTIES.getString("cargo.url") + host + ":" + port + ":" + dbname;
        try {
            Class.forName(PROPERTIES.getString("cargo.driver"));
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(ConnectionString, user, password);

        } catch (SQLException e) {
            LOG.info(e.getMessage());
        }
    }

}
