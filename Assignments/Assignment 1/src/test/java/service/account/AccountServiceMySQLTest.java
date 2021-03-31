package service.account;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Client;
import model.ClientAccount;
import model.builder.ClientAccountBuilder;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;

public class AccountServiceMySQLTest {
    private static AccountServiceMySQL accountServiceMySQL;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        accountServiceMySQL = new AccountServiceMySQL(new AccountRepositoryMySQL(connectionWrapper.getConnection()));
    }

    @Test
    public void testFindAll() {
        List<ClientAccount> all = accountServiceMySQL.findAll();
        Assert.assertFalse(all.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<ClientAccount> accounts = accountServiceMySQL.findAll();
        int current = accounts.get(accounts.size() - 1).getId();

        ClientAccount clientAccount = new ClientAccountBuilder().setCardNumber("1111111111111111").setClientId(1).setAccountType("Visa").setAmount(1000).setDateCreated(LocalDate.now().toString()).build();
        accountServiceMySQL.save(clientAccount);

        Assert.assertNotNull(accountServiceMySQL.findById(current));
    }

    @Test
    public void testSave() {
        Notification<Boolean> saved = accountServiceMySQL.save(new ClientAccountBuilder().setCardNumber("2222222222222222").setClientId(1).setAccountType("Visa").setAmount(1000).setDateCreated(LocalDate.now().toString()).build());
        Assert.assertTrue(saved.getFormattedErrors().isEmpty());
    }

    @Test
    public void testDelete() {
        ClientAccount clientAccount = new ClientAccountBuilder().setCardNumber("3333333333333333").setClientId(1).setAccountType("Visa").setAmount(1000).setDateCreated(LocalDate.now().toString()).build();
        accountServiceMySQL.save(clientAccount);
        boolean deleted = accountServiceMySQL.delete(clientAccount.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testUpdate() {
        ClientAccount clientAccount = new ClientAccountBuilder().setCardNumber("1234123412341234").setClientId(1).setAccountType("Visa").setAmount(1000).setDateCreated(LocalDate.now().toString()).build();
        clientAccount.setAccount_type("Mastercard");
        Notification<Boolean> updated = accountServiceMySQL.update(clientAccount);
        Assert.assertTrue(updated.getFormattedErrors().isEmpty());

    }

    @Test
    public void testTransfer() throws EntityNotFoundException {
        ClientAccount clientAccount1 = new ClientAccountBuilder().setCardNumber("4444444444444444").setClientId(1).setAccountType("Mastercard").setAmount(1000).setDateCreated(LocalDate.now().toString()).build();
        ClientAccount clientAccount2 = new ClientAccountBuilder().setCardNumber("5555555555555555").setClientId(1).setAccountType("Pay Pal").setAmount(1000).setDateCreated(LocalDate.now().toString()).build();

        accountServiceMySQL.save(clientAccount1);
        accountServiceMySQL.save(clientAccount2);

        accountServiceMySQL.transfer(clientAccount1, clientAccount2,100);

        boolean success;

        if(clientAccount1.getAmount() == 900 && clientAccount2.getAmount() == 1100)
            success = true;
        else
            success = false;

        Assert.assertTrue(success);

    }

    @Test
    public void testRemoveAll() {
        accountServiceMySQL.save(new ClientAccountBuilder().setCardNumber("4564456445644564").setClientId(1).setAccountType("Pay Pal").setAmount(1000).setDateCreated(LocalDate.now().toString()).build());
        accountServiceMySQL.removeAll();
        List<ClientAccount> accounts = accountServiceMySQL.findAll();
        Assert.assertTrue(accounts.isEmpty());
    }
}
