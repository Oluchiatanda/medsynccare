package org.example;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "patient")
public class Patient {
    @DatabaseField(generatedId = true)
    private int patientId = 1;

    @DatabaseField(canBeNull = false)
    private String fullname;

    @DatabaseField(canBeNull = false)
    private String emailAddress;
    @DatabaseField(canBeNull = false)
    private String password;


    private String accessToken; // To store the access token


    @DatabaseField(canBeNull = false)
    private String notificationToken;

    @DatabaseField(canBeNull = false)
    private String confirmPassword;


    public Patient() {
    }

    public Patient(String fullname, String password) {
        this.fullname = fullname;
        this.password = password;
    }

    // standard getters, setters
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullname;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }
}
