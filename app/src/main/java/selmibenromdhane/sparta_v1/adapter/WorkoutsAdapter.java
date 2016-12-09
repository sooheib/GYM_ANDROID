package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Model.Exercise;
import selmibenromdhane.sparta_v1.Model.Workout;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.EditRoutineActivity;
import selmibenromdhane.sparta_v1.activity.EditWorkoutActivity;
import selmibenromdhane.sparta_v1.activity.WorkoutsActivity;


public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutsHolder> {

    private ArrayList<Workout> items;
    private Context context;
    private LayoutInflater inflater;
    private final int resId = R.layout.list_item_workout;
    private boolean clickable;
    private Class targetActivity;


    public WorkoutsAdapter(Context context, ArrayList<Workout> items, boolean clickable, Class targetActivity) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
        this.clickable = clickable;
        this.targetActivity = targetActivity;

    }

    @Override
    public WorkoutsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = inflater.inflate(resId, parent, false);

        return new WorkoutsHolder(layout);
    }

    @Override
    public void onBindViewHolder(WorkoutsHolder holder, int position) {

        Workout item = items.get(position);

        holder.tvWorkoutName.setText(item.getTitle());
        holder.tvWorkoutSubtitle.setText(item.getType().toString() + " , " + item.getMuscle().toString());

        if (holder.llWorkoutExercises.getChildCount() != 0)
            holder.llWorkoutExercises.removeAllViews();
        //for each exercise in the workout , create a text view and add it the layout of the item
        for (Exercise ex : item.getExercises()) {
            TextView tv = new TextView(context);
            tv.setText(ex.getTitle());
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            Drawable img = context.getResources().getDrawable(R.drawable.bullet);
            img.setBounds(0, 0, 10, 10);
            tv.setCompoundDrawables(img, null, null, null);
            tv.setCompoundDrawablePadding(8);
            tv.setPadding(4, 4, 4, 4);
            tv.setTextColor(context.getResources().getColor(R.color.primary_text));
            holder.llWorkoutExercises.addView(tv);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class WorkoutsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvWorkoutName, tvWorkoutSubtitle;
        LinearLayout llWorkoutExercises;

        public WorkoutsHolder(View itemView) {
            super(itemView);

            tvWorkoutName = (TextView) itemView.findViewById(R.id.tvWorkoutName);
            tvWorkoutSubtitle = (TextView) itemView.findViewById(R.id.tvWorkoutSubtitle);
            llWorkoutExercises = (LinearLayout) itemView.findViewById(R.id.llWorkoutExercises);

            if (clickable)
                itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (targetActivity == WorkoutsActivity.class) {
                //Launch the workouts activity
                Intent intent = new Intent(context, WorkoutsActivity.class);

                //and pass the ids of the workouts that are already already in this day
                //so that they will be already checked
                ArrayList<Integer> ids = new ArrayList<>();
                for (Workout w : items) {
                    ids.add(w.getId());
                }
                intent.putIntegerArrayListExtra("ids", ids);

                ((EditRoutineActivity) context).startActivityForResult(intent, 100);
            } else if (targetActivity == EditWorkoutActivity.class) {

                //launch EditWorkoutActivity , passing the selected workout through the intent
                Intent intent = new Intent(context, EditWorkoutActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("workout", items.get(getAdapterPosition()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

        }
    }
}
