package com.example.shekhar.queres;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Shekhar on 09/07/17.
 */

public class ConnectionListener extends BroadcastReceiver{
    private final static String TAG = "ConnectionListener" ;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null && intent.getExtras() != null){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
                context.startService(new Intent(context, SearchQueriesService.class));
                Log.i(TAG,"Network Connected !");
            }else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)){
                Log.i(TAG,"Network not Connected !");
            }
        }
    }

}
