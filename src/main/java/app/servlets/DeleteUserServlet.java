package app.servlets;

import app.dao.impl.EmployeeDAO;
import app.dao.impl.PostgresDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Integer emp_id = Integer.valueOf(request.getParameter("emp_id"));
        PostgresDAO dao = new PostgresDAO();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        EmployeeDAO empDAO = new EmployeeDAO(dao);
        empDAO.delete(emp_id);
        response.sendRedirect("Admin.jsp");
    }
}
