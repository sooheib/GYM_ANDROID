package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import selmibenromdhane.sparta_v1.Enumeration.EnumExerciseTypes;
import selmibenromdhane.sparta_v1.Enumeration.EnumMuscleGroups;
import selmibenromdhane.sparta_v1.Enumeration.EnumWeekDays;
import selmibenromdhane.sparta_v1.helper.Database;

import selmibenromdhane.sparta_v1.manager.Routine;
import selmibenromdhane.sparta_v1.manager.Workout;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.FragmentRoutineDay;

public class EditRoutineActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private Database database;
    private boolean isCreation;

    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    private Routine routine;

    private Spinner sType;
    private EditText etRoutineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_routine);

        database = Database.getInstance(this);

        setUpToolbar();


        setUpViews();

        //if no object was passed through the intent , it's creation mode
        if (getIntent().getSerializableExtra("routine") == null) {
            routine = new Routine();
            isCreation = true;

            //else it's edit mode
        } else {
            routine = (Routine) getIntent().getSerializableExtra("routine");
            fillUIFromRoutine();

            isCreation = false;
        }

        setupViewPager();

    }

    /**
     * Init and Set up the views about the info of the workout such as the name , muscle and type
     */
    private void setUpViews() {

        etRoutineName = (EditText) findViewById(R.id.etRoutineName);

        sType = (Spinner) findViewById(R.id.sRoutineType);

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

    }

    /**
     * fill UI views from the workout's values
     */
    private void fillUIFromRoutine() {
        etRoutineName.setText(routine.getTitle());
       // sType.setSelection(routine.getType().getValue() - 1);
    }


    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Set upa the viewpager and the fragments inside each tab
     */
    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.routineViewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //for every day of the week (for every tab)
        //add a FragmentRoutineDay , passing the workouts for that day
        for (int i = 0; i < EnumWeekDays.values().length; i++) {
            FragmentRoutineDay fragment = FragmentRoutineDay.newInstance(i, routine.getWorkouts().get(i));
            adapter.addFragment(fragment, EnumWeekDays.values()[i].toString());
        }
        viewPager.setOffscreenPageLimit(7);
        viewPager.setAdapter(adapter);

        //Create the tabs
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //just call super , so that the onActivityResult of the FragmentRoutineDay is called

        adapter.fragmentList.get(tabLayout.getSelectedTabPosition()).onActivityResult(requestCode, resultCode, data);
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
            database.deleteRoutine(routine.getId());
            finish();
        } else if (id == R.id.action_save) {
            //Create a hashmap with a list of workouts for each day of the week
            HashMap<Integer, ArrayList<Workout>> newHash = new HashMap<>();
            int days = 0;
            for (int i = 0; i < 7; i++) {
                newHash.put(i, adapter.fragmentList.get(i).getWorkouts());
                if (adapter.fragmentList.get(i).getWorkouts().size() > 0)
                    days++;
            }
            //if there are no workouts selected , alert the user
            if (days == 0) {
                Snackbar.make(etRoutineName, "You should have at least one workout day!", Snackbar.LENGTH_LONG).show();
                return true;
            }
            routine.setWorkouts(newHash);

            routine.setTitle(etRoutineName.getText().toString());
        //    routine.setType(EnumExerciseTypes.values()[sType.getSelectedItemPosition()]);
            routine.setDays(days);

            if (isCreation) {
                database.insert(routine);
                Toast.makeText(EditRoutineActivity.this, "Routine Created !", Toast.LENGTH_LONG).show();
            } else {
                database.update(routine);
                Toast.makeText(EditRoutineActivity.this, "Routine Updated !", Toast.LENGTH_LONG).show();
            }
            finish();
        }

        return true;
    }


    /**
     * The adapter used by the viewpager
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<FragmentRoutineDay> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public FragmentRoutineDay getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        /**
         * add a fragment to the view pager list
         *
         * @param fragment the fragment to add
         * @param title    the title of the tab
         */
        public void addFragment(FragmentRoutineDay fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
