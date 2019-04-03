package pl.andrzej.dobrzewiedziec.service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

import pl.andrzej.dobrzewiedziec.dao.DAOFactory;
import pl.andrzej.dobrzewiedziec.dao.InformationDAO;
import pl.andrzej.dobrzewiedziec.model.Information;
import pl.andrzej.dobrzewiedziec.model.User;

public class InformationService {
    public void addInformation(String name, String desc, String url, User user) {
        Information information = createInformationObject(name, desc, url, user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        InformationDAO informationDao = factory.getInformationDAO();
        informationDao.create(information);
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

    public Information getInformationById(long informationId) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        InformationDAO informationDao = factory.getInformationDAO();
        Information information = informationDao.read(informationId);
        return information;
    }

    public boolean updateInformation(Information information) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        InformationDAO informationDao = factory.getInformationDAO();
        boolean result = informationDao.update(information);
        return result;
    }

    public List<Information> getAllInformations() {
        return getAllInformations(null);
    }

    public List<Information> getAllInformations(Comparator<Information> comparator) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        InformationDAO informationDao = factory.getInformationDAO();
        List<Information> informations = informationDao.getAll();
        if (comparator != null && informations != null) {
            informations.sort(comparator);
        }
        return informations;
    }
}
