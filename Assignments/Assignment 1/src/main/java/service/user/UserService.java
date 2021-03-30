package service.user;

import model.Client;
import model.User;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id) throws EntityNotFoundException;

    Notification<Boolean> save(User user);

    void removeAll();

    boolean delete(int id);

    Notification<Boolean> update(User user);
}
