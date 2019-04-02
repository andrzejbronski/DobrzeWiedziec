package pl.andrzej.dobrzewiedziec.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import pl.andrzej.dobrzewiedziec.model.Information;
import pl.andrzej.dobrzewiedziec.model.User;
import pl.andrzej.dobrzewiedziec.model.Vote;
import pl.andrzej.dobrzewiedziec.model.VoteType;
import pl.andrzej.dobrzewiedziec.service.InformationService;
import pl.andrzej.dobrzewiedziec.service.VoteService;

@WebServlet("/vote")
public class VoteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if(loggedUser != null) {
            VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
            long userId  = loggedUser.getId();
            long informationId = Long.parseLong(request.getParameter("information_id"));
            updateVote(userId, informationId, voteType);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
    private void updateVote(long userId, long informationId, VoteType voteType) {
        VoteService voteService = new VoteService();
        Vote existingVote = voteService.getVoteByInformationUserId(informationId, userId);
        Vote updatedVote = voteService.addOrUpdateVote(informationId, userId, voteType);
        if(existingVote != updatedVote || !updatedVote.equals(existingVote)) {
            updateInformation(informationId, existingVote, updatedVote);
        }
    }
    private void updateInformation(long informationId, Vote oldVote, Vote newVote) {
        InformationService informationService = new InformationService();
        Information informationById = informationService.getInformationById(informationId);
        Information updatedInformation = null;
        if(oldVote == null && newVote != null) {
            updatedInformation = addInformationVote(informationById, newVote.getVoteType());
        } else if(oldVote != null && newVote != null) {
            updatedInformation = removeInformationVote(informationById, oldVote.getVoteType());
            updatedInformation = addInformationVote(updatedInformation, newVote.getVoteType());
        }
        informationService.updateInformation(updatedInformation);
    }
    private Information addInformationVote(Information information, VoteType voteType) {
        Information informationCopy = new Information(information);
        if(voteType == VoteType.VOTE_UP) {
            informationCopy.setUpVote(informationCopy.getUpVote() + 1);
        } else if(voteType == VoteType.VOTE_DOWN) {
            informationCopy.setDownVote(informationCopy.getDownVote() + 1);
        }
        return informationCopy;
    }

    private Information removeInformationVote(Information information, VoteType voteType) {
        Information informationCopy = new Information(information);
        if(voteType == VoteType.VOTE_UP) {
            informationCopy.setUpVote(informationCopy.getUpVote() - 1);
        } else if(voteType == VoteType.VOTE_DOWN) {
            informationCopy.setDownVote(informationCopy.getDownVote() - 1);
        }
        return informationCopy;
    }
}
