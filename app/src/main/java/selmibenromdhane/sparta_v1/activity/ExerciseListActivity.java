package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Data.Database;
import selmibenromdhane.sparta_v1.Enumeration.EnumMuscleGroups;
import selmibenromdhane.sparta_v1.Model.Exercise;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.adapter.ExerciseAdapter;
import selmibenromdhane.sparta_v1.adapter.ExerciseSelectableAdapter;

public class ExerciseListActivity extends BaseActivity {

    private Toolbar toolbar;
    private Database database;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private boolean isSelectMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        isSelectMode = (getCallingActivity() != null && getCallingActivity().getClassName().equals(EditWorkoutActivity.class.getName()));

       // initToolbar();

        init();

        setUpRecyclerView();

    }

    private void init() {
        database = Database.getInstance(this);
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (isSelectMode) {
            //This Activity was called by EditWorkoutActivity
            adapter = new ExerciseSelectableAdapter(
                    this,
                    getRecylerData(),
                    getIntent().getIntegerArrayListExtra("ids"));
        } else {
            //This Activity was called by main menu
            adapter = new ExerciseAdapter(this, getRecylerData());
        }

        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Prepares the dataset for the recycler view
     * Gets all exercises from the db , grouped by muscle and adds a null pointer between different muscle groups
     * so that the recycler view works fine with two view types(header and item views)
     *
     * @return the final data used by the recycler view
     */
    private ArrayList<Exercise> getRecylerData() {
        ArrayList<Exercise> items = database.getListExercises();
        ArrayList<Integer> counts = database.getCountsByMuscle();
        items.add(0, null);
        int position = 0;
        for (int i = 0; i < EnumMuscleGroups.values().length - 1; i++) {
            position += counts.get(i) + 1;
            items.add(position, null);
        }
        return items;
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
            //pass selected exercises to the EditWorkoutActivity
            ArrayList<Exercise> exercises = ((ExerciseSelectableAdapter) adapter).getSelectedExercises();
            Intent intent = new Intent();
            intent.putExtra("exercises", exercises);
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
}
