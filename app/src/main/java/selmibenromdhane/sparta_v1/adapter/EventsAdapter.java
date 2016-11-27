package selmibenromdhane.sparta_v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.alexvasilkov.android.commons.adapters.ItemsAdapter;
import com.alexvasilkov.android.commons.utils.Views;

import java.util.Arrays;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.EventsActivity;
import selmibenromdhane.sparta_v1.activity.Events1Activity;
import selmibenromdhane.sparta_v1.manager.Event;
import selmibenromdhane.sparta_v1.utils.EventUtils;

/**
 * Created by sooheib on 11/26/16.
 */
public class EventsAdapter extends ItemsAdapter<Event> implements View.OnClickListener {

    public EventsAdapter(Context context) {
        super(context);
        setItemsList(Arrays.asList(Event.getAllPaintings(context.getResources())));
    }

    @Override
    protected View createView(Event item, int pos, ViewGroup parent, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.event_item, parent, false);
        ViewHolder vh = new ViewHolder();
        vh.image = Views.find(view, R.id.list_item_image);
        vh.image.setOnClickListener(this);
        vh.title = Views.find(view, R.id.list_item_title);
        view.setTag(vh);

        return view;
    }

    @Override
    protected void bindView(Event item, int pos, View convertView) {
        ViewHolder vh = (ViewHolder) convertView.getTag();

        vh.image.setTag(R.id.list_item_image, item);
        EventUtils.loadPaintingImage(vh.image, item);
        vh.title.setText(item.getTitle());
    }

    @Override
    public void onClick(View view) {
        Event item = (Event) view.getTag(R.id.list_item_image);
        if (view.getContext() instanceof EventsActivity) {
            ((EventsActivity) view.getContext()).openDetails(view, item);
        } else if (view.getContext() instanceof Events1Activity) {
            Toast.makeText(view.getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class ViewHolder {
        ImageView image;
        TextView title;
    }

}
