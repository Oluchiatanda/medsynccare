package org.example;

import com.j256.ormlite.field.DatabaseField;

import java.time.LocalDateTime;

public class Reminder {
    @DatabaseField(generatedId = true)
    private int reminderId;

    @DatabaseField
    private String title;

    @DatabaseField
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    @DatabaseField
    private boolean isSynced;
    public void setDateTime(String dateTime) {

        this.dateTime = dateTime;
    }

    public int getReminderId() {
        return reminderId;
    }
    public void setReminderId(int reminderId) {

        this.reminderId = reminderId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



}
