package com.likeminded.apps.ivysaursworkout;

/**
 * Created by JasonKien on 12/31/16.
 */

public class UserModel {

    private String ID, Week, Day;
    private long MaxBench, MaxSquat, MaxDeadlift, MaxOverheadPress, MaxBarbellRow;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
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
        return MaxBench;
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

    public String getDay() {
        return Day;
    }
    public void setDay(String day) {
        this.Day = day;
    }
}
