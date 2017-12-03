package com.example.yogi.syncapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by YOGI on 10-11-2017.
 */

public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MySingleton(Context context)
    {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if(requestQueue==null)
        {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return  requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context)
    {
        if(mySingleton==null)
        {
            mySingleton = new MySingleton(context);
        }
        return  mySingleton;
    }

    public<T> void addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }

}

