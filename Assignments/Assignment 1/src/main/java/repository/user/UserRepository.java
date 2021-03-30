package repository.user;

import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password);

    User findById(int id) throws EntityNotFoundException;

    boolean save(User user);

    boolean update(User newUser);

    boolean delete(int id);

    void removeAll();

}
