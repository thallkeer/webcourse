package app.servlets;

import app.dao.impl.PostgresDAO;
import app.dao.impl.EmployeeBeanDAO;

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


        PostgresDAO dao = new PostgresDAO();
        dao.setURL(PostgresDAO.DEFAULT_HOST, PostgresDAO.DEFAULT_DATABASE, PostgresDAO.DEFAULT_PORT);
        dao.Connect(PostgresDAO.DEFAULT_LOGIN, PostgresDAO.DEFAULT_PASSWORD);
        EmployeeBeanDAO userBean = new EmployeeBeanDAO(dao);


        try {
            int userValidate = userBean.authenticateUser(login,password);

            switch (userValidate) {
                case 1: {
                    System.out.println("Admin's Home");

                    HttpSession session = request.getSession(); //Creating a session
                    session.setAttribute("Admin", login); //setting session attribute
                    request.setAttribute("login", login);

                    request.getRequestDispatcher("/Views/Admin.jsp").forward(request, response);
                }
                case 2: {
                    System.out.println("User's Home");

                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10*60);
                    session.setAttribute("User", login);
                    request.setAttribute("login", login);

                    request.getRequestDispatcher("/Views/User.jsp").forward(request, response);
                }
                default:{
                    System.out.println("Error message = Wrong login or password");
                    request.setAttribute("errMessage", "Wrong login or password");

                    request.getRequestDispatcher("/Views/Authpage.jsp").forward(request, response);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
