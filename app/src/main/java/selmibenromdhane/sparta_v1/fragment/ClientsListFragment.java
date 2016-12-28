package selmibenromdhane.sparta_v1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.parser.ClientsListDownloader;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sooheib on 12/21/16.
 */

public class ClientsListFragment extends Fragment {

    private RecyclerView recyclerView;

    private LinearLayoutManager lLayout;
    public static String scheduleID;

    SharedPreferences sh;
    SharedPreferences.Editor ed;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        lLayout = new LinearLayoutManager(getActivity());


        View rootView = inflater.inflate(R.layout.client_list_fragment, container, false);

        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(lLayout);

        sh = this.getActivity().getSharedPreferences("report",MODE_PRIVATE);

        scheduleID=sh.getString("scheduleid","99");





        System.out.println("ababa"+scheduleID);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new ClientsListDownloader(getActivity(),AppConfig.URL_CLIENTS,recyclerView).execute();

        return rootView;
    }


}

