package app.servlets.Task;

import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet{
    private Task taskToEdit;
    private TaskDAO taskDAO;

    @Override
    public void init() {
       taskDAO = new TaskDAO(PostgresDAO.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer task_id;
        task_id = Integer.valueOf(req.getParameter("task_id"));
        taskToEdit = taskDAO.getTaskById(task_id);
        String parent = taskDAO.getTaskParent(task_id).getDescription();
        req.setAttribute("parent",parent);
        req.setAttribute("task", taskToEdit);
        req.getRequestDispatcher("EditTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String newDescription = req.getParameter("description");
        taskDAO.update(taskToEdit.getTask_id(),newDescription);
        resp.sendRedirect("/tasks");
    }
}
