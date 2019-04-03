package pl.andrzej.dobrzewiedziec.service;

import java.sql.Timestamp;
import java.util.Date;

import pl.andrzej.dobrzewiedziec.dao.DAOFactory;
import pl.andrzej.dobrzewiedziec.dao.VoteDAO;
import pl.andrzej.dobrzewiedziec.model.Vote;
import pl.andrzej.dobrzewiedziec.model.VoteType;

public class VoteService {
    public Vote addVote(long informationId, long userId, VoteType voteType) {
        Vote vote = new Vote();
        vote.setInformationId(informationId);
        vote.setUserId(userId);
        vote.setDate(new Timestamp(new Date().getTime()));
        vote.setVoteType(voteType);
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        vote = voteDao.create(vote);
        return vote;

    }

    public Vote updateVote(long informationId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote voteToUpdate = voteDao.getVoteByUserIdInformationId(userId, informationId);
        if (voteToUpdate != null) {
            voteToUpdate.setVoteType(voteType);
            voteDao.update(voteToUpdate);
        }
        return voteToUpdate;
    }

    public Vote addOrUpdateVote(long informationId, long userId, VoteType voteType) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote vote = voteDao.getVoteByUserIdInformationId(userId, informationId);
        Vote resultVote = null;
        if (vote == null) {
            resultVote = addVote(informationId, userId, voteType);
        } else {
            resultVote = updateVote(informationId, userId, voteType);
        }
        return resultVote;
    }

    public Vote getVoteByInformationUserId(long informationId, long userId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        VoteDAO voteDao = factory.getVoteDAO();
        Vote vote = voteDao.getVoteByUserIdInformationId(userId, informationId);
        return vote;
    }
}
