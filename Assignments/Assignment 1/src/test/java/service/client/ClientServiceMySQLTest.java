package service.client;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Client;
import model.builder.ClientBuilder;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.client.ClientRepositoryMySQL;

import java.util.List;

public class ClientServiceMySQLTest {
    private static ClientServiceMySQL clientServiceMySQL;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);

        clientServiceMySQL = new ClientServiceMySQL(new ClientRepositoryMySQL(connectionWrapper.getConnection()));
    }

    @Test
    public void testFindAll() {
        List<Client> clients = clientServiceMySQL.findAll();
        Assert.assertTrue(clients.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<Client> clients = clientServiceMySQL.findAll();
        int current = clients.get(clients.size() - 1).getId();

        Client client = new ClientBuilder().setName("Andrei").setIdentity_card_number("1111").setPNC("123456789123").setAddress("Cluj").setEmail("andrei@andrei.com").build();
        clientServiceMySQL.save(client);

        Assert.assertNotNull(clientServiceMySQL.findById(current + 1));
    }

    @Test
    public void testSave() {
        Notification<Boolean> saved = clientServiceMySQL.save(new ClientBuilder().setName("Mircea").setIdentity_card_number("0000").setPNC("223456789123").setAddress("Arad").setEmail("mircea@gmail.com").build());
        Assert.assertTrue(saved.getFormattedErrors().isEmpty());
    }

    @Test
    public void testDelete() {
        Client client = new ClientBuilder().setName("Elisa").setIdentity_card_number("0000").setPNC("223456789124").setAddress("Arad").setEmail("elisa@gmail.com").build();
        clientServiceMySQL.save(client);
        boolean deleted = clientServiceMySQL.delete(client.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testUpdate() {
        Client client = new ClientBuilder().setName("Daniel").setIdentity_card_number("0000").setPNC("123456789124").setAddress("Bucuresti").setEmail("daniel@gmail.com").build();
        client.setName("Vlad");
        Notification<Boolean> updated = clientServiceMySQL.update(client);
        Assert.assertTrue(updated.getFormattedErrors().isEmpty());
    }

    @Test
    public void testRemoveAll() {
        clientServiceMySQL.save(new ClientBuilder().setName("Catalin").setIdentity_card_number("0000").setPNC("123456789127").setAddress("Sibiu").setEmail("catalin@gmail.com").build());
        clientServiceMySQL.removeAll();
        List<Client> noClients = clientServiceMySQL.findAll();
        Assert.assertTrue(noClients.isEmpty());
    }
}
