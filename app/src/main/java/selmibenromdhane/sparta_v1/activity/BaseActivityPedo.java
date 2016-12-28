package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.helper.SQLiteUserHandler;
import selmibenromdhane.sparta_v1.helper.SessionManager;


public class BaseActivityPedo extends AppCompatActivity implements OnNavigationItemSelectedListener {

    // delay to launch nav drawer item, to allow close animation to play
    static final int NAVDRAWER_LAUNCH_DELAY = 250;
    // fade in and fade out durations for the main content when switching between
    // different Activities of the app through the Nav Drawer
    static final int MAIN_CONTENT_FADEOUT_DURATION = 150;
    static final int MAIN_CONTENT_FADEIN_DURATION = 250;


    private SessionManager session;
    private SQLiteUserHandler db;

    // Navigation drawer:
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    // Helper
    private Handler mHandler;
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mHandler = new Handler();


        // SqLite database handler
        db = new SQLiteUserHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        //ActionBar ab = getSupportActionBar();
        //if (ab != null) {
        //    mActionBar = ab;
        //    ab.setDisplayHomeAsUpEnabled(true);
        //}

        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected int getNavigationDrawerID() {
        return 0;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        return goToNavigationItem(itemId);
    }

    protected boolean goToNavigationItem(final int itemId) {

        if (itemId == getNavigationDrawerID()) {
            // just close drawer because we are already in this activity
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        // delay transition so the drawer can close
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callDrawerItem(itemId);
            }
        }, NAVDRAWER_LAUNCH_DELAY);

        mDrawerLayout.closeDrawer(GravityCompat.START);

        selectNavigationItem(itemId);

        // fade out the active activity
        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.animate().alpha(0).setDuration(MAIN_CONTENT_FADEOUT_DURATION);
        }
        return true;
    }

    // set active navigation item
    private void selectNavigationItem(int itemId) {
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            boolean b = itemId == mNavigationView.getMenu().getItem(i).getItemId();
            mNavigationView.getMenu().getItem(i).setChecked(b);
        }
    }

    /**
     * Enables back navigation for activities that are launched from the NavBar. See
     * {@code AndroidManifest.xml} to find out the parent activity names for each activity.
     *
     * @param intent
     */
    private void createBackStack(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            TaskStackBuilder builder = TaskStackBuilder.create(this);
            builder.addNextIntentWithParentStack(intent);
            builder.startActivities();
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void callDrawerItem(final int itemId) {

        Intent intent;

        switch (itemId) {
            case R.id.menu_home:
                //Fragment fragment = new MainFragment();

                //FragmentManager fragmentManager = getSupportFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                intent = new Intent(this, MainActivityPedo.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                break;
            case R.id.menu_training:
                intent = new Intent(this, TrainingOverviewActivity.class);
                createBackStack(intent);
                break;

            case R.id.home:
                Toast.makeText(getApplicationContext(),"Home Selected", Toast.LENGTH_SHORT).show();
//                        ContentFragment fragment = new ContentFragment();
//                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.viewpager,fragment);
//                        fragmentTransaction.commit();

                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

            // For rest of the options we just show a toast on click

            case R.id.classes:
                // classes();

                Toast.makeText(getApplicationContext(),"Class Selected", Toast.LENGTH_SHORT).show();
                Intent intentc = new Intent(this,ClassesActivity.class);
                startActivity(intentc);
                finish();
                break;
            case R.id.trainers:
                Toast.makeText(getApplicationContext(),"Trainers Selected", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(this,TrainersActivity.class);
                startActivity(intent5);
                finish();
                break;
            case R.id.events:
                Intent intent1 = new Intent(this,EventsActivity.class);
                startActivity(intent1);
                finish();
                Toast.makeText(getApplicationContext(),"Event Selected", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.setting:
//                Toast.makeText(getApplicationContext(),"Setting Selected", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.exercise:
                Intent intent2= new Intent(this, ExerciseListActivity.class);
                startActivity(intent2);
                finish();
                Toast.makeText(getApplicationContext(),"Exercises Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.perso:
                Intent intent3 = new Intent(this, ProfileActivity.class);
                startActivity(intent3);
                finish();
                Toast.makeText(getApplicationContext(),"Profile Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_workouts:
                Intent intentw = new Intent(this, WorkoutsActivity.class);
                startActivity(intentw);
                finish();
                Toast.makeText(getApplicationContext(),"Workout Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.routine:
                Intent intentr = new Intent(this, RoutinesActivity.class);
                startActivity(intentr);
                finish();
                Toast.makeText(getApplicationContext(),"Workout Selected", Toast.LENGTH_SHORT).show();
                break;

            case R.id.signout:
                logoutUser();
                break;


            default:
        }
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(BaseActivityPedo.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null && mainContent.getAlpha() != 1.0f) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADEIN_DURATION);
        }

        selectNavigationItem(getNavigationDrawerID());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        selectNavigationItem(getNavigationDrawerID());

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADEIN_DURATION);
        }
    }


}