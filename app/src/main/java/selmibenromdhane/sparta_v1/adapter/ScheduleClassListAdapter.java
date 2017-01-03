package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.manager.Client;
import selmibenromdhane.sparta_v1.manager.Session;
import selmibenromdhane.sparta_v1.utils.CircleTransform;

/**
 * Created by sooheib on 12/22/16.
 */

public class ScheduleClassListAdapter  extends RecyclerView.Adapter<ScheduleClassListHolders> {

    private ArrayList<Session> itemList;
    private Context context;
    public static String DESCRIPTION_EXTRA="course_desc";
    public static String COURSE_EXTRA="course_code";
    public static String IMAGE_EXTRA="course_cover";
    Calendar calendar=Calendar.getInstance();
    ImageView img;

    public ScheduleClassListAdapter(Context context, ArrayList<Session> itemList) {
        this.context = context;
        this.itemList = itemList;
    }


    @Override
    public ScheduleClassListHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notif, null);
        ScheduleClassListHolders rcv = new ScheduleClassListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ScheduleClassListHolders holder, int position) {
        final Session session = itemList.get(position);
        SimpleDateFormat sdftoday = new SimpleDateFormat("EEE");


        String date=session.getStartDate();
        String hour=session.getStartTime();
        //String day=sdftoday.format(date);
         String dateString=date+" at "+hour;

        holder.content.setText(session.getCourse());
        holder.date.setText(dateString);
        Picasso.with(context).load(session.trainer_photo)
                .resize(60, 60)
                .transform(new CircleTransform())
                .into(holder.image);
        setAnimation(holder.itemView, position);
        // view detail message conversation


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                System.out.println("asasa");

            }
        });


        }

    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }



}
