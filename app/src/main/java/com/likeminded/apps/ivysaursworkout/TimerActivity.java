package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    public Button CompleteSetBtn;

    public int NextSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        final TextView timerTextView = (TextView) findViewById(R.id.timerTextView);

        initText();
        initBtn();

        new CountDownTimer(90000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerTextView.setText("done!");
            }
        }.start();
    }

    public void initText()
    {
        Intent intentMain2Activity = getIntent();

        //Get the item from the intent sent from the MainActivity
        String workOutText = intentMain2Activity.getStringExtra(Main2Activity.WorkOutText);
        int set = intentMain2Activity.getIntExtra(Main2Activity.Set, 0);

        NextSet = set + 1;

        String setText = "Set " + Integer.toString(set);

        TextView workOutTextView = (TextView) findViewById(R.id.workOutTextView);
        TextView setTextView = (TextView) findViewById(R.id.setTextView);

        workOutTextView.setText(workOutText);
        setTextView.setText(setText);
    }

    public void initBtn()
    {
        CompleteSetBtn = (Button)findViewById(R.id.complete_set_btn);

        CompleteSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent completeSetIntent = new Intent(TimerActivity.this, Main2Activity.class);

                //Get the item from the intent sent from the MainActivity
                //String username = intentMainActivity.getStringExtra(MainActivity.USERNAME);

                completeSetIntent.putExtra(Main2Activity.Set, NextSet);

                //String setText = "Set " + Integer.toString(exercise1Set);

                //timerIntent.putExtra(Set1, setText);

                startActivity(completeSetIntent);
            }
        });
    }
}
