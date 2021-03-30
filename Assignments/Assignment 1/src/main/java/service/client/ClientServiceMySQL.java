package service.client;

import model.Client;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.List;

public class ClientServiceMySQL implements ClientService{
    private final ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository repository) {
        this.clientRepository = repository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(int id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }

    @Override
    public Notification<Boolean> save(Client client) {

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if (!clientValid) {
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.save(client));
        return clientNotification;
    }

    @Override
    public Notification<Boolean> update(Client newClient) {

        ClientValidator clientValidator = new ClientValidator(newClient);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> clientNotification = new Notification<>();

        if (!clientValid) {
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);
        } else
            clientNotification.setResult(clientRepository.update(newClient));
        return clientNotification;
    }

    @Override
    public boolean delete(int id) {
        return clientRepository.delete(id);
    }

    @Override
    public void removeAll() {
        clientRepository.removeAll();
    }
}
