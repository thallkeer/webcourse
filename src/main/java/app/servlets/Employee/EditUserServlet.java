package app.servlets.Employee;

import app.dao.IEmployeeDAO;
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

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IEmployeeDAO empDAO = new EmployeeDAO(PostgresDAO.getInstance());
        Integer emp_id=0;
        Employee emp=null;
        if (request.getParameter("emp_id")!=null){
            emp_id = Integer.valueOf(request.getParameter("emp_id"));
            emp = empDAO.getUser(emp_id);
        }
        request.setAttribute("emp",emp);
        request.getRequestDispatcher("EditUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login,password,fio;
        Integer auth_lvl;
        double account;
        if (req.getParameter("emp_id")!=null && req.getParameter("login") != null &&
                req.getParameter("password") != null &&
                req.getParameter("fio") != null &&
                req.getParameter("auth_lvl") != null && req.getParameter("account") != null) {
            HttpSession session = req.getSession();
            Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));
            login = req.getParameter("login");
            password = req.getParameter("password");
            fio = req.getParameter("fio");
            auth_lvl = Integer.valueOf(req.getParameter("auth_lvl"));
            account = Double.parseDouble(req.getParameter("account"));
            Employee editedEmployee = new Employee(emp_id,login,password,fio,auth_lvl,account);
            IEmployeeDAO empDAO = new EmployeeDAO(PostgresDAO.getInstance());
            empDAO.updateUser(editedEmployee);
            resp.sendRedirect("/employees");
        }

    }
}


