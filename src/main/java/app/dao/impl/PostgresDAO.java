package app.dao.impl;

import app.dao.BaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDAO extends BaseDAO {
    private  Connection  connection = null;
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
    public Connection getConnection () {
        return connection;
    }
    @Override
    public void Connect (String login, String password) {
        super.Connect(login, password);
        try {
            connection = DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            connection = null;
        }
    }

    public ResultSet selectTable(String table){
        return execSQL("select * from " + table);
    }

    public int getMaxId(String table, String id){
        ResultSet resultSet =execSQL(String.format("SELECT %1$s \n" +
                        "FROM %2$s \n" +
                        "ORDER BY %1$s DESC \n" +
                        "LIMIT 1",
                id,
                table)
        );
        try {
            if ( resultSet.next())
                return resultSet.getInt(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}