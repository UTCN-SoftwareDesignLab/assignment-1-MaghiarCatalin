package service.account;

import model.ClientAccount;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountService {

    List<ClientAccount> findAll();

    ClientAccount findById(Long id) throws EntityNotFoundException;

    Notification<Boolean> save(ClientAccount account);

    Notification<Boolean> update(ClientAccount account);

    boolean delete(Long id);

    void removeAll();
}
