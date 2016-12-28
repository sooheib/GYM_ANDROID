package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcEvent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import selmibenromdhane.sparta_v1.activity.DetailScheduleActivity1;
import selmibenromdhane.sparta_v1.activity.DetailsClassesActivity1;
import selmibenromdhane.sparta_v1.adapter.ClientsListAdapter;
import selmibenromdhane.sparta_v1.adapter.ScheduleClassListAdapter;
import selmibenromdhane.sparta_v1.manager.Client;
import selmibenromdhane.sparta_v1.manager.Schedule;
import selmibenromdhane.sparta_v1.manager.Session;

/**
 * Created by sooheib on 12/22/16.
 */

public class ScheduleClassListParser extends AsyncTask<Void,Void,Integer>  implements View.OnClickListener{

    private ArrayList<Session> sessions = new ArrayList<>();

    Context c;
    String jsonData;
    RecyclerView rv;
    Calendar calendar = Calendar.getInstance();
    DetailsClassesActivity1 detailsClassesActivity1=new DetailsClassesActivity1();
    String class_id;

    public static String scheduleday1;
    public static String nameCourse="";
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
    public static  String COUNTMEMBER="countMumber";
    public static  String MAX_CAPACITY="max_capacity";





    ProgressDialog pd;
    // ArrayList<Session> schedules=new ArrayList<>();


    public ScheduleClassListParser(Context c, String jsonData, RecyclerView rv) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
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
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }


    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if (result == 0) {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();
        } else {
            //BIND DATA TO LISTVIEW
            // final SessionAdapter adapter=new SessionAdapter(c,schedules);
            //lv.setAdapter(adapter);

            final ScheduleClassListAdapter recyclerViewAdapter=new ScheduleClassListAdapter(c, sessions);

            System.out.println("00000");
            rv.setAdapter(recyclerViewAdapter);
            System.out.println("1111");

            //rv.setOnClickListener();

            rv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c,"SSSS",Toast.LENGTH_LONG).show();
                    System.out.println("ssssssss");


                }
            });
//
//            rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    System.out.println("ssssssssssss");
//                    Toast.makeText(c,"SSSS",Toast.LENGTH_LONG).show();
//
//                    Session item = (Session) adapter.getItem(position);
//
//                    Intent intent=new Intent(c, DetailScheduleActivity1.class);
//
//                    intent.putExtra(SCHEDULE_ID, item.getSchedule_i());
//                    intent.putExtra(SCHEDULE_DATE, item.getDay());
//                    intent.putExtra(COURSE_EXTRA, item.getCourse());
//                    intent.putExtra(TRAINER_EXTRA, item.getTrainer());
//                    intent.putExtra(SCHEDULE_HOUR, item.getStartTime());
//                    intent.putExtra(COURSE_CAPACITY, item.getCourse_maxC());//int
//                    intent.putExtra(COURSE_COVER, item.getCourse_cover());
//                    intent.putExtra(COURSE_DESC, item.getCourse_desc());
//                    intent.putExtra(TRAINER_PHOTO, item.getTrainer_photo());
//                    intent.putExtra(ROOM_NUMBER, item.getRoom_name());
//                    intent.putExtra(COUNTMEMBER,item.getCountMumber());
//
//                    c.startActivity(intent);
//
//                }
//            });

        }
    }

    private int parseData()
    {

        class_id=detailsClassesActivity1.course_id;
        System.out.println("ccccc"+class_id);

        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;


            sessions.clear();
            Session session;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

               String id=jo.getString("14");

                if(id.equalsIgnoreCase(class_id)){

                    //intent.putExtra(SCHEDULE_HOUR, item.getStartTime());

                    String date=jo.getString("1");
                    System.out.println(date);

                    String hour=jo.getString("2");
                    System.out.println(hour);

                    //intent.putExtra(COURSE_EXTRA, item.getCourse());

                    String coursename=jo.getString("3");
                    System.out.println(coursename);

                    //intent.putExtra(COURSE_DESC, item.getCourse_desc());
                    String coursdesc=jo.getString("4");
                    System.out.println(coursdesc);

                    // intent.putExtra(COURSE_COVER, item.getCourse_cover());
                    String coursecover=jo.getString("5");

                    coursecover="https://spartaapp.azurewebsites.net/Backend/partials/user_images/"+coursecover;

                    System.out.println(coursecover);

                    //intent.putExtra(COURSE_CAPACITY, item.getCourse_maxC());//int
                    String coursecapacity=jo.getString("6");
                    System.out.println("coursecapacity"+coursecapacity);

                    //intent.putExtra(TRAINER_EXTRA, item.getTrainer());
                    String trainername=jo.getString("7");

                    System.out.println(trainername);

                    // intent.putExtra(TRAINER_PHOTO, item.getTrainer_photo());
                    String trainerphoto=jo.getString("8");
                    trainerphoto="https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/"+trainerphoto;

                    System.out.println(trainerphoto);

                    //intent.putExtra(ROOM_NUMBER, item.getRoom_name());
                    String roomname=jo.getString("9");
                    System.out.println(roomname);

                    String countMumber=jo.getString("10");
                    System.out.println("countMumber"+countMumber);


                    session=new Session();

                    session.setStartTime(hour);
                    session.setStartDate(date);

                    session.setCourse(coursename);
                    session.setTrainer_photo(trainerphoto);
//                schedule.setCourse_cover(coursecover);
//                schedule.setCourse_desc(coursdesc);
//                schedule.setDay(scheduleday);
//                schedule.setRoom_name(roomname);
//                schedule.setCountMumber(Integer.parseInt(countMumber));
//                schedule.setCourse_maxC(Integer.parseInt(coursecapacity));
//                schedule.setStartDate(datee);
                    sessions.add(session);

                }

            }
            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        System.out.println("assssssssssss");

    }
}
