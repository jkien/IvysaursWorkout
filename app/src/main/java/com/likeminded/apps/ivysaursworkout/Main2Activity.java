package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView TextView_week, TextView_day, TextView_exercise_1, TextView_exercise_2, TextView_exercise_3, TextView_exercise_4;

    boolean RoundToNearestFive = true;

    //private String bench;

    private long NewBench, NewSquat, NewDeadlift, NewOverheadPress, NewBarbellRow;

    private final long BenchAndRowIncrement = 10/3, SquatAndDeadliftIncrement = 15/3, OverheadPressIncrement = 5/3;

    public enum Week {
        A, B
    }

    public enum Day {
        DAY1, DAY2, DAY3
    }

    public Button doneBtn;

    public void init()
    {
        doneBtn = (Button)findViewById(R.id.done_btn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent finishWorout = new Intent(Main2Activity.this, MainActivity.class);

                Intent intentMainActivity = getIntent();

                //Get the item from the intent sent from the MainActivity
                String username = intentMainActivity.getStringExtra(MainActivity.USERNAME);

                finishWorout.putExtra(MainActivity.USERNAME, username);

                startActivity(finishWorout);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intentMainActivity = getIntent();

        //Get the item from the intent sent from the MainActivity
        String username = intentMainActivity.getStringExtra(MainActivity.USERNAME);

        //TODO: Does this use the same DB created in MainActivity?
        SQLiteHelper sqLiteHelper = new SQLiteHelper(Main2Activity.this);
        UserModel user = sqLiteHelper.getUserByUsername(username);

        NewBench = user.getMaxBench();
        NewSquat = user.getMaxSquat();
        NewDeadlift = user.getMaxDeadlift();
        NewOverheadPress = user.getMaxOverheadPress();
        NewBarbellRow = user.getMaxBarbellRow();

        //increment workout day to set the next workout
        user.incrementDay();

        setWorkOut(user);

        user.setMaxBench(NewBench);
        user.setMaxSquat(NewSquat);
        user.setMaxDeadlift(NewDeadlift);
        user.setMaxOverheadPress(NewOverheadPress);
        user.setMaxBarbellRow(NewBarbellRow);

        sqLiteHelper.updateUser(user);

        init();
    }

    public void setWorkOut(UserModel user)
    {
        //int bench_max = 0;

        Week week = Week.valueOf(user.getWeek());
        Day day = Day.valueOf(user.getDay());

        //This is for grabbing the string sent from the Intent from MainActivity
//        try {
//            bench_max = Integer.parseInt(bench);
//        } catch(NumberFormatException nfe) {
//            System.out.println("Could not parse " + nfe);
//        }

        TextView_week = (TextView) findViewById(R.id.textView_week);
        TextView_week.setText("Week " + week.toString());

        TextView_day = (TextView) findViewById(R.id.textView_day);
        TextView_day.setText("Day  " + day.toString());

        setBench(user.getMaxBench(), week, day);
        setOverheadPress(user.getMaxOverheadPress(), week, day);

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        setSquat(user.getMaxSquat(), week, day);
                        setChinUps(week, day);
                        break;
                    case DAY2:
                        setDeadlift(user.getMaxDeadlift(), week, day);
                        setBarbellRow(user.getMaxBarbellRow(), week, day);
                        break;
                    case DAY3:
                        setSquat(user.getMaxSquat(), week, day);
                        setChinUps(week, day);
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        setDeadlift(user.getMaxDeadlift(), week, day);
                        setBarbellRow(user.getMaxBarbellRow(), week, day);
                        break;
                    case DAY2:
                        setSquat(user.getMaxSquat(), week, day);
                        setChinUps(week, day);
                        break;
                    case DAY3:
                        setDeadlift(user.getMaxDeadlift(), week, day);
                        setBarbellRow(user.getMaxBarbellRow(), week, day);
                        break;
                }
                break;
        }



    }



    public long roundToNearestFive(long weight)
    {
        long roundedWeight = -1;
        //round to nearest 5
        if(RoundToNearestFive)
        {
            roundedWeight = 5 * (Math.round(weight / 5));
        }

        return roundedWeight;
    }

    public void setBench(long prevWeight, Week week, Day day)
    {
        String reps = "";
        String weight = "";

        long nextWeightMax = prevWeight + BenchAndRowIncrement;

        NewBench = nextWeightMax;

        long nextWeight = nextWeightMax;

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "4x4";
                        break;
                    case DAY2:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY3:
                        reps = "3x4 + 1xAMRAP";
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY2:
                        reps = "4x4";
                        break;
                    case DAY3:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                }
                break;
        }

        //round to nearest 5
        nextWeight = roundToNearestFive(nextWeight);
        weight = Long.toString(nextWeight);

        TextView_exercise_1 = (TextView) findViewById(R.id.textView_exercise_1);
        TextView_exercise_1.setText("Bench " + weight + " " + reps);
    }

    public void setOverheadPress(long prevWeight, Week week, Day day)
    {
        String reps = "";
        String weight = "";

        long nextWeightMax = prevWeight + OverheadPressIncrement;

        NewOverheadPress = nextWeightMax;

        long nextWeight = nextWeightMax;

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY2:
                        reps = "4x4";
                        break;
                    case DAY3:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        reps = "4x4";
                        break;
                    case DAY2:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY3:
                        reps = "3x4 + 1xAMRAP";
                        break;
                }
                break;
        }

        //round to nearest 5
        nextWeight = roundToNearestFive(nextWeight);
        weight = Long.toString(nextWeight);

        TextView_exercise_3 = (TextView) findViewById(R.id.textView_exercise_3);
        TextView_exercise_3.setText("Overhead Press " + weight + " " + reps);
    }

    public void setSquat(long prevWeight, Week week, Day day)
    {
        String reps = "";
        String weight = "";

        long nextWeightMax = prevWeight + SquatAndDeadliftIncrement;

        NewSquat = nextWeightMax;

        long nextWeight = nextWeightMax;

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY2:
                        reps = "ERROR";
                        break;
                    case DAY3:
                        reps = "3x4 + 1xAMRAP";
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        reps = "ERROR";
                        break;
                    case DAY2:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY3:
                        reps = "ERROR";
                        break;
                }
                break;
        }

        //round to nearest 5
        nextWeight = roundToNearestFive(nextWeight);
        weight = Long.toString(nextWeight);

        TextView_exercise_2 = (TextView) findViewById(R.id.textView_exercise_2);
        TextView_exercise_2.setText("Squat " + weight + " " + reps);
    }

    public void setDeadlift(long prevWeight, Week week, Day day)
    {
        String reps = "";
        String weight = "";

        long nextWeightMax = prevWeight + SquatAndDeadliftIncrement;

        NewDeadlift = nextWeightMax;

        long nextWeight = nextWeightMax;

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "ERROR";
                        break;
                    case DAY2:
                        reps = "4x4";
                        break;
                    case DAY3:
                        reps = "ERROR";
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY2:
                        reps = "ERROR";
                        break;
                    case DAY3:
                        reps = "3x4 + 1xAMRAP";
                        break;
                }
                break;
        }

        //round to nearest 5
        nextWeight = roundToNearestFive(nextWeight);
        weight = Long.toString(nextWeight);

        TextView_exercise_2 = (TextView) findViewById(R.id.textView_exercise_2);
        TextView_exercise_2.setText("Deadlift " + weight + " " + reps);
    }

    public void setBarbellRow(long prevWeight, Week week, Day day)
    {
        String reps = "";
        String weight = "";

        long nextWeightMax = prevWeight + BenchAndRowIncrement;

        NewBarbellRow = nextWeightMax;

        long nextWeight = nextWeightMax;

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "ERROR";
                        break;
                    case DAY2:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                    case DAY3:
                        reps = "ERROR";
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        reps = "4x4";
                        break;
                    case DAY2:
                        reps = "ERROR";
                        break;
                    case DAY3:
                        reps = "4x8";
                        nextWeight = nextWeightMax * 9/10;
                        break;
                }
                break;
        }

        //round to nearest 5
        nextWeight = roundToNearestFive(nextWeight);
        weight = Long.toString(nextWeight);

        TextView_exercise_4 = (TextView) findViewById(R.id.textView_exercise_4);
        TextView_exercise_4.setText("Barbell Row " + weight + " " + reps);
    }

    public void setChinUps(Week week, Day day)
    {
        String reps = "";

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "4x8";
                        break;
                    case DAY2:
                        reps = "ERROR";
                        break;
                    case DAY3:
                        reps = "4x4";
                        break;
                }
                break;
            case B:
                switch (day) {
                    case DAY1:
                        reps = "ERROR";
                        break;
                    case DAY2:
                        reps = "4x8";
                        break;
                    case DAY3:
                        reps = "ERROR";
                        break;
                }
                break;
        }

        TextView_exercise_4 = (TextView) findViewById(R.id.textView_exercise_4);
        TextView_exercise_4.setText("Chin Ups " + reps);
    }

}
