package controller;

import model.Client;
import model.ClientAccount;
import model.builder.ClientAccountBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import service.account.AccountService;
import service.client.ClientService;
import view.DTO.ClientAccountDTO;
import view.DTO.ClientDTO;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController {
    private final EmployeeView employeeView;
    private final AccountService accountService;
    private final ClientService clientService;

    public EmployeeController(EmployeeView employeeView, AccountService accountService, ClientService clientService) {

        this.employeeView = employeeView;
        this.employeeView.setVisible(false);

        this.accountService = accountService;
        this.clientService = clientService;

        employeeView.setCreateAccountButtonListener(new CreateAccountButtonListener());
        employeeView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
        employeeView.setDeleteAccountButtonListener(new DeleteAccountButtonListener());
        employeeView.setViewAccountButtonListener(new ViewAccountButtonListener());

        employeeView.setCreateClientButtonListener(new CreateClientButtonListener());
        employeeView.setUpdateClientButtonListener(new UpdateClientButtonListener());
        employeeView.setDeleteClientButtonListener(new DeleteClientButtonListener());
        employeeView.setViewClientButtonListener(new ViewClientButtonListener());

    }

    private class CreateAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            ClientAccountDTO clientAccountDTO = employeeView.getAccountDTO();
            ClientAccount clientAccount = new ClientAccountBuilder().buildFromDTO(clientAccountDTO);

            Notification<Boolean> accountNotification = accountService.save(clientAccount);

            if (accountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), accountNotification.getFormattedErrors());
            } else {
                if (!accountNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account failed creation");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account created successfully");
                }
            }
        }
    }


    private class UpdateAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            ClientAccount clientAccount = null;
            try {
                clientAccount = accountService.findById(Long.parseLong(employeeView.getAccountId()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            ClientAccountDTO clientAccountDTO = employeeView.getAccountDTO();
            ClientAccount clientAccount1 = new ClientAccountBuilder().buildFromDTO(clientAccountDTO);

            clientAccount1.setId(clientAccount.getId());

            Notification<Boolean> accountNotification = accountService.update(clientAccount1);

            if (accountNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), accountNotification.getFormattedErrors());
            } else {
                if (!accountNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account did not update");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account updated successfully");
                }
            }
        }
    }

    private class DeleteAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            ClientAccount clientAccount = null;
            try {
                clientAccount = accountService.findById(Long.parseLong(employeeView.getAccountId()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            Boolean deleted = accountService.delete(clientAccount.getId());

            if (deleted)
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account successfully deleted");
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account failed uncreation");

        }
    }

    private class ViewAccountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            ClientAccount clientAccount = null;
            try {
                clientAccount = accountService.findById(Long.parseLong(employeeView.getAccountId()));
            } catch (
                    EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            JOptionPane.showMessageDialog(employeeView.getContentPane(), "Account information: Card number: " +
                    clientAccount.getCard_number() + " , Type: " + clientAccount.getAccount_type() +
                    ", Amount: " + clientAccount.getAmount() + ", Date Created: " +
                    clientAccount.getDate_created());
        }
    }

    public void setVisible(boolean b) {
        employeeView.setVisible(b);
    }

    private class CreateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            ClientDTO clientDTO = employeeView.getClientDTO();
            Client client = new ClientBuilder().buildFromDTO(clientDTO);

            Notification<Boolean> clientNotification = clientService.save(client);

            if (clientNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), clientNotification.getFormattedErrors());
            } else {
                if (!clientNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client failed creation");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client created successfully");
                }
            }
        }
    }

    private class UpdateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Client client = null;
            try {
                client = clientService.findById(Long.parseLong(employeeView.getClientIdTXT()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            ClientDTO clientDTO = employeeView.getClientDTO();
            Client client1 = new ClientBuilder().buildFromDTO(clientDTO);

            client1.setId(client.getId());

            Notification<Boolean> clientNotification = clientService.save(client1);
            if (clientNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), clientNotification.getFormattedErrors());
            } else {
                if (!clientNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client did not update");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client updated successfully");
                }
            }
        }
    }

    private class DeleteClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Client client = null;
            try {
                client = clientService.findById(Long.parseLong(employeeView.getClientIdTXT()));
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            Boolean deleted = clientService.delete(client.getId());

            if (deleted)
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client deleted successfully");
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client failed uncreation");

        }
    }

    private class ViewClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Client client = null;
            try {
                client = clientService.findById(Long.parseLong(employeeView.getClientIdTXT()));
            } catch (
                    EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }

            JOptionPane.showMessageDialog(employeeView.getContentPane(), "Client information: Name: " +
                    client.getName() + " , Identity card number: " + client.getIdentity_card_number() +
                    ", PNC: " + client.getPNC() + ", Address: " + client.getAddress() +
                    ", Email: " + client.getEmail());
        }
    }
}
