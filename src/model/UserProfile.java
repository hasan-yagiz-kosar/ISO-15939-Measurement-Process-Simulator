package model;

public class UserProfile {
    private String username;
    private String school;
    private String sessionName;

    public UserProfile(String username, String school, String sessionName) {
        this.username = username;
        this.school = school;
        this.sessionName = sessionName;
    }

    public boolean isValid() {
        return username != null && !username.trim().isEmpty() &&
                school != null && !school.trim().isEmpty() &&
                sessionName != null && !sessionName.trim().isEmpty();
    }

    public String getUsername() { return username; }
    public String getSchool() { return school; }
    public String getSessionName() { return sessionName; }
}