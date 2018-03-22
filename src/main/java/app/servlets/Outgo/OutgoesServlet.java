package app.servlets.Outgo;


import app.dao.IEmployeeDAO;
import app.dao.IOutgoDAO;
import app.dao.ITaskDAO;
import app.dao.impl.EmployeeDAO;
import app.dao.impl.OutgoDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Outgo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/outgoes")
public class OutgoesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        IOutgoDAO dao = new OutgoDAO((PostgresDAO) session.getAttribute("dao"));
        Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));
        IEmployeeDAO empDAO = new EmployeeDAO((PostgresDAO) session.getAttribute("dao"));
        String fio = empDAO.getFioById(emp_id);
        try {
            List<Outgo> outgos = dao.getOutgoesByEmpId(emp_id);
            req.setAttribute("outgos",outgos);
            req.setAttribute("fio",fio);
            req.setAttribute("emp_id",emp_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("Outgoes.jsp").forward(req,resp);

    }
}
