package repository.account;

import model.ClientAccount;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {
    List<ClientAccount> findAll();

    ClientAccount findById(int id) throws EntityNotFoundException;

    boolean save(ClientAccount clientAccount);

    boolean update(ClientAccount newAccount);

    boolean delete(int id);

    void removeAll();

    void transfer(ClientAccount clientAccount1, ClientAccount clientAccount2, Integer amount) throws EntityNotFoundException;
}
