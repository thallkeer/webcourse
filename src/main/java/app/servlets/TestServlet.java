package app.servlets;

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
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BaseDAO dao = PostgresDAO.getInstance();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        TaskDAO taskDAO = new TaskDAO(dao);

//        Set<Integer> parentids = taskDAO.getParents().keySet();
//        List<Task> tasklist = new ArrayList<>();
//
//
//        for (Integer id:parentids
//                ) {
//            tasklist.addAll(taskDAO.getTasksTreeByTaskId(id));
//        }
//        int tmpid =0;
        List<Task> parents = taskDAO.getParentsList();

        req.setAttribute("parents",parents);
        req.getRequestDispatcher("Test.jsp").forward(req,resp);
    }
}
