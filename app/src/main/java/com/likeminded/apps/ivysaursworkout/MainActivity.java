package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button nextBtn;
    public final static String USERNAME = "com.likeminded.apps.ivysaursworkout.USERNAME";
    public final static String MAX_BENCH = "com.likeminded.apps.ivysaursworkout.MAX_BENCH";
    public final static String MAX_SQUAT = "com.likeminded.apps.ivysaursworkout.MAX_SQUAT";
    public final static String MAX_DEADLIFT = "com.likeminded.apps.ivysaursworkout.MAX_DEADLIFT";
    public final static String MAX_OVERHEADPRESS = "com.likeminded.apps.ivysaursworkout.MAX_OVERHEADPRESS";
    public final static String MAX_BARBELLROW = "com.likeminded.apps.ivysaursworkout.MAX_BARBELLROW";



    public void init()
    {
        nextBtn = (Button)findViewById(R.id.max_submit_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent submitMax = new Intent(MainActivity.this, Main2Activity.class);

                //TODO: Consider a NumberPicker instead of EditText?
                EditText editText_username = (EditText) findViewById(R.id.username);
                EditText editText_bench = (EditText) findViewById(R.id.bench_max);
                EditText editText_squat = (EditText) findViewById(R.id.squat_max);
                EditText editText_deadlift = (EditText) findViewById(R.id.deadlift_max);
                EditText editText_overheadPress = (EditText) findViewById(R.id.overhead_press_max);
                EditText editText_barbellRow = (EditText) findViewById(R.id.barbell_row_max);

                //send value with intent to next activity
                //String bench = editText_bench.getText().toString();
                //submitMax.putExtra(MAX_BENCH, bench);

                //Store max values in DB
                SQLiteHelper sqLiteHelper = new SQLiteHelper(MainActivity.this);
                String username = editText_username.getText().toString();

                submitMax.putExtra(USERNAME, username);

                long bench = Integer.parseInt(editText_bench.getText().toString());
                long squat = Integer.parseInt(editText_squat.getText().toString());
                long deadlift = Integer.parseInt(editText_deadlift.getText().toString());
                long overheadPress = Integer.parseInt(editText_overheadPress.getText().toString());
                long barbellRow = Integer.parseInt(editText_barbellRow.getText().toString());

                UserModel currentUser = sqLiteHelper.getUserByUsername(username);

                if(currentUser == null)
                {
                    //Set the week to B and the last day of 3 so that when we calculate the next workout it starts on A and Day1
                    sqLiteHelper.insertUser(username, bench, squat, deadlift, overheadPress, barbellRow, "B", "DAY3");
                }
                else
                {
                    currentUser.setUsername(username);
                    currentUser.setMaxBench(bench);
                    currentUser.setMaxSquat(squat);
                    currentUser.setMaxDeadlift(deadlift);
                    currentUser.setMaxOverheadPress(overheadPress);
                    currentUser.setMaxBarbellRow(barbellRow);
                    sqLiteHelper.updateUser(currentUser);
                }

                startActivity(submitMax);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setValuesFromDB();
        init();
    }

    public void saveMaxWeights()
    {

    }

    public void setValuesFromDB()
    {
        String username = "";

        try {
            Intent intentMain2Activity = getIntent();

            username = intentMain2Activity.getStringExtra(MainActivity.USERNAME);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        if(username != null && username != "")
        {
            SQLiteHelper sqLiteHelper = new SQLiteHelper(MainActivity.this);
            UserModel user = sqLiteHelper.getUserByUsername(username);

            EditText editText_username = (EditText) findViewById(R.id.username);
            EditText editText_bench = (EditText) findViewById(R.id.bench_max);
            EditText editText_squat = (EditText) findViewById(R.id.squat_max);
            EditText editText_deadlift = (EditText) findViewById(R.id.deadlift_max);
            EditText editText_overheadPress = (EditText) findViewById(R.id.overhead_press_max);
            EditText editText_barbellRow = (EditText) findViewById(R.id.barbell_row_max);

            editText_username.setText(user.getUsername());
            editText_bench.setText(Long.toString(user.getMaxBench()));
            editText_squat.setText(Long.toString(user.getMaxSquat()));
            editText_deadlift.setText(Long.toString(user.getMaxDeadlift()));
            editText_overheadPress.setText(Long.toString(user.getMaxOverheadPress()));
            editText_barbellRow.setText(Long.toString(user.getMaxBarbellRow()));
        }
    }
}
