package app.servlets.Outgo;

import app.dao.BaseDAO;
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
import java.util.Map;

@WebServlet("/addOutgo")
public class AddOutgoServlet extends HttpServlet {
    private Integer emp_id = 0;
    private BaseDAO dao;

    @Override
    public void init() {
        dao = PostgresDAO.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("emp_id") != null) {
            emp_id = Integer.valueOf(req.getParameter("emp_id"));
            HttpSession session = req.getSession();
            TaskDAO taskDAO = new TaskDAO(dao);
            Map<Integer, String> descs = taskDAO.getParents();
            session.setAttribute("descs", descs);
            double sum = 0;
            req.setAttribute("sum", sum);
            req.setAttribute("emp_id", emp_id);
            req.getRequestDispatcher("AddOutgo.jsp").forward(req, resp);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int first_id, second_id, third_id;
        double summ;
        OutgoDAO outgoDAO = new OutgoDAO(dao);
        TaskDAO taskDAO = new TaskDAO(dao);

        if (request.getParameter("firstchoice") != null && request.getParameter("secondchoice") != null) {
            first_id = Integer.parseInt(request.getParameter("firstchoice"));
            second_id = Integer.parseInt(request.getParameter("secondchoice"));
            summ = Double.parseDouble(request.getParameter("sum"));
            Outgo outgo = new Outgo();
            outgo.setEmp_id(emp_id);
            Task task = new Task();
            task.setPtask_id(first_id);
            task.setDescription(taskDAO.getDescriptionByTaskId(second_id));
            taskDAO.addCategory(task);
            if (request.getParameter("thirdchoice") != null) {
                third_id = Integer.parseInt(request.getParameter("thirdchoice"));
                int ptask_id = taskDAO.getIdByDescription(first_id, task.getDescription());
                task = new Task();
                task.setPtask_id(ptask_id);
                task.setDescription(taskDAO.getDescriptionByTaskId(third_id));
                taskDAO.addCategory(task);
                outgo.setTask_id(taskDAO.getIdByDescription(task.getPtask_id(), task.getDescription()));
            } else {
                //чтобы не добавлять каждому проекту много детей, будем менять айдишник у задачи из базового Проекта и добавлять только ее
                outgo.setTask_id(taskDAO.getIdByDescription(first_id, task.getDescription()));
            }
            outgo.setSumm(summ);
            outgoDAO.addOutgo(outgo);
            EmployeeDAO employeeDAO = new EmployeeDAO(dao);
            Employee emp = employeeDAO.getUser(emp_id);
            emp.reduceBalance(summ);
            employeeDAO.updateUser(emp);
        }
        response.sendRedirect("/outgoes?emp_id=" + emp_id);
    }
}



