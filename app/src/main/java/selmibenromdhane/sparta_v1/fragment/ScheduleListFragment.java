package selmibenromdhane.sparta_v1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.DetailScheduleActivity1;
import selmibenromdhane.sparta_v1.adapter.FriendsListAdapter;
import selmibenromdhane.sparta_v1.manager.Constant;

/**
 * Created by sooheib on 12/4/16.
 */

public class ScheduleListFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendsListAdapter mAdapter;
    private View view;
    private SearchView search;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_list, null);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //set data and list adapter
        mAdapter = new FriendsListAdapter(getActivity(), Constant.getFriendsData(getActivity()));
        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new FriendsListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, Friend obj, int position) {
//                DetailScheduleActivity1.navigate(() getActivity(), v, obj);
//            }
//        });
        return view;
    }
}
