package repository.user;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.ClientAccount;
import model.User;
import model.builder.ClientAccountBuilder;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.account.AccountRepositoryMySQL;

import java.time.LocalDate;
import java.util.List;

public class UserRepositoryMySQLTest {
    private static UserRepositoryMySQL userRepositoryMySQL;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        userRepositoryMySQL = new UserRepositoryMySQL(connectionWrapper.getConnection());
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepositoryMySQL.findAll();
        Assert.assertTrue(users.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<User> users = userRepositoryMySQL.findAll();
        int current = users.get(users.size() - 1).getId();

        User user = new UserBuilder().setUsername("rusu@gmail.com").setPassword("paroltest123!").setRole("employee").build();
        userRepositoryMySQL.save(user);

        Assert.assertNotNull(userRepositoryMySQL.findById(current + 1));
    }

    @Test
    public void testSave() {
        boolean saved = userRepositoryMySQL.save(new UserBuilder().setUsername("gavrea@gmail.com").setPassword("paroltest123!").setRole("employee").build());
        Assert.assertTrue(saved);
    }

    @Test
    public void testDelete() {
        User user = new UserBuilder().setUsername("cata@gmail.com").setPassword("paroltest123!").setRole("employee").build();
        userRepositoryMySQL.save(user);
        boolean deleted = userRepositoryMySQL.delete(user.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testUpdate() {
        User user = new UserBuilder().setUsername("horatiu@gmail.com").setPassword("paroltest123!").setRole("employee").build();
        user.setRole("Admin");
        boolean updated = userRepositoryMySQL.update(user);
        Assert.assertTrue(updated);

    }

    @Test
    public void testRemoveAll() {
        userRepositoryMySQL.save(new UserBuilder().setUsername("liviu@gmail.com").setPassword("paroltest123!").setRole("employee").build());
        userRepositoryMySQL.removeAll();
        List<User> users = userRepositoryMySQL.findAll();
        Assert.assertTrue(users.isEmpty());
    }
}
