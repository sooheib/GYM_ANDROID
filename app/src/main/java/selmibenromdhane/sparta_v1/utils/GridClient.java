package selmibenromdhane.sparta_v1.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import selmibenromdhane.sparta_v1.manager.Event;
import selmibenromdhane.sparta_v1.manager.Trainer;

public class GridClient {

    private GridClient() {}


    public static void loadPaintingImage(ImageView image, Event painting) {
        Glide.with(image.getContext().getApplicationContext())
                .load(painting.getEvent_cover())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }

    public static void loadPaintingImage(ImageView image, Trainer painting) {
        Glide.with(image.getContext().getApplicationContext())
                .load(painting.getPhoto())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);
    }


}
