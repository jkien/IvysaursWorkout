package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private TextView TextView_bench_A;

    boolean RoundToNearestFive = true;

    //private String bench;

    private long CurrentBench, CurrentSquat, CurrentDeadlift, CurrentOverheadPress, CurrentBarbellRow;

    private final long BenchAndRowIncrement = 10/3, SquatAndDeadliftIncrement = 15/3, OverheadPressIncrement = 5/3;

    public enum Week {
        A, B
    }

    public enum Day {
        DAY1, DAY2, DAY3
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intentMainActivity = getIntent();

        //Get the item from the intent sent from the MainActivity
        //bench = intentMainActivity.getStringExtra(MainActivity.MAX_BENCH);

        //TODO: Does this use the same DB created in MainActivity?
        SQLiteHelper sqLiteHelper = new SQLiteHelper(Main2Activity.this);
        ArrayList<UserModel> users = sqLiteHelper.getAllUsers();

        UserModel user = users.get(0);

        setWorkOut(user);
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

        String bench_text = setBench(user.getMaxBench(), week, day);

        TextView_bench_A = (TextView) findViewById(R.id.textView_bench_A);
        TextView_bench_A.setText(bench_text);
    }

    public String setBench(long prevWeight, Week week, Day day)
    {
        String reps = "";
        String weight = "";

        long nextWeight = prevWeight + BenchAndRowIncrement;

        //round to nearest 5
        if(RoundToNearestFive)
        {
            nextWeight = 5 * (Math.round(nextWeight / 5));
        }

        switch (week) {
            case A:
                switch (day) {
                    case DAY1:
                        reps = "4x4";
                        break;
                    case DAY2:
                        reps = "4x8";
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
                        break;
                    case DAY2:
                        reps = "4x4";
                        break;
                    case DAY3:
                        reps = "4x8";
                        break;
                }
                break;
        }

        weight = Long.toString(nextWeight);

        return weight + " " + reps;
    }

}
