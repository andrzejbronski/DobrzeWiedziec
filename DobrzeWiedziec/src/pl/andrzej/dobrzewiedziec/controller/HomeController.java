package pl.andrzej.dobrzewiedziec.controller;

import pl.andrzej.dobrzewiedziec.model.Information;
import pl.andrzej.dobrzewiedziec.service.InformationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@WebServlet("")
public class HomeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        saveInformationsInRequest(request);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    private void saveInformationsInRequest(HttpServletRequest request) {
        InformationService informationService = new InformationService();
        List<Information> allInformations = informationService.getAllInformations(new Comparator<Information>() {
            //more votes = higher
            @Override
            public int compare(Information d1, Information d2) {
                int d1Vote = d1.getUpVote() - d1.getDownVote();
                int d2Vote = d2.getUpVote() - d2.getDownVote();
                if(d1Vote < d2Vote) {
                    return 1;
                } else if(d1Vote > d2Vote) {
                    return -1;
                }
                return 0;
            }
        });
        request.setAttribute("informations", allInformations);
    }
}
