package app.servlets.Task;
import app.dao.BaseDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer task_id = Integer.valueOf(req.getParameter("task_id"));
        TaskDAO taskDAO = new TaskDAO(PostgresDAO.getInstance());
        taskDAO.delete(task_id);
        resp.sendRedirect("/outgoes");
    }
}
