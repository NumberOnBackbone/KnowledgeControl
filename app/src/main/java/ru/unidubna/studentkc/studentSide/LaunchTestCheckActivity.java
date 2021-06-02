package ru.unidubna.studentkc.studentSide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import ru.unidubna.studentkc.R;


public class LaunchTestCheckActivity extends AppCompatActivity {

    View mContentView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_resolve);
        View mContentView = findViewById(R.id.test_resolve_constraint);
        Button dropTestButton = findViewById(R.id.drop_test_button);
        Chronometer timer = findViewById(R.id.timer);

        FragmentManager fragmentManager = getSupportFragmentManager();
        AnswerTestFragment answerTestFragment = new AnswerTestFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.fragment_container_view_answer_test, answerTestFragment)
                .commit();


        long deadScore = 10 * 1000;
        long startBase = SystemClock.elapsedRealtime() + deadScore ;
        timer.setBase(startBase);
        timer.setCountDown(true);
        timer.start();
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMs = startBase - SystemClock.elapsedRealtime();
                if(elapsedMs <= 0){
                    finish();
                }

            }
        });

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        dropTestButton.setOnClickListener(v -> finish());
    }
}