package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView textView_bench_A;
    private String bench;

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
        bench = intentMainActivity.getStringExtra(MainActivity.MAX_BENCH);

        setWorkOut(Week.A, Day.DAY1);
    }

    public void setWorkOut(Week week, Day day)
    {
        int bench_max =0;

        try {
            bench_max = Integer.parseInt(bench);
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        String bench_text = setBench(bench_max, week, day);

        textView_bench_A = (TextView) findViewById(R.id.textView_bench_A);
        textView_bench_A.setText(bench_text);
    }

    public String setBench(int prevMax, Week week, Day day)
    {
        String reps = "";
        String weight = "";

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

        return weight + " " + reps;
    }

}
