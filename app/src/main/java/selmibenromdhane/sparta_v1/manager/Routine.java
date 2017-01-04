package selmibenromdhane.sparta_v1.manager;

import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import selmibenromdhane.sparta_v1.Enumeration.EnumExerciseTypes;
import selmibenromdhane.sparta_v1.Enumeration.EnumMuscleGroups;
import selmibenromdhane.sparta_v1.Enumeration.EnumWeekDays;


public class Routine implements Serializable {

    private String title;
    private int id, days;
    private EnumExerciseTypes type;
    private EnumMuscleGroups muscle;
    private HashMap<Integer, ArrayList<Workout>> workouts;

    public Routine() {
        //create an empty hashmap in order to be populated
        workouts = new HashMap<>();
        for (int i = 0; i < EnumWeekDays.values().length; i++) {
            workouts.put(i, new ArrayList<Workout>());
        }
    }

    public Routine(String title, EnumExerciseTypes type, int days) {
        this.title = title;
        this.type = type;
        this.days = days;
    }

    public Routine(Cursor c, HashMap<Integer, ArrayList<Workout>> workouts) {
        id = c.getInt(0);
        title = c.getString(1);
        days = c.getInt(2);
        type = EnumExerciseTypes.valueOf(c.getInt(3));

        this.workouts = workouts;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumExerciseTypes getType() {
        return type;
    }

    public void setType(EnumExerciseTypes type) {
        this.type = type;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public EnumMuscleGroups getMuscle() {
        return muscle;
    }

    public void setMuscle(EnumMuscleGroups muscle) {
        this.muscle = muscle;
    }

    public HashMap<Integer, ArrayList<Workout>> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(HashMap<Integer, ArrayList<Workout>> workouts) {
        this.workouts = workouts;
    }
}
