package DAL;

import UI.MVC.Model.ParseModel;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseConnector {
    private static DatabaseConnector instance = null;
    private static final String DB_SETTINGS = "Utilities/database.Properties";
    private SQLServerDataSource ds;
    private Thread thread;
    private Connection connection;

    /**
     * the method responsible for getting a connection to the database
     * @throws IOException
     */
    private DatabaseConnector() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(DB_SETTINGS));
        ds = new SQLServerDataSource();
        ds.setServerName(properties.getProperty("ServerIp"));
        ds.setDatabaseName(properties.getProperty("DataBase"));
        ds.setUser(properties.getProperty("UserName"));
        ds.setPassword(properties.getProperty("Password"));
    }

    public static DatabaseConnector getInstance() throws IOException {
        if (instance == null){
            instance = new DatabaseConnector();
        }
        return instance;
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
