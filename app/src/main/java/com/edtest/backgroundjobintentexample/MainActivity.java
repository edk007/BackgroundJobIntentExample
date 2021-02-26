package com.edtest.backgroundjobintentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "BACKGROUND_JOB_INTENT_EXAMPLE";
    public static final String TAG2 = "MAIN_ACTIVITY: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestJobIntentService.enqueueWork(this);
    }
}