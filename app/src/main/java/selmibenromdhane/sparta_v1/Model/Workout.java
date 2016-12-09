package selmibenromdhane.sparta_v1.Model;

import android.database.Cursor;


import java.io.Serializable;
import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Enumeration.EnumExerciseTypes;
import selmibenromdhane.sparta_v1.Enumeration.EnumMuscleGroups;


public class Workout implements Serializable {

    private int id;
    private String title;
    private EnumExerciseTypes type;
    private EnumMuscleGroups muscle;
    private ArrayList<Exercise> exercises;

    /**
     * Default constructor
     */
    public Workout() {
        exercises = new ArrayList<>();
    }

    /**
     * Constructor for workout with a list of workouts
     *
     * @param cursor    the cursor from the db
     * @param exercises the list of exercises
     */
    public Workout(Cursor cursor, ArrayList<Exercise> exercises) {
        this(cursor);
        this.exercises = exercises;
    }

    /**
     * Constructor for workout from cursor
     *
     * @param cursor the cursor from the db
     */
    public Workout(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.title = cursor.getString(1);
        this.muscle = EnumMuscleGroups.valueOf(cursor.getInt(2));
        this.type = EnumExerciseTypes.valueOf(cursor.getInt(3));

        this.exercises = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnumExerciseTypes getType() {
        return type;
    }

    public void setType(EnumExerciseTypes type) {
        this.type = type;
    }

    public EnumMuscleGroups getMuscle() {
        return muscle;
    }

    public void setMuscle(EnumMuscleGroups muscle) {
        this.muscle = muscle;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises.clear();
        this.exercises.addAll(exercises);
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }
}
