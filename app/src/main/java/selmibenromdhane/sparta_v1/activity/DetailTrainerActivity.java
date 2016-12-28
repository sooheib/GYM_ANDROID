package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.ClientsListFragment;
import selmibenromdhane.sparta_v1.fragment.ScheduleAboutFragment;
import selmibenromdhane.sparta_v1.fragment.TrainerScheduleFragment;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;

/**
 * Created by sooheib on 12/23/16.
 */

public class DetailTrainerActivity extends AppCompatActivity {




    String name="";
    String cover="";



    private ViewPager mViewPager;
    private TrainerScheduleFragment frag_trainerSchedule;


    private ActionBar actionBar;
    public static final String EXTRA_OBJCT = "com.app.sample.social.FRIEND";


    LoginActivity loginActivity=new LoginActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule1);
       // sh = getSharedPreferences("report", MODE_PRIVATE);

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

            name = intent.getStringExtra(ScheduleParser.COURSE_EXTRA);
            cover = intent.getStringExtra(ScheduleParser.COURSE_COVER);




        }
        // scollable toolbar
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(name);
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);

        Picasso.with(DetailTrainerActivity.this).load(cover).into(ivImage);


        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        DetailTrainerActivity.MyPagerAdapter adapter = new DetailTrainerActivity.MyPagerAdapter(getSupportFragmentManager());

        if( frag_trainerSchedule == null ){ frag_trainerSchedule = new TrainerScheduleFragment(); }
      //  if( frag_clientList == null ){ frag_clientList = new ClientsListFragment(); }
        // if( frag_friendPhotos == null ){ frag_friendPhotos = new FriendPhotosFragment(); }

        adapter.addFragment(frag_trainerSchedule, "SCHEDULE");
     //   adapter.addFragment(frag_clientList, "Clients");
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
