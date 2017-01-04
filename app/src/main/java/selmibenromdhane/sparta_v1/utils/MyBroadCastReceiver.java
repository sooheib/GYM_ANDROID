package selmibenromdhane.sparta_v1.utils;

/**
 * Created by anuja on 4/28/2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import selmibenromdhane.sparta_v1.R;

public class MyBroadCastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        String desc = intent.getStringExtra("desc");
        Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();

        Intent i = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, i, 0);
        Notification noti = new Notification.Builder(context).setTicker("Title")
                .setContentTitle("Your next course is ").setContentText(desc).setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent).getNotification();
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,noti);
    }

}
