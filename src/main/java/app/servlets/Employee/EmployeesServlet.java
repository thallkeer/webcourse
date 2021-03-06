package app.servlets.Employee;

import app.dao.BaseDAO;
import app.dao.IEmployeeDAO;
import app.dao.impl.EmployeeDAO;
import app.dao.impl.PostgresDAO;
import app.entities.Employee;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet{

    private BaseDAO dao;

    @Override
    public void init() {
        dao = PostgresDAO.getInstance();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> emps;
        IEmployeeDAO empDAO;
        dao = PostgresDAO.getInstance();
        empDAO = new EmployeeDAO(dao);
        emps = empDAO.getAll();
        req.setAttribute("emps", emps);
        req.getRequestDispatcher("Users.jsp").forward(req, resp);
    }
}
