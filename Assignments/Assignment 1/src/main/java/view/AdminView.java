package view;

import view.DTO.ActivityDTO;
import view.DTO.ClientDTO;
import view.DTO.UserDTO;

import javax.swing.*;

import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame{
    private JButton CreateUserBTN;
    private JButton UpdateUserBTN;
    private JButton DeleteUserBTN;
    private JButton ViewUserBTN;
    private JButton GetUserActivityBTN;

    private JTextField userIdTXT;
    private JTextField usernameTXT;
    private JTextField passwordTXT;
    private JTextField roleTXT;
    private JTextField dateTXT;

    public AdminView() {
        setSize(800, 800);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(userIdTXT);
        add(usernameTXT);
        add(passwordTXT);
        add(roleTXT);
        add(dateTXT);
        add(CreateUserBTN);
        add(UpdateUserBTN);
        add(DeleteUserBTN);
        add(ViewUserBTN);
        add(GetUserActivityBTN);
    }

    public void initializeFields() {
        userIdTXT = new JTextField("user id");
        usernameTXT = new JTextField("username");
        passwordTXT = new JTextField("password");
        roleTXT = new JTextField("role");
        dateTXT = new JTextField("date");
        CreateUserBTN = new JButton("Create User");
        UpdateUserBTN = new JButton("Update User");
        DeleteUserBTN = new JButton("Delete User");
        ViewUserBTN = new JButton("View User");
        GetUserActivityBTN = new JButton("Get activities");
    }

    public String getUserIdTXT(){
        return userIdTXT.getText();
    }

    public String getUsernameTXT(){
        return usernameTXT.getText();
    }

    public String getPasswordTXT(){
        return passwordTXT.getText();
    }

    public String getRoleTXT(){
        return roleTXT.getText();
    }

    public String getDateTXT(){
        return dateTXT.getText();
    }

    public void setCreateUserButtonListener(ActionListener createUserButtonListener) {
        CreateUserBTN.addActionListener(createUserButtonListener);
    }
    public void setUpdateUserButtonListener(ActionListener updateUserButtonListener) {
        UpdateUserBTN.addActionListener(updateUserButtonListener);
    }
    public void setDeleteUserButtonListener(ActionListener deleteUserButtonListener) {
        DeleteUserBTN.addActionListener(deleteUserButtonListener);
    }
    public void setViewUserButtonListener(ActionListener viewUserButtonListener) {
        ViewUserBTN.addActionListener(viewUserButtonListener);
    }

    public void setGetUserActivityButtonListener(ActionListener getUserActivityButtonListener) {
        GetUserActivityBTN.addActionListener(getUserActivityButtonListener);
    }

    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(getUsernameTXT());
        userDTO.setPassword(getPasswordTXT());
        userDTO.setRole(getRoleTXT());
        return userDTO;
    }
}
