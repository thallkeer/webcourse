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

@WebServlet("/addProject")
public class AddProjectServlet extends HttpServlet {
    @Override //Для проектов
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String login = (String) req.getSession().getAttribute("login");
        String title = req.getParameter("title");
        String org = req.getParameter("org");
        boolean archival = req.getParameterValues("archival") != null;
        PostgresDAO dao = PostgresDAO.getInstance();
        if (!title.equals("")){
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
