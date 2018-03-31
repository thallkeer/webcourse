package app.servlets;

import app.dao.BaseDAO;
import app.dao.impl.PostgresDAO;
import app.dao.impl.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");


        BaseDAO dao = PostgresDAO.getInstance();
//        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
//        dao.connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        EmployeeDAO userBean = new EmployeeDAO(dao);


        try {
            int userValidate = userBean.authenticateUser(login,password);

            switch (userValidate) {
                case 1: {
                    //System.out.println("Admin's Home");

                    HttpSession session = request.getSession(); //Creating a session
                    session.setAttribute("Admin", login); //setting session attribute
                    request.setAttribute("login", login);
                    request.getRequestDispatcher("Users.jsp").forward(request, response);
                    break;
                }
                case 2: {
                    //System.out.println("User's Home");

                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10*60);
                    session.setAttribute("User", login);
                    request.setAttribute("login", login);
                    request.getRequestDispatcher("Outgoes.jsp").forward(request, response);
                    break;
                }
                default:{
                    //System.out.println("Error message = Wrong login or password");
                    request.setAttribute("errMessage", "Wrong login or password");
                    request.getRequestDispatcher("Authpage.jsp").forward(request, response);
                    break;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
