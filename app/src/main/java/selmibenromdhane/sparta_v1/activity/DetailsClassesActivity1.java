package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.ClassAboutFragment;
import selmibenromdhane.sparta_v1.fragment.ClassTrainerFragement;
import selmibenromdhane.sparta_v1.fragment.ScheduleClassListFragment;
import selmibenromdhane.sparta_v1.manager.Schedule;
import selmibenromdhane.sparta_v1.parser.ClassesCardParser;

public class DetailsClassesActivity1 extends AppCompatActivity {


    String cover="";
    String name="";
    public static String course_id;
    public static String description;

    private ViewPager mViewPager;
    private ClassAboutFragment frag_friendAbout;
    private ScheduleClassListFragment frag_friendActivity;
    private ClassTrainerFragement frag_trainerfragment;

    private ActionBar actionBar;

    SharedPreferences sh;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_classes1);
        sh = getSharedPreferences("report",MODE_PRIVATE);

        // init toolbar

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0xFFB300);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (null != intent) {

            cover=intent.getStringExtra(ClassesCardParser.IMAGE_EXTRA);
            name=intent.getStringExtra(ClassesCardParser.COURSE_EXTRA);
            description=intent.getStringExtra(ClassesCardParser.DESCRIPTION_EXTRA);
            course_id=intent.getStringExtra(ClassesCardParser.COURSE_ID);

            ed=sh.edit();
            ed.putString("description",description);
            ed.putString("course_id",course_id);
            System.out.println(description);
            ed.commit();


        }
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);
        ImageView ivImage = (ImageView)findViewById(R.id.ivImage);

        Picasso.with(DetailsClassesActivity1.this).load(cover).into(ivImage);

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
        DetailsClassesActivity1.MyPagerAdapter adapter = new DetailsClassesActivity1.MyPagerAdapter(getSupportFragmentManager());

        if( frag_friendAbout == null ){ frag_friendAbout = new ClassAboutFragment(); }
          if( frag_friendActivity == null ){ frag_friendActivity = new ScheduleClassListFragment(); }
         if( frag_trainerfragment == null ){ frag_trainerfragment = new ClassTrainerFragement(); }

        adapter.addFragment(frag_friendAbout, "ABOUT");
        adapter.addFragment(frag_trainerfragment, "Trainer");
        adapter.addFragment(frag_friendActivity, "SCHEDULE");

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
