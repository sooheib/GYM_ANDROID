package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.ScheduleAboutFragment;
import selmibenromdhane.sparta_v1.fragment.ScheduleListFragment;
import selmibenromdhane.sparta_v1.manager.Friend;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;

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

    private ViewPager mViewPager;
    private ScheduleAboutFragment frag_friendAbout;
    private ScheduleListFragment frag_clientList;

    private ActionBar actionBar;
    public static final String EXTRA_OBJCT = "com.app.sample.social.FRIEND";

    public static Friend friend;

    SharedPreferences sh;
    SharedPreferences.Editor ed;


    public DetailScheduleActivity1() {
    }

    public static void navigate(AppCompatActivity activity, View transitionImage, Friend obj) {
        Intent intent = new Intent(activity, DetailScheduleActivity1.class);
        intent.putExtra(EXTRA_OBJCT, obj);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (activity, transitionImage, EXTRA_OBJCT);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule1);
        sh = getSharedPreferences("report",MODE_PRIVATE);

        // animation transition
        ViewCompat.setTransitionName(findViewById(android.R.id.content), EXTRA_OBJCT);

        // init toolbar

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0xFFB300);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        if (null != intent) {

            cover=intent.getStringExtra(ScheduleParser.COURSE_COVER);

        name=intent.getStringExtra(ScheduleParser.COURSE_EXTRA);
            day=intent.getStringExtra(ScheduleParser.SCHEDULE_DATE);
            hour=intent.getStringExtra(ScheduleParser.SCHEDULE_HOUR);
            room=intent.getStringExtra(ScheduleParser.ROOM_NUMBER);
            trainer=intent.getStringExtra(ScheduleParser.TRAINER_EXTRA);
            photo=intent.getStringExtra(ScheduleParser.TRAINER_PHOTO);
            description=intent.getStringExtra(ScheduleParser.COURSE_DESC);




            ed=sh.edit();
            ed.putString("day",day);
            System.out.println(day);
            ed.putString("time",hour);
            System.out.println(hour);

            ed.putString("room",room);
            System.out.println(room);

            ed.putString("description",description);
            System.out.println(description);

            ed.putString("trainer",trainer);
            System.out.println(trainer);

            ed.putString("photo",photo);
            System.out.println(photo);

            ed.commit();


        }
        // scollable toolbar
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);
        ImageView ivImage = (ImageView)findViewById(R.id.ivImage);

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
          if( frag_clientList == null ){ frag_clientList = new ScheduleListFragment(); }
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

}
