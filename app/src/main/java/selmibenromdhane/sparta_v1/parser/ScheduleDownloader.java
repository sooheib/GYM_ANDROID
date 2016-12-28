package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import selmibenromdhane.sparta_v1.utils.Connector;

/**
 * Created by sooheib on 11/8/16.
 */

public class ScheduleDownloader extends AsyncTask<Void,Void,String> implements View.OnClickListener {


    Context c;
    String urlAddress;
    ListView lv;
    GridView lvv;
    String jsondata;

    ProgressDialog pd;
    SwipeRefreshLayout swipeRefreshLayout;

    public ScheduleDownloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;

        System.out.println("hello1");

    }

    public ScheduleDownloader(Context c, String urlSchedule, ListView classListView, SwipeRefreshLayout swipeRefreshLayout) {
        this.c = c;
        this.urlAddress = urlSchedule;
        this.lv = classListView;
        this.swipeRefreshLayout=swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving...Please wait");
        pd.show();



        System.out.println("hello2");

    }

    @Override
    protected String doInBackground(Void... params) {

        return downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);



        pd.dismiss();

       // swipeRefreshLayout.setRefreshing(false);

        if(jsonData==null)
        {
            Toast.makeText(c,"Unsuccessful,No data Retrieved", Toast.LENGTH_SHORT).show();
        }else {
            //PARSE

            System.out.println("hello2");

          //  ScheduleParser parser=new ScheduleParser(c,jsonData,lv);

            ScheduleParser parser=new ScheduleParser(c,jsonData,lv,swipeRefreshLayout);

            swipeRefreshLayout.setOnClickListener(this);



            parser.execute();

        }
    }

    private String downloadData()
    {

        HttpURLConnection con= Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }

        try
        {
            InputStream is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData=new StringBuffer();


            while ((line=br.readLine()) != null)
            {
                jsonData.append(line+"\n");
            }

            br.close();
            is.close();

            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onClick(View v) {

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // parseData();
                doInBackground();
                onPostExecute(jsondata);
               ScheduleParser parser=new ScheduleParser(c,jsondata,lv,swipeRefreshLayout);
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }
}