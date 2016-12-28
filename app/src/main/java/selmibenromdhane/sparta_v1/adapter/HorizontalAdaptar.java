package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.manager.Trainer;
import selmibenromdhane.sparta_v1.utils.PicassoClient;

/**
 * Created by sooheib on 12/27/16.
 */

public class HorizontalAdaptar extends RecyclerView.Adapter<HorizontalHolder> {

    LayoutInflater inflater;
    private Context context;
    private ArrayList<Trainer> itemList;

    public final int[] mPosition;
    public final int[] mColors;

    private final Random mRandom = new Random();

    public HorizontalAdaptar(Context context, ArrayList<Trainer> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColors = new int[10];
        mPosition = new int[10];

        for (int i = 0; 10 > i; ++i) {
            mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            mPosition[i] = i;

        }
    }

    @Override
    public HorizontalHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
        HorizontalHolder holder = new HorizontalHolder(layoutView);

        return holder;
    }

    @Override
    public void onBindViewHolder(HorizontalHolder holder, int position) {

        holder.cItem1.setText(itemList.get(position).getLast_name());
        PicassoClient.downloadImage(context, itemList.get(position).getPhoto(),holder.pp);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                System.out.println("0000000000000000");
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
