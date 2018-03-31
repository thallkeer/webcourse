package app.servlets.Outgo;


import app.dao.BaseDAO;
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
import java.util.*;

@WebServlet("/outgoes")
public class OutgoesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseDAO dao = PostgresDAO.getInstance();
        OutgoDAO outgoDAO = new OutgoDAO(dao);
        Integer emp_id = Integer.valueOf(req.getParameter("emp_id"));
        EmployeeDAO empDAO = new EmployeeDAO(dao);
        String fio = empDAO.getFioById(emp_id);
        List<Outgo> outgos = outgoDAO.getOutgoesByEmpId(emp_id);
        req.setAttribute("outgos", outgos);
        req.setAttribute("fio", fio);
        req.setAttribute("emp_id", emp_id);
        double sum =0;
        req.setAttribute("sum",sum);
        req.getRequestDispatcher("Outgoes.jsp").forward(req, resp);

    }
}
