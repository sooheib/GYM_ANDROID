package selmibenromdhane.sparta_v1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by sooheib on 11/15/16.
 */

public class SQLiteCourseHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteUserHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    //private static final String DATABASE_NAME = "sql7143415";
    private static final String DATABASE_NAME = "SPARTA";


    // Login table name
    private static final String TABLE_COURSE = "course";

    // Login Table Columns names
    private static final String KEY_COURSE = "course_crn";
    private static final String KEY_COURSEDESC = "course_desc";
    private static final String KEY_COURSECODE = "course_code";
    private static final String KEY_COURSECOVER = "course_cover";
    private static final String KEY_COURSEMAXC = "course_maxCapacity";

    public SQLiteCourseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + KEY_COURSE + " INTEGER PRIMARY KEY," + KEY_COURSEDESC + " TEXT,"
                + KEY_COURSECODE + " TEXT ," + KEY_COURSECOVER + " TEXT,"
                + KEY_COURSEMAXC + " INTEGER" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addCourse(String coursedesc, String coursecode, String coursecover,int coursemaxC) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSEDESC, coursedesc); // Desc
        values.put(KEY_COURSECODE, coursecode); // Code
        values.put(KEY_COURSECOVER, coursecover); // Cover
        values.put(KEY_COURSEMAXC, coursemaxC); // Max

        // Inserting Row
        long id = db.insert(TABLE_COURSE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New course inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getCourseDetails() {
        HashMap<String, String> course = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            course.put("course_desc", cursor.getString(1));
            course.put("course_desc", cursor.getString(2));
            course.put("course_cover", cursor.getString(3));
            course.put("course_maxCapacity", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // return course
        Log.d(TAG, "Fetching course from Sqlite: " + course.toString());

        return course;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteCourses() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_COURSE, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }

}
