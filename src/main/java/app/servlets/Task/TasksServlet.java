package app.servlets.Task;

import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int emp_id  = Integer.parseInt(req.getParameter("emp_id"));
        PostgresDAO dao = PostgresDAO.getInstance();
        TaskDAO taskDAO = new TaskDAO(dao);
        List<Task> tasks =  taskDAO.getAll(true);//taskDAO.getUserTasks(login);
        req.setAttribute("tasks",tasks);
        req.setAttribute("emp_id",emp_id);
        req.getRequestDispatcher("Tasks.jsp").forward(req,resp);

    }


}
