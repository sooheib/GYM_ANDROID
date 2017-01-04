package selmibenromdhane.sparta_v1.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailScheduleActivity1;
import selmibenromdhane.sparta_v1.activity.LoginActivity;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;
import selmibenromdhane.sparta_v1.utils.CircleTransform;
import selmibenromdhane.sparta_v1.utils.MySingleton;

import static android.content.Context.MODE_PRIVATE;

public class ScheduleAboutFragment extends Fragment {
ScheduleParser scheduleParser=new ScheduleParser();
    SharedPreferences sh;
    SharedPreferences.Editor ed;
    public String day;
    public String time;
    public String room;
    public String description;
    public String trainer;
    public String photo;
    public int courseMax;
    public int countMember;
    String clientID,scheduleID;
    String urlbooking= AppConfig.URL_RESERV;
    String urldeletebooking=AppConfig.URL_DELETERESERVE;
    LoginActivity loginActivity=new LoginActivity();
    Button booking;
    Button cancelbooking;

    private String dejaInscri="";

    Configuration croutonConfiguration = new Configuration.Builder().setDuration(2000).build();
    Style style = new Style.Builder()
            .setBackgroundColorValue(Color.parseColor("#008000"))
            .setGravity(Gravity.CENTER_HORIZONTAL)
            .setConfiguration(croutonConfiguration)
            .setHeight(150)
            .setTextColorValue(Color.parseColor("#1f1f1f")).build();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_schedule_about, null);
        TextView trainerText= (TextView) view.findViewById(R.id.trainer);
        ImageView trainerPhoto= (ImageView) view.findViewById(R.id.trainerphoto);
        TextView d3= (TextView) view.findViewById(R.id.description3);
        TextView d1= (TextView) view.findViewById(R.id.description1);
        TextView d2= (TextView) view.findViewById(R.id.description2);
        TextView d4= (TextView) view.findViewById(R.id.description4);
        TextView desc= (TextView) view.findViewById(R.id.descriptioxn);
        final Button booking= (Button) view.findViewById(R.id.booking);
        final Button cancelbooking=(Button)view.findViewById(R.id.bookingcancel);

        sh = this.getActivity().getSharedPreferences("report",MODE_PRIVATE);



        scheduleID=sh.getString("scheduleid","99");
        clientID=loginActivity.userId;

        day=sh.getString("day","99");
        d1.setText(day);

        System.out.println("******"+day);

        time=sh.getString("time","99");
        System.out.println("******"+time);

        d2.setText(time);
        room=sh.getString("room","99");
        System.out.println("******"+room);
        d3.setText(room);
        description=sh.getString("description","99");
        System.out.println("******"+description);
        desc.setText(description);
        trainer=sh.getString("trainer","99");
        System.out.println("******"+trainer);
        trainerText.setText(trainer);
        photo=sh.getString("photo","99");
        System.out.println("******"+photo);

        countMember=sh.getInt("scheduleCount",countMember);
        System.out.println("******"+countMember);

        courseMax=sh.getInt("eventMax",courseMax);
        System.out.println("******"+courseMax);

        int available=courseMax-countMember;

        d4.setText(courseMax+"Clients / "+available+" Available");


        Picasso.with(getActivity().getApplicationContext()).load(photo).
                transform(new CircleTransform()).into(trainerPhoto);



        if(countMember>=courseMax)
        {
            Toast.makeText(getContext(), "Cours complet", Toast.LENGTH_LONG);
            new SweetAlertDialog(getContext())
                    .setContentText("COURSE COMPLETE : countMember " + countMember + " maxC " + courseMax)
                    .show();
            cancelbooking.setVisibility(View.INVISIBLE);
            booking.setVisibility(View.INVISIBLE);

        }
        else{

            Crouton.showText((Activity) getContext(), courseMax - countMember + " available places  ///countMember " + countMember + " maxC " + courseMax, style);
            booking.setVisibility(View.VISIBLE);
            cancelbooking.setVisibility(View.INVISIBLE);

        }


        checkreservation(clientID,scheduleID,AppConfig.URL_CHECk,booking,cancelbooking);


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Booking");
                builder.setMessage("Are you sure ");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        if(countMember<courseMax)
                        {  //diseableButto(true);
                            System.out.println("najmou nzidou");

                            addBooking(clientID,scheduleID ,urlbooking);

                            cancelbooking.setVisibility(View.VISIBLE);
                            booking.setVisibility(View.INVISIBLE);

                            AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
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
                            Toast.makeText(getContext(),"Course Not Available",Toast.LENGTH_LONG);

                            AlertDialog.Builder builder2=new AlertDialog.Builder(getContext());
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
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setTitle("Booking");
                builder.setMessage("DELETE ? ");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //deleteBooking(clientID);

                        deleteBooking(clientID,scheduleID,urldeletebooking);

                        //buttonBooking.setText("Cancel");
                        //buttonCancel.setVisibility(View.GONE);
                        cancelbooking.setVisibility(View.INVISIBLE);

                        booking.setVisibility(View.VISIBLE);

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



        return view;
    }



    public void addBooking(final String userID,final String scheduleID ,String urlbooking)
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
                                Toast.makeText(getContext(),code, Toast.LENGTH_LONG).show();
                                // test=0;
                            }
                            else {
                                Toast.makeText(getContext(),code, Toast.LENGTH_LONG).show();
                                //test=1;


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"errooooor",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("scheduleID",String.valueOf(scheduleID));
                return params;
            }
        };
        MySingleton.getinstance(getContext()).addToRequest(stringRequest);
    }

    public void deleteBooking(final String userID,final String scheduleID ,String urldeletebooking)
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
                                Toast.makeText(getContext(),code, Toast.LENGTH_LONG).show();
                                // test=0;
                            }
                            else {
                                Toast.makeText(getContext(),code, Toast.LENGTH_LONG).show();
                                //test=1;


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"errooooor",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("scheduleID",String.valueOf(scheduleID));
                return params;
            }
        };
        MySingleton.getinstance(getContext()).addToRequest(stringRequest);
    }


    public void checkreservation(final String userID, final String scheduleID , String url, final Button b1, final Button b2){

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

                                if(userID.equalsIgnoreCase(clie_id)&&scheduleID.equalsIgnoreCase(sched_id)){
                                    System.out.println("hahahahahahaha"+sched_id+" "+clie_id);
                                   Toast.makeText(getContext(),"you just reserved", Toast.LENGTH_LONG).show();
                                    Crouton.showText((Activity) getContext(),"you just reserved",style);


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



    public void CheckPlace(final String userID,final String scheduleID ,String url){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String code= jsonObject.getString("code");
                            if (code.equals("-1"))
                            {
                                dejaInscri="true";
                                Toast.makeText(getContext(),"code"+ code, Toast.LENGTH_LONG).show();
                                Crouton.showText((Activity) getContext(),code+"you just reserved",style);

                                booking.setVisibility(View.INVISIBLE);
                                cancelbooking.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                dejaInscri="false";

                                Toast.makeText(getContext(),"Cours not complet",Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(),"code"+ code, Toast.LENGTH_LONG).show();
                                Crouton.showText((Activity) getContext(),courseMax-countMember+ " available places  ///countMember "+ countMember +" maxC "+courseMax,style);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"errooooor",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID",userID);
                params.put("scheduleID",String.valueOf(scheduleID));
                return params;
            }
        };
        MySingleton.getinstance(getContext()).addToRequest(stringRequest);

    }




}
