package selmibenromdhane.sparta_v1.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import selmibenromdhane.sparta_v1.manager.Event;

public class EventUtils {

    private EventUtils() {}

    public static void loadPaintingImage(ImageView image, Event painting) {
        Glide.with(image.getContext().getApplicationContext())
                .load(painting.getImageId())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }

}
