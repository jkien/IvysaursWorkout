package com.likeminded.apps.ivysaursworkout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button nextBtn;
    public final static String MAX_BENCH = "com.likeminded.apps.ivysaursworkout.MAX_BENCH";
    public final static String MAX_SQUAT = "com.likeminded.apps.ivysaursworkout.MAX_SQUAT";
    public final static String MAX_OVERHEADPRESS = "com.likeminded.apps.ivysaursworkout.MAX_OVERHEADPRESS";
    public final static String MAX_DEADLIFT = "com.likeminded.apps.ivysaursworkout.MAX_DEADLIFT";
    public final static String MAX_BARBELLROWS = "com.likeminded.apps.ivysaursworkout.MAX_BARBELLROWS";

    public void init()
    {
        nextBtn = (Button)findViewById(R.id.max_submit_btn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent submitMax = new Intent(MainActivity.this, Main2Activity.class);
                EditText editText_bench = (EditText) findViewById(R.id.bench_max);
                String bench = editText_bench.getText().toString();
                submitMax.putExtra(MAX_BENCH, bench);
                startActivity(submitMax);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void saveMaxWeights()
    {

    }
}
