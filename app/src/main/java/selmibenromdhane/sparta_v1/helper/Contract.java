package selmibenromdhane.sparta_v1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.enumeration.EnumEquipment;
import selmibenromdhane.sparta_v1.enumeration.EnumMuscleGroups;
import selmibenromdhane.sparta_v1.manager.Exercise;

public class Contract {

    public static final String DATABASE_NAME = "GymWorkoutMate.db";
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_TABLE_ROUTINES =
            "CREATE TABLE IF NOT EXISTS " + Routines.TABLE_NAME + " (" +
                    Routines._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Routines.COLUMN_TITLE + " TEXT NOT NULL," +
                    Routines.COLUMN_DAYS + " INTEGER NOT NULL," +
                    Routines.COLUMN_TYPE + " INTEGER NOT NULL" + //INTEGER BASED ON THE ENUMERATION
                    ")";

    public static final String SQL_CREATE_TABLE_WORKOUTS =
            "CREATE TABLE IF NOT EXISTS " + Workouts.TABLE_NAME + " (" +
                    Workouts._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Workouts.COLUMN_TITLE + " TEXT NOT NULL," +
                    Workouts.COLUMN_MUSCLE + " INTEGER NOT NULL," + //INTEGER BASED ON THE ENUMERATION
                    Workouts.COLUMN_TYPE + " INTEGER NOT NULL" + //INTEGER BASED ON THE ENUMERATION
                    ")";

    public static final String SQL_CREATE_TABLE_EXERCISES =
            "CREATE TABLE IF NOT EXISTS " + Exercises.TABLE_NAME + " (" +
                    Exercises._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Exercises.COLUMN_TITLE + " TEXT NOT NULL," +
                    Exercises.COLUMN_IMAGE1 + " INTEGER NOT NULL," +
                    Exercises.COLUMN_IMAGE2 + " INTEGER NOT NULL," +
                    Exercises.COLUMN_MUSCLE + " INTEGER NOT NULL," + //INTEGER BASED ON THE ENUMERATION
                    Exercises.COLUMN_OTHER_MUSCLES + " TEXT," + //e.g 1-3-4 (INTEGERS BASED ON ENUMERATION)
                    Exercises.COLUMN_MECHANICS + " INTEGER NOT NULL," + //1=COMPOUND , 0=ISOLATION
                    Exercises.COLUMN_EQUIPMENT + " INTEGER NOT NULL" + //INTEGER BASED ON THE ENUMERATION
                    ")";

    public static final String SQL_CREATE_TABLE_EXERCISE_WORKOUTS_CONNECTION =
            "CREATE TABLE IF NOT EXISTS " + ExerciseWorkoutConnection.TABLE_NAME + " (" +
                    ExerciseWorkoutConnection._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ExerciseWorkoutConnection.COLUMN_EXERCISE + " INTEGER NOT NULL," +
                    ExerciseWorkoutConnection.COLUMN_WORKOUT + " INTEGER NOT NULL," +
                    ExerciseWorkoutConnection.COLUMN_NUMSETS + " INTEGER NOT NULL," +
                    ExerciseWorkoutConnection.COLUMN_SETS + " TEXT NOT NULL," +  //e.g "10-10-8-6"
                    ExerciseWorkoutConnection.COLUMN_RESTTIME + " INTEGER NOT NULL" +
                    ")";

    public static final String SQL_CREATE_TABLE_WORKOUTS_ROUTINES_CONNECTION =
            "CREATE TABLE IF NOT EXISTS " + WorkoutRoutineConnection.TABLE_NAME + " (" +
                    WorkoutRoutineConnection._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    WorkoutRoutineConnection.COLUMN_WORKOUT + " INTEGER NOT NULL," +
                    WorkoutRoutineConnection.COLUMN_ROUTINE + " INTEGER NOT NULL," +
                    WorkoutRoutineConnection.COLUMN_DAY + " INTEGER NOT NULL" + //INTEGER BASED ON THE ENUMERATION
                    ")";

    public abstract class Routines implements BaseColumns {
        public static final String TABLE_NAME = "Routines";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DAYS = "days";
        public static final String COLUMN_TYPE = "type";
    }

    public abstract class Workouts implements BaseColumns {
        public static final String TABLE_NAME = "Workouts";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_MUSCLE = "muscle";
    }

    public abstract class Exercises implements BaseColumns {
        public static final String TABLE_NAME = "Exercises";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMAGE1 = "image1";
        public static final String COLUMN_IMAGE2 = "image2";
        public static final String COLUMN_MUSCLE = "muscle";
        public static final String COLUMN_OTHER_MUSCLES = "other_muscles";
        public static final String COLUMN_MECHANICS = "mechanics";
        public static final String COLUMN_EQUIPMENT = "equipment";
    }

    public abstract class ExerciseWorkoutConnection implements BaseColumns {
        public static final String TABLE_NAME = "ExerciseWorkoutCon";
        public static final String COLUMN_EXERCISE = "exerciseId";
        public static final String COLUMN_WORKOUT = "workoutId";
        public static final String COLUMN_NUMSETS = "numsets";
        public static final String COLUMN_SETS = "sets";
        public static final String COLUMN_RESTTIME = "rest";
    }

    public abstract class WorkoutRoutineConnection implements BaseColumns {
        public static final String TABLE_NAME = "WorkoutRoutineCon";
        public static final String COLUMN_WORKOUT = "workoutId";
        public static final String COLUMN_ROUTINE = "routineId";
        public static final String COLUMN_DAY = "day_of_week";
    }

    public static void createExercises(Context context, SQLiteDatabase db) {
        ArrayList<Exercise> initExercises = new ArrayList<>();

        //CHEST
        initExercises.add(new Exercise("Barbell Bench Press1", "barbell_bench2", "barbell_bench_press3"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Barbell Bench Press1", "barbell_bench1", "barbell_bench_press2"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Barbell Incline Bench Press", "barbell_incline_bench_press1", "barbell_incline_bench_press2"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Cable Crossover", "cable_crossover1", "cable_crossover2"
                , EnumMuscleGroups.CHEST, "3", 0, EnumEquipment.CABLE));
        initExercises.add(new Exercise("Dips - Chest Version", "dips_chest1", "dips_chest2"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Dumbbell Flyes", "dumbbell_flyes1", "dumbbell_flyes2"
                , EnumMuscleGroups.CHEST, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Incline Dumbbell Flyes", "incline_dumbbell_flyes1", "incline_dumbbell_flyes2"
                , EnumMuscleGroups.CHEST, "3", 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Dumbbell Bench Press", "dumbbell_bench_press1", "dumbbell_bench_press2"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Incline Dumbbell Bench Press", "incline_dumbbell_press1", "incline_dumbbell_press2"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Push Ups", "pushups1", "pushups2"
                , EnumMuscleGroups.CHEST, "3-5", 1, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Peck Deck", "peck_deck1", "peck_deck2"
                , EnumMuscleGroups.CHEST, null, 0, EnumEquipment.MACHINE));

        //BACK
        initExercises.add(new Exercise("Pull Ups", "pullups1", "pullups2"
                , EnumMuscleGroups.BACK, "4", 1, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Chin Ups", "chinup1", "chinup2"
                , EnumMuscleGroups.BACK, "4", 1, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Barbell Shrug", "barbell_shrug1", "barbell_shrug2"
                , EnumMuscleGroups.BACK, null, 0, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Dumbbell Shrug", "dumbbell_shrug1", "dumbbell_shrug2"
                , EnumMuscleGroups.BACK, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Front Lat Pulldown - Close Grip", "front_lat_pulldown_close1", "front_lat_pulldown_close2"
                , EnumMuscleGroups.BACK, "3-4", 1, EnumEquipment.CABLE));
        initExercises.add(new Exercise("Front Lat Pulldown - Wide Grip", "front_lat_pulldown_wide1", "front_lat_pulldown_wide2"
                , EnumMuscleGroups.BACK, "3-4", 1, EnumEquipment.CABLE));
        initExercises.add(new Exercise("One Arm Dumbbel Row", "one_arm_dumbbel_row1", "one_arm_dumbbel_row2"
                , EnumMuscleGroups.BACK, "3-4", 1, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Seated Cable Row", "seated_cable_rows1", "seated_cable_rows2"
                , EnumMuscleGroups.BACK, "3-4", 1, EnumEquipment.CABLE));
        initExercises.add(new Exercise("Hyperextensions", "hyperextensions1", "hyperextensions2"
                , EnumMuscleGroups.BACK, "6", 0, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Hyperextensions - No Bench", "hyperextensions_nobench1", "hyperextensions_nobench2"
                , EnumMuscleGroups.BACK, "6", 0, EnumEquipment.BODY_ONLY));

        //SHOULDERS
        initExercises.add(new Exercise("Standing Barbell Military Press", "military_press1", "military_press2"
                , EnumMuscleGroups.SHOULDERS, "5", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Seated Barbell Military Press", "seated_barbell_military_press1", "seated_barbell_military_press2"
                , EnumMuscleGroups.SHOULDERS, "5", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Side Lateral Raise", "side_lateral_raise1", "side_lateral_raise2"
                , EnumMuscleGroups.SHOULDERS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Upright Barbell Row", "upright_barbell_row1", "upright_barbell_row2"
                , EnumMuscleGroups.SHOULDERS, "2", 1, EnumEquipment.BARBELL));


        //BICEPS
        initExercises.add(new Exercise("Barbell Biceps Curls", "barbell_curl1", "barbell_curl2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Barbell Biceps Curls - Close Grip", "standing_barbell_curl_close1", "standing_barbell_curl_close2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Barbell Biceps Curls - Wide Grip", "standing_barbell_curl_wide1", "standing_barbell_curl_wide2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("EZ-Bar Biceps Curls", "ezbar_curl1", "ezbar_curl2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Dumbbell Biceps Curls", "dumbbell_bicep_curl1", "dumbbell_bicep_curl2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Alternate Dumbbell Biceps Curls", "dumbbell_alternate_bicep_curl1", "dumbbell_alternate_bicep_curl2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Hammer Curls", "hammer_curls1", "hammer_curls2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Incline Dumbbell Curls", "incline_dumbbell_curl1", "incline_dumbbell_curl2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Incline Dumbbell Hammer Curls", "incline_hammer_curls1", "incline_hammer_curls2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Concentration Curls", "concentration_curls1", "concentration_curls2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.DUMBBELL));
        initExercises.add(new Exercise("Preacher Curls", "preacher_curl1", "preacher_curl2"
                , EnumMuscleGroups.BICEPS, null, 0, EnumEquipment.BARBELL));

        //TRICEPS
        initExercises.add(new Exercise("Bench Dips", "bench_dips1", "bench_dips2"
                , EnumMuscleGroups.TRICEPS, "1-3", 1, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Barbell Bench Press - Close Grip", "barbell_bench_press_close1", "barbell_bench_press_close1"
                , EnumMuscleGroups.TRICEPS, "1-3", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("EZ-Bar Skullcrusher", "ezbar_skullcrusher1", "ezbar_skullcrusher2"
                , EnumMuscleGroups.TRICEPS, null, 0, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Triceps Pushdown - Rope", "triceps_pushdown_rope1", "triceps_pushdown_rope2"
                , EnumMuscleGroups.TRICEPS, null, 0, EnumEquipment.CABLE));
        initExercises.add(new Exercise("Triceps Pushdown - V-Bar", "triceps_pushdown_vbar1", "triceps_pushdown_vbar2"
                , EnumMuscleGroups.TRICEPS, null, 0, EnumEquipment.CABLE));

        //LEGS
        initExercises.add(new Exercise("Full Squats", "full_squat1", "full_squat1"
                , EnumMuscleGroups.LEGS, "2", 1, EnumEquipment.BARBELL));
        initExercises.add(new Exercise("Leg Press", "leg_press2", "leg_press2"
                , EnumMuscleGroups.LEGS, null, 1, EnumEquipment.MACHINE));
        initExercises.add(new Exercise("Seated Leg Press", "seated_leg_press2", "seated_leg_press2"
                , EnumMuscleGroups.LEGS, null, 1, EnumEquipment.MACHINE));
        initExercises.add(new Exercise("Leg Extensions", "leg_extensions1", "leg_extensions2"
                , EnumMuscleGroups.LEGS, null, 0, EnumEquipment.MACHINE));
        initExercises.add(new Exercise("Lying Leg Curls", "lying_leg_curls1", "lying_leg_curls2"
                , EnumMuscleGroups.LEGS, null, 0, EnumEquipment.MACHINE));
        initExercises.add(new Exercise("Seated Leg Curls", "seated_leg_curls1", "seated_leg_curls2"
                , EnumMuscleGroups.LEGS, null, 0, EnumEquipment.MACHINE));
        initExercises.add(new Exercise("Standing Calf Raise", "standing_calf_raises1", "standing_calf_raises2"
                , EnumMuscleGroups.LEGS, null, 0, EnumEquipment.MACHINE));
        initExercises.add(new Exercise("Seated Calf Raise", "seated_calf_raise1", "seated_calf_raise2"
                , EnumMuscleGroups.LEGS, null, 0, EnumEquipment.MACHINE));

        //ABS
        initExercises.add(new Exercise("Decline Crunches", "decline_crunch1", "decline_crunch1"
                , EnumMuscleGroups.ABS, null, 0, EnumEquipment.BODY_ONLY));
        initExercises.add(new Exercise("Crunches", "crunches1", "crunches2"
                , EnumMuscleGroups.ABS, null, 0, EnumEquipment.BODY_ONLY));

        for (Exercise item : initExercises) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Contract.Exercises.COLUMN_TITLE, item.getTitle());
            contentValues.put(Contract.Exercises.COLUMN_IMAGE1, item.getImg1());
            contentValues.put(Contract.Exercises.COLUMN_IMAGE2, item.getImg2());
            contentValues.put(Contract.Exercises.COLUMN_MUSCLE, item.getMuscle().getValue());
            contentValues.put(Contract.Exercises.COLUMN_OTHER_MUSCLES, item.getOther_muscles());
            contentValues.put(Contract.Exercises.COLUMN_MECHANICS, item.getMechanics());
            contentValues.put(Exercises.COLUMN_EQUIPMENT, item.getEquipment().getValue());

            db.insert(Contract.Exercises.TABLE_NAME, "null", contentValues);
        }
    }
}
