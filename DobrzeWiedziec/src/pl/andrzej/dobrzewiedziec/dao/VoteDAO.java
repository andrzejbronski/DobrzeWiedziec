package pl.andrzej.dobrzewiedziec.dao;

import pl.andrzej.dobrzewiedziec.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {

    public Vote getVoteByUserIdInformationId(long userId, long informationId);
}
