package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import selmibenromdhane.sparta_v1.Model.Routine;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.EditRoutineActivity;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.RoutinesHolder> {

    private ArrayList<Routine> items;
    private Context context;
    private LayoutInflater inflater;
    private final int resId = R.layout.list_item_routine;

    public RoutinesAdapter(Context context, ArrayList<Routine> items) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RoutinesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = inflater.inflate(resId, parent, false);

        return new RoutinesHolder(layout);
    }

    @Override
    public void onBindViewHolder(RoutinesHolder holder, int position) {

        Routine item = items.get(position);

        holder.tvRoutineName.setText(item.getTitle());
        holder.tvRoutineType.setText(item.getType().toString());

        for (int i = 0; i < 7; i++) {
            if (item.getWorkouts().get(i).size() == 0) {
                holder.tvDays.get(i).setBackgroundResource(R.drawable.day_view_rest);
            } else {
                holder.tvDays.get(i).setBackgroundResource(R.drawable.day_view_busy);
            }
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RoutinesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvRoutineName, tvRoutineType, tvMonday, tvTuesday, tvThursday, tvWednesday, tvFriday, tvSaturday, tvSunday;
        RelativeLayout root;

        ArrayList<TextView> tvDays;

        public RoutinesHolder(View itemView) {
            super(itemView);

            root = (RelativeLayout) itemView.findViewById(R.id.routine_item_root);
            tvRoutineName = (TextView) itemView.findViewById(R.id.tvRoutineName);
            tvRoutineType = (TextView) itemView.findViewById(R.id.tvRoutineType);

            tvDays = new ArrayList<>();
            tvMonday = (TextView) itemView.findViewById(R.id.tvMondayView);
            tvTuesday = (TextView) itemView.findViewById(R.id.tvTuesdayView);
            tvWednesday = (TextView) itemView.findViewById(R.id.tvWednesdayView);
            tvThursday = (TextView) itemView.findViewById(R.id.tvThursdayView);
            tvFriday = (TextView) itemView.findViewById(R.id.tvFridayView);
            tvSaturday = (TextView) itemView.findViewById(R.id.tvSaturdayView);
            tvSunday = (TextView) itemView.findViewById(R.id.tvSundayView);
            tvDays.add(tvMonday);
            tvDays.add(tvTuesday);
            tvDays.add(tvWednesday);
            tvDays.add(tvThursday);
            tvDays.add(tvFriday);
            tvDays.add(tvSaturday);
            tvDays.add(tvSunday);

            root.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            //launch EditRoutineActivity , passing the selected routine through the intent
            Intent intent = new Intent(context, EditRoutineActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("routine", items.get(getAdapterPosition()));
            intent.putExtras(bundle);
            context.startActivity(intent);


        }
    }
}
