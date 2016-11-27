package selmibenromdhane.sparta_v1.manager;


import android.content.res.Resources;
import android.content.res.TypedArray;

import selmibenromdhane.sparta_v1.R;


public class Event {

    private final int imageId;
    private final String title;
    private final String year;
    private final String location;

    private Event(int imageId, String title, String year, String location) {
        this.imageId = imageId;
        this.title = title;
        this.year = year;
        this.location = location;
    }

    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public static Event[] getAllPaintings(Resources res) {
        String[] titles = res.getStringArray(R.array.paintings_titles);
        String[] years = res.getStringArray(R.array.paintings_years);
        String[] locations = res.getStringArray(R.array.paintings_locations);
        TypedArray images = res.obtainTypedArray(R.array.paintings_images);

        int size = titles.length;
        Event[] paintings = new Event[size];

        for (int i = 0; i < size; i++) {
            final int imageId = images.getResourceId(i, -1);
            paintings[i] = new Event(imageId, titles[i], years[i], locations[i]);
        }

        images.recycle();

        return paintings;
    }

}
