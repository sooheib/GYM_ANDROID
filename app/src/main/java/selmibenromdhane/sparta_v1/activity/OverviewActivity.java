package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.utils.Constants;
import selmibenromdhane.sparta_v1.utils.ImageUtils;


public class OverviewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView tvUsername, tvSubtitle;

    private Toolbar toolbar;

    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        setUpToolbar();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        setUpDrawer();

    }

    @Override
    protected void onResume() {
        super.onResume();

        tvUsername.setText(prefs.getString(Constants.TAG_NAME, "John Smith"));
        tvSubtitle.setText(prefs.getInt(Constants.TAG_HEIGHT, 180) + " cm , " + prefs.getFloat(Constants.TAG_WEIGHT, 80) + " Kg");
    }

    private void setUpDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header = LayoutInflater.from(this).inflate(R.layout.nav_header_overview, null);
        ImageView profile = (ImageView) nav_header.findViewById(R.id.profile_photo);

        //get the uri of the preferred profile image
        Uri imageUri = Uri.parse(prefs.getString(Constants.TAG_PHOTO_PATH, ""));
        ImageUtils.loadProfileImage(this, imageUri, profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OverviewActivity.this, EditProfileActivity.class));
            }
        });
        tvUsername = ((TextView) nav_header.findViewById(R.id.tvUsername));
        tvSubtitle = ((TextView) nav_header.findViewById(R.id.tvUserSecondaryText));
        navigationView.addHeaderView(nav_header);

    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_routines) {
            startActivity(new Intent(OverviewActivity.this, RoutinesActivity.class));
        } else if (id == R.id.nav_workouts) {
            startActivity(new Intent(OverviewActivity.this, WorkoutsActivity.class));
        } else if (id == R.id.nav_exercises) {
            startActivity(new Intent(OverviewActivity.this, ExerciseListActivity.class));
        } else if (id == R.id.nav_settings) {
            Snackbar.make(toolbar, "You should enter a valid name for the workout!", Snackbar.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
