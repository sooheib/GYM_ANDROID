package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailScheduleActivity;
import selmibenromdhane.sparta_v1.activity.TrainersActivity;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.manager.Session;
import selmibenromdhane.sparta_v1.manager.Trainer;
import selmibenromdhane.sparta_v1.utils.MySingleton;

import static android.R.attr.id;

/**
 * Created by sooheib on 11/28/16.
 */

public class TrainersAdapter extends BaseFlipAdapter<Trainer> {

    private final int PAGES = 3;
    private int[] IDS_INTEREST={R.id.interest_1, R.id.interest_2, R.id.interest_3,R.id.interest_4}
            ;
    Context context;
    private RequestQueue requestQueue;

    List<Integer> trainerlist = new ArrayList<Integer>(Arrays.asList(0));

    int trainer_idfirst = 0;
    int trainer_idsecond = 0;

    List<Trainer> items;

    String jsonData;


    ArrayList<String> courses = new ArrayList<>();


    public TrainersAdapter(Context context, List<Trainer> items, FlipSettings settings) {
        super(context, items, settings);
        this.context = context;
        this.items=items;

    }

    @Override
    public View getPage(int position, View convertView, ViewGroup parent, Trainer item1, Trainer item2) {
        final TrainersHolder holder;
        Trainer trainer = getItem(position);
        trainer_idfirst = item1.getEmployee_id();

        trainer_idsecond = item2.getEmployee_id();


        if (convertView == null) {
            holder = new TrainersHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(TrainersActivity.LAYOUT_INFLATER_SERVICE);


            //  convertView = mInflater.inflate(R.layout.trainer_merge_page,null);
           // convertView.setOnClickListener(null);
          //  convertView.setEnabled(false);
            convertView=mInflater.inflate(R.layout.trainer_merge_page, parent, false);
            holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);

            holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);

            holder.infoPage = mInflater.inflate(R.layout.trainers_info, parent, false);



            holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);

            holder.nickName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("gogogo");
                }
            });

            //  holder.interests= (List<TextView>) holder.infoPage.findViewById(R.id.interest_1);

            for (int id : IDS_INTEREST)
              holder.interests.add((TextView) holder.infoPage.findViewById(id));

            convertView.setTag(holder);
        } else {
            holder = (TrainersHolder) convertView.getTag();
        }

        //  System.out.println("first"+trainer_idfirst);

        switch (position) {
            // Merged page with 2 friends
            case 1:
                //  Bitmap photoTrainer1 = urltoBitmap1.getBitmapFromURL(item1.getPhoto());

                Picasso.with(context).load(item1.getPhoto()).resize(500,500).into(holder.leftAvatar);

                //  System.out.println("waw1");

                if (item2 != null) {
                    //  Bitmap photoTrainer2 = urltoBitmap2.getBitmapFromURL(item2.getPhoto());

                    // holder.leftAvatar.setImageDrawable(getResources().getDrawable(R.drawable.anastasia));

                    Picasso.with(context).load(item2.getPhoto()).resize(500,500).into(holder.rightAvatar);



                    //GridClient.loadPaintingImage(holder.rightAvatar, trainer);


                    // holder.rightAvatar.setImageBitmap(photoTrainer2);

                    //  System.out.println("waw2");
                }
                break;
            default:
                fillHolder(holder, position == 0 ? item1 : item2);
                holder.nickName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("gogogo");
                    }
                });
                holder.infoPage.setTag(holder);


                return holder.infoPage;


        }

        return convertView;
    }

    public boolean exist(List<Integer> list, int trainer) {

        for (int i = 0; i < list.size(); i++) {
            if (trainer_idfirst == list.get(i)) {
                return true;
            }
        }


        trainerlist.add(trainer);
        return false;
    }


    @Override
    public int getPagesCount() {
        return PAGES;
    }

    private void fillHolder(final TrainersHolder holder, Trainer friend) {
        if (friend == null)
            return;
        Iterator<TextView> iViews = holder.interests.iterator();
        Iterator<String> iInterests = friend.getInterests().iterator();
        while (iViews.hasNext() && iInterests.hasNext())
            iViews.next().setText(iInterests.next());
        //holder.infoPage.setBackgroundColor(0x00CCFF33);
        holder.nickName.setText(friend.getFirst_name()+" "+friend.getLast_name());
        // holder.interests.get(0).setText("ssssss");
        // for (int id : IDS_INTEREST)

        String trainer_id = String.valueOf(friend.getEmployee_id());
        System.out.println("trainerrrrrrrr" + trainer_id);

        abcc(trainer_id, holder);


        System.out.println("size+" + holder.interests.size());
        //holder.interests.size();

        for (int i = 0; i < holder.interests.size(); i++) {

            final String sport = (String) holder.interests.get(i).getText();

            holder.interests.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("sssssssssssssssss");
                }
            });



            holder.interests.get(i).setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    v.setEnabled(false);
                    v.setOnClickListener(null);


                    Toast.makeText(context, "bonjour", Toast.LENGTH_LONG).show();
                    System.out.println("sport" + sport);
                }
            });

            //   holder.interests.get(i).setOnClickListener((View.OnClickListener) this);

            holder.interests.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("sssssssssssssss");
                }
            });

        }





    }

    public void abcc(final String trainer_id, final TrainersHolder holder) {
        JsonArrayRequest arrayreq = new JsonArrayRequest(AppConfig.URL_SCHEDULE, new Response.Listener<JSONArray>() {

            // Takes the response from the JSON request
            @Override
            public void onResponse(JSONArray response) {
                try {

                    //   JSONObject scheduleObj = response.getJSONObject(0);

                    // JSONArray scheduleArry = scheduleObj.getJSONArray("0");


                    courses.clear();
                    int j = 0;
                    for (int i = 0; i < response.length(); i++) {


                        JSONObject scheduleObj = response.getJSONObject(i);
                        // Retrieves the string labeled "colorName" and "hexValue",
                        // and converts them into javascript objects

//                                String course = scheduleObj.getString("3");
//                                courses.add(course);
//                                System.out.println("coursesxx"+course);
//
                        String trainerid = scheduleObj.getString("13");
                        System.out.println("gdim id=" + trainer_id + "" + "trainer id=" + scheduleObj.getString("13"));
                        if (trainer_id.equalsIgnoreCase(trainerid)) {


                            System.out.println("trainerxx" + trainerid);

                            String course = scheduleObj.getString("3");
                            //courses.add(course);
                            System.out.println("coursesxx" + course);
                            holder.interests.get(j).setText(course);

                            j = j + 1;
                        } else {
                            // holder.interests.get(j).setText("");

                        }
                    }
                    // System.out.println("sizee"+courses.size());

//        for (int j=0;j<courses.size();j++){
//            System.out.println("howhow"+courses.get(j));
//            holder.interests.get(j).setText(courses.get(j));
//        }
                    // holder.interests.get(0).setText("abc");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON array request "arrayreq" to the request queue
//        requestQueue.add(arrayreq);

        MySingleton.getinstance(context).addToRequest(arrayreq);


        //  System.out.println("cawcaw"+courses.size());

        // return courses;
    }



}
