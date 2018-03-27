package app.dao.impl;

import app.dao.IEmployeeDAO;
import app.entities.Employee;

import javax.ejb.Stateful;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class EmployeeDAO implements IEmployeeDAO {
    private PostgresDAO dao;

    public  EmployeeDAO(){}

    public EmployeeDAO(PostgresDAO dao){
        this.dao = dao;
    }

    @Override
    public int authenticateUser(String login, String password) {
        ResultSet resultSet = dao.execSQL(String.format("select * from employee where login = '%1$s'" +
                        " and password = '%2$s'",
                login,
                password));
        try {
            if (resultSet.next()) {
                return resultSet.getInt("auth_lvl");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void addUser(Employee employee) {
        dao.execute(String.format("INSERT INTO employee(" +
                        "login, password, fio,auth_lvl,account) values('%1$s','%2$s','%3$s','%4$s','%5$s')",
                employee.getLogin(),
                employee.getPassword(),
                employee.getFio(),
                employee.getAuth_lvl(),
                employee.getAccount()));
    }

    public boolean isUserExists(String login){
        ResultSet rs = dao.execSQL(String.format("SELECT EXISTS(SELECT id FROM table WHERE login='%1$s')",login));
        return rs!=null;
    }


    public Employee getUser(String login, String password) {
        Employee employee = new Employee(login, password);
        ResultSet resultSet = dao.execSQL(String.format("select * from employee where login = '%1$s'" +
                        " and password = '%2$s'",
                login,
                password));
        try {
            if (resultSet.next()) {
                employee.setEmployee_id(resultSet.getInt(USER_ID));
                employee.setFio(resultSet.getString(FIO));
                employee.setAuth_lvl(resultSet.getInt(AUTH_LVL));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getUser(int id) {
        Employee employee = new Employee();
        ResultSet resultSet = dao.execSQL(String.format("select * from employee where employee_id = '%1$s'",id));
        try {
            while (resultSet.next())
            {
                employee.setEmployee_id(id);
                employee.setLogin(resultSet.getString(LOGIN));
                employee.setPassword(resultSet.getString(PASSWORD));
                employee.setFio(resultSet.getString(FIO));
                employee.setAuth_lvl(resultSet.getInt(AUTH_LVL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Integer getIdByLogin(String login) {
        ResultSet rs = dao.execSQL(String.format("select employee_id from employee where login='%1$s'",login));
        try {
            rs.next();
            return rs.getInt("employee_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getLoginById(int id){
        ResultSet rs = dao.execSQL(String.format("select login from employee where employee_id='%1$s'",id));
        try {
            rs.next();
            return rs.getString("login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getFioById(int id){
        ResultSet rs = dao.execSQL(String.format("select fio from employee where employee_id='%1$s'",id));
        try {
            rs.next();
            return rs.getString("fio");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Employee getUser(String login) {
        Employee employee = new Employee();
        ResultSet rs = dao.execSQL(String.format("select * from employee where login='%1$s'",login));
        try {
            while (rs.next()){
                employee.setEmployee_id(rs.getInt("employee_id"));
                employee.setLogin(rs.getString("login"));
                employee.setFio(rs.getString("fio"));
                employee.setAuth_lvl(rs.getInt("auth_lvl"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void updateUser(Employee employee) {
        dao.execute(String.format("update employee set login = '%1$s', password = '%2$s', fio = '%3$s'," +
                        " auth_lvl = '%4$s', account = '%5$s'  where employee_id = %6$s",
               employee.getLogin(),
                employee.getPassword(),
                employee.getFio(),
                employee.getAuth_lvl(),
                employee.getAccount(),
                employee.getEmployee_id())
        );

    }

    public void deleteUser(Employee employee) {
        dao.execute(String.format("delete from employee where login = '%1$s' and password = '%2$s'",
                employee.getLogin(),
                employee.getPassword()));
    }

    public void delete(int id) {
        dao.execute(String.format("delete from employee where employee_id = '%1$s'",id));
    }


    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        ResultSet rs = dao.execSQL("select * from employee");
        Employee emp;
        try {
        while (rs.next()){
            emp = new Employee(rs.getInt("employee_id"),rs.getString("login"),rs.getString("password"),
                    rs.getString("fio"),rs.getInt("auth_lvl"),rs.getDouble("account"));
            employees.add(emp);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
