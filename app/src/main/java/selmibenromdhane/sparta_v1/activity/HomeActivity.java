package selmibenromdhane.sparta_v1.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.Pager;
import selmibenromdhane.sparta_v1.parser.ScheduleParser;

import static android.R.attr.id;
import static selmibenromdhane.sparta_v1.R.drawable.design_fab_background;
import static selmibenromdhane.sparta_v1.R.drawable.fab_add;

public class HomeActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,AdapterView.OnItemClickListener{

    //This is our tablayoutfsf
    private TabLayout tabLayout;

    private static Context mContext;

    //This is our viewPager
    private ViewPager viewPager;

    SharedPreferences sh;
    SharedPreferences.Editor ed;


    public static String selectedday;
    public  String selectedday1;

//Date format
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    public static final String SELECTED_DAY="DAY";


    LinearLayout linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.background9, opt);
        int height = opt.outHeight;
        int width = opt.outWidth;
        String imageType = opt.outMimeType;


        Bitmap bitmap1=decodeSampledBitmapFromResource(getResources(),R.drawable.background9, 642, 962);


        Drawable drawable=new BitmapDrawable(getResources(),bitmap1);

        linearLayout= (LinearLayout) findViewById(R.id.main_layout);

        linearLayout.setBackground(drawable);






        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("GYM Schedule"));
        tabLayout.addTab(tabLayout.newTab().setText("My Schedule"));
       // tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
       // tabLayout.setupWithViewPager(viewPager);


        sh = getSharedPreferences("report",MODE_PRIVATE);


        selectedday1=sh.getString("selected","99");
        System.out.println("selectedday1"+selectedday1);


        Intent intent = getIntent();
        if (null != intent) {


            selectedday=intent.getStringExtra(HomeActivity.SELECTED_DAY);

        }


        System.out.println("selectedday"+selectedday);
        ed=sh.edit();
        ed.putString("selected",selectedday);
        ed.commit();



    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //  noinspection SimplifiableIfStatement
        if (id == R.id.calendar) {

            new SimpleCalendarDialogFragment().show(getSupportFragmentManager(), "test-simple-calendar");

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(HomeActivity.this,"bonjour",Toast.LENGTH_LONG).show();
    }

    public static class SimpleCalendarDialogFragment extends AppCompatDialogFragment implements OnDateSelectedListener {

        private TextView textView;
        public String month;
        public String today;


        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            //inflate custom layout and get views
            //pass null as parent view because will be in dialog layout
            View view = inflater.inflate(R.layout.dialog_basic, null);

            textView = (TextView) view.findViewById(R.id.textView);

            MaterialCalendarView widget = (MaterialCalendarView) view.findViewById(R.id.calendarView);

            widget.setOnDateChangedListener(this);

            return new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .create();
        }




        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
           // textView.setText(FORMATTER.format(date.getDate()));
         //  textView.setText(date.getMonth()+" "+date.getDay());

            SimpleDateFormat sdftoday = new SimpleDateFormat("dd");
            SimpleDateFormat sdfmonth = new SimpleDateFormat("MM");

            SimpleDateFormat sdfyear = new SimpleDateFormat("yyyy");



            String today=sdftoday.format(date.getDate());
            String month=sdfmonth.format(date.getDate());
            String year=sdfyear.format(date.getDate());
            String selectedday=month+"/"+today+"/"+year;
            textView.setText(month+"/"+today+"/"+year);

            Intent i = new Intent(getContext(), HomeActivity.class);
            i.putExtra(SELECTED_DAY,selectedday);
            getContext().startActivity(i);




        }
    }

    public static int calculateMyInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of bitmap
        final int oriHeight = options.outHeight;
        final  int oriWidth=options.outWidth;
        int inSampleSize = 1;

        if (oriHeight > reqHeight || oriWidth > reqWidth) {

            final int halfHeight = oriHeight / 2;
            final int halfWidth = oriWidth / 2;

            // Calculate the largest inSampleSize value which is power of 2 and keeps both width and height larger than the requested width and height.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth)
            {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resource, int resourceId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resource, resourceId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateMyInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resource, resourceId, options);
    }







}
