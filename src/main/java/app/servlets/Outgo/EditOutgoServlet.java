package app.servlets.Outgo;

import app.dao.BaseDAO;
import app.dao.impl.OutgoDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.TaskDAO;
import app.entities.Outgo;
import app.entities.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/editOutgo")
public class EditOutgoServlet extends HttpServlet{
    private Outgo outgoToEdit;
    private BaseDAO dao;
    private TaskDAO taskDAO;
    private OutgoDAO outgoDAO;

    @Override
    public void init() throws ServletException {
        dao = PostgresDAO.getInstance();
        outgoDAO = new OutgoDAO(dao);
        taskDAO = new TaskDAO(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int outgo_id = Integer.valueOf(req.getParameter("outgo_id"));
        outgoToEdit = outgoDAO.getOutgoById(outgo_id);
        int parent_id = taskDAO.getParentTaskId(outgoToEdit.getTask_id());
        //получаем родителя самого верхнего уровня по task_id редактируемого расхода
        Task tmp = taskDAO.getTaskParent(outgoToEdit.getTask_id());
        Map<Integer,String> descs = taskDAO.getParents();
        //получаем для первого уровня категории уровнем ниже
        Map<Integer,String> seconds = taskDAO.getNextLvl(tmp.getTask_id());
        Map<Integer,String> thirds = taskDAO.getNextLvl(parent_id);

        int[] indexes = new int[]{ tmp.getTask_id(),parent_id,outgoToEdit.getTask_id()};
        req.setAttribute("sum",outgoToEdit.getSumm());
        session.setAttribute("descs",descs);
        req.setAttribute("seconds",seconds);
        req.setAttribute("thirds",thirds);
        req.setAttribute("indexes",indexes);
        req.getRequestDispatcher("EditOutgo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int second_id, third_id;
        double summ;
        if (req.getParameter("firstchoice") != null && req.getParameter("secondchoice") != null) {
//            first_id = Integer.parseInt(request.getParameter("firstchoice"));
            second_id = Integer.parseInt(req.getParameter("secondchoice"));
            summ = Double.parseDouble(req.getParameter("sum"));
            if (req.getParameter("thirdchoice") != null) {
                third_id = Integer.parseInt(req.getParameter("thirdchoice"));
                outgoToEdit.setTask_id(third_id);
            } else {
                outgoToEdit.setTask_id(second_id);
            }
            outgoToEdit.setSumm(summ);

            outgoDAO.updateOutgo(outgoToEdit);
        }
        resp.sendRedirect("/outgoes?emp_id=" + outgoToEdit.getEmp_id());
    }
}
