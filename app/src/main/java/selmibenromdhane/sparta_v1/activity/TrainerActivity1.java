package selmibenromdhane.sparta_v1.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import selmibenromdhane.sparta_v1.R;
import selmibenromdhane.sparta_v1.app.AppConfig;
import selmibenromdhane.sparta_v1.app.AppController;
import selmibenromdhane.sparta_v1.manager.Trainer;

public class TrainerActivity1 extends EuclidActivity {

   ArrayList<Trainer> trainers=new ArrayList<>();
    List<Trainer> list = new ArrayList<>();

    public static List<Map<String, Object>> profilesList1 = new ArrayList<>();
    public static int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrainerActivity1.this, "Oh hi!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected BaseAdapter getAdapter() {

        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        final int[] avatars = {
                R.drawable.anastasia,
                R.drawable.andriy,
                R.drawable.dmitriy,
                R.drawable.dmitry_96,
                R.drawable.ed,
                R.drawable.illya,
                R.drawable.kirill,
                R.drawable.konstantin,
                R.drawable.oleksii,
                R.drawable.pavel,
                R.drawable.vadim};
        final String[] names = getResources().getStringArray(R.array.array_names);


        JsonArrayRequest checkReq = new JsonArrayRequest(AppConfig.URL_TRAINER,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("check", response.toString());

                        trainers.clear();

                        Map<String, Object> profileMap;
                        List<Map<String, Object>> profilesList = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);

                                Trainer trainer = new Trainer();
                                String employee_id = obj.getString("0");
                                System.out.println(obj.getString("0"));

                                trainer.setEmployee_id(Integer.parseInt(employee_id));


                                trainer.setFirst_name(obj.getString("1"));
                                System.out.println(obj.getString("1"));

                                trainer.setLast_name(obj.getString("2"));
                                System.out.println(obj.getString("2"));

                                trainer.setPhoto("https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/" + obj.getString("6"));
                                System.out.println("https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/" + obj.getString("6"));

                                profileMap = new HashMap<>();

                                profileMap.put(EuclidListAdapter.KEY_AVATAR, "https://spartaapp.azurewebsites.net/Backend/partials/teacher_photos/" + obj.getString("6"));
                                profileMap.put(EuclidListAdapter.KEY_NAME, obj.getString("2"));
                                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, getString(R.string.lorem_ipsum_short));
                                profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));

                                profilesList.add(profileMap);

                                profilesList1 = profilesList;

                                trainers.add(trainer);
                                list.add(trainer);

//                                SharedPreferences settings;
//                                SharedPreferences.Editor editor;
//
//                                settings = TrainerActivity1.this.getSharedPreferences("pref",
//                                        Context.MODE_PRIVATE);
//                                editor = settings.edit();
//
//
//

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("check", "Error: " + error.getMessage());

            }
        });

        AppController.getInstance().addToRequestQueue(checkReq);

//
//        SharedPreferences settings;
//        List<Trainer> trainers1 = null;
//
//        settings = TrainerActivity1.this.getSharedPreferences("pref",
//                Context.MODE_PRIVATE);
//
//        if (settings.contains("list")) {
//            String jsonUsers = settings.getString("list", null);
//            Gson gson = new Gson();
//            Trainer[] userItems = gson.fromJson(jsonUsers,
//                    Trainer[].class);
//
//            trainers1 = Arrays.asList(userItems);
//            trainers1 = new ArrayList<Trainer>(trainers1);
//            System.out.println("123456"+trainers1.size());
//
//        }
//        else {
//            System.out.println("123");
//        }
//

        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, getString(R.string.lorem_ipsum_short));
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, getString(R.string.lorem_ipsum_long));
            profilesList.add(profileMap);
       }
        return new EuclidListAdapter(this, R.layout.list_item, profilesList);
    }
}