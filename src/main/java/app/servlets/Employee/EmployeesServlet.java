package app.servlets.Employee;

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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostgresDAO dao = new PostgresDAO();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        HttpSession session =  req.getSession();
        session.setAttribute("dao",dao);
        session.setAttribute("login",req.getAttribute("login"));
        IEmployeeDAO empDAO = new EmployeeDAO(dao);
        List<Employee> emps = null;
        try {
            emps = empDAO.getAll();
            req.setAttribute("emps",emps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("Admin.jsp").forward(req, resp);
    }
}
