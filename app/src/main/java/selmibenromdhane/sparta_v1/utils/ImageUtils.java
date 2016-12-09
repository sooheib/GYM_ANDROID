package selmibenromdhane.sparta_v1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import selmibenromdhane.sparta_v1.R;


public class ImageUtils {

    /**
     * Load the profile image , transform it to circle shape before loading it
     * @param context the context
     * @param uri the uri of the image file to be loaded
     * @param imageView the Image view
     */
    public static void loadProfileImage(Context context, Uri uri, ImageView imageView) {

        Picasso.with(context).load(uri)
                .placeholder(R.drawable.ic_profile_placeholder)
                .error(R.drawable.ic_profile_placeholder)
                .transform(new RoundedTransformation(R.dimen.profile_image_size, 0))
                .resizeDimen(R.dimen.profile_image_size, R.dimen.profile_image_size)
                .centerCrop()
                .into(imageView);
    }

    /**
     * Class representing a circular trasnformation of a View . Used for the profile image
     * to be shown as a circular image .
     */
    static class RoundedTransformation implements com.squareup.picasso.Transformation {
        private final int radius;
        private final int margin;  // dp

        public RoundedTransformation(final int radius, final int margin) {
            this.radius = radius;
            this.margin = margin;
        }

        @Override
        public Bitmap transform(final Bitmap source) {
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

            Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

            if (source != output) {
                source.recycle();
            }

            return output;
        }

        @Override
        public String key() {
            return "rounded";
        }
    }
}
