package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Model.Exercise;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.ExerciseDetailsActivity;


public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private ArrayList<Exercise> items;
    private Context context;
    private LayoutInflater inflater;

    public ExerciseAdapter(Context context, ArrayList<Exercise> items) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.list_item_exercise, parent, false);

            ExerciseViewHolder holder = new ExerciseViewHolder(view, viewType);
            return holder;
        } else if (viewType == TYPE_HEADER) {

            View view = inflater.inflate(R.layout.list_header_exercise, parent, false);

            ExerciseViewHolder holder = new ExerciseViewHolder(view, viewType);
            return holder;


        }
        return null;

    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        if (holder.type == TYPE_HEADER) {
            holder.title.setText(items.get(position + 1).getMuscle().toString());

        } else {
            Exercise item = items.get(position);

            holder.title.setText(item.getTitle());

            Picasso.with(context).load("file:///android_asset/Exercises/" + item.getImg1() + ".jpg").into(holder.img1);
            Picasso.with(context).load("file:///android_asset/Exercises/" + item.getImg2() + ".jpg").into(holder.img2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) == null)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int type;
        TextView title;
        ImageView img1, img2;

        public ExerciseViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TYPE_HEADER) {
                title = (TextView) itemView.findViewById(R.id.tvTitle);
                type = TYPE_HEADER;
            } else {

                type = TYPE_ITEM;
                title = (TextView) itemView.findViewById(R.id.tvExerciseTitle);
                img1 = (ImageView) itemView.findViewById(R.id.image1);
                img2 = (ImageView) itemView.findViewById(R.id.image2);
                itemView.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", items.get(getAdapterPosition()));
            Intent intent = new Intent(context, ExerciseDetailsActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}