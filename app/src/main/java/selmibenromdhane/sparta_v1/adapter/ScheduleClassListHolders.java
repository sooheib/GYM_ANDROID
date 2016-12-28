package selmibenromdhane.sparta_v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import selmibenromdhane.sparta_v1.R;

/**
 * Created by sooheib on 12/22/16.
 */

public class ScheduleClassListHolders extends RecyclerView.ViewHolder {

    public TextView content;
    public TextView date;
    public ImageView image;
    public LinearLayout lyt_parent;
    ItemClickListener itemClickListener;


    public ScheduleClassListHolders(View v) {

        super(v);
        content = (TextView) v.findViewById(R.id.content);
        date = (TextView) v.findViewById(R.id.date);
        image = (ImageView) v.findViewById(R.id.image);
        lyt_parent = (LinearLayout) v.findViewById(R.id.lyt_parent);
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
}
