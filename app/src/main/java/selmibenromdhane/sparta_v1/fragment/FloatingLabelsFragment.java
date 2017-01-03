package selmibenromdhane.sparta_v1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.LoginActivity;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.utils.MySingleton;


/**
 * Created by rufflez on 6/20/15.
 */
public class FloatingLabelsFragment extends Fragment {
    SwitchCompat switchButton;
    EditText inputSex;
    TextView input_age;
    ImageButton vw;
    FloatingActionButton changePassword;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.floating_labels, container, false);
        final View root = inflater.inflate(R.layout.tab_layout, container, false);

        switchButton = (SwitchCompat) rootView.findViewById(R.id.switch_sex);
        inputSex = (EditText) rootView.findViewById(R.id.input_sex);
        input_age = (TextView) rootView.findViewById(R.id.input_age);
        vw=(ImageButton) root.findViewById(R.id.user_profile_photo);
        changePassword = (FloatingActionButton) rootView.findViewById(R.id.changePassword);
        switchButton.setChecked(true);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    inputSex.setText("Male");
                } else {
                    inputSex.setText("Female");
                }
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogueChangePassword dialog = new DialogueChangePassword();
                dialog.show(getFragmentManager(),"dd");

            }
        });
        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "d", Toast.LENGTH_SHORT).show();

            }
        });
        getProfile();
        return rootView;


    }

    public void getProfile() {

        JsonArrayRequest req = new JsonArrayRequest(AppConfig.URL_GETPROFILE,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                                JSONObject person = (JSONObject) response.get(0);








                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", LoginActivity.userId);
                return params;
            }
        };
        MySingleton.getinstance(getContext()).addToRequest(req);

    }


}
