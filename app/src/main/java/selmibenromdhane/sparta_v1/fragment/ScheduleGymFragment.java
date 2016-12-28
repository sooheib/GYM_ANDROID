package selmibenromdhane.sparta_v1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.parser.ScheduleDownloader;

import static android.content.Context.MODE_PRIVATE;


//Our class extending fragment
public class ScheduleGymFragment extends Fragment {


    SharedPreferences sh;
    SharedPreferences.Editor ed;
    public static String selecteddayGS;





    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_schedulegym, container, false);


        sh = this.getActivity().getSharedPreferences("report",MODE_PRIVATE);


        swipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        selecteddayGS=sh.getString("selected","99");
        System.out.println("**selectedday**"+selecteddayGS);

        final ListView classListView = (ListView) rootView.findViewById(R.id.list1);
       classListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ssssssssssss");
            }
        });

       // new ScheduleDownloader(getActivity(), AppConfig.URL_SCHEDULE,classListView).execute();

        new ScheduleDownloader(getActivity(), AppConfig.URL_SCHEDULE1,classListView,swipeRefreshLayout).execute();


        return rootView;

    }

}
