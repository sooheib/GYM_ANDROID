package selmibenromdhane.sparta_v1.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.parser.ClassTrainerDownloader;

/**
 * Created by sooheib on 12/27/16.
 */

public class ClassTrainerFragement extends Fragment {

    SharedPreferences sh;
    SharedPreferences.Editor ed;
    public String description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_fragment_trainer, null);

        RecyclerView rh = (RecyclerView) view.findViewById(R.id.list_horizontal);

        new ClassTrainerDownloader(getActivity(), AppConfig.URL_SCHEDULE1,rh).execute();

        return view;
    }

}
