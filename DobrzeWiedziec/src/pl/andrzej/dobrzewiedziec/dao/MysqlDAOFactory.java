package pl.andrzej.dobrzewiedziec.dao;

public class MysqlDAOFactory extends DAOFactory {

    @Override
    public InformationDAO getInformationDAO() {
        return new InformationDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public VoteDAO getVoteDAO() {
        return new VoteDAOImpl();
    }

}