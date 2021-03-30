package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();

    Client findById(int id) throws EntityNotFoundException;

    boolean save(Client client);

    boolean update(Client newClient);

    boolean delete(int id);

    void removeAll();
}
