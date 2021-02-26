package com.edtest.backgroundjobintentexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    public static final String TAG = "BACKGROUND_JOB_INTENT_EXAMPLE";
    public static final String TAG2 = "BOOT_RECEIVER: ";

    Context c;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            c = context;
            Log.w(TAG, TAG2 + "onReceive");

            //start the service
            TestJobIntentService.enqueueWork(context);
        }
    }
}
