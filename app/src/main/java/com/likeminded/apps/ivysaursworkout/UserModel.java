package com.likeminded.apps.ivysaursworkout;

/**
 * Created by JasonKien on 12/31/16.
 */

public class UserModel {

    private String ID;
    private int maxBench, maxSquat, maxDeadlift, maxOverheadPress, maxBarbellRow;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public int getMaxBench() {
        return maxBench;
    }
    public void setMaxBench(int bench) {
        this.maxBench = bench;
    }

    public int getMaxSquat() {
        return maxSquat;
    }
    public void setMaxSquat(int squat) {
        this.maxSquat = squat;
    }

    public int getMaxDeadlift() {
        return maxDeadlift;
    }
    public void setMaxDeadlift(int deadlift) {
        this.maxDeadlift = deadlift;
    }

    public int getMaxOverheadPress() {
        return maxBench;
    }
    public void setMaxOverheadPress(int overheadPress) {
        this.maxOverheadPress = overheadPress;
    }

    public int getMaxBarbellRow() {
        return maxBarbellRow;
    }
    public void setMaxBarbellRow(int barbellRow) {
        this.maxBarbellRow = barbellRow;
    }
}
