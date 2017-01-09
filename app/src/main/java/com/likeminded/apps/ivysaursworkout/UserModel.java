package com.likeminded.apps.ivysaursworkout;

/**
 * Created by JasonKien on 12/31/16.
 */

public class UserModel {

    private String Username, ID, Week, Day;
    private long MaxBench, MaxSquat, MaxDeadlift, MaxOverheadPress, MaxBarbellRow;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        this.Username = username;
    }

    public long getMaxBench() {
        return MaxBench;
    }
    public void setMaxBench(long bench) {
        this.MaxBench = bench;
    }

    public long getMaxSquat() {
        return MaxSquat;
    }
    public void setMaxSquat(long squat) {
        this.MaxSquat = squat;
    }

    public long getMaxDeadlift() {
        return MaxDeadlift;
    }
    public void setMaxDeadlift(long deadlift) {
        this.MaxDeadlift = deadlift;
    }

    public long getMaxOverheadPress() {
        return MaxOverheadPress;
    }
    public void setMaxOverheadPress(long overheadPress) {
        this.MaxOverheadPress = overheadPress;
    }

    public long getMaxBarbellRow() {
        return MaxBarbellRow;
    }
    public void setMaxBarbellRow(long barbellRow) {
        this.MaxBarbellRow = barbellRow;
    }

    public String getWeek() {
        return Week;
    }
    public void setWeek(String week) {
        this.Week = week;
    }
    private void incrementWeek() {
        if(Week == "A")
        {
            this.Week = "B";
        }
        else
        {
            this.Week = "A";
        }
    }

    public String getDay() {
        return Day;
    }
    public void setDay(String day) {
        this.Day = day;
    }
    public void incrementDay() {
        if(Day == "DAY1")
        {
            this.Day = "DAY2";
        }
        else if(Day == "DAY2")
        {
            this.Day = "DAY3";
        }
        else
        {
            this.Day = "DAY1";
            incrementWeek();
        }
    }

}
