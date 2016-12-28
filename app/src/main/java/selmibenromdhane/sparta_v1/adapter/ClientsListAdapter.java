package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailsClassesActivity1;
import selmibenromdhane.sparta_v1.manager.Client;
import selmibenromdhane.sparta_v1.manager.Course;
import selmibenromdhane.sparta_v1.utils.CircleTransform;
import selmibenromdhane.sparta_v1.utils.PicassoClient;

/**
 * Created by sooheib on 12/21/16.
 */

public class ClientsListAdapter extends RecyclerView.Adapter<ClientsListHolders> {

    private ArrayList<Client> itemList;
    private Context context;
    public static String DESCRIPTION_EXTRA="course_desc";
    public static String COURSE_EXTRA="course_code";
    public static String IMAGE_EXTRA="course_cover";
    ImageView img;

    public ClientsListAdapter(Context context, ArrayList<Client> itemList) {
        this.itemList = itemList;
        this.context = context;

    }

    @Override
    public ClientsListHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_friends, null);
        ClientsListHolders rcv = new ClientsListHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(ClientsListHolders holder, int position) {
        final Client s=itemList.get(position);




        holder.className.setText(itemList.get(position).getClient_name());

        Picasso.with(context).load(s.getClient_photo()).resize(100, 100).transform(new CircleTransform()).into(holder.classPhoto);
        setAnimation(holder.itemView, position);
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
