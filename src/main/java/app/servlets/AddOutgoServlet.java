package app.servlets;

import app.dao.impl.EmployeeDAO;
import app.dao.impl.PostgresDAO;
import app.entities.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddOutgoServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {

        String login= request.getParameter("login");

        HttpSession session = request.getSession();
        PostgresDAO dao = new PostgresDAO();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        EmployeeDAO empDAO = new EmployeeDAO(dao);
        Employee emp = empDAO.getUser(login);


    }
}
