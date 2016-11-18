package selmibenromdhane.sparta_v1.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by sooheib on 11/8/16.
 */

public class ScheduleDownloader extends AsyncTask<Void,Void,String> {


    Context c;
    String urlAddress;
    ListView lv;
    GridView lvv;

    ProgressDialog pd;


    public ScheduleDownloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
      pd.setTitle("Retrieve");
        pd.setMessage("Retrieving...Please wait");
       pd.show();


    }

    @Override
    protected String doInBackground(Void... params) {
        return downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

    pd.dismiss();

        if(jsonData==null)
        {
            Toast.makeText(c,"Unsuccessful,No data Retrieved", Toast.LENGTH_SHORT).show();
        }else {
            //PARSE

            ScheduleParser parser=new ScheduleParser(c,jsonData,lv);
           parser.execute();

        }
    }

    private String downloadData()
    {
        HttpURLConnection con=Connector.connect(urlAddress);
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
}
