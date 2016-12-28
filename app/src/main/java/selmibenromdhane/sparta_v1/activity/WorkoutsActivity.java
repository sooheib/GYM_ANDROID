package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;

import selmibenromdhane.sparta_v1.helper.Database;
import selmibenromdhane.sparta_v1.manager.Workout;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.adapter.WorkoutsAdapter;
import selmibenromdhane.sparta_v1.adapter.WorkoutsSelectableAdapter;
import selmibenromdhane.sparta_v1.fragment.FragmentWorkouts;

public class WorkoutsActivity extends BaseActivity {

    private Database database;
    private RecyclerView.Adapter adapter;

    private FloatingActionButton fab;

    //The fragment with the list of workout included in this activity
    private FragmentWorkouts fragmentWorkouts;
    private FragmentTransaction transaction;

    private boolean isSelectMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        init();

        //setUpToolbar();

        setUpFab();

        //if the calling activity is EditRoutine , then this is launched for picking workouts to add , so it is select mode
        isSelectMode = (getCallingActivity() != null && getCallingActivity().getClassName().equals(EditRoutineActivity.class.getName()));

        //if it is select mode , the fab must not be visible
        if (isSelectMode)
            fab.hide();

        setUpWorkoutsFragment();

    }

    /**
     * Set up the fragment with the list of workouts
     */
    private void setUpWorkoutsFragment() {
        transaction = getSupportFragmentManager().beginTransaction();

        fragmentWorkouts = FragmentWorkouts.newInstance(database.getListWorkouts(), isSelectMode, true, EditWorkoutActivity.class);
        transaction.add(R.id.container, fragmentWorkouts);
        transaction.commit();
    }

    private void init() {
        database = Database.getInstance(this);
    }

    private void setUpFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkoutsActivity.this, EditWorkoutActivity.class));
            }
        });
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // If calling activity is not null , this activity was started from startActivityForResult()
        //so it was called from EditWorkoutActivity , should display "Done" button
        if (isSelectMode) {
            getMenuInflater().inflate(R.menu.menu_list_selectable, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if (isSelectMode)
                finish();
            else
                NavUtils.navigateUpFromSameTask(this);
        }
        if (id == R.id.action_done) {
            //pass selected workouts to the calling activity
            ArrayList<Workout> workouts = ((WorkoutsSelectableAdapter) fragmentWorkouts.getAdapter()).getSelectedWorkouts();
            Intent intent = new Intent();
            intent.putExtra("workouts", workouts);
            setResult(RESULT_OK, intent);
            finish();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isSelectMode)
            finish();
        else
            NavUtils.navigateUpFromSameTask(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //The adapter is recreated every time the activity resumes
        //thus , the first time and every time the user returns to this activity
        //in order to always be updated according to the db
        if (isSelectMode) {
            adapter = new WorkoutsSelectableAdapter(this, database.getListWorkouts(), getIntent().getIntegerArrayListExtra("ids"));
        } else {
            adapter = new WorkoutsAdapter(this, database.getListWorkouts(), true, EditWorkoutActivity.class);
        }
        fragmentWorkouts.changeAdapter(adapter);
    }

}
