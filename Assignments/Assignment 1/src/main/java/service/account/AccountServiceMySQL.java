package service.account;

import model.Client;
import model.ClientAccount;
import model.validation.AccountValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.List;

public class AccountServiceMySQL implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceMySQL(AccountRepository repository) {
        this.accountRepository = repository;
    }

    @Override
    public List<ClientAccount> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public ClientAccount findById(int id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public Notification<Boolean> save(ClientAccount clientAccount) {

        AccountValidator accountValidator = new AccountValidator(clientAccount);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> accountNotification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(Boolean.FALSE);
        } else
            accountNotification.setResult(accountRepository.save(clientAccount));
        return accountNotification;
    }

    @Override
    public Notification<Boolean> update(ClientAccount clientAccount) {

        AccountValidator accountValidator = new AccountValidator(clientAccount);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> accountNotification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(accountNotification::addError);
            accountNotification.setResult(Boolean.FALSE);
        } else
            accountNotification.setResult(accountRepository.update(clientAccount));
        return accountNotification;
    }

    @Override
    public void removeAll() {
        accountRepository.removeAll();
    }

    @Override
    public boolean delete(int id) {
        return accountRepository.delete(id);
    }

    @Override
    public void transfer(ClientAccount clientAccount1, ClientAccount clientAccount2, Integer amount) throws EntityNotFoundException {
        accountRepository.transfer(clientAccount1, clientAccount2, amount);
    }
}
