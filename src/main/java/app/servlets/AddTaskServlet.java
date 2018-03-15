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
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

@WebServlet("/AddTask")
public class AddTaskServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String login = (String) req.getSession().getAttribute("login");
        String title = req.getParameter("title");
        String org = req.getParameter("org");
        boolean archival = req.getParameterValues("archival") != null;

        if (!title.equals("")){
            PostgresDAO dao = new PostgresDAO();
            dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
            dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
            TaskDAO taskDAO = new TaskDAO(dao);
            Task task = new Task();
            task.setDescription(title);
            task.setOrganization(org);
            task.setArchival(archival);
            taskDAO.addProject(task);
            req.getRequestDispatcher("Tasks.jsp").forward(req, resp);
        }

    }
}
