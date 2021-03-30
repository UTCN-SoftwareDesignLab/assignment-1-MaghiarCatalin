package repository.activity;

import model.Activity;
import model.User;
import model.builder.UserBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepositoryMySQL implements ActivityRepository{

    private final Connection connection;

    public ActivityRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Activity activity) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO activity values (null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, activity.getEmployeeId());
            insertUserStatement.setString(2, activity.getActivityName());
            insertUserStatement.setString(3, activity.getActivityTimeStamp().toString());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            int activityId = rs.getInt(1);
            activity.setId(activityId);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Activity> retrieveActivitiesByUser(String username, String date) {
        try{
            List<Activity> activities =  new ArrayList<>();
            Statement statement = connection.createStatement();
            String fetchUserSql = "SELECT * FROM activity WHERE username = " + username;
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);

            while (userResultSet.next()) {
                if (userResultSet.getString("activity_time_stamp").equals(date)){
                    activities.add(new Activity(userResultSet.getString("username"), userResultSet.getString("activityName"),
                            userResultSet.getString("activity_time_stamp")));
                }
            }
            return activities;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
