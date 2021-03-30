package service.user;

import model.Client;
import model.User;
import model.validation.ClientValidator;
import model.validation.Notification;
import model.validation.UserValidator;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.user.UserRepository;
import service.client.ClientService;

import java.util.List;

public class UserServiceMySQL implements UserService {
    private final UserRepository userRepository;

    public UserServiceMySQL(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) throws EntityNotFoundException {
        return userRepository.findById(id);
    }

    @Override
    public Notification<Boolean> save(User user) {

        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        Notification<Boolean> userNotification = new Notification<>();

        if (!userValid) {
            userValidator.getErrors().forEach(userNotification::addError);
            userNotification.setResult(Boolean.FALSE);
        } else
            userNotification.setResult(userRepository.save(user));
        return userNotification;
    }

    @Override
    public Notification<Boolean> update(User newUser) {

        UserValidator userValidator = new UserValidator(newUser);
        boolean userValid = userValidator.validate();
        Notification<Boolean> userNotification = new Notification<>();

        if (!userValid) {
            userValidator.getErrors().forEach(userNotification::addError);
            userNotification.setResult(Boolean.FALSE);
        } else
            userNotification.setResult(userRepository.update(newUser));
        return userNotification;
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public void removeAll() {
        userRepository.removeAll();
    }
}
