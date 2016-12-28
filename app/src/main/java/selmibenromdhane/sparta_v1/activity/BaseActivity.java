package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.helper.SQLiteUserHandler;
import selmibenromdhane.sparta_v1.helper.SessionManager;

public class BaseActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private SessionManager session;
    ImageView imageView;


    private SQLiteUserHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_base);
    }
    @NonNull
    @Override
    public void setContentView(int layoutResID)
    {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);

        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background9, opt);
        int height = opt.outHeight;
        int width = opt.outWidth;
        String imageType = opt.outMimeType;



        Bitmap bitmap1=decodeSampledBitmapFromResource(getResources(),R.drawable.spartalogo, 100, 100);


        Drawable drawable=new BitmapDrawable(getResources(),bitmap1);


        // SqLite database handler
        db = new SQLiteUserHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        //Initializing NavigationView

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

//        View headerView= navigationView.inflateHeaderView(R.layout.header);

       // imageView= (ImageView) headerView.findViewById(R.id.profile_image);

       // imageView.setImageDrawable(drawable);


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
                        // classes();

                        Toast.makeText(getApplicationContext(),"Class Selected", Toast.LENGTH_SHORT).show();
                        Intent intentc = new Intent(BaseActivity.this,ClassesActivity.class);
                        startActivity(intentc);
                        finish();
                        return true;
                    case R.id.trainers:
                        Toast.makeText(getApplicationContext(),"Trainers Selected", Toast.LENGTH_SHORT).show();
                        Intent intent5 = new Intent(BaseActivity.this,TrainerActivity1.class);
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
                        Intent intent2= new Intent(BaseActivity.this, ExerciseListActivity.class);
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

                    case R.id.nav_workouts:
                        Intent intentw = new Intent(BaseActivity.this, WorkoutsActivity.class);
                        startActivity(intentw);
                        finish();
                        Toast.makeText(getApplicationContext(),"Workout Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.routine:
                        Intent intentr = new Intent(BaseActivity.this, RoutinesActivity.class);
                        startActivity(intentr);
                        finish();
                        Toast.makeText(getApplicationContext(),"Workout Selected", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.menu_home:
                        //Fragment fragment = new MainFragment();

                        //FragmentManager fragmentManager = getSupportFragmentManager();
                        //fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
                        Intent intentpedo1 = new Intent(BaseActivity.this, MainActivityPedo.class);
                        intentpedo1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentpedo1);
                        finish();

                        return true;
                    case R.id.menu_training:
                     Intent   intentpedo2 = new Intent(BaseActivity.this, TrainingOverviewActivity.class);
                        startActivity(intentpedo2);
                        finish();

                        // createBackStack(intent);

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


    @NonNull
    @Override
    public ActionBar getSupportActionBar() {
        // Making getSupportActionBar() method to be @NonNull
        ActionBar actionBar = super.getSupportActionBar();
        if (actionBar == null) {
            throw new NullPointerException("Action bar was not initialized");
        }
        return actionBar;
    }


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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


    public static int calculateMyInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of bitmap
        final int oriHeight = options.outHeight;
        final  int oriWidth=options.outWidth;
        int inSampleSize = 1;

        if (oriHeight > reqHeight || oriWidth > reqWidth) {

            final int halfHeight = oriHeight / 2;
            final int halfWidth = oriWidth / 2;

            // Calculate the largest inSampleSize value which is power of 2 and keeps both width and height larger than the requested width and height.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth)
            {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resource, int resourceId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resource, resourceId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateMyInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resource, resourceId, options);
    }




}
