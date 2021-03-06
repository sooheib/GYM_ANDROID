package selmibenromdhane.sparta_v1.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailScheduleActivity1;
import selmibenromdhane.sparta_v1.activity.HomeActivity;
import selmibenromdhane.sparta_v1.activity.LoginActivity;
import selmibenromdhane.sparta_v1.activity.RegisterActivity;
import selmibenromdhane.sparta_v1.adapter.ScheduleAdapter;
import selmibenromdhane.sparta_v1.adapter.SessionAdapter;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.manager.Article;
import selmibenromdhane.sparta_v1.manager.Schedule;
import selmibenromdhane.sparta_v1.manager.Session;
import selmibenromdhane.sparta_v1.parser.ScheduleOwnDownloader;
import selmibenromdhane.sparta_v1.utils.MyBroadCastReceiver;
import selmibenromdhane.sparta_v1.utils.MySingleton;

import static android.content.Context.MODE_PRIVATE;
import static com.android.volley.Request.*;


public class ScheduleOwnFragment extends Fragment {

    //final static String urlAddress="http://172.16.155.135/Upload-Insert-Update-Delete-Image-PHP-MySQL/getSchedule.php";


    String clientID = "12123";
    LoginActivity loginActivity = new LoginActivity();

    String tag_string_req = "req_register";
    private ProgressDialog pDialog;
    private SessionAdapter adapter;
    ArrayList<Session> schedules = new ArrayList<>();

    SharedPreferences sh;
    SharedPreferences.Editor ed;

    public static String selecteddayGS;



    //Overriden method onCreateView

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_scheduleown, container, false);

        sh = this.getActivity().getSharedPreferences("report",MODE_PRIVATE);


        selecteddayGS=sh.getString("selected","99");

        System.out.println("**selected**"+selecteddayGS);



        SwipeMenuListView classListView = (SwipeMenuListView) rootView.findViewById(R.id.list2);

        new ScheduleOwnDownloader(getActivity(), AppConfig.URL_OWNSCHEDULE, classListView).execute();

        System.out.println("ScheduleOwnFragment");
        SwipeMenuCreator creator = new SwipeMenuCreator() {


            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                //deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_black);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        classListView.setMenuCreator(creator);
        classListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {


                return false;
            }
        });
        String dateStr="01-03-2016";
        String timeStr="21:45";
        String desc="Event1";

        SetAlarm(dateStr,timeStr,desc);

        classListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);

        return rootView;
}

    public void SetAlarm(String dateStr, String timeStr, String desc) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        String time = "00:00";  // If time is not given
//        if(timeStr.contains("AM")) {
//            time = timeStr.substring(0,5);
//        } else if(timeStr.contains("PM")) {
//            int hour = Integer.parseInt(timeStr.substring(0,2));
//            time = (hour+12) + ":" + timeStr.substring(3,5);
//        }
        Date date = null;
        try {
            date = sdf.parse(dateStr + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        Intent intent = new Intent(getActivity(), MyBroadCastReceiver.class);
        intent.putExtra("desc", desc);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 1253, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | Intent.FILL_IN_DATA);

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(getActivity(), "Alarm Set.", Toast.LENGTH_LONG).show();
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

}