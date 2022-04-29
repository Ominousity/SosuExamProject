package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseConnector {
    private static final String DB_SETTINGS = "database.properties";
    private SQLServerDataSource ds;

    /**
     * the method responsible for getting a connection to the database
     * @throws IOException
     */
    public DatabaseConnector() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DB_SETTINGS));
        ds = new SQLServerDataSource();
        ds.setServerName(properties.getProperty("ServerIp"));
        ds.setDatabaseName(properties.getProperty("DataBase"));
        ds.setUser(properties.getProperty("UserName"));
        ds.setPassword(properties.getProperty("Password"));
    }

    /**
     * a get method used in other method to use the database connection
     * @return a connection
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
