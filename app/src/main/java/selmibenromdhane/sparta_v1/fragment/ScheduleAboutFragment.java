package selmibenromdhane.sparta_v1.fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;

import static android.content.Context.MODE_PRIVATE;


public class ScheduleAboutFragment extends Fragment {
ScheduleParser scheduleParser=new ScheduleParser();
    SharedPreferences sh;
    SharedPreferences.Editor ed;
    public String day;
    public String time;
    public String room;
    public String description;
    public String trainer;
    public String photo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_about, null);
        TextView trainerText= (TextView) view.findViewById(R.id.trainer);
        ImageView trainerPhoto= (ImageView) view.findViewById(R.id.trainerphoto);
        TextView d3= (TextView) view.findViewById(R.id.description3);
        TextView d1= (TextView) view.findViewById(R.id.description1);
        TextView d2= (TextView) view.findViewById(R.id.description2);
        TextView d4= (TextView) view.findViewById(R.id.description4);
        TextView desc= (TextView) view.findViewById(R.id.descriptioxn);

        sh = this.getActivity().getSharedPreferences("report",MODE_PRIVATE);
        day=sh.getString("day","99");
        d1.setText(day);

        System.out.println("******"+day);

        time=sh.getString("time","99");
        System.out.println("******"+time);

        d2.setText(time);
        room=sh.getString("room","99");
        System.out.println("******"+room);
        d3.setText(room);
        description=sh.getString("description","99");
        System.out.println("******"+description);
        desc.setText(description);
        trainer=sh.getString("trainer","99");
        System.out.println("******"+trainer);
        trainerText.setText(trainer);
        photo=sh.getString("photo","99");
        System.out.println("******"+photo);

       Picasso.with(getActivity().getApplicationContext()).load(photo).into(trainerPhoto);


//        URL url1 = null;
//        try {
//            url1 = new URL(photo);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        HttpURLConnection connection1 = null;
//        try {
//            connection1 = (HttpURLConnection) url1.openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        connection1.setDoInput(true);
//        try {
//            connection1.connect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        InputStream input1 = null;
//        try {
//            input1 = connection1.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Bitmap bitmap1 = BitmapFactory.decodeStream(input1);
//
//        // Picasso.with(DetailScheduleActivity.this).load(photo).into(circleImageView);
//
//        trainerPhoto.setImageBitmap(getCircleBitmap(bitmap1));

        return view;
    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

}
