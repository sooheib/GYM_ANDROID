package selmibenromdhane.sparta_v1.adapter;

/**
 * Created by sooheib on 11/8/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.manager.Course;
import selmibenromdhane.sparta_v1.utils.PicassoClient;


public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Course> courses;

    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Course> courses) {
        this.c = c;
        this.courses = courses;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.grid_item_layout,parent,false);
        }

        TextView nametxt= (TextView) convertView.findViewById(R.id.text);
        ImageView img= (ImageView) convertView.findViewById(R.id.image);

        //BIND DATA
        Course course = courses.get(position);

        nametxt.setText(course.getCourse_code());

        //IMG
        PicassoClient.downloadImage(c, course.getCourse_cover(),img);


        return convertView;
    }
}
