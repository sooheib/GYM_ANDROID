package selmibenromdhane.sparta_v1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by sooheib on 11/7/16.
 */

public class SQLieClassesHandler extends SQLiteOpenHelper {
    private String TAG = this.getClass().getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    //private static final String DATABASE_NAME = "sql7143415";
    private static final String DATABASE_NAME = "SPARTA";


    // Login table name
    private static final String TABLE_CLASS = "tbl_users";

    // Login Table Columns names
    private static final String KEY_ID = "userID";
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "userProfession";

    public SQLieClassesHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_CLASS+ "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"+")";
        db.execSQL(CREATE_CLASS_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addClass(String username, String profession) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, username); // Name
        values.put(KEY_EMAIL, profession); // Profession

        // Inserting Row
        long id = db.insert(TABLE_CLASS, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getClassDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CLASS;
       // ArrayList<Class> classes = new ArrayList<Class>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("userName", cursor.getString(1));
            user.put("userProfession", cursor.getString(2));
           // Classes cla=new Classes();
          //  cla.getCourse_desc();
            //classes.add(cla);
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteClass() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_CLASS, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }
}
