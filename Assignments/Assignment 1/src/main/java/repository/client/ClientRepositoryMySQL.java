package repository.client;

import controller.LoginController;
import model.Activity;
import model.Client;
import model.User;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import repository.activity.ActivityRepositoryMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;


public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;


    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client findById(int id) throws EntityNotFoundException {
        try{
            Statement statement = connection.createStatement();
            String fetchClientSql = "SELECT * FROM client WHERE id = " + id;
            ResultSet clientResultSet = statement.executeQuery(fetchClientSql);
            if(clientResultSet.next()){
                return new ClientBuilder()
                        .setId(clientResultSet.getInt("id"))
                        .setName(clientResultSet.getString("name"))
                        .setIdentity_card_number(clientResultSet.getString("identity_card_number"))
                        .setPNC(clientResultSet.getString("personal_numerical_code"))
                        .setAddress(clientResultSet.getString("address"))
                        .setEmail(clientResultSet.getString("email"))
                        .build();
            }else {
                throw new EntityNotFoundException(id, User.class.getSimpleName());
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new EntityNotFoundException(id, User.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, client.getName());
            insertUserStatement.setString(2, client.getIdentity_card_number());
            insertUserStatement.setString(3, client.getPNC());
            insertUserStatement.setString(4, client.getAddress());
            insertUserStatement.setString(5, client.getEmail());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            int clientId = rs.getInt(1);
            client.setId(clientId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean update(Client newClient) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("UPDATE " + CLIENT + " SET name = ?, identity_card_number = ?, " +
                            "personal_numerical_code = ?, address = ?, email = ?  WHERE id = " + newClient.getId());

            insertUserStatement.setString(1, newClient.getName());
            insertUserStatement.setString(2, newClient.getIdentity_card_number());
            insertUserStatement.setString(3, newClient.getPNC());
            insertUserStatement.setString(4, newClient.getAddress());
            insertUserStatement.setString(5, newClient.getEmail());
            insertUserStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id){
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("DELETE FROM client WHERE  " + "id = " + id);
            insertUserStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setIdentity_card_number(rs.getString("identity_card_number"))
                .setPNC(rs.getString("personal_numerical_code"))
                .setAddress(rs.getString("address"))
                .setEmail(rs.getString("email"))
                .build();
    }
}
