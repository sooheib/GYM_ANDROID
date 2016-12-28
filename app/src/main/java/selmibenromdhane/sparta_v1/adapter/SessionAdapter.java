package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.manager.Session;

/**
 * Created by sooheib on 11/12/16.
 */

public class SessionAdapter extends BaseAdapter {

    Context c;
    ArrayList<Object> schedules;


    LayoutInflater inflater;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    public SessionAdapter(Context c, ArrayList<Object> schedules) {
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
        int type = getItemViewType(position);
        if(convertView==null)
        {

        switch (type) {

            case TYPE_ITEM:
                convertView=inflater.inflate(R.layout.one_article,null);
                break;
            case TYPE_SEPARATOR:
                convertView=inflater.inflate(R.layout.header_schedule,null);
                break;
        }
        }


        switch (type) {

            case TYPE_ITEM:

                Session schedule= (Session) getItem(position);

                TextView coursetxt= (TextView) convertView.findViewById(R.id.courseSchedule);
        TextView trainersetxt= (TextView) convertView.findViewById(R.id.trainerSchedule);
        TextView hourtext= (TextView) convertView.findViewById(R.id.hourSchedule);

        String course=String.valueOf(schedule.getCourse());
        coursetxt.setText(course);

        String trainer=String.valueOf(schedule.getTrainer());
        trainersetxt.setText(trainer);

        hourtext.setText(schedule.getStartTime());

                break;
            case TYPE_SEPARATOR:
                String header=(String) getItem(position);
                TextView headerTv=(TextView) convertView.findViewById(R.id.textheader);
                //SET HEADER TEXT AND MAYBE BACKGROUND
                headerTv.setText(header);
            default:
                break;
        }

        return convertView;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Session) {
            return TYPE_ITEM;
        }
        return TYPE_SEPARATOR;

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
        return (getItemViewType(position) == TYPE_ITEM);
    }


}
