package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.helper.SQLiteUserHandler;
import selmibenromdhane.sparta_v1.helper.SessionManager;

public class BaseActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private SessionManager session;



    private SQLiteUserHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_base);


    }
    @Override
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // SqLite database handler
        db = new SQLiteUserHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        //Initializing NavigationView

        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home Selected", Toast.LENGTH_SHORT).show();
//                        ContentFragment fragment = new ContentFragment();
//                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.viewpager,fragment);
//                        fragmentTransaction.commit();

                        Intent intent = new Intent(BaseActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.classes:
                        classes();
                        return true;
                    case R.id.trainers:
                        Toast.makeText(getApplicationContext(),"Trainers Selected", Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent(BaseActivity.this, TrainersActivity.class);
                        startActivity(intent5);
                        finish();
                        return true;
                    case R.id.events:
                        Intent intent1 = new Intent(BaseActivity.this,EventsActivity.class);
                        startActivity(intent1);
                        finish();

                        Toast.makeText(getApplicationContext(),"Event Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.setting:
                        Toast.makeText(getApplicationContext(),"Setting Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.exercise:
                        Intent intent2= new Intent(BaseActivity.this, ExercisesActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(getApplicationContext(),"Exercises Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.perso:
                        Intent intent3 = new Intent(BaseActivity.this, ProfileActivity.class);
                        startActivity(intent3);
                        finish();
                        Toast.makeText(getApplicationContext(),"Profile Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.signout:
                        logoutUser();
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });


//        if (useToolbar())
//        {
//            setSupportActionBar(toolbar);
//            setTitle("SPARTA");
//        }
//        else
//        {
//            toolbar.setVisibility(View.GONE);
//        }
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        // drawerLayout.setDrawerListener(actionBarDrawerToggle);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }
    protected boolean useToolbar()
    {
        return true;
    }

    private void classes() {
        Intent intent = new Intent(BaseActivity.this, ClassesActivity.class);
        startActivity(intent);
        finish();
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


}
