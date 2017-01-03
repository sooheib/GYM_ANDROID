package selmibenromdhane.sparta_v1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.activity.LoginActivity;
import selmibenromdhane.sparta_v1.adapter.ExpandableListAdapter;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.utils.MySingleton;


/**
 * Created by rufflez on 6/21/15.
 */
public class CoordinatorFragment extends Fragment {
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){



        View rootView = inflater.inflate(R.layout.coordinator_layout, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerViewJournal);

        setupRecyclerView(recyclerView);


        return rootView;
    }

    private void setupRecyclerView(final RecyclerView recyclerView){
        final List<ExpandableListAdapter.Item> data = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));


        JsonArrayRequest req = new JsonArrayRequest(AppConfig.URL_GetLog,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject person = (JSONObject) response.get(0);
                            String aux=person.getString("date");
                            int j=0;

                            data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, person.getString("date")));
                            data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, person.getString("title")));
                            Toast.makeText(getContext(), person.getString("date"), Toast.LENGTH_SHORT).show();


                            for (int i = 1; i < response.length(); i++) {
                                 person = (JSONObject) response.get(i);
                                Toast.makeText(getContext(), person.getString("title"), Toast.LENGTH_SHORT).show();

                                if(person.getString("date").equals(aux)){
                                    data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, person.getString("title")+"  "+person.getString("time")));

                                }else
                                {
                                    data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, person.getString("date")));
                                    data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, person.getString("title")+"                            "+person.getString("time")));
                                    aux=person.getString("date");
                                }




                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        finally {
                            recyclerView.setAdapter(new ExpandableListAdapter(data));

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "erreur", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("userID", LoginActivity.userId);
                return params;
            }
        };
        //data.addAll(data2);

        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Plaes");
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Kerala"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Tamil Nadu"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Karnataka"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Maharashtra"));

        //data.add(places);

        MySingleton.getinstance(getActivity().getApplicationContext()).addToRequest(req);

    }






}
