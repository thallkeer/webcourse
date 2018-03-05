package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class BaseDAO {
    protected String driver = null;
    protected String url = null;
    protected Properties properties = null;
    public BaseDAO(String driver) {
        this.driver = driver;
    }
    /**
     * Процедура регистрации драйвера JDBC
     */
    protected void RegisterDriverManager() {
        try {
            Class.forName(driver).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {e.printStackTrace();
        }
    }
    /**
     * Процедура определения строки подключения URL к серверу БД
     */
    public abstract void setURL (String host, String database, int port);

    public abstract Connection getConnection ();

    public void Connect (String login, String password) {
        RegisterDriverManager();

        properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("user", login);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "utf8");
    }

    /**
     * Функция выполнения SQL-запроса
     */
    public ResultSet execSQL (final String sql) {
        ResultSet result = null;
        try {
            if (getConnection() != null) {
                Statement statement = getConnection().createStatement();
                result = statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            System.err.println ("SQLException : code = " + String.valueOf(e.getErrorCode()) +
                    " - " + e.getMessage());
            System.err.println ("\tSQL : " + sql);

        }
        return result;
    }

    public void execute(final String sql){
        try {
            Statement statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
