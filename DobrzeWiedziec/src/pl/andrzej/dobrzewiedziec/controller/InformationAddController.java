package pl.andrzej.dobrzewiedziec.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import pl.andrzej.dobrzewiedziec.model.User;
import pl.andrzej.dobrzewiedziec.service.InformationService;

@WebServlet("/add")
public class InformationAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String description = request.getParameter("inputDescription");
        String url = request.getParameter("inputUrl");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if(request.getUserPrincipal() != null) {
            InformationService informationService = new InformationService();
            informationService.addInformation(name, description, url, authenticatedUser);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            response.sendError(403);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getUserPrincipal() != null) {
            request.getRequestDispatcher("new.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }
}
