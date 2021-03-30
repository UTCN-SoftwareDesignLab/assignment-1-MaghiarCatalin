package controller;

import model.User;
import model.validation.Notification;
import service.user.AuthenticationService;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {
    public static String username;
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final EmployeeController employeeController;
    private final AdminController adminController;

    public LoginController(LoginView loginView, AuthenticationService authenticationService, EmployeeController employeeController, AdminController adminController) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.employeeController = employeeController;
        this.adminController = adminController;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username1 = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<User> loginNotification = authenticationService.login(username1, password);

            if (loginNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
            } else {
                if(!username1.equals("admin@gmail.com")) {
                    username = username1;
                    loginView.setVisible(false);
                    employeeController.setVisible(true);
                } else {
                    loginView.setVisible(false);
                    adminController.setVisible(true);
                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);

            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}
