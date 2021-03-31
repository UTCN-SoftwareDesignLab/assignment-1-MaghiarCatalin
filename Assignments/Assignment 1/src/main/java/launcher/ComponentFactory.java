package launcher;

import controller.AdminController;
import controller.EmployeeController;
import controller.LoginController;
import database.DBConnectionFactory;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceMySQL;
import service.activity.ActivityServiceMySQL;
import service.client.ClientService;
import service.client.ClientServiceMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceMySQL;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final LoginView loginView;
    private final EmployeeView employeeView;
    private final AdminView adminView;

    private final LoginController loginController;
    private final EmployeeController employeeController;
    private final AdminController adminController;


    private final AuthenticationService authenticationService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final UserService userService;


    private final UserRepository userRepository;
    private final ClientRepositoryMySQL clientRepositoryMySQL;
    private final AccountRepositoryMySQL accountRepositoryMySQL;
    private final UserRepositoryMySQL userRepositoryMySQL;
    private final ActivityServiceMySQL activityServiceMySQL;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.userRepository = new UserRepositoryMySQL(connection);
        this.clientRepositoryMySQL = new ClientRepositoryMySQL(connection);
        this.accountRepositoryMySQL = new AccountRepositoryMySQL(connection);
        this.userRepositoryMySQL = new UserRepositoryMySQL(connection);
        this.activityServiceMySQL = new ActivityServiceMySQL(connection);
        this.clientService = new ClientServiceMySQL(clientRepositoryMySQL);
        this.accountService = new AccountServiceMySQL(accountRepositoryMySQL);
        this.userService = new UserServiceMySQL(userRepositoryMySQL);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository);
        this.loginView = new LoginView();
        this.employeeView = new EmployeeView();
        this.adminView = new AdminView();
        this.adminController = new AdminController(this.adminView, this.userService, this.activityServiceMySQL);
        this.employeeController = new EmployeeController(this.employeeView, this.accountService, this.clientService, this.activityServiceMySQL);
        this.loginController = new LoginController(loginView, authenticationService, employeeController, adminController);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }


    public ClientRepositoryMySQL getClientRepositoryMySQL() {
        return clientRepositoryMySQL;
    }

    public AccountRepositoryMySQL getAccountRepositoryMySQL() {
        return accountRepositoryMySQL;
    }

    public static ComponentFactory getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginController getLoginController() {
        return loginController;
    }
}
