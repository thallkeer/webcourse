package app.servlets.Outgo;

import app.dao.BaseDAO;
import app.dao.impl.EmployeeDAO;
import app.dao.impl.OutgoDAO;
import app.dao.impl.PostgresDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteOutgo")
public class DeleteOutgoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int outgo_id = Integer.valueOf(req.getParameter("outgo_id"));
        BaseDAO dao = PostgresDAO.getInstance();
        OutgoDAO outgoDAO = new OutgoDAO(dao);
        outgoDAO.deleteOutgo(outgo_id);
        resp.sendRedirect("/outgoes");
    }
}
