package view;

import model.Client;
import model.ClientAccount;
import view.DTO.ClientAccountDTO;
import view.DTO.ClientDTO;

import javax.swing.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame{

    ClientDTO clientDTO;
    ClientAccountDTO clientAccountDTO;

    private JButton CreateAccountBTN;
    private JButton UpdateAccountBTN;
    private JButton DeleteAccountBTN;
    private JButton ViewAccountBTN;

    private JButton CreateClientBTN;
    private JButton UpdateClientBTN;
    private JButton DeleteClientBTN;
    private JButton ViewClientBTN;

    private JTextField cardNumberTXT;
    private JTextField typeTXT;
    private JTextField amountTXT;
    private JTextField creationDateTXT;
    private JTextField accountIdTXT;

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
        add(accountIdTXT);
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
    }

    public void initializeFields() {
        this.clientDTO = new ClientDTO();
        this.clientAccountDTO = new ClientAccountDTO();
        cardNumberTXT = new JTextField("Card number");
        typeTXT = new JTextField("account type");
        amountTXT = new JTextField("money amount");
        creationDateTXT = new JTextField("creation date");
        accountIdTXT = new JTextField("account id");
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

    public String getAccountId() {
        return accountIdTXT.getText();
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

    public ClientDTO getClientDTO() {
        initializeClientDTO();
        return clientDTO;
    }

    public ClientAccountDTO getAccountDTO() {
        initializeAccountDTO();
        return clientAccountDTO;
    }

    public void initializeClientDTO() {

        this.clientDTO.setName(getClientNameTXT());
        this.clientDTO.setIdentity_card_number(getClientIdentityCardNumberTXT());
        this.clientDTO.setPNC(getClientPCNTXT());
        this.clientDTO.setAddress(getClientAddressTXT());
        this.clientDTO.setEmail(getClientEmailTXT());
    }

    public void initializeAccountDTO() {

        this.clientAccountDTO.setCard_number(getCardNumber());
        this.clientAccountDTO.setAccount_type(getTypeTXT());
        this.clientAccountDTO.setAmount(Integer.parseInt(getAmountTXT()));
        this.clientAccountDTO.setDate_created(getCreationDateTXT());
    }
}
