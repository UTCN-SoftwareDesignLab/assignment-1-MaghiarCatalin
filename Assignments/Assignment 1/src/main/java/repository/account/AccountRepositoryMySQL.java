package repository.account;

import controller.LoginController;
import model.Activity;
import model.Client;
import model.ClientAccount;
import model.User;
import model.builder.ClientAccountBuilder;
import repository.EntityNotFoundException;
import repository.activity.ActivityRepositoryMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;


public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ClientAccount> findAll() {
        List<ClientAccount> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public ClientAccount findById(int id) throws EntityNotFoundException {
        try{
            Statement statement = connection.createStatement();
            String fetchAccountSql = "SELECT * FROM account WHERE id = " + id;
            ResultSet accountResultSet = statement.executeQuery(fetchAccountSql);
            if(accountResultSet.next()) {
                return new ClientAccountBuilder()
                        .setId(accountResultSet.getInt("id"))
                        .setClientId(accountResultSet.getInt("client_id"))
                        .setCardNumber(accountResultSet.getString("card_number"))
                        .setAccountType(accountResultSet.getString("account_type"))
                        .setAmount(accountResultSet.getInt("amount"))
                        .setDateCreated(accountResultSet.getString("date_created"))
                        .build();
            } else {
                throw new EntityNotFoundException(id, User.class.getSimpleName());
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new EntityNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public boolean save(ClientAccount clientAccount) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setInt(1,clientAccount.getClient_id());
            insertUserStatement.setString(2, clientAccount.getCard_number());
            insertUserStatement.setString(3, clientAccount.getAccount_type());
            insertUserStatement.setInt(4, clientAccount.getAmount());
            insertUserStatement.setString(5, clientAccount.getDate_created());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            int accountId = rs.getInt(1);
            clientAccount.setId(accountId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean update(ClientAccount newAccount) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("UPDATE " + ACCOUNT + " SET client_id = ?, card_number = ?, " +
                            "account_type = ?, amount = ?, date_created = ?  WHERE id = " + newAccount.getId());

            insertUserStatement.setLong(1, newAccount.getClient_id());
            insertUserStatement.setString(2, newAccount.getCard_number());
            insertUserStatement.setString(3, newAccount.getAccount_type());
            insertUserStatement.setInt(4, newAccount.getAmount());
            insertUserStatement.setString(5, newAccount.getDate_created());
            insertUserStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id){
        try{
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("DELETE FROM account WHERE  " + "id = " + id);
            insertUserStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transfer(ClientAccount clientAccount1, ClientAccount clientAccount2, Integer amount) throws EntityNotFoundException {

        clientAccount1.setAmount(clientAccount1.getAmount() - amount);
        clientAccount2.setAmount(clientAccount2.getAmount() + amount);

        update(clientAccount1);
        update(clientAccount2);
    }

    private ClientAccount getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new ClientAccountBuilder()
                .setId(rs.getInt("id"))
                .setClientId(rs.getInt("client_id"))
                .setCardNumber(rs.getString("card_number"))
                .setAccountType(rs.getString("account_type"))
                .setAmount(rs.getInt("amount"))
                .setDateCreated(rs.getString("date_created"))
                .build();
    }
}

