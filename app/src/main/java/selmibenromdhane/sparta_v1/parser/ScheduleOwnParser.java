package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import selmibenromdhane.sparta_v1.activity.DetailScheduleActivity;
import selmibenromdhane.sparta_v1.activity.LoginActivity;
import selmibenromdhane.sparta_v1.adapter.ScheduleAdapter;
import selmibenromdhane.sparta_v1.adapter.SessionAdapter;
import selmibenromdhane.sparta_v1.fragment.ScheduleGymFragment;
import selmibenromdhane.sparta_v1.fragment.ScheduleOwnFragment;
import selmibenromdhane.sparta_v1.manager.Session;

/**
 * Created by sooheib on 11/21/16.
 */

public class ScheduleOwnParser extends AsyncTask<Void,Void,Integer> {

    Context c;
    String jsonData;
    SwipeMenuListView lv;

    public static String CLIENT_ID="client_id";
    public static String COURSE_EXTRA="course_code";
    public static String COURSE_COVER="course_cover";
    public static String COURSE_DESC="course_desc";
    public static String COURSE_CAPACITY="course_maxCapacity";

    public static String TRAINER_EXTRA="last_name";
    public static String TRAINER_PHOTO="photo";

    public static String SCHEDULE_ID ="schedule_id";
    public static String SCHEDULE_HOUR ="startTime";
    public static String SCHEDULE_DATE ="day";

    public static  String ROOM_NUMBER="room_number";
    public String client_id;




    private ScheduleAdapter adapter;

    ProgressDialog pd;
    ArrayList<Object> schedules=new ArrayList<>();


    ScheduleGymFragment scheduleGymFragment=new ScheduleGymFragment();
    String todayy;
    String monthh;

    Calendar calendar = Calendar.getInstance();

    String currentday;
    String selectedday="";

    LoginActivity loginActivity=new LoginActivity();


    public ScheduleOwnParser(Context c, String jsonData, SwipeMenuListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }


    @Override
    protected Integer doInBackground(Void... params) {
        return   this.parseData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();



    }



    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }else {
            //BIND DATA TO LISTVIEW
            final SessionAdapter adapter=new SessionAdapter(c,schedules);
            lv.setAdapter(adapter);

            //   lvv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("ssssssssssss");
                    Toast.makeText(c,"SSSS",Toast.LENGTH_LONG).show();

                    lv.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

                        @Override
                        public void onSwipeStart(int position) {
                            // swipe start
                            System.out.println("go");

                        }

                        @Override
                        public void onSwipeEnd(int position) {

                            schedules.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    });


                }
            });
        }
    }




    private int parseData()
    {

        client_id=loginActivity.userId;

        SimpleDateFormat sdftoday = new SimpleDateFormat("dd");
        SimpleDateFormat sdfmonth = new SimpleDateFormat("MM");
        SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");

        currentday=sdfmonth.format(calendar.getTime())+"/"+sdftoday.format(calendar.getTime())+"/"+sdfyear.format(calendar.getTime());


        selectedday=scheduleGymFragment.selecteddayGS;
        System.out.println("bb"+selectedday);

        if(!selectedday.equalsIgnoreCase("99")){
            currentday=selectedday;
        }
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            schedules.clear();
            Session schedule;


            String schedule1="12/02/2016";
            for(int i=0;i<ja.length();i++) {

                jo = ja.getJSONObject(i);

                String idC = jo.getString("0");
                System.out.println("IDC " + idC);
                String scheduleday = jo.getString("2");
                System.out.println("SCHEDULEOWN " + scheduleday);

                if ((idC.equalsIgnoreCase(client_id))) {

                    System.out.println("************currentday" + currentday);


//                    if (currentday.compareTo(scheduleday) <= 0) {

                        String hour = jo.getString("3");
                        System.out.println("HOUROWN " + hour);

                        String coursename = jo.getString("4");
                        System.out.println("COURSEOWN " + coursename);

                        String trainername = jo.getString("5");
                        System.out.println("TRAINEROWN " + trainername);

                        schedule = new Session();
                        schedule.setStartTime(hour);
                        schedule.setCourse(coursename);
                        schedule.setTrainer(trainername);

                        if (!schedule1.equalsIgnoreCase(scheduleday)) {
                            schedules.add(scheduleday);

                            schedule1 = scheduleday;

                        }
                        schedules.add(schedule);

                        System.out.println("asasas" + schedules);
                    }


                }
//            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}