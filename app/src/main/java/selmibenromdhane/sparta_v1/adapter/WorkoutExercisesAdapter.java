package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Model.Exercise;
import selmibenromdhane.sparta_v1.Model.Set;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.EditSetsActivity;
import selmibenromdhane.sparta_v1.activity.EditWorkoutActivity;


public class WorkoutExercisesAdapter extends RecyclerView.Adapter<WorkoutExercisesAdapter.WorkoutExerciseViewHolder> {
    private ArrayList<Exercise> items;
    private Context context;
    private LayoutInflater inflater;

    public WorkoutExercisesAdapter(Context context, ArrayList<Exercise> items) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public WorkoutExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_workout_exercise, parent, false);

        WorkoutExerciseViewHolder holder = new WorkoutExerciseViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(WorkoutExerciseViewHolder holder, int position) {

        Exercise item = items.get(position);

        holder.title.setText(item.getTitle());

        Picasso.with(context).load("file:///android_asset/Exercises/" + item.getImg1() + ".jpg").into(holder.img1);
        Picasso.with(context).load("file:///android_asset/Exercises/" + item.getImg2() + ".jpg").into(holder.img2);

        //remove all views from layout that contains the set views
        holder.llExercisesInWorkout.removeAllViews();
        //for each set of the exercise , create a text view and add it to the layout of the item
        for (Set s : item.getSets()) {
            TextView tv = new TextView(context);
            tv.setText(s.getReps() + "");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 4, 4, 4);
            tv.setLayoutParams(params);
            tv.setBackgroundResource(R.drawable.set_view);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(context.getResources().getColor(R.color.primary_text));

            holder.llExercisesInWorkout.addView(tv);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class WorkoutExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, edit, delete;
        ImageView img1, img2;
        LinearLayout llExercisesInWorkout;

        public WorkoutExerciseViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvExerciseTitle);
            img1 = (ImageView) itemView.findViewById(R.id.image1);
            img2 = (ImageView) itemView.findViewById(R.id.image2);
            edit = (TextView) itemView.findViewById(R.id.edit);
            delete = (TextView) itemView.findViewById(R.id.delete);
            llExercisesInWorkout = (LinearLayout) itemView.findViewById(R.id.llExerciseInWorkout);

            itemView.findViewById(R.id.exercise_workout_root).setOnClickListener(this);
            edit.setOnClickListener(this);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    items.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            //launch EditSetsActivity , passing the selected exercise through the intent
            Intent intent = new Intent(context, EditSetsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("exercise", items.get(getAdapterPosition()));
            intent.putExtras(bundle);
            ((EditWorkoutActivity) context).startActivityForResult(intent, 200);
        }
    }
}
