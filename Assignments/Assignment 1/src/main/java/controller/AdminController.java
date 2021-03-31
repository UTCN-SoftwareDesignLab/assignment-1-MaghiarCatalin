package controller;

import model.Activity;
import model.Client;
import model.ClientAccount;
import model.User;
import model.builder.ClientAccountBuilder;
import model.builder.ClientBuilder;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.user.UserRepository;
import service.account.AccountService;
import service.account.AccountServiceMySQL;
import service.activity.ActivityService;
import service.client.ClientService;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceMySQL;
import view.AdminView;
import view.DTO.ClientAccountDTO;
import view.DTO.ClientDTO;
import view.DTO.UserDTO;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private final AdminView adminView;
    private final UserService userService;
    private final ActivityService activityServiceMySQL;

    public AdminController(AdminView adminView, UserService userService, ActivityService activityServiceMySQL) {

        this.adminView = adminView;
        this.adminView.setVisible(false);

        this.userService = userService;
        this.activityServiceMySQL = activityServiceMySQL;

        adminView.setCreateUserButtonListener(new AdminController.CreateUserButtonListener());
        adminView.setViewUserButtonListener(new AdminController.ViewUserButtonListener());
        adminView.setDeleteUserButtonListener(new AdminController.DeleteUserButtonListener());
        adminView.setUpdateUserButtonListener(new AdminController.UpdateUserButtonListener());
    }

    private class CreateUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            UserDTO userDTO = adminView.getUserDTO();
            User user = new UserBuilder().buildFromDTO(userDTO);

            Notification<Boolean> accountNotification = userService.save(user);

            if (accountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), accountNotification.getFormattedErrors());
            } else {
                if (!accountNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User failed creation");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User created successfully");
                }
            }
        }
    }


    private class UpdateUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            User user = null;
            try {
                user = userService.findById(Integer.parseInt(adminView.getUserIdTXT()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            UserDTO userDTO = adminView.getUserDTO();
            User user1 = new UserBuilder().buildFromDTO(userDTO);

            user1.setId(user.getId());

            Notification<Boolean> accountNotification = userService.update(user1);

            if (accountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), accountNotification.getFormattedErrors());
            } else {
                if (!accountNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User did not update");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "User updated successfully");
                }
            }
        }
    }

    private class DeleteUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            User user = null;
            try {
                user = userService.findById(Integer.parseInt(adminView.getUserIdTXT()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            Boolean deleted = userService.delete(user.getId());

            if (deleted)
                JOptionPane.showMessageDialog(adminView.getContentPane(), "User successfully deleted");
            else
                JOptionPane.showMessageDialog(adminView.getContentPane(), "User failed uncreation");

        }
    }

    private class ViewUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            User user = null;
            try {
                user = userService.findById(Integer.parseInt(adminView.getUserIdTXT()));
            } catch (
                    EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            JOptionPane.showMessageDialog(adminView.getContentPane(), "Account information: Username: " +
                    user.getUsername() + " , Password: " + user.getPassword() + ", Role: " + user.getRoles());
        }
    }

    private class GetUserActivityButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Activity> activities = new ArrayList<>();
            activities = activityServiceMySQL.retrieveActivitiesByUser(adminView.getUsernameTXT(), adminView.getDateTXT());

            if(!activities.isEmpty()){
                for(Activity activity : activities) {
                    System.out.println(activity.getEmployeeId() + activity.getActivityName() + activity.getActivityTimeStamp());
                }
            }else{
                System.out.println("No activities");
            }
        }
    }

    public void setVisible(boolean b) {
        adminView.setVisible(b);
    }

}
