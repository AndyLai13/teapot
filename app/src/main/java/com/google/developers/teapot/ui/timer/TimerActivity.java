package com.google.developers.teapot.ui.timer;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.developers.teapot.R;

/**
 * Activity that displays the steep timer.
 */
public class TimerActivity extends AppCompatActivity {

    private SteepProgress mSteepProgress;
    private TimerViewModel mTimerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button start = findViewById(R.id.start);
        Button reset = findViewById(R.id.reset);

        start.setOnClickListener(view -> {
            reset.setEnabled(true);
            mTimerViewModel.start();
        });

        reset.setOnClickListener(view -> {
            view.setEnabled(false);
            start.setEnabled(true);
            mTimerViewModel.reset();
        });
    }
}
