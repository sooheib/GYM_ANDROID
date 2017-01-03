package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.BaseActivity;
import selmibenromdhane.sparta_v1.manager.Schedule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sooheib on 11/8/16.
 */

public class ScheduleAdapter extends BaseActivity implements ListAdapter {

    Context c;
    ArrayList<Schedule> schedules;

    LayoutInflater inflater;
    public ScheduleAdapter(Context c, ArrayList<Schedule> schedules) {
        this.c = c;
        this.schedules = schedules;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return schedules.size();
    }

    @Override
    public Object getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.one_article,parent,false);
        }
        // txt.setTypeface(font);
        Typeface myTypeface=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");
        TextView coursetxt= (TextView) convertView.findViewById(R.id.courseSchedule);
        coursetxt.setTypeface(myTypeface);
        Typeface myTypeface1=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");

        TextView trainersetxt= (TextView) convertView.findViewById(R.id.trainerSchedule);
        trainersetxt.setTypeface(myTypeface1);
        TextView hourtext= (TextView) convertView.findViewById(R.id.hourSchedule);
        Typeface myTypeface2=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Bold.ttf");

        hourtext.setTypeface(myTypeface2);



        //ImageView img= (ImageView) convertView.findViewById(R.id.image);

        //BIND DATA
        Schedule schedule=schedules.get(position);

        coursetxt.setText(schedule.getCourse());
        trainersetxt.setText(schedule.getTrainer());
        hourtext.setText(schedule.getHour());

        //IMG
        //  PicassoClient.downloadImage(c,spacecraft.getCourse_code(),img);


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
      return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;

    }
}
