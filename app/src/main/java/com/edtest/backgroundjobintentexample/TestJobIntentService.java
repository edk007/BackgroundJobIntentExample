package com.edtest.backgroundjobintentexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestJobIntentService extends JobIntentService {
    public static final String TAG = "BACKGROUND_JOB_INTENT_EXAMPLE";
    public static final String TAG2 = "TEST_JOB_INTENT_SERVICE: ";

    static final int JOB_ID = 1000;

    Runnable testServiceRunnable;
    ScheduledThreadPoolExecutor exec;
    ScheduledFuture testServiceTask;

    public static void enqueueWork(Context context) {
        Intent intent = new Intent(context, TestJobIntentService.class);
        enqueueWork(context, TestJobIntentService.class, JOB_ID, intent);
    }

    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, TestJobIntentService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        testServiceRunnable = new Runnable() {
            @Override
            public void run() {
                //should start something after the scan interval
                String ts = DateFormat.getDateTimeInstance().format(new Date());
                Log.w(TAG, TAG2 + "DO_WORK: " + ts);
                writeToFile("TIME_STAMP: " + ts + "\n");
            }
        };

        //launch the runnable every 1 second
        exec = new ScheduledThreadPoolExecutor(1);
        testServiceTask = exec.scheduleAtFixedRate(testServiceRunnable,0,1, TimeUnit.MINUTES);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void writeToFile(String data) {
        String TAG3 = "WRITE_FILE_OUTPUT: ";
        Log.w(TAG,TAG2 + TAG3);
        String fileName = "BACKGROUND_WORKER_LOG.txt";
        File externalFilesPath = TestJobIntentService.this.getExternalFilesDir(null);
        Log.w(TAG, TAG2 + TAG3 + "FILE_NAME: " + fileName);
        Log.w(TAG, TAG2 + TAG3 + "EXTERNAL_FILE_PATH: " + externalFilesPath.toString());

        File file = new File(externalFilesPath, fileName);
        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            try {
                stream.write(data.getBytes());
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
