package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.manager.Session;

/**
 * Created by sooheib on 11/12/16.
 */

public class SessionAdapter extends BaseAdapter {

    Context c;
    ArrayList<Session> schedules;

    LayoutInflater inflater;

    public SessionAdapter(Context c, ArrayList<Session> schedules) {
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

        TextView coursetxt= (TextView) convertView.findViewById(R.id.courseSchedule);
        TextView trainersetxt= (TextView) convertView.findViewById(R.id.trainerSchedule);
        TextView hourtext= (TextView) convertView.findViewById(R.id.hourSchedule);


        //ImageView img= (ImageView) convertView.findViewById(R.id.image);

        //BIND DATA
        Session schedule=schedules.get(position);

       String course=String.valueOf(schedule.getCourse());
        coursetxt.setText(course);

        String trainer=String.valueOf(schedule.getTrainer());
        trainersetxt.setText(trainer);

        hourtext.setText(schedule.getStartTime());

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
