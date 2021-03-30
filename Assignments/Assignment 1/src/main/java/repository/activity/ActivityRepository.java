package repository.activity;

import model.Activity;
import model.Client;

import java.util.List;

public interface ActivityRepository {
    boolean save(Activity activity);

    List<Activity> retrieveActivitiesByUser(String username, String date);
}
