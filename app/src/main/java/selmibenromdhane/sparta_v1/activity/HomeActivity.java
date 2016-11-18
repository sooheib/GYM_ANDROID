package selmibenromdhane.sparta_v1.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.fragment.Pager;

//Implementing the interface OnTabSelectedListener to our HomeActivity
//This interface would help in swiping views
public class HomeActivity extends BaseActivity implements TabLayout.OnTabSelectedListener,AdapterView.OnItemClickListener{

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

//Date format
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Adding toolbar to the activity


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
            textView.setText(FORMATTER.format(date.getDate()));
        }
    }



}
