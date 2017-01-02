package selmibenromdhane.sparta_v1.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.fragment.ClientsListFragment;
import selmibenromdhane.sparta_v1.fragment.ScheduleAboutFragment;
import selmibenromdhane.sparta_v1.fragment.ScheduleListFragment;
import selmibenromdhane.sparta_v1.manager.Friend;
import selmibenromdhane.sparta_v1.manager.Schedule;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;
import selmibenromdhane.sparta_v1.utils.MySingleton;


public class DetailScheduleActivity1 extends AppCompatActivity {
    private static final String KEY_SCHEDULE ="schedule_id" ;
    private static final String KEY_CLIENT ="clientID" ;
    String cover="";
    String name="";
    public static String description;
    public static String trainer;
    public static String photo;
    public static String hour;
    public static String room;
    public static String day;

    public static int countMember;
    public static int courseMax;



    Context context;



    String scheduleID;
    String clientID ="12123";




    private ViewPager mViewPager;
    private ScheduleAboutFragment frag_friendAbout;
    private ClientsListFragment frag_clientList;

    private ActionBar actionBar;
    public static final String EXTRA_OBJCT = "com.app.sample.social.FRIEND";


    LoginActivity loginActivity=new LoginActivity();

    SharedPreferences sh;
    SharedPreferences.Editor ed;

    Button bookcancel;
    Button bookapply;


    public DetailScheduleActivity1() {
    }



    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule1);
        sh = getSharedPreferences("report", MODE_PRIVATE);

        // animation transition
        ViewCompat.setTransitionName(findViewById(android.R.id.content), EXTRA_OBJCT);

        // init toolbar

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0xFFB300);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView textView1 = new TextView(this);
        textView1.setText("Course Complete");

        TextView textView2 = new TextView(this);
        textView2.setText("You are already Booked");




        Intent intent = getIntent();

        if (null != intent) {

            cover = intent.getStringExtra(ScheduleParser.COURSE_COVER);

            name = intent.getStringExtra(ScheduleParser.COURSE_EXTRA);
            day = intent.getStringExtra(ScheduleParser.SCHEDULE_DATE);
            hour = intent.getStringExtra(ScheduleParser.SCHEDULE_HOUR);
            room = intent.getStringExtra(ScheduleParser.ROOM_NUMBER);
            trainer = intent.getStringExtra(ScheduleParser.TRAINER_EXTRA);
            photo = intent.getStringExtra(ScheduleParser.TRAINER_PHOTO);
            description = intent.getStringExtra(ScheduleParser.COURSE_DESC);
//            countMember=Integer.parseInt(intent.getStringExtra(ScheduleParser.COUNTMEMBER));
//            eventMax=Integer.parseInt(intent.getStringExtra(ScheduleParser.MAX_CAPACITY));

            countMember = intent.getIntExtra(ScheduleParser.COUNTMEMBER, 0);
            courseMax = intent.getIntExtra(ScheduleParser.COURSE_CAPACITY, 0);




            bookapply= (Button) findViewById(R.id.booking);
            bookcancel= (Button) findViewById(R.id.bookingcancel);


            System.out.println("count M:" + countMember);
            System.out.println("course Max:" + courseMax);


            scheduleID = intent.getStringExtra(ScheduleParser.SCHEDULE_ID);
            clientID = loginActivity.userId;


            System.out.println("schedule_id:" + scheduleID);
            System.out.println("clientID:" + clientID);


            ed = sh.edit();

            ed.putString("scheduleid",scheduleID);
            System.out.println("scheduleid"+scheduleID);

            ed.putString("day", day);
            System.out.println(day);
            ed.putString("time", hour);
            System.out.println(hour);

            ed.putString("room", room);
            System.out.println(room);

            ed.putString("description", description);
            System.out.println(description);

            ed.putString("trainer", trainer);
            System.out.println(trainer);

            ed.putString("photo", photo);
            System.out.println(photo);

            ed.putInt("eventMax", courseMax);
            ed.putInt("scheduleCount", countMember);

            ed.commit();


        }
        // scollable toolbar
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);

        Picasso.with(DetailScheduleActivity1.this).load(cover).into(ivImage);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        } else if(item.getItemId() == R.id.action_send_message){
            //    Intent i = new Intent(getApplicationContext(), ActivityChatDetails.class);
            // i.putExtra(ActivityChatDetails.KEY_FRIEND, friend);
            //  startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_friend_details, menu);
        return true;
    }
    private void setupViewPager(ViewPager mViewPager) {
        DetailScheduleActivity1.MyPagerAdapter adapter = new DetailScheduleActivity1.MyPagerAdapter(getSupportFragmentManager());

        if( frag_friendAbout == null ){ frag_friendAbout = new ScheduleAboutFragment(); }
          if( frag_clientList == null ){ frag_clientList = new ClientsListFragment(); }
        // if( frag_friendPhotos == null ){ frag_friendPhotos = new FriendPhotosFragment(); }

        adapter.addFragment(frag_friendAbout, "ABOUT");
        adapter.addFragment(frag_clientList, "Clients");
        //adapter.addFragment(frag_friendPhotos, "PHOTOS");

        mViewPager.setAdapter(adapter);
    }


    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }



    public void addBooking(final String userID,final String scheduleID ,String urlbooking)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlbooking,
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
                               // test=0;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                //test=1;


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
        MySingleton.getinstance(DetailScheduleActivity1.this).addToRequest(stringRequest);
    }

    public void deleteBooking(final String userID,final String scheduleID ,String urldeletebooking)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urldeletebooking,
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
                                // test=0;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                //test=1;


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
        MySingleton.getinstance(DetailScheduleActivity1.this).addToRequest(stringRequest);
    }




}
