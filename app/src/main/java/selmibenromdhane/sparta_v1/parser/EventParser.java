package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.adapter.EventsAdapter;
import selmibenromdhane.sparta_v1.manager.Event;

/**
 * Created by sooheib on 11/29/16.
 */

public class EventParser extends AsyncTask<Void,Void,Integer> {


    Context c;
    String jsonData;
    ListView lv;
    ProgressDialog pd;
    ArrayList<Event> events=new ArrayList<>();


    public EventParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();

    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }


    @Override
    protected void onPostExecute(Integer s) {
        super.onPostExecute(s);

        pd.dismiss();

        if (s == 0) {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();
        } else {
            //BIND DATA TO LISTVIEW
            //FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
            final EventsAdapter adapter = new EventsAdapter(c,events);

            lv.setAdapter(adapter);
            System.out.println("good1");
        }
    }
    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            events.clear();

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                Event event = new Event();

                event.setEvent_id(jo.getString("0"));
                System.out.println(jo.getString("0"));

                event.setEvent_name(jo.getString("1"));
                System.out.println(jo.getString("1"));
                event.setEvent_location(jo.getString("2"));
                System.out.println(jo.getString("2"));
                event.setEvent_startDate(jo.getString("3"));
                System.out.println(jo.getString("3"));
                event.setEvent_endDate(jo.getString("4"));
                System.out.println(jo.getString("4"));
                event.setEvent_description(jo.getString("5"));
                System.out.println(jo.getString("5"));
                event.setEvent_cover("https://spartaapp.azurewebsites.net/Backend/partials/event_images/"
                        +jo.getString("6"));
                System.out.println("https://spartaapp.azurewebsites.net/Backend/partials/event_images/"
                        +jo.getString("6"));
                String maxC=jo.getString("7");
                event.setEvent_maxCapacity(Integer.parseInt(maxC));
                System.out.println(jo.getInt("7"));
                String countR=jo.getString("8");
                event.setEvent_countReserved(Integer.parseInt(countR));
                System.out.println(jo.getInt("8"));
                events.add(event);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

