package selmibenromdhane.sparta_v1.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import selmibenromdhane.sparta_v1.utils.StepDetectionServiceHelper;



public class OnBootCompletedBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        StepDetectionServiceHelper.startAllIfEnabled(context);
    }
}
