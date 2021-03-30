package view.DTO;

public class ActivityDTO {
    String employeeId;
    String activityName;
    String activityTimeStamp;

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
