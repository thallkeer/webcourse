package app.dao;

import app.entities.Employee;

public interface EmployeeDAO {
    public int authenticateUser(String login, String password);
    public boolean addUser(Employee employee);
    public Employee getUser(String login, String password);
    public Employee getUser(int id);
    public Employee getUser(String login);
    public void update(int id, Employee employee);
    public void delete(Employee employee);
    public  void delete(int id);
}
