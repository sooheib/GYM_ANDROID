package selmibenromdhane.sparta_v1.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import selmibenromdhane.sparta_v1.R;

/**
 * Created by sooheib on 11/8/16.
 */
public class PicassoClient {
    public static void downloadImage(Context c, String imageUrl, ImageView img)
    {
        if(imageUrl.length()>0 && imageUrl!=null)
        {
            Picasso.with(c).load(imageUrl).placeholder(R.drawable.placeholder).into(img);
        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
        }
    }
}

