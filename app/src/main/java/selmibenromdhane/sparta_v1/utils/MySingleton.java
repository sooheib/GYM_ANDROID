package selmibenromdhane.sparta_v1.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mounir on 18/11/2016.
 */

public class MySingleton {
    private static MySingleton minstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    public MySingleton(Context context)
    {
        mCtx=context;
        requestQueue=getRequestQueue();
    }


    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
        {
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;
    }

    public static synchronized MySingleton getinstance (Context context)
    {
        if(minstance==null)
        {
            minstance=new MySingleton(context);
        }
        return minstance;
    }

    public <T> void addToRequest(Request<T> request)
    {
        requestQueue.add(request);

    }


}
