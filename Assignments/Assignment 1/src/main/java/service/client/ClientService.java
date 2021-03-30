package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(int id) throws EntityNotFoundException;

    Notification<Boolean> save(Client client);

    void removeAll();

    boolean delete(int id);

    Notification<Boolean> update(Client client);
}
