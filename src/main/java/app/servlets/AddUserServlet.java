package app.servlets;

import app.dao.impl.EmployeeDAO;
import app.dao.impl.PostgresDAO;
import app.entities.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("Admin.jsp").forward(req, resp);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login= request.getParameter("login");
        String password= request.getParameter("password");
        String fio= request.getParameter("fio");
        Integer auth_lvl= Integer.valueOf(request.getParameter("auth_lvl"));

        HttpSession session = request.getSession();
        PostgresDAO dao = new PostgresDAO();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        EmployeeDAO empDAO = new EmployeeDAO(dao);


        Employee newEmp = new Employee(login,password,fio,auth_lvl);
        if(!empDAO.isUserExists(newEmp.getLogin()))
        {
            empDAO.addUser(newEmp);
            request.getRequestDispatcher("Admin.jsp").forward(request, response);
        }
    }
}
