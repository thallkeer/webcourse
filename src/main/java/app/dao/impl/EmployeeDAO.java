package app.dao.impl;

import app.dao.BaseDAO;
import app.dao.IEmployeeDAO;
import app.entities.Employee;

import javax.ejb.Stateful;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class EmployeeDAO implements IEmployeeDAO {
    private BaseDAO dao;

    public EmployeeDAO(){}

    public EmployeeDAO(BaseDAO dao){
        this.dao = dao;
    }

    @Override
    public int authenticateUser(String login, String password) {
        String query = "select auth_lvl from employee where login = ?" +
                        " and password = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("auth_lvl");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void addUser(Employee employee) {
        String query = "INSERT INTO employee(" +
                "login, password, fio,auth_lvl,account) VALUES(?,?,?,?,?)";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employee.getLogin());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getFio());
            ps.setInt(4, employee.getAuth_lvl());
            ps.setDouble(4, employee.getAccount());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isUserExists(String login) {
        String query = "SELECT EXISTS(SELECT id FROM employee WHERE login=?)";
        boolean res=false;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            res = ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public Employee getUser(String login, String password) {
        Employee employee = new Employee(login, password);
        String query = "select * from employee where login = ?" +
                        " and password = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmployee_id(rs.getInt(USER_ID));
                employee.setFio(rs.getString(FIO));
                employee.setAuth_lvl(rs.getInt(AUTH_LVL));
                employee.setAccount(rs.getDouble(ACC));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public Employee getUser(int id) {
        Employee employee = new Employee();
        String query = "select * from employee where employee_id=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmployee_id(rs.getInt(USER_ID));
                employee.setLogin(rs.getString(LOGIN));
                employee.setPassword(rs.getString(PASSWORD));
                employee.setFio(rs.getString(FIO));
                employee.setAuth_lvl(rs.getInt(AUTH_LVL));
                employee.setAccount(rs.getDouble(ACC));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Integer getIdByLogin(String login) {
        String query = "select employee_id from employee where login=?";
        int res=-1;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            res= rs.getInt("employee_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getLoginById(int id){
        String query = "select login from employee where employee_id=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getFioById(int id){
        String query = "select fio from employee where employee_id=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("fio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Employee getUser(String login) {
        Employee employee = new Employee();
        String query = "select * from employee where login=?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setEmployee_id(rs.getInt(USER_ID));
                employee.setLogin(login);
                employee.setPassword(rs.getString(PASSWORD));
                employee.setFio(rs.getString(FIO));
                employee.setAuth_lvl(rs.getInt(AUTH_LVL));
                employee.setAccount(rs.getDouble(ACC));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void updateUser(Employee employee) {
        String query = "UPDATE employee SET login = ?, password = ?, fio = ?," +
                " auth_lvl = ?, account = ?  WHERE employee_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employee.getLogin());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getFio());
            ps.setInt(4, employee.getAuth_lvl());
            ps.setDouble(5, employee.getAccount());
            ps.setInt(6,employee.getEmployee_id());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        String query = "delete from employee where employee_id = ?";
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employee ORDER BY employee_id ASC";
        Employee emp;
        try (Connection connection = dao.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                emp = new Employee(rs.getInt("employee_id"), rs.getString("login"), rs.getString("password"),
                        rs.getString("fio"), rs.getInt("auth_lvl"), rs.getDouble("account"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
