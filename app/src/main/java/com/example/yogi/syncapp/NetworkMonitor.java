package com.example.yogi.syncapp;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YOGI on 12-11-2017.
 */

public class NetworkMonitor extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {

        if(checkInternetConnection(context))
        {

            final DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

            Cursor cursor = dbHelper.readFromLocalDatabase(sqLiteDatabase);

            while (cursor.moveToNext())
            {
                int sync_status = cursor.getInt(cursor.getColumnIndex(DbContract.SYNC_STATUS));
                if(sync_status==DbContract.SYNC_STATUS_FAILED)
                {
                    final String name = cursor.getString(cursor.getColumnIndex(DbContract.NAME));
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        SQLiteDatabase sqLiteDatabase1 = dbHelper.getWritableDatabase();

                                        JSONObject jsonObject = new JSONObject(response);
                                        String Response = jsonObject.getString("response");
                                        if(Response.equals("ok"))
                                        {
                                            Log.d("YOGI","updated in the database "+name);
                                            dbHelper.updateLocalDatabase(name,DbContract.SYNC_STATUS_OK,sqLiteDatabase1);
                                            context.sendBroadcast(new Intent(DbContract.UI_UPDATE_BROADCAST));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("name",name);
                            return params;
                        }
                    };

                    MySingleton.getInstance(context).addToRequestQueue(stringRequest);
                }
            }

            dbHelper.close();
        }
    }

    public boolean checkInternetConnection(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isConnected());
    }
}
