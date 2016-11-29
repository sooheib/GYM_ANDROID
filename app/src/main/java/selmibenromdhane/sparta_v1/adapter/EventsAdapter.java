package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.EventsActivity;
import selmibenromdhane.sparta_v1.manager.Event;
import selmibenromdhane.sparta_v1.utils.GridClient;

/**
 * Created by sooheib on 11/26/16.
 */
public class EventsAdapter extends ArrayAdapter<Event> implements View.OnClickListener {


    Context c;
    ArrayList<Event> events;


    public EventsAdapter(Context context, ArrayList<Event> events) {
        super(context,0,events);
        this.c=context;
        this.events = events;
        System.out.println("good good");

    }



//    @Override
//    protected View createView(Event item, int pos, ViewGroup parent, LayoutInflater inflater) {
//
//
//        View view = inflater.inflate(R.layout.event_item, parent, false);
//
//
//        if(view==null)
//        {
//            System.out.println("vide");
//        }
//        ViewHolder vh = new ViewHolder();
//        vh.image = Views.find(view, R.id.list_item_image);
//        vh.image.setOnClickListener(this);
//        vh.title = Views.find(view, R.id.list_item_title);
//        view.setTag(vh);
//
//        return view;    }
//    @Override
//    protected void bindView(Event item, int pos, View convertView) {
//        System.out.println("good2");
//
//        ViewHolder vh = (ViewHolder) convertView.getTag();
//
//        vh.image.setTag(R.id.list_item_image, item);
//        GridClient.loadPaintingImage(vh.image, item);
//        vh.title.setText(item.getEvent_name());
//    }
//


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }
               ViewHolder vh = new ViewHolder();

        ImageView imageView= (ImageView) convertView.findViewById(R.id.list_item_image);
        TextView textView= (TextView) convertView.findViewById(R.id.list_item_title);
        vh.image=imageView;
        vh.title=textView;
//        vh.image = Views.find(convertView, R.id.list_item_image);
        vh.image.setOnClickListener(this);
//        vh.title = Views.find(convertView, R.id.list_item_title);
        convertView.setTag(vh);

            ViewHolder vh1 = (ViewHolder) convertView.getTag();
        vh.image.setTag(R.id.list_item_image, event);
        GridClient.loadPaintingImage(vh1.image, event);
        vh.title.setText(event.getEvent_name());
    return convertView;
    }

    @Override
    public void onClick(View view) {
        Event item = (Event) view.getTag(R.id.list_item_image);
        if (view.getContext() instanceof EventsActivity) {
            ((EventsActivity) view.getContext()).openDetails(view, item);
        } else if (view.getContext() instanceof EventsActivity) {
            Toast.makeText(view.getContext(), item.getEvent_name(), Toast.LENGTH_SHORT).show();
        }
    }


    private static class ViewHolder {
        ImageView image;
        TextView title;
    }

}
