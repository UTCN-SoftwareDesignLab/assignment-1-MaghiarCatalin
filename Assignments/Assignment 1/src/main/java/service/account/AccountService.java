package service.account;

import model.ClientAccount;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountService {

    List<ClientAccount> findAll();

    ClientAccount findById(int id) throws EntityNotFoundException;

    Notification<Boolean> save(ClientAccount account);

    Notification<Boolean> update(ClientAccount account);

    boolean delete(int id);

    void removeAll();

    void transfer(ClientAccount clientAccount1, ClientAccount clientAccount2, Integer amount) throws EntityNotFoundException;
}
