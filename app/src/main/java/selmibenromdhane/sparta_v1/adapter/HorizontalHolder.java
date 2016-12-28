package selmibenromdhane.sparta_v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import selmibenromdhane.sparta_v1.R;

/**
 * Created by sooheib on 12/27/16.
 */

public class HorizontalHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    ItemClickListener itemClickListener;
    CircleImageView apraisorProfilePic;
    TextView cItem1;
    CircleImageView pp;
    public HorizontalHolder(final View itemView) {
        super(itemView);
        cItem1 = (TextView) itemView.findViewById(R.id.c_item_1);
        pp = (CircleImageView)itemView.findViewById(R.id.profilePicture);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        this.itemClickListener.onItemClick();

    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener=itemClickListener;
    }
}
