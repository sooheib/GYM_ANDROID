package selmibenromdhane.sparta_v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sooheib on 11/28/16.
 */

class TrainersHolder  implements  View.OnClickListener{
    ImageView leftAvatar;
    ImageView rightAvatar;
    View infoPage;
    ImageView cal;
    ItemClickListener itemClickListener;
    View.OnClickListener onClickListener;


    List<TextView> interests = new ArrayList<>();
    TextView nickName;


    public TrainersHolder() {
        View.OnClickListener onClickListener;
        ItemClickListener itemClickListener;
    }

    public void onClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;

    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        System.out.println("sssssssssssssssssssssssss");
    }
}
