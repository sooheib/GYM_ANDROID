package selmibenromdhane.sparta_v1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.parser.ClassParser;

public class DetailsClassesActivity extends AppCompatActivity {
    TextView tv1, tv2,tv3;


    String userName = "";
    String userProfession = "";
    String userPic="";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_classes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        tv1 = (TextView) findViewById(R.id.description);
        //tv2 = (TextView) findViewById(R.id.description);
       // tv1 = (ImageView) findViewById(R.id.duration);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     getSupportActionBar().setDisplayShowHomeEnabled(true);
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.toolbar_layout);

        //setText(new Date)
        Intent intent = getIntent();
        if (null != intent) {
            userProfession = intent.getStringExtra(ClassParser.DESCRIPTION_EXTRA);
            userName=intent.getStringExtra(ClassParser.COURSE_EXTRA);
            userPic=intent.getStringExtra(ClassParser.IMAGE_EXTRA);
            //String url = "" //place your url here
            ImageView toolbarImage= (ImageView) findViewById(R.id.toolbar);


            URL url = null;
            try {
                url = new URL(userPic);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setDoInput(true);
            try {
                connection.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream input = null;
            try {
                input = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
                Drawable drawable = new BitmapDrawable(getResources(), myBitmap);
                appBarLayout.setBackground(drawable);
            //picassoLoader(this, appBarLayout, userPic);

       //     Bitmap bmImg = BitmapFactory.decodeStream();
            //BitmapDrawable background9 = new BitmapDrawable(bmImg);
//            try {
//                URL url = new URL(userPic);
//                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                appBarLayout.setBackground(bmp);
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



        }

        //appBarLayout.setTitle(course);

        appBarLayout.setTitle(userName);
//        appBarLayout.setExpandedTitleColor();

        //appBarLayout.setBackground(userPic);

        TextView tv1 = (TextView) findViewById(R.id.description);
        tv1.setText(userProfession);
    }

//    private void picassoLoader(DetailsClassesActivity detailsClassesActivity, CollapsingToolbarLayout toolbarImage, String userPic) {
//        Log.d("PICASSO", "loading image");
//        Picasso.with(detailsClassesActivity).load(userPic).into((Target) toolbarImage);
//    }


    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
////         handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;}


    public void onBackPressed(){
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
