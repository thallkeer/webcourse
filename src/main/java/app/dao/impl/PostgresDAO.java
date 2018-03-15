package app.dao.impl;

import app.dao.BaseDAO;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDAO extends BaseDAO {
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_DATABASE = "finance_flows";
    public static final String DEFAULT_LOGIN = "postgres";
    public static final String DEFAULT_PASSWORD = "1234";
    public static final int DEFAULT_PORT = 5432;

    public PostgresDAO() {
        super ("org.postgresql.Driver");
    }
    @Override
    public void setURL (String host, String database, int port) {
        if (database.length() > 0)
            this.url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
        else
            this.url = "jdbc:postgresql://" + host + ":" + port;
    }
    @Override
    public Connection getConnection (){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, properties);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
