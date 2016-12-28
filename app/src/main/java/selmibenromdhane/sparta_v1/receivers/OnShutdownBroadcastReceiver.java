package selmibenromdhane.sparta_v1.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import selmibenromdhane.sparta_v1.utils.StepDetectionServiceHelper;



public class OnShutdownBroadcastReceiver extends BroadcastReceiver {
    private static final String LOG_CLASS = OnShutdownBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_CLASS, "onReceive");
        StepDetectionServiceHelper.startPersistenceService(context);
    }
}
