package app.servlets.Outgo;

import app.dao.BaseDAO;
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
    private Map<Integer, String> seconds;
    private Map<Integer, String> thirds;
    private int[] indexes = new int[3];
    private ITaskDAO taskDAO;

    @Override
    public void init() {
//        descs = null;
//        seconds = null;
//        thirds = null;
        indexes = new int[3];
        taskDAO = new TaskDAO(PostgresDAO.getInstance());
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Map<Integer, String> descs = (Map<Integer, String>) session.getAttribute("descs");
        double sum=0;
        if (req.getParameter("sum")!=null) {
            sum = Double.parseDouble(req.getParameter("sum"));
        }
        boolean isChanged = false;
        if (req.getParameter("firstchoice") != null) {

            int selectedfirstIndex = Integer.valueOf(req.getParameter("firstchoice"));
            if (selectedfirstIndex != indexes[0] && req.getParameter("thirdchoice") != null) {
                isChanged = true;
            }
            indexes[0] = selectedfirstIndex;
            if (selectedfirstIndex != 2 && selectedfirstIndex != 3) {
                seconds = taskDAO.getNextLvl(selectedfirstIndex);
                seconds.putAll(dublicateHelper(taskDAO.getNextLvl(1)));
            } else {
                seconds = taskDAO.getNextLvl(selectedfirstIndex);
            }

        }
        if (req.getParameter("secondchoice") != null ) {
            if (!isChanged || req.getParameter("editMode") != null) {
                int selectedsecondIndex = Integer.valueOf(req.getParameter("secondchoice"));
                indexes[1] = selectedsecondIndex;
                thirds = taskDAO.getNextLvl(selectedsecondIndex);
            } else thirds = null;
        }
        req.setAttribute("sum", sum);
        req.setAttribute("indexes", indexes);
        req.setAttribute("descs", descs);
        req.setAttribute("seconds", seconds);
        req.setAttribute("thirds", thirds);
        if (req.getParameter("editMode") != null) {
            req.getRequestDispatcher("EditOutgo.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("AddOutgo.jsp").forward(req, resp);
        }
    }

    private Map<Integer,String> dublicateHelper(Map<Integer,String> param){
        Map<Integer,String> resMap = new HashMap<>();
        for (Map.Entry entry : param.entrySet()){
            if (!seconds.containsValue(entry.getValue())){
                int key = (int) entry.getKey();
               resMap.put(key, (String) entry.getValue());
            }
        }
        return resMap;
    }
}


