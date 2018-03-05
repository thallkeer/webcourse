package app.dao.impl;

import app.dao.IEmployeeDAO;
import app.entities.Employee;

import javax.ejb.Stateful;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateful
public class EmployeeDAO implements IEmployeeDAO {
    private PostgresDAO dao;

    public EmployeeDAO(){}

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
    public boolean addUser(Employee employee) {
        if (getUser(employee.getLogin()) == null) {
            dao.execute(String.format("INSERT INTO employee(" +
                            "login, password, fio) values('%1$s','%2$s','%3$s')",
                    employee.getLogin(),
                    employee.getPassword(),
                    employee.getFio()));
            return true;
        } else
            return false;
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
                employee.setAuth_lvl(resultSet.getInt(AUTH_LVL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getUser(String login) {
        return null;
    }

    public void update(int id, Employee employee) {
        dao.execute(String.format("update employee set login = '%1$s', password = '%2$s'" +
                        "where user_id = %3$s ",
               employee.getLogin(),
                employee.getPassword(),
                employee.getEmployee_id())
        );

    }

    public void delete(Employee employee) {
        dao.execute(String.format("delete from employee where login = '%1$s' and password = '%2$s'",
                employee.getLogin(),
                employee.getPassword()));
    }

    public void delete(int id) {
        dao.execute(String.format("delete from employee where employee_id = '%1$s'",id));
    }
}
