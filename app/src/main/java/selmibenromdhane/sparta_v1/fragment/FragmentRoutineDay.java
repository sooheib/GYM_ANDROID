package selmibenromdhane.sparta_v1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Model.Workout;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.WorkoutsActivity;

/**
 * A fragmentWorkouts representing a day of the week inside a weekly routine.
 * Contains the workouts for this day
 */
public class FragmentRoutineDay extends Fragment implements View.OnClickListener {

    public static final String ARG_POS = "ARG_POS";
    public static final String ARG_WORKOUTS = "ARG_WORKOUTS";

    private FragmentWorkouts fragmentWorkouts;
    private FragmentTransaction transaction;

    private View placeholder;

    //The view for the entire fragmentWorkouts
    private View fragmentView;

    private int position;

    private ArrayList<Workout> workouts;

    /**
     * Create a new instance of FragmentRoutineDay
     *
     * @param pos      the position of the fragmentWorkouts in the view pager
     * @param workouts a list of workouts that are included in this day of the week
     * @return the fragmentWorkouts
     */
    public static FragmentRoutineDay newInstance(int pos, ArrayList<Workout> workouts) {
        Bundle args = new Bundle();
        args.putInt(ARG_POS, pos);
        args.putSerializable(ARG_WORKOUTS, workouts);
        FragmentRoutineDay fragment = new FragmentRoutineDay();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentRoutineDay() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POS);
        workouts = (ArrayList<Workout>) getArguments().getSerializable(ARG_WORKOUTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //if the view of the fragmentWorkouts is null , it is not initialized yet , so initialize
        if (fragmentView == null) {
            fragmentView = inflater.inflate(R.layout.fragment_day_view, container, false);

            placeholder = fragmentView.findViewById(R.id.placeholder);

            //on click of place holder , launch workouts activity for the user to add workouts
            placeholder.setOnClickListener(this);

        }

        return fragmentView;
    }


    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    @Override
    public void onResume() {
        super.onResume();

        //because onResume is called after onActivityResult , the fragment with the workouts selected
        //is created after they are returned from the workouts activity intent
        //(if this is done in onCreateView , the new workouts will not have been returned by then so the fragment will not be created then
        // and will be created when this tab is scrolled off the screen so that the on create view is recalled
        // with the new workouts this time)

        //if there are no workouts  , show the placeholder view (rest day view)
        if (workouts.size() == 0) {
            placeholder.setVisibility(View.VISIBLE);
            if (fragmentWorkouts != null) {
                transaction = getChildFragmentManager().beginTransaction();
                transaction.remove(fragmentWorkouts);
                transaction.commit();
            }
        } else {
            //else there are some workouts so must add a workouts fragmentWorkouts
            placeholder.setVisibility(View.GONE);

            transaction = getChildFragmentManager().beginTransaction();
            //create a new instance of workouts fragmentWorkouts containing the workouts of this day
            fragmentWorkouts = FragmentWorkouts.newInstance(workouts, false, true, WorkoutsActivity.class);
            transaction.replace(R.id.container, fragmentWorkouts);
            transaction.commit();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //request code is 100 , for activity workout list , for selecting workouts to add
        if (requestCode == 100) {
            //if result code is ok , must take the returned list of exercises
            if (resultCode == getActivity().RESULT_OK) {
                //the workouts returned by the workouts activity , after user has selected some workouts
                ArrayList<Workout> returnedWorkout = (ArrayList<Workout>) data.getSerializableExtra("workouts");
                //the list of workouts that were already in this day before the user added some more
                ArrayList<Workout> currentWorkouts = workouts;

                //At first , must delete the exercise that were unchecked
                //thus , they were present in the list and they are not now
                boolean found;
                //So , if an exercise is in the current(the old) list and not in the returned list
                //delete it
                for (int i = 0; i < currentWorkouts.size(); i++) {

                    found = false;
                    for (Workout w : returnedWorkout) {

                        if (currentWorkouts.get(i).getId() == w.getId())
                            found = true;
                    }
                    if (!found) {
                        currentWorkouts.remove(i);
                        i--;
                    }
                }

                //Then , must add the exercises that are newly checked , thus , are not in the
                //current(old) list but are in the returned list
                for (Workout workout : returnedWorkout) {

                    found = false;
                    for (Workout w : currentWorkouts) {
                        if (workout.getId() == w.getId())
                            found = true;
                    }
                    if (!found) {
                        currentWorkouts.add(workout);
                    }
                }

            }
        }
    }


    @Override
    public void onClick(View v) {

        //Launch the workouts activity
        Intent intent = new Intent(getContext(), WorkoutsActivity.class);

        //and pass the ids of the workouts that are already already in this day
        //so that they will be already checked
        ArrayList<Integer> ids = new ArrayList<>();
        for (Workout w : workouts) {
            ids.add(w.getId());
        }
        intent.putIntegerArrayListExtra("ids", ids);

        startActivityForResult(intent, 100);
    }
}
