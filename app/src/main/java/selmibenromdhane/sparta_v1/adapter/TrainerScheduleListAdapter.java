package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.manager.Session;
import selmibenromdhane.sparta_v1.manager.Trainer;
import selmibenromdhane.sparta_v1.utils.CircleTransform;

/**
 * Created by sooheib on 12/23/16.
 */

public class TrainerScheduleListAdapter extends RecyclerView.Adapter<TrainerScheduleListHolders> {


    private ArrayList<Trainer> itemList;
    private Context context;
    public static String DESCRIPTION_EXTRA="course_desc";
    public static String COURSE_EXTRA="course_code";
    public static String IMAGE_EXTRA="course_cover";

    public TrainerScheduleListAdapter(Context context, ArrayList<Trainer> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public TrainerScheduleListHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notif, null);
        TrainerScheduleListHolders rcv = new TrainerScheduleListHolders(layoutView);
        return rcv;

    }

    @Override
    public void onBindViewHolder(TrainerScheduleListHolders holder, int position) {

        final Trainer trainer = itemList.get(position);
        //holder.content.setText(session.getCourse());
        //holder.date.setText(session.getStartTime());
        //Picasso.with(context).load(session.trainer_photo)
          //      .resize(60, 60)
            //    .transform(new CircleTransform())
              //  .into(holder.image);
      //  setAnimation(holder.itemView, position);
        // view detail message conversation

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
