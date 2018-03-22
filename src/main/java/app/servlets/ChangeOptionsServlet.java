package app.servlets;

import app.dao.ITaskDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/changeOptions")
public class ChangeOptionsServlet extends HttpServlet {
    Map<Integer,String> descs = null;
    Map<Integer,String> seconds = null;
    Map<Integer,String> thirds = null;
    int[] indexes = new int[3];

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PostgresDAO dao = (PostgresDAO) session.getAttribute("dao");
        ITaskDAO taskDAO = new TaskDAO(dao);
        descs = (Map<Integer, String>) session.getAttribute("descs");
        boolean isChanged=false;
        if (req.getParameter("firstchoice") != null) {

            int selectedfirstIndex = Integer.valueOf(req.getParameter("firstchoice"));
            if (selectedfirstIndex!= indexes[0] && req.getParameter("thirdchoice") != null)
            {
                isChanged=true;
            }
            indexes[0]=selectedfirstIndex;
            seconds = taskDAO.getNextLvl(selectedfirstIndex);

        }
         if(req.getParameter("secondchoice") != null)
        {
            if (!isChanged){
                int selectedsecondIndex = Integer.valueOf(req.getParameter("secondchoice"));
                indexes[1]=selectedsecondIndex;
                thirds = taskDAO.getNextLvl(selectedsecondIndex);
            }
            else thirds = null;
        }
        req.setAttribute("indexes",indexes);
        req.setAttribute("descs",descs);
        req.setAttribute("seconds",seconds);
        req.setAttribute("thirds",thirds);
        req.getRequestDispatcher("AddOutgo.jsp").forward(req, resp);
    }

}
