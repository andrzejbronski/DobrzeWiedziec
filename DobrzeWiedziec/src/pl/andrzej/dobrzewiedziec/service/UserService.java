package pl.andrzej.dobrzewiedziec.service;

import pl.andrzej.dobrzewiedziec.dao.DAOFactory;
import pl.andrzej.dobrzewiedziec.dao.UserDAO;
import pl.andrzej.dobrzewiedziec.model.User;

public class UserService {

    public void addUser(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setActive(true);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        userDao.create(user);
    }
}
