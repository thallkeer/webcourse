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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int parent_id = Integer.parseInt(req.getParameter("projsel"));
        String upcategorydesc = req.getParameter("upcategory");
        String downcategorydesc = req.getParameter("downcategory");
        BaseDAO dao = PostgresDAO.getInstance();
        TaskDAO taskDAO = new TaskDAO(dao);
        Task upcategory = new Task();
        upcategory.setPtask_id(parent_id);
        upcategory.setDescription(upcategorydesc);
        if (!downcategorydesc.equals("")){
            taskDAO.addCategoryAndChild(upcategory,downcategorydesc);
        }
        else {
            taskDAO.addCategory(upcategory);
        }
        resp.sendRedirect("AddOutgo.jsp");
    }
}
