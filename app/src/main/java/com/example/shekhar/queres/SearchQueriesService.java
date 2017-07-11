package com.example.shekhar.queres;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.sql.RowId;
import java.util.List;

/**
 * Created by Shekhar on 10/07/17.
 */

public class SearchQueriesService extends Service{
    private static final String TAG = "SearchQueriesService";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"Started service");
        searchResults(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void searchResults(Context c){
        //search for results
       // searchforResults();
        showNotification(c);
    }



    private  void showNotification(Context ctx){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx)
                                                        .setSmallIcon(R.drawable.ic_launcher)
                                                        .setContentTitle("Hello Chacha")
                                                        .setContentText("You wanted to know about : " + new SavePreferences(ctx).getStringSetPref().get(0) + "! Click to see more");
        Intent notifacationIntent = new Intent(ctx, ShowResults.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, notifacationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Service.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
        Log.i(TAG,"Notification sent");
        stopSelf();
    }
}
