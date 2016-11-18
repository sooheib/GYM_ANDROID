package selmibenromdhane.sparta_v1.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import selmibenromdhane.sparta_v1.R;

/**
 * Created by sooheib on 11/8/16.
 */
public class ScheduleDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScheduleDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
         //   mItem = D.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.website_name);
                appBarLayout.setTitle("souheib");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_detail_schedule, container, false);

        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            ((WebView) rootView.findViewById(R.id.scheduleDetail))
//                    .loadUrl(mItem.website_url);
//
//        }

        return rootView;
    }
}
