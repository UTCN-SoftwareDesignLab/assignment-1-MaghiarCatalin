package repository.user;

import controller.LoginController;
import model.Activity;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.activity.ActivityRepository;
import repository.activity.ActivityRepositoryMySQL;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static database.Constants.Tables.USER;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;


    public UserRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRole(userResultSet.getString("role"))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }

    @Override
    public User findById(int id) throws EntityNotFoundException {
        try{
            Statement statement = connection.createStatement();
            String fetchUserSql = "SELECT * FROM user WHERE id = " + id;
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if(userResultSet.next()){
                return new UserBuilder()
                        .setId(userResultSet.getInt("id"))
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRole(userResultSet.getString("role"))
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
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setString(3, "employee");
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            int userId = rs.getInt(1);
            user.setId(userId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean update(User newUser) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("UPDATE " + USER + " SET username = ?, password = ? WHERE id = " + newUser.getId());

            insertUserStatement.setString(1, newUser.getUsername());
            insertUserStatement.setString(2, newUser.getPassword());
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
                    .prepareStatement("DELETE FROM user WHERE  " + "id = " + id);
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
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
