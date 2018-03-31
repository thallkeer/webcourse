package app.dao;

import app.entities.Employee;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO {
    String USER_ID = "employee_id";
    String LOGIN = "login";
    String PASSWORD = "password";
    String AUTH_LVL = "auth_lvl";
    String FIO = "fio";
    String ACC = "account";

    public int authenticateUser(String login, String password);
    public void addUser(Employee employee);
    public Employee getUser(String login, String password);
    public Employee getUser(int id);
    public Employee getUser(String login);
    public Integer getIdByLogin(String login);
    public String getLoginById(int id);
    public String getFioById(int id);
    public void updateUser(Employee employee);
    public void deleteUser(int id);
    public boolean isUserExists(String login);
    public List<Employee> getAll() throws SQLException;
}
