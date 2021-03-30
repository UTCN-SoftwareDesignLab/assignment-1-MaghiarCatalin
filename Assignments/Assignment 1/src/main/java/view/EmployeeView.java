package view;

import model.Client;
import model.ClientAccount;
import view.DTO.ClientAccountDTO;
import view.DTO.ClientDTO;

import javax.swing.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame{

    private JButton CreateAccountBTN;
    private JButton UpdateAccountBTN;
    private JButton DeleteAccountBTN;
    private JButton ViewAccountBTN;
    private JButton TransferMoneyBTN;

    private JButton CreateClientBTN;
    private JButton UpdateClientBTN;
    private JButton DeleteClientBTN;
    private JButton ViewClientBTN;

    private JTextField cardNumberTXT;
    private JTextField typeTXT;
    private JTextField amountTXT;
    private JTextField creationDateTXT;
    private JTextField accountId1TXT;
    private JTextField accountId2TXT;
    private JTextField amountTransferTXT;

    private JTextField clientIdTXT;
    private JTextField clientNameTXT;
    private JTextField clientIdentityCardNumberTXT;
    private JTextField clientPNC_TXT;
    private JTextField clientAddressTXT;
    private JTextField clientEmailTXT;

    public EmployeeView() {
        setSize(800, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(cardNumberTXT);
        add(typeTXT);
        add(amountTXT);
        add(creationDateTXT);
        add(accountId1TXT);
        add(accountId2TXT);
        add(amountTransferTXT);
        add(clientIdTXT);
        add(clientNameTXT);
        add(clientIdentityCardNumberTXT);
        add(clientPNC_TXT);
        add(clientAddressTXT);
        add(clientEmailTXT);
        add(CreateAccountBTN);
        add(UpdateAccountBTN);
        add(DeleteAccountBTN);
        add(ViewAccountBTN);
        add(CreateClientBTN);
        add(UpdateClientBTN);
        add(DeleteClientBTN);
        add(ViewClientBTN);
        add(TransferMoneyBTN);
    }

    public void initializeFields() {
        cardNumberTXT = new JTextField("Card number");
        typeTXT = new JTextField("account type");
        amountTXT = new JTextField("money amount");
        creationDateTXT = new JTextField("creation date");
        accountId1TXT = new JTextField("account id1");
        accountId2TXT = new JTextField("account id2");
        amountTransferTXT = new JTextField("amount for transfer");
        clientIdTXT = new JTextField("client id");
        clientNameTXT = new JTextField("client name");
        clientIdentityCardNumberTXT = new JTextField("client identity card number");
        clientPNC_TXT = new JTextField("client personal numerical code");
        clientAddressTXT = new JTextField("client address");
        clientEmailTXT = new JTextField("client email");
        CreateAccountBTN = new JButton("Create Account");
        UpdateAccountBTN = new JButton("Update Account");
        DeleteAccountBTN = new JButton("Delete Account");
        ViewAccountBTN = new JButton("View Account");
        CreateClientBTN = new JButton("Create Client");
        UpdateClientBTN = new JButton("Update Client");
        DeleteClientBTN = new JButton("Delete Client");
        ViewClientBTN = new JButton("View Client");
        TransferMoneyBTN = new JButton("Transfer Money");
    }

    public String getCardNumber(){
        return cardNumberTXT.getText();
    }

    public String getTypeTXT(){
        return typeTXT.getText();
    }

    public String getAmountTXT(){
        return amountTXT.getText();
    }

    public String getCreationDateTXT(){
        return creationDateTXT.getText();
    }

    public String getAccountId1TXT() {
        return accountId1TXT.getText();
    }

    public String getAccountId2TXT() {
        return accountId2TXT.getText();
    }

    public String getAmountTransferTXT() {
        return amountTransferTXT.getText();
    }

    public String getClientIdTXT(){
        return clientIdTXT.getText();
    }

    public String getClientNameTXT(){
        return clientNameTXT.getText();
    }

    public String getClientIdentityCardNumberTXT(){
        return clientIdentityCardNumberTXT.getText();
    }

    public String getClientPCNTXT(){
        return clientPNC_TXT.getText();
    }

    public String getClientAddressTXT(){
        return clientAddressTXT.getText();
    }

    public String getClientEmailTXT(){
        return clientEmailTXT.getText();
    }

    public void setCreateAccountButtonListener(ActionListener createAccountButtonListener) {
        CreateAccountBTN.addActionListener(createAccountButtonListener);
    }
    public void setUpdateAccountButtonListener(ActionListener updateAccountButtonListener) {
        UpdateAccountBTN.addActionListener(updateAccountButtonListener);
    }
    public void setDeleteAccountButtonListener(ActionListener deleteAccountButtonListener) {
        DeleteAccountBTN.addActionListener(deleteAccountButtonListener);
    }
    public void setViewAccountButtonListener(ActionListener viewAccountButtonListener) {
        ViewAccountBTN.addActionListener(viewAccountButtonListener);
    }

    public void setCreateClientButtonListener(ActionListener createClientButtonListener) {
        CreateClientBTN.addActionListener(createClientButtonListener);
    }
    public void setUpdateClientButtonListener(ActionListener updateClientButtonListener) {
        UpdateClientBTN.addActionListener(updateClientButtonListener);
    }
    public void setDeleteClientButtonListener(ActionListener deleteClientButtonListener) {
        DeleteClientBTN.addActionListener(deleteClientButtonListener);
    }
    public void setViewClientButtonListener(ActionListener viewClientButtonListener) {
        ViewClientBTN.addActionListener(viewClientButtonListener);
    }

    public void setTransferMoneyButtonListener(ActionListener transferMoneyButtonListener) {
        TransferMoneyBTN.addActionListener(transferMoneyButtonListener);
    }

    public ClientDTO getClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(getClientNameTXT());
        clientDTO.setIdentity_card_number(getClientIdentityCardNumberTXT());
        clientDTO.setPNC(getClientPCNTXT());
        clientDTO.setAddress(getClientAddressTXT());
        clientDTO.setEmail(getClientEmailTXT());
        return clientDTO;
    }

    public ClientAccountDTO getAccountDTO() {
        ClientAccountDTO clientAccountDTO = new ClientAccountDTO();
        clientAccountDTO.setClient_id(Integer.parseInt(getClientIdTXT()));
        clientAccountDTO.setCard_number(getCardNumber());
        clientAccountDTO.setAccount_type(getTypeTXT());
        clientAccountDTO.setAmount(Integer.parseInt(getAmountTXT()));
        clientAccountDTO.setDate_created(getCreationDateTXT());
        return clientAccountDTO;
    }
}
