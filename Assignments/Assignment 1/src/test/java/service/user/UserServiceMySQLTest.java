package service.user;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.user.UserRepositoryMySQL;

import java.util.List;

public class UserServiceMySQLTest {
    private static UserServiceMySQL userServiceMySQL;

    @BeforeClass
    public static void setup() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        userServiceMySQL = new UserServiceMySQL(new UserRepositoryMySQL(connectionWrapper.getConnection()));
    }

    @Test
    public void testFindAll() {
        List<User> users = userServiceMySQL.findAll();
        Assert.assertTrue(users.isEmpty());
    }

    @Test
    public void testFindById() throws EntityNotFoundException {
        List<User> users = userServiceMySQL.findAll();
        int current = users.get(users.size() - 1).getId();

        User user = new UserBuilder().setUsername("rusu@gmail.com").setPassword("paroltest123!").setRole("employee").build();
        userServiceMySQL.save(user);

        Assert.assertNotNull(userServiceMySQL.findById(current + 1));
    }

    @Test
    public void testSave() {
        Notification<Boolean> saved = userServiceMySQL.save(new UserBuilder().setUsername("gavrea@gmail.com").setPassword("paroltest123!").setRole("employee").build());
        Assert.assertTrue(saved.getFormattedErrors().isEmpty());
    }

    @Test
    public void testDelete() {
        User user = new UserBuilder().setUsername("cata@gmail.com").setPassword("paroltest123!").setRole("employee").build();
        userServiceMySQL.save(user);
        boolean deleted = userServiceMySQL.delete(user.getId());
        Assert.assertTrue(deleted);
    }

    @Test
    public void testUpdate() {
        User user = new UserBuilder().setUsername("horatiu@gmail.com").setPassword("paroltest123!").setRole("employee").build();
        user.setRole("Admin");
        Notification<Boolean> updated = userServiceMySQL.update(user);
        Assert.assertTrue(updated.getFormattedErrors().isEmpty());

    }

    @Test
    public void testRemoveAll() {
        userServiceMySQL.save(new UserBuilder().setUsername("liviu@gmail.com").setPassword("paroltest123!").setRole("employee").build());
        userServiceMySQL.removeAll();
        List<User> users = userServiceMySQL.findAll();
        Assert.assertTrue(users.isEmpty());
    }
}
