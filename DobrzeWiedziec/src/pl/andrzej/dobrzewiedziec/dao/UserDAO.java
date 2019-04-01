package pl.andrzej.dobrzewiedziec.dao;

import java.util.List;
import pl.andrzej.dobrzewiedziec.model.User;

public interface UserDAO extends GenericDAO<User, Long> {

    List<User> getAll();
    User getUserByUsername(String username);

}
