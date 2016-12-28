package selmibenromdhane.sparta_v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import selmibenromdhane.sparta_v1.R;

/**
 * Created by sooheib on 12/23/16.
 */

public class TrainerScheduleListHolders extends RecyclerView.ViewHolder {

    public TextView content;
    public TextView date;
    public ImageView image;
    public LinearLayout lyt_parent;
    public TrainerScheduleListHolders(View v) {

        super(v);
        content = (TextView) v.findViewById(R.id.content);
        date = (TextView) v.findViewById(R.id.date);
        image = (ImageView) v.findViewById(R.id.image);
        lyt_parent = (LinearLayout) v.findViewById(R.id.lyt_parent);

    }
}
