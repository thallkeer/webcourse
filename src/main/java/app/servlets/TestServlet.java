package app.servlets;

import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/test")
public class TestServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostgresDAO dao = PostgresDAO.getInstance();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        TaskDAO taskDAO = new TaskDAO(dao);

        Set<Integer> parentids = taskDAO.getParents().keySet();
        List<Task> tasklist = new ArrayList<>();


        for (Integer id:parentids
                ) {
            tasklist.addAll(taskDAO.getTasksTreeByTaskId(id));
        }
        req.setAttribute("tasks",tasklist);
        req.setAttribute("tmpid",0);
        req.getRequestDispatcher("Test.jsp").forward(req,resp);
    }
}
