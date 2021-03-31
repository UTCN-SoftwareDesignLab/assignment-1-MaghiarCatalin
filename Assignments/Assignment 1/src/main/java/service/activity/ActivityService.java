package service.activity;

import model.Activity;
import model.validation.Notification;

import java.util.List;

public interface ActivityService {
    boolean save(Activity activity);

    List<Activity> retrieveActivitiesByUser(String username, String date);
}
