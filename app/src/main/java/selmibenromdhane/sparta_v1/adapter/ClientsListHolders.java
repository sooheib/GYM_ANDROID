package selmibenromdhane.sparta_v1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import selmibenromdhane.sparta_v1.R;

/**
 * Created by sooheib on 12/21/16.
 */

public class ClientsListHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView className;
    public ImageView classPhoto;
    ItemClickListener itemClickListener;


    public ClientsListHolders(final View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        className = (TextView)itemView.findViewById(R.id.name);
        classPhoto = (ImageView)itemView.findViewById(R.id.image);

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
