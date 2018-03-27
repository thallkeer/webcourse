package app.servlets.Outgo;

import app.dao.IEmployeeDAO;
import app.dao.IOutgoDAO;
import app.dao.ITaskDAO;
import app.dao.impl.EmployeeDAO;
import app.dao.impl.OutgoDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Employee;
import app.entities.Outgo;
import app.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@WebServlet("/addOutgo")
public class AddOutgoServlet extends HttpServlet {
    Integer emp_id=0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (req.getParameter("emp_id")!=null){
            emp_id = Integer.valueOf(req.getParameter("emp_id"));
            HttpSession session = req.getSession();
            PostgresDAO dao = PostgresDAO.getInstance();
            TaskDAO taskDAO = new TaskDAO(dao);
            Map<Integer,String> descs = taskDAO.getParents();
            session.setAttribute("descs",descs);
            req.setAttribute("emp_id",emp_id);
            req.getRequestDispatcher("AddOutgo.jsp").forward(req,resp);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int second_id,third_id;
        double summ;
        PostgresDAO dao = PostgresDAO.getInstance();
        TaskDAO taskDAO = new TaskDAO(dao);
        OutgoDAO outgoDAO = new OutgoDAO(dao);
        if (request.getParameter("firstchoice") != null && request.getParameter("secondchoice")!=null){
//            first_id = Integer.parseInt(request.getParameter("firstchoice"));
            second_id = Integer.parseInt(request.getParameter("secondchoice"));
            summ = Double.parseDouble(request.getParameter("sum"));
            Outgo outgo = new Outgo();
            outgo.setEmp_id(emp_id);
            if (request.getParameter("thirdchoice")!=null){
                third_id = Integer.parseInt(request.getParameter("thirdchoice"));
                outgo.setTask_id(third_id);
            }
            else {
                outgo.setTask_id(second_id);
            }
            outgo.setSumm(summ);

            outgoDAO.addOutgo(outgo);
        }
        response.sendRedirect("/outgoes?emp_id="+emp_id);

    }
}
