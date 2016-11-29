package selmibenromdhane.sparta_v1.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.helper.SQLiteUserHandler;
import selmibenromdhane.sparta_v1.helper.SessionManager;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;
import selmibenromdhane.sparta_v1.utils.MySingleton;
import selmibenromdhane.sparta_v1.utils.UrltoBitmap;

public class DetailScheduleActivity extends AppCompatActivity {
    private static final String KEY_SCHEDULE ="schedule_id" ;
    private static final String KEY_CLIENT ="clientID" ;

    TextView tv1, tv2, tv3,tv4,tv5,tv6,tvclient,tvschedule;

    private static final String TAG1 = DetailScheduleActivity.class.getSimpleName();


    Context context;
    String description="";
    String course = "";
    String trainer = "";
    String hour = "";
    String cover="";
    String photo="";
    String room="";
    String day="";
    String scheduleID;
    String clientID ="12123";
    int maxC;
    String userID;
    int countMember;
    int test;
    private SessionManager session;
    private SQLiteUserHandler db;
    private ProgressDialog pDialog;

    private static final String TAG = DetailScheduleActivity.class.getSimpleName();

    LoginActivity loginActivity=new LoginActivity();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ImageView circleImageView= (ImageView) findViewById(R.id.trainerphoto);
        tv1 = (TextView) findViewById(R.id.trainer);
        tv2 = (TextView) findViewById(R.id.hour);
        tv3 = (TextView) findViewById(R.id.duration);
        tv4= (TextView) findViewById(R.id.room);
        tv5= (TextView) findViewById(R.id.description);
        tvclient=(TextView)findViewById(R.id.client_id);
        tvschedule=(TextView)findViewById(R.id.schedule_id);
     //   seanceBDD=new SeanceBDD(this);

        tv6= (TextView) findViewById(R.id.day);
        db = new SQLiteUserHandler(getApplicationContext());

        db.getUserDetails();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.toolbar_layout);


        Intent intent = getIntent();
        if (null != intent) {

            ///


            course = intent.getStringExtra(ScheduleParser.COURSE_EXTRA);
            trainer = intent.getStringExtra(ScheduleParser.TRAINER_EXTRA);

            scheduleID =intent.getStringExtra(ScheduleParser.SCHEDULE_ID);
            hour = intent.getStringExtra(ScheduleParser.SCHEDULE_HOUR);
            clientID =loginActivity.userId;
            System.out.println("***********clientID:***************"+ clientID);
            cover=intent.getStringExtra(ScheduleParser.COURSE_COVER);
            room=intent.getStringExtra(ScheduleParser.ROOM_NUMBER);
            photo=intent.getStringExtra(ScheduleParser.TRAINER_PHOTO);
            description=intent.getStringExtra(ScheduleParser.COURSE_DESC);
            day=intent.getStringExtra(ScheduleParser.SCHEDULE_DATE);
            System.out.println("test            "+intent.getIntExtra(ScheduleParser.COURSE_CAPACITY,0));
            maxC=(intent.getIntExtra(ScheduleParser.COURSE_CAPACITY,0));
            ImageView toolbarImage= (ImageView) findViewById(R.id.toolbar);
            System.out.println(intent.getIntExtra(ScheduleParser.COUNTMEMBER,0));
            countMember=intent.getIntExtra(ScheduleParser.COUNTMEMBER,0);

            UrltoBitmap drawableSchedule=new UrltoBitmap();
            Bitmap coverSchedule=drawableSchedule.getBitmapFromURL(cover);
            Drawable drawableS = new BitmapDrawable(getResources(), coverSchedule);
            appBarLayout.setBackground(drawableS);







            URL url1 = null;
            try {
                url1 = new URL(photo);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection1 = null;
            try {
                connection1 = (HttpURLConnection) url1.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection1.setDoInput(true);
            try {
                connection1.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream input1 = null;
            try {
                input1 = connection1.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bitmap bitmap1 = BitmapFactory.decodeStream(input1);

           // Picasso.with(DetailScheduleActivity.this).load(photo).into(circleImageView);

               circleImageView.setImageBitmap(getCircleBitmap(bitmap1));
        }


        appBarLayout.setTitle(course);


        System.out.println("desc: "+description);
        System.out.println("course: "+cover);
        System.out.println("photo: "+photo);
        System.out.println("room: "+room);
        System.out.println("day:"  +day);
        System.out.println("max:"  +maxC);
        System.out.println("countMember:"  +countMember);



        System.out.println("schedule_id:"  + scheduleID);
        System.out.println("clientID:"  + clientID);

        TextView tv1 = (TextView) findViewById(R.id.trainer);
        tv1.setText(trainer);

        TextView tv2 = (TextView) findViewById(R.id.hour);
        tv2.setText(hour);

        TextView tv3 = (TextView) findViewById(R.id.duration);
//        tv3.setText("60min");
        TextView tv4= (TextView) findViewById(R.id.room);
        tv4.setText(room);
        tv5.setText(description);
        tv6.setText(day);
        tvschedule.setText(scheduleID);
        tvclient.setText(clientID);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(countMember<=maxC)
        {  diseableFab(true);
            Toast.makeText(getApplicationContext(),"Cours complet",Toast.LENGTH_LONG);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addReserv(clientID,scheduleID,AppConfig.URL_RESERV);
                diseableFab(true);//desable FAB when booking added
  //              seanceBDD.open();
//                seanceBDD.insertSeance(Integer.parseInt(clientID),Integer.parseInt(scheduleID));

            }

        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
//
        }
    }





    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
////         handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;}

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    public void onBackPressed(){
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void Booking(View view) {

        //  int scheduleID=Integer.parseInt(schedule_idd);
        //int clientID=Integer.parseInt(client_idd);
        BookSchedule(scheduleID, userID);
    }

    private void BookSchedule(final String schedule_id, final String client_id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_BOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetailScheduleActivity.this,response,Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailScheduleActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_SCHEDULE,schedule_id);
                params.put(KEY_CLIENT,client_id);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void addReserv(final String userID,final String scheduleID ,String url)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String code= jsonObject.getString("code");
                            if(code.equals("failed"))
                            {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                test=0;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                test=1;


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"errooooor",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("scheduleID",String.valueOf(scheduleID));
                return params;
            }
        };
        MySingleton.getinstance(DetailScheduleActivity.this).addToRequest(stringRequest);
    }

    public void diseableFab(Boolean test) //desable FAB when booking added
    {        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (test)
        {
            fab.setVisibility(FloatingActionButton.INVISIBLE);
        }
    }
}

