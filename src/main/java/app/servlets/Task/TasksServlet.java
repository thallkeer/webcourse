package app.servlets.Task;

import app.dao.BaseDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Task;
import app.entities.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseDAO dao = PostgresDAO.getInstance();
        TaskDAO taskDAO = new TaskDAO(dao);
        List<Task> tasklist = taskDAO.getTasksTree();
        req.setAttribute("tasks",tasklist);
        req.getRequestDispatcher("Tasks.jsp").forward(req,resp);

    }


}
