package model;

import java.time.LocalDate;

public class Activity {
    int id;
    String employeeId;
    String activityName;
    String activityTimeStamp;

    public Activity(String employeeId, String activityName, String activityTimeStamp) {
        this.employeeId = employeeId;
        this.activityName = activityName;
        this.activityTimeStamp = activityTimeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTimeStamp() {
        return activityTimeStamp;
    }

    public void setActivityTimeStamp(String activityTimeStamp) {
        this.activityTimeStamp = activityTimeStamp;
    }
}
