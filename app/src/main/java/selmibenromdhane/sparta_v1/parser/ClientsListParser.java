package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import selmibenromdhane.sparta_v1.adapter.ClientsListAdapter;
import selmibenromdhane.sparta_v1.fragment.ClientsListFragment;
import selmibenromdhane.sparta_v1.manager.Client;

/**
 * Created by sooheib on 12/21/16.
 */

public class ClientsListParser extends AsyncTask<Void,Void,Integer> {


    private ArrayList<Client> clients = new ArrayList<>();

    Context c;
    String jsonData;
    RecyclerView rv;
    String schedule_id;
    ClientsListFragment clientsListFragment=new ClientsListFragment();




    ProgressDialog pd;
   // ArrayList<Session> schedules=new ArrayList<>();


    public ClientsListParser(Context c, String jsonData, RecyclerView rv) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
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
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if (result == 0) {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();
        } else {
            //BIND DATA TO LISTVIEW


            final ClientsListAdapter recyclerViewAdapter=new ClientsListAdapter(c, clients);

            System.out.println("00000");
            rv.setAdapter(recyclerViewAdapter);
            System.out.println("1111");

        }
    }

    private int parseData()
    {

        schedule_id=clientsListFragment.scheduleID;

        System.out.println("caca"+schedule_id);
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;


            clients.clear();
            Client client;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

//                int id=jo.getInt("0");

                String schedule=jo.getString("4");

                if(schedule.equalsIgnoreCase(schedule_id)){



//                String client_id=jo.getString("0");
////                System.out.println(client_id);

                    String clientname=jo.getString("0");
//                System.out.println(clientname);

                    String clientphoto=jo.getString("1");


//                System.out.println("https://spartaapp.azurewebsites.net/Backend/partials/client_images/"+clientphoto);

                    client=new Client();
                    client.setClient_name(clientname);
                    System.out.println(clientname);
                    client.setClient_photo("https://spartaapp.azurewebsites.net/Backend/partials/client_images/"+clientphoto);
                    System.out.println("https://spartaapp.azurewebsites.net/Backend/partials/client_images/"+clientphoto);

                    clients.add(client);
                    System.out.println("clients:"+clients);

                }

            }

            return 1;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
