package app.servlets.Employee;

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

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login= request.getParameter("login");
        String password= request.getParameter("password");
        String fio= request.getParameter("fio");
        Integer auth_lvl=-1;
        if (!request.getParameter("auth_lvl").equals("")) {
            auth_lvl = Integer.valueOf(request.getParameter("auth_lvl"));
        }

        if (!login.equals("") && !password.equals("") && auth_lvl!=-1) {
            PostgresDAO dao = new PostgresDAO();
            dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
            dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
            EmployeeDAO empDAO = new EmployeeDAO(dao);

            Employee newEmp = new Employee(login, password, fio, auth_lvl);
            if (!empDAO.isUserExists(newEmp.getLogin())) {
                empDAO.addUser(newEmp);
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
            }
        }
    }
}
