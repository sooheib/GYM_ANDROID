package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.helper.SQLiteUserHandler;
import selmibenromdhane.sparta_v1.helper.SessionManager;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;

import static android.R.attr.bitmap;

public class DetailScheduleActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3,tv4,tv5,tv6;

    String description="";
    String course = "";
    String trainer = "";
    String hour = "";
    String cover="";
    String photo="";
    String room="";
    String day="";
    int scheduleID;
    String userID;
    String maxC;
    private SessionManager session;
    private SQLiteUserHandler db;

    private static final String TAG = DetailScheduleActivity.class.getSimpleName();

    LoginActivity loginActivity=new LoginActivity();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ImageView circleImageView= (ImageView) findViewById(R.id.trainerphoto);
        tv1 = (TextView) findViewById(R.id.trainer);
        tv2 = (TextView) findViewById(R.id.hour);
        tv3 = (TextView) findViewById(R.id.duration);
        tv4= (TextView) findViewById(R.id.room);
        tv5= (TextView) findViewById(R.id.description);
//
        tv6= (TextView) findViewById(R.id.day);
//        session = new SessionManager(getApplicationContext());
//        db = new SQLiteUserHandler(getApplicationContext());
//
//        db.getUserDetails();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.toolbar_layout);


        Intent intent = getIntent();
        if (null != intent) {

            ///


            course = intent.getStringExtra(ScheduleParser.COURSE_EXTRA);
            trainer = intent.getStringExtra(ScheduleParser.TRAINER_EXTRA);
            hour = intent.getStringExtra(ScheduleParser.SCHEDULE_HOUR);
//            scheduleID=Integer.parseInt(intent.getStringExtra(ScheduleParser.SCHEDULE_DATE));
           // userID=loginActivity.getUserID();
            cover=intent.getStringExtra(ScheduleParser.COURSE_COVER);
            room=intent.getStringExtra(ScheduleParser.ROOM_NUMBER);
            photo=intent.getStringExtra(ScheduleParser.TRAINER_PHOTO);
            description=intent.getStringExtra(ScheduleParser.COURSE_DESC);
            day=intent.getStringExtra(ScheduleParser.SCHEDULE_DATE);
            maxC=intent.getStringExtra(ScheduleParser.COURSE_CAPACITY);

            ImageView toolbarImage= (ImageView) findViewById(R.id.toolbar);

            URL url = null;
            try {
                url = new URL(cover);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setDoInput(true);
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream input = null;
            try {
                input = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Drawable drawable = new BitmapDrawable(getResources(), myBitmap);
           appBarLayout.setBackground(drawable);



            URL url1 = null;
            try {
                url1 = new URL(photo);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection1 = null;
            try {
                connection1 = (HttpURLConnection) url1.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection1.setDoInput(true);
            try {
                connection1.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream input1 = null;
            try {
                input1 = connection1.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }



            Bitmap bitmap1 = BitmapFactory.decodeStream(input1);

            circleImageView.setImageBitmap(getCircleBitmap(bitmap1));

          //  Drawable drawable1 = new BitmapDrawable(getResources(),bitmap1);


            //circleImageView.setBackground(drawable1);




        }


        appBarLayout.setTitle(course);


        System.out.println("desc: "+description);
        System.out.println("course: "+cover);
        System.out.println("photo: "+photo);
        System.out.println("room: "+room);
        System.out.println("day:"  +day);
        System.out.println("max:"  +maxC);


        TextView tv1 = (TextView) findViewById(R.id.trainer);
        tv1.setText(trainer);

        TextView tv2 = (TextView) findViewById(R.id.hour);
        tv2.setText(hour);

        TextView tv3 = (TextView) findViewById(R.id.duration);
//        tv3.setText("60min");
        TextView tv4= (TextView) findViewById(R.id.room);
        tv4.setText(room);
        tv5.setText(description);
        tv6.setText(day);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.
//            Bundle arguments = new Bundle();
//            arguments.putString(ScheduleDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(ScheduleDetailFragment.ARG_ITEM_ID));
//            ScheduleDetailFragment fragment = new ScheduleDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.scheduleDetail, fragment)
//                    .commit();
        }
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
////         handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;}

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    public void onBackPressed(){
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void Booking(View view) {
        //  Booking1(scheduleID,userID);

    }

//    private void Booking1(final int scheduleID, final String userID  ) {
//        // Tag used to cancel the request
//        String tag_string_req = "req_booking";
//
//       // pDialog.setMessage("Registering ...");
//       // showDialog();
//
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                AppConfig.URL_REGISTER, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "Register Response: " + response.toString());
//             //   hideDialog();
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    boolean error = jObj.getBoolean("error");
//                    if (!error) {
//                        // User successfully stored in MySQL
//                        // Now store the user in sqlite
//                        String reservationid = jObj.getString("reservation_id");
//
//                        JSONObject reservation = jObj.getJSONObject("reservation_t");
//                        String scheduleidd =reservation.getString("schedule_id");
//                        String useridd = reservation.getString("user_id");
//                        String created_at = reservation
//                                .getString("created_at");
//
//                        // Inserting row in users table
//                        //db.addUser(name, email, uid, created_at);
//
//                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();
//
//                        // Launch login activity
////                        Intent intent = new Intent(
////                                ScheduleOwnFragment.this,
////                                LoginActivity.class);
////                        startActivity(intent);
////                        finish();
//                    } else {
//
//                        // Error occurred in registration. Get the error
//                        // message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Registration Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();
//            //    hideDialog();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting params to register url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("schedule_id", scheduleID);
//                params.put("user_id", userID);
//              //  params.put("password", password);
//
//                return params;
//            }
//
//        };
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
//    }
}

