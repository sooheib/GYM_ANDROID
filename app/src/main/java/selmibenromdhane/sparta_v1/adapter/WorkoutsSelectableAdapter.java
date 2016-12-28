package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import java.util.ArrayList;

import selmibenromdhane.sparta_v1.manager.Exercise;
import selmibenromdhane.sparta_v1.manager.Workout;
import selmibenromdhane.sparta_v1.R;


public class WorkoutsSelectableAdapter extends RecyclerView.Adapter<WorkoutsSelectableAdapter.WorkoutsHolder> {

    private ArrayList<Workout> items;
    private Context context;
    private boolean[] selected;
    private LayoutInflater inflater;
    private final int resId = R.layout.list_item_workout_selectable;

    public WorkoutsSelectableAdapter(Context context, ArrayList<Workout> items, ArrayList<Integer> ids) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);

        selected = new boolean[items.size()];
        for (int i = 0; i < items.size(); i++) {
            if (ids.size() > 0 && items.get(i) != null)
                if (ids.contains(items.get(i).getId()))
                    selected[i] = true;
                else
                    selected[i] = false;
        }
    }

    /**
     * Get the selected exercises
     *
     * @return the arraylist with the exercises that are checked
     */
    public ArrayList<Workout> getSelectedWorkouts() {
        ArrayList<Workout> workouts = new ArrayList<>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                workouts.add(items.get(i));
            }
        }
        return workouts;
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
        holder.checkBox.setChecked(selected[position]);

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

    class WorkoutsHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        TextView tvWorkoutName, tvWorkoutSubtitle;
        LinearLayout llWorkoutExercises;
        RelativeLayout root;
        CheckBox checkBox;

        public WorkoutsHolder(View itemView) {
            super(itemView);

            root = (RelativeLayout) itemView.findViewById(R.id.workout_item_root);
            tvWorkoutName = (TextView) itemView.findViewById(R.id.tvWorkoutName);
            tvWorkoutSubtitle = (TextView) itemView.findViewById(R.id.tvWorkoutSubtitle);
            llWorkoutExercises = (LinearLayout) itemView.findViewById(R.id.llWorkoutExercises);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);

            checkBox.setOnCheckedChangeListener(this);
            root.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            selected[getAdapterPosition()] = !selected[getAdapterPosition()];
            checkBox.setChecked(selected[getAdapterPosition()]);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            selected[getAdapterPosition()] = isChecked;
        }
    }
}
