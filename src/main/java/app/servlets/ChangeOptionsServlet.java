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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebServlet("/ChangeOptions")
public class ChangeOptionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int selectedfirstIndex = Integer.valueOf(req.getParameter("firstchoice"));
//        String canGet = req.getParameter("hiden");
//        int selectedsecondIndex = 1;
//        if(canGet.equals("true"))
//            selectedsecondIndex= Integer.parseInt(Objects.requireNonNull(req.getParameter("secondchoice")));

        PostgresDAO dao = new PostgresDAO();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        ITaskDAO taskDAO = new TaskDAO(dao);
        List<String> descs = taskDAO.getDescriptionByIdLvl(selectedfirstIndex+1,2);
        req.setAttribute("descs2", descs);
//        if (selectedsecondIndex!=-1){
//            req.setAttribute("descs3", taskDAO.getNextLvl(4));
//        }
        req.getRequestDispatcher("Outgoes.jsp").forward(req, resp);

    }

}
