package selmibenromdhane.sparta_v1.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.alexvasilkov.foldablelayout.shading.GlanceFoldShading;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.manager.Event;
import selmibenromdhane.sparta_v1.parser.EventDownloader;
import selmibenromdhane.sparta_v1.utils.GridClient;
import selmibenromdhane.sparta_v1.utils.MySingleton;

public class EventsActivity extends BaseActivity1 {

    private View listTouchInterceptor;
    private View detailsLayout;
    private UnfoldableView unfoldableView;


    public int eventMax;
    public int countMember;
    String clientID;
    String eventID="";
    String urlbooking= AppConfig.URL_RESEREVENT;
    String urldeletebooking=AppConfig.URL_RESEREVENTDELETE;
    String urlcheck=AppConfig.URL_CHEC_RESERV;

    LoginActivity loginActivity=new LoginActivity();

    Configuration croutonConfiguration = new Configuration.Builder().setDuration(2000).build();
    Style style = new Style.Builder()
            .setBackgroundColorValue(Color.parseColor("#008000"))
            .setGravity(Gravity.CENTER_HORIZONTAL)
            .setConfiguration(croutonConfiguration)
            .setHeight(150)
            .setTextColorValue(Color.parseColor("#1f1f1f")).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView listView = Views.find(this, R.id.list_view);

        new EventDownloader(EventsActivity.this, AppConfig.URL_EVENT,listView).execute();

       // listView.setAdapter(new EventsAdapter(EventsActivity.this,events));
     //  listView.setAdapter(new EventsAdapter(this));
        System.out.println("good1");



        listTouchInterceptor = Views.find(this, R.id.touch_interceptor_view);
        listTouchInterceptor.setClickable(false);

        detailsLayout = Views.find(this, R.id.details_layout);
        detailsLayout.setVisibility(View.INVISIBLE);

        unfoldableView = Views.find(this, R.id.unfoldable_view);

        Bitmap glance = BitmapFactory.decodeResource(getResources(), R.drawable.unfold_glance);
        unfoldableView.setFoldShading(new GlanceFoldShading(glance));

        unfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
                detailsLayout.setVisibility(View.VISIBLE);
                System.out.println("123456");
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                listTouchInterceptor.setClickable(false);
                detailsLayout.setVisibility(View.INVISIBLE);
            }
        });

    }
    @Override
    public void onBackPressed() {

        if (unfoldableView != null
                && (unfoldableView.isUnfolded() || unfoldableView.isUnfolding())) {
            unfoldableView.foldBack();
        }
        else {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
    }



    public void openDetails(View coverView, final Event painting) {
        final ImageView image = Views.find(detailsLayout, R.id.details_image);
        final TextView title = Views.find(detailsLayout, R.id.details_title);
        final TextView description = Views.find(detailsLayout, R.id.details_text);
        final Button bookingevent=Views.find(detailsLayout,R.id.booking);
        final Button cancelbooking=Views.find(detailsLayout,R.id.cancelbooking);


        final Event event=painting;



        System.out.println("sssssssssssssssssssss"+painting.getEvent_maxCapacity());


//        SimpleDateFormat sdftoday = new SimpleDateFormat("dd");
//        String dayd=sdftoday.format(painting.getEvent_startDate());
//        String dayf=sdftoday.format(painting.getEvent_endDate());
//
//        SimpleDateFormat day = new SimpleDateFormat("EEEE");
//
//        String daystart=day.format(painting.setEvent_startDate());

        GridClient.loadPaintingImage(image, painting);
        title.setText(painting.getEvent_name());

        SpannableBuilder builder = new SpannableBuilder(this);
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.date).append(": ")
                .clearStyle()
                .append(painting.getEvent_startDate()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.durationn).append(": ")
                .clearStyle()
                .append(painting.getEvent_endDate()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append(R.string.location).append(": ")
                .clearStyle()
                .append(painting.getEvent_location()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("max Capacity").append(": ")
                .clearStyle()
                .append(String.valueOf(painting.getEvent_maxCapacity()) +" / "+ (String.valueOf(painting.getEvent_maxCapacity()-painting.getEvent_countReserved()))+"available").append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()

                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("Description").append(": ")
                .clearStyle()
                .append(painting.getEvent_description() ).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply();
        description.setText(builder.build());

        clientID =loginActivity.userId;
        eventID=painting.getEvent_id();
        System.out.println("eeeeeeeeeeee"+eventID);

        eventMax =painting.getEvent_maxCapacity();
        countMember=painting.getEvent_countReserved();

        if(countMember>= eventMax)
        {
            Toast.makeText(EventsActivity.this, "Cours complet", Toast.LENGTH_LONG);
            new SweetAlertDialog(EventsActivity.this)
                    .setContentText("COURSE COMPLETE : countMember " + countMember + " maxC " + eventMax)
                    .show();
       cancelbooking.setVisibility(View.INVISIBLE);
     bookingevent.setVisibility(View.INVISIBLE);

        }
        else{

            Crouton.showText((Activity) EventsActivity.this, eventMax - countMember + " available places  ///countMember " + countMember + " maxC " + eventMax, style);
       bookingevent.setVisibility(View.VISIBLE);
            cancelbooking.setVisibility(View.INVISIBLE);

        }



       checkreservation(clientID,eventID,AppConfig.URL_CHECkEVENT,bookingevent,cancelbooking);


        bookingevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(EventsActivity.this);
                builder.setTitle("Booking");
                builder.setMessage("Are you sure ");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        if(countMember<eventMax)
                        {  //diseableButto(true);
                            System.out.println("najmou nzidou");

                            addBooking(clientID,eventID ,urlbooking);

                            cancelbooking.setVisibility(View.VISIBLE);
                            bookingevent.setVisibility(View.INVISIBLE);

                            AlertDialog.Builder builder1=new AlertDialog.Builder(EventsActivity.this);
                            builder1.setTitle("Booking");
                            builder1.setMessage("successfully booking");
                            builder1.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder1.show();
                        }
                        else
                        {
                            System.out.println("manajmouch nzidou");
                            Toast.makeText(EventsActivity.this,"Course Not Available",Toast.LENGTH_LONG);

                            AlertDialog.Builder builder2=new AlertDialog.Builder(EventsActivity.this);
                            builder2.setTitle("Booking");
                            builder2.setMessage("Class is full ");
                            builder2.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder2.show();
                        }
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        arg0.cancel();

                    }
                });
                builder.show();
            }
        });


        cancelbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(EventsActivity.this);
                builder.setTitle("Booking");
                builder.setMessage("DELETE ? ");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //deleteBooking(clientID);

                        deleteBooking(clientID,eventID,urldeletebooking);

                        //buttonBooking.setText("Cancel");
                        //buttonCancel.setVisibility(View.GONE);
                        //
                        cancelbooking.setVisibility(View.INVISIBLE);

                        bookingevent.setVisibility(View.VISIBLE);

                        // buttonBooking.setVisibility(View.VISIBLE);
//                Button buttoncancel= (Button) findViewById(R.id.bookingcancel);
//                buttoncancel.setVisibility(View.VISIBLE);

                        //  bookcancel.setVisibility(View.VISIBLE);

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        arg0.cancel();

                    }
                });
                builder.show();            }
        });




        unfoldableView.unfold(coverView, detailsLayout);
    }


    public void addBooking(final String userID,final String eventID ,String urlbooking)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlbooking,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String code= jsonObject.getString("code");
                            if(code.equals("failed"))
                            {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                // test=0;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                //test=1;


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"errooooor",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("eventID",String.valueOf(eventID));
                return params;
            }
        };
        MySingleton.getinstance(EventsActivity.this).addToRequest(stringRequest);
    }

    public void deleteBooking(final String userID,final String eventID ,String urldeletebooking)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urldeletebooking,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String code= jsonObject.getString("code");
                            if(code.equals("failed"))
                            {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                // test=0;
                            }
                            else {
                                Toast.makeText(getApplicationContext(),code, Toast.LENGTH_LONG).show();
                                //test=1;


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"errooooor",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("eventID",String.valueOf(eventID));
                return params;
            }
        };
        MySingleton.getinstance(EventsActivity.this).addToRequest(stringRequest);
    }


    public void checkreservation(final String userID, final String eventID , String url, final Button b1, final Button b2){

        int i=0;
        // Creating volley request obj
        JsonArrayRequest checkReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("check", response.toString());
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);


                                //String res_id=obj.getString("0");
                                String clie_id=obj.getString("0");
                                System.out.println("c****"+clie_id);
                                String sched_id=obj.getString("1");
                                System.out.println("s****"+sched_id);

                                if(userID.equalsIgnoreCase(clie_id)&&eventID.equalsIgnoreCase(sched_id)){
                                    System.out.println("hahahahahahaha"+sched_id+" "+clie_id);
                                    Toast.makeText(EventsActivity.this,"you just reserved", Toast.LENGTH_LONG).show();
                                    Crouton.showText((Activity) EventsActivity.this,"you just reserved",style);


                                    b1.setVisibility(View.INVISIBLE);

                                    b2.setVisibility(View.VISIBLE);
                                    //cancelbooking.setVisibility(View.VISIBLE);
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("check", "Error: " + error.getMessage());

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(checkReq);

    }


}
