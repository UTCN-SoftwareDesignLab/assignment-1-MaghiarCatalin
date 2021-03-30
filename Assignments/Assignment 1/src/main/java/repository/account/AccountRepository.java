package repository.account;

import model.ClientAccount;
import repository.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {
    List<ClientAccount> findAll();

    ClientAccount findById(Long id) throws EntityNotFoundException;

    boolean save(ClientAccount clientAccount);

    boolean update(ClientAccount newAccount);

    boolean delete(Long id);

    void removeAll();
}
