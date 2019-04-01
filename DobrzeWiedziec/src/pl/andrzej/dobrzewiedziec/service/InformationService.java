package pl.andrzej.dobrzewiedziec.service;
import java.sql.Timestamp;
import java.util.Date;
import pl.andrzej.dobrzewiedziec.dao.DAOFactory;
import pl.andrzej.dobrzewiedziec.dao.InformationDAO;
import pl.andrzej.dobrzewiedziec.model.Information;
import pl.andrzej.dobrzewiedziec.model.User;

public class InformationService {
    public void addInformation(String name, String desc, String url, User user) {
        Information information = createInformationObject(name, desc, url, user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        InformationDAO discoveryDao = factory.getInformationDAO();
        discoveryDao.create(information);
    }
    private Information createInformationObject(String name, String desc, String url, User user) {
        Information information = new Information();
        information.setName(name);
        information.setDescription(desc);
        information.setUrl(url);
        User userCopy = new User(user);
        information.setUser(userCopy);
        information.setTimestamp(new Timestamp(new Date().getTime()));
        return information;
    }
}
