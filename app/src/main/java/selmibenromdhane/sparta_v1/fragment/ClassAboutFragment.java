package selmibenromdhane.sparta_v1.fragment;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import selmibenromdhane.sparta_v1.R;


import static android.content.Context.MODE_PRIVATE;


public class ClassAboutFragment extends Fragment {
    SharedPreferences sh;
    SharedPreferences.Editor ed;
    public String description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classes_about, null);

        TextView desc= (TextView) view.findViewById(R.id.descriptioxn);

        sh = this.getActivity().getSharedPreferences("report",MODE_PRIVATE);

        description=sh.getString("description","99");
        System.out.println("******"+description);
        desc.setText(description);

        return view;
    }


}
