package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Data.Database;
import selmibenromdhane.sparta_v1.Enumeration.EnumExerciseTypes;
import selmibenromdhane.sparta_v1.Enumeration.EnumMuscleGroups;
import selmibenromdhane.sparta_v1.Model.Exercise;
import selmibenromdhane.sparta_v1.Model.Set;
import selmibenromdhane.sparta_v1.Model.Workout;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.adapter.WorkoutExercisesAdapter;

public class EditWorkoutActivity extends AppCompatActivity {

    private Database database;
    private RecyclerView recyclerView;
    private WorkoutExercisesAdapter adapter;

    private Workout workout = null;
    private EditText etWorkoutName;
    private Spinner sType, sMuscle;

    private boolean isCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        database = Database.getInstance(this);

        setUpToolbar();

        setUpFab();

        setUpViews();

        setUpRecyclerView();

        //if no object was passed through the intent , it's creation mode
        if (getIntent().getSerializableExtra("workout") == null) {
            workout = new Workout();
            isCreation = true;

            //else it's edit mode
        } else {
            workout = (Workout) getIntent().getSerializableExtra("workout");
            fillUIFromWorkout();
            isCreation = false;
        }
    }

    /**
     * fill UI views from the workout's values
     */
    private void fillUIFromWorkout() {
        etWorkoutName.setText(workout.getTitle());
        sType.setSelection(workout.getType().getValue() - 1);
        sMuscle.setSelection(workout.getMuscle().getValue() - 1);
        refreshExerciseList(workout.getExercises());
    }

    /**
     * Init and Set up the views about the info of the workout such as the name , muscle and type
     */
    private void setUpViews() {

        etWorkoutName = (EditText) findViewById(R.id.etWorkoutName);

        sType = (Spinner) findViewById(R.id.sWorkoutType);
        sMuscle = (Spinner) findViewById(R.id.sWorkoutMuscle);

        String[] types = new String[EnumExerciseTypes.values().length];
        int i = 0;
        for (EnumExerciseTypes type : EnumExerciseTypes.values()) {
            types[i++] = type.toString();
        }
        String[] muscles = new String[EnumMuscleGroups.values().length];
        i = 0;
        for (EnumMuscleGroups muscle : EnumMuscleGroups.values()) {
            muscles[i++] = muscle.toString();
        }

        ArrayAdapter typesAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                types);
        ArrayAdapter musclesAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                muscles);
        sType.setSelection(0);
        sType.setAdapter(typesAdapter);
        sMuscle.setSelection(0);
        sMuscle.setAdapter(musclesAdapter);

    }

    private void setUpFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Launch the exercise list activity
                Intent intent = new Intent(EditWorkoutActivity.this, ExerciseListActivity.class);

                //and pass the ids of the exercises that are already selected
                ArrayList<Integer> ids = new ArrayList<>();
                for (Exercise e : workout.getExercises()) {
                    ids.add(e.getId());
                }
                intent.putIntegerArrayListExtra("ids", ids);

                startActivityForResult(intent, 100);
            }
        });
    }

    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rvWorkoutExercises);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //request code is 200 , for activity EditSetActivity , for managing sets of exercise
        if (requestCode == 200) {
            //if result is ok , user chose to save the changes to the sets
            if (resultCode == RESULT_OK) {
                //get the exercise that was changed
                int id = data.getIntExtra("id", 0);
                int resttime = data.getIntExtra("resttime", 10);
                Log.i("nikos" , resttime + "");
                //and apply the new sets list to it
                for (Exercise e : workout.getExercises()) {
                    if (e.getId() == id) {
                        e.setResttime(resttime);
                        e.setSets(((ArrayList<Set>) data.getExtras().getSerializable("sets")));
                    }
                }
                //refresh the exercises list
                refreshExerciseList(workout.getExercises());
            }
        }

        //request code is 100 , for activity exercise list , for selecting exercises to add
        if (requestCode == 100) {
            //if result code is ok , must take the returned list of exercises
            if (resultCode == RESULT_OK) {
                ArrayList<Exercise> returnedExercises = (ArrayList<Exercise>) data.getSerializableExtra("exercises");
                ArrayList<Exercise> currentExercises = workout.getExercises();

                //At first , must delete the exercise that were unchecked
                //thus , they were present in the list and they are not now
                boolean found;
                //So , if an exercise is in the current(the old) list and not in the returned list
                //delete it
                for (int i = 0; i < currentExercises.size(); i++) {

                    found = false;
                    for (Exercise e : returnedExercises) {

                        if (currentExercises.get(i).getId() == e.getId())
                            found = true;
                    }
                    if (!found) {
                        currentExercises.remove(i);
                        i--;
                    }
                }

                //Then , must add the exercises that are newly checked , thus , are not in the
                //current(old) list but are in the returned list
                for (Exercise exercise : returnedExercises) {

                    found = false;
                    for (Exercise e : currentExercises) {
                        if (exercise.getId() == e.getId())
                            found = true;
                    }
                    if (!found) {
                        exercise.addSet(new Set(10));
                        exercise.addSet(new Set(10));
                        exercise.addSet(new Set(10));
                        workout.addExercise(exercise);
                    }
                }

                //refresh exercise list , with the new list
                refreshExerciseList(workout.getExercises());

            }
        }
    }

    private void refreshExerciseList(ArrayList<Exercise> newList) {
        adapter = new WorkoutExercisesAdapter(this, newList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //if is edit mode , delete option should be present in the menu
        if (isCreation)
            getMenuInflater().inflate(R.menu.activity_add_item, menu);
        else
            getMenuInflater().inflate(R.menu.activity_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.action_delete) {
            //delete workout and exit
            database.deleteWorkout(workout.getId());
            Toast.makeText(EditWorkoutActivity.this, workout.getTitle() + " deleted!", Toast.LENGTH_SHORT).show();
            finish();
        } else if (id == R.id.action_save) {

            //if there are no exercises selected , alert
            if (workout.getExercises().size() == 0) {
                Snackbar.make(etWorkoutName, "You should have at least one exercise in the workout!", Snackbar.LENGTH_LONG).show();
                return true;
            }
            //if name has not been set , alert
            if (etWorkoutName.getText().toString().trim().equals("")) {
                Snackbar.make(etWorkoutName, "You should enter a valid name for the workout!", Snackbar.LENGTH_LONG).show();
                return true;
            }

            //assign values to the workout
            workout.setTitle(etWorkoutName.getText().toString());
            workout.setMuscle(EnumMuscleGroups.values()[sMuscle.getSelectedItemPosition()]);
            workout.setType(EnumExerciseTypes.values()[sType.getSelectedItemPosition()]);

            //if it's creation mode , add the new workout in the db
            if (isCreation) {

                database.insert(workout);
                Toast.makeText(EditWorkoutActivity.this, "Workout Created !", Toast.LENGTH_LONG).show();

                //else it's update mode , so update the workout
            } else {
                database.update(workout);
                Toast.makeText(EditWorkoutActivity.this, "Workout Updated !", Toast.LENGTH_LONG).show();
            }

            finish();
        }

        return true;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }
}
