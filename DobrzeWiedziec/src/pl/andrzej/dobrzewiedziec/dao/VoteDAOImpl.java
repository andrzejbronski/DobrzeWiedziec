package pl.andrzej.dobrzewiedziec.dao;

import java.util.List;
import pl.andrzej.dobrzewiedziec.model.Vote;

public class VoteDAOImpl implements VoteDAO {

    @Override
    public Vote create(Vote newObject) {
        return null;
    }

    @Override
    public Vote read(Long primaryKey) {
        return null;
    }

    @Override
    public boolean update(Vote updateObject) {
        return false;
    }

    @Override
    public boolean delete(Long key) {
        return false;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public Vote getVoteByUserIdInformationId(long userId, long informationId) {
        return null;
    }
}
