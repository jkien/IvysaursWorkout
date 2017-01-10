package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public Button doneBtn, exercise1Btn, exercise2Btn, exercise3Btn, exercise4Btn;

    public final static String WorkOutText = "WorkOutText";
    public final static String Set = "Set";
    public final static String Set1 = "Set1";
    public final static String Set2 = "Set2";
    public final static String Set3 = "Set3";
    public final static String Set4 = "Set4";

    public static int exercise1Set = 1, exercise2Set = 1, exercise3Set = 1, exercise4Set = 1, currentExercise = 1;

    public void init()
    {
        workOutBtnsInit();

        doneBtn = (Button)findViewById(R.id.done_btn);

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent finishWorout = new Intent(Main2Activity.this, MainActivity.class);

                Intent intentMainActivity = getIntent();

                //Get the item from the intent sent from the MainActivity
                //String username = intentMainActivity.getStringExtra(MainActivity.USERNAME);

                //finishWorout.putExtra(MainActivity.USERNAME, username);

                SQLiteHelper sqLiteHelper = new SQLiteHelper(Main2Activity.this);
                UserModel user = sqLiteHelper.getUserByUsername(MainActivity.USER1);

                //increment workout day to set the next workout
                user.incrementDay();

                user.setMaxBench(NewBench);
                user.setMaxSquat(NewSquat);
                user.setMaxDeadlift(NewDeadlift);
                user.setMaxOverheadPress(NewOverheadPress);
                user.setMaxBarbellRow(NewBarbellRow);

                //update after done button is clicked
                sqLiteHelper.updateUser(user);

                startActivity(finishWorout);
            }
        });
    }

    public void workOutBtnsInit()
    {
        exercise1Btn = (Button)findViewById(R.id.textView_exercise_1);

        exercise1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent timerIntent = new Intent(Main2Activity.this, TimerActivity.class);

                timerIntent.putExtra(WorkOutText, exercise1Btn.getText());

                timerIntent.putExtra(Set, exercise1Set);

                startActivity(timerIntent);
            }
        });

        exercise2Btn = (Button)findViewById(R.id.textView_exercise_2);

        exercise2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent timerIntent = new Intent(Main2Activity.this, TimerActivity.class);

                timerIntent.putExtra(WorkOutText, exercise2Btn.getText());

                timerIntent.putExtra(Set, exercise2Set);

                startActivity(timerIntent);
            }
        });

        exercise3Btn = (Button)findViewById(R.id.textView_exercise_3);

        exercise3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent timerIntent = new Intent(Main2Activity.this, TimerActivity.class);

                timerIntent.putExtra(WorkOutText, exercise3Btn.getText());

                timerIntent.putExtra(Set, exercise3Set);

                startActivity(timerIntent);
            }
        });

        exercise4Btn = (Button)findViewById(R.id.textView_exercise_4);

        exercise4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent timerIntent = new Intent(Main2Activity.this, TimerActivity.class);

                timerIntent.putExtra(WorkOutText, exercise4Btn.getText());

                timerIntent.putExtra(Set, exercise4Set);

                startActivity(timerIntent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intentMainActivity = getIntent();

        //Get the item from the intent sent from the MainActivity
        //String username = intentMainActivity.getStringExtra(MainActivity.USERNAME);

        //TODO: Does this use the same DB created in MainActivity?
        SQLiteHelper sqLiteHelper = new SQLiteHelper(Main2Activity.this);
        UserModel user = sqLiteHelper.getUserByUsername(MainActivity.USER1);

        NewBench = user.getMaxBench();
        NewSquat = user.getMaxSquat();
        NewDeadlift = user.getMaxDeadlift();
        NewOverheadPress = user.getMaxOverheadPress();
        NewBarbellRow = user.getMaxBarbellRow();

        //increment workout day to set the next workout
        user.incrementDay();

        Intent timerActivity = getIntent();

        //Get the item from the intent sent from the MainActivity
        int nextSet = timerActivity.getIntExtra(Set, 1);

        disableAllExcerciseBtns();

        if(currentExercise == 1) {
            //get the set number
            if (nextSet <= 4) {
                exercise1Set = nextSet;
                exercise1Btn = (Button) findViewById(R.id.textView_exercise_1);
                exercise1Btn.setEnabled(true);
            } else {
                exercise1Set = 1;

                //disable set button
                exercise1Btn = (Button) findViewById(R.id.textView_exercise_1);
                exercise1Btn.setEnabled(false);

                exercise2Btn = (Button) findViewById(R.id.textView_exercise_2);
                exercise2Btn.setEnabled(true);

                currentExercise = 2;
            }
        }
        else if(currentExercise == 2) {
            //get the set number
            if (nextSet <= 4) {
                exercise2Set = nextSet;
                exercise2Btn = (Button) findViewById(R.id.textView_exercise_2);
                exercise2Btn.setEnabled(true);
            } else {
                exercise2Set = 1;

                //disable set button
                exercise2Btn = (Button) findViewById(R.id.textView_exercise_2);
                exercise2Btn.setEnabled(false);

                exercise3Btn = (Button) findViewById(R.id.textView_exercise_3);
                exercise3Btn.setEnabled(true);

                currentExercise = 3;
            }
        }
        else if(currentExercise == 3) {
            //get the set number
            if (nextSet <= 4) {
                exercise3Set = nextSet;
                exercise3Btn = (Button) findViewById(R.id.textView_exercise_3);
                exercise3Btn.setEnabled(true);
            } else {
                exercise3Set = 1;

                //disable set button
                exercise3Btn = (Button) findViewById(R.id.textView_exercise_3);
                exercise3Btn.setEnabled(false);

                exercise4Btn = (Button) findViewById(R.id.textView_exercise_4);
                exercise4Btn.setEnabled(true);

                currentExercise = 4;
            }
        }
        else if(currentExercise == 4) {
            //get the set number
            if (nextSet <= 4) {
                exercise4Set = nextSet;
                exercise4Btn = (Button) findViewById(R.id.textView_exercise_4);
                exercise4Btn.setEnabled(true);
            } else {
                exercise4Set = 1;

                //disable set button
                exercise4Btn = (Button) findViewById(R.id.textView_exercise_4);
                exercise4Btn.setEnabled(false);

                currentExercise = 1;
            }
        }

            setWorkOut(user);

        init();
    }

    public void disableAllExcerciseBtns()
    {
        exercise1Btn = (Button) findViewById(R.id.textView_exercise_1);
        exercise1Btn.setEnabled(false);

        exercise2Btn = (Button) findViewById(R.id.textView_exercise_2);
        exercise2Btn.setEnabled(false);

        exercise3Btn = (Button) findViewById(R.id.textView_exercise_3);
        exercise3Btn.setEnabled(false);

        exercise4Btn = (Button) findViewById(R.id.textView_exercise_4);
        exercise4Btn.setEnabled(false);
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

        TextView_exercise_1 = (Button) findViewById(R.id.textView_exercise_1);
        TextView_exercise_1.setText("Bench " + weight + " " + reps + " Set " + Integer.toString(exercise1Set) + " Complete");
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

        TextView_exercise_3 = (Button) findViewById(R.id.textView_exercise_3);
        TextView_exercise_3.setText("Overhead Press " + weight + " " + reps + " Set " + Integer.toString(exercise3Set) + " Complete");
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

        TextView_exercise_2 = (Button) findViewById(R.id.textView_exercise_2);
        TextView_exercise_2.setText("Squat " + weight + " " + reps + " Set " + Integer.toString(exercise2Set) + " Complete");
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

        TextView_exercise_2 = (Button) findViewById(R.id.textView_exercise_2);
        TextView_exercise_2.setText("Deadlift " + weight + " " + reps + " Set " + Integer.toString(exercise2Set) + " Complete");
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

        TextView_exercise_4 = (Button) findViewById(R.id.textView_exercise_4);
        TextView_exercise_4.setText("Barbell Row " + weight + " " + reps + " Set " + Integer.toString(exercise4Set) + " Complete");
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

        TextView_exercise_4 = (Button) findViewById(R.id.textView_exercise_4);
        TextView_exercise_4.setText("Chin Ups " + reps + " Set " + Integer.toString(exercise4Set) + " Complete");
    }

}
