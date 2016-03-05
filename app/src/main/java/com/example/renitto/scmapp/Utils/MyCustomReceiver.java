package com.example.renitto.scmapp.Utils;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.renitto.scmapp.Presenter.ActivityHome;
import com.example.renitto.scmapp.R;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Renitto on 3/1/2016.
 */
public class MyCustomReceiver extends ParsePushBroadcastReceiver {
    private static final String TAG = "MyCustomReceiver";
    String STR_Header, STR_Description, STR_ImgUrl;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String action = intent.getAction();
            String channel = intent.getExtras().getString("com.parse.Channel");
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

            STR_Header = json.getString("header").toString();
            STR_Description = json.getString("description").toString();
            STR_ImgUrl = json.getString("imgUrl").toString();


            Log.d("", "========================MESSAGE" + json.getString("alert").toString());

            // Custom behavior goes here

        } catch (JSONException e) {
            Log.d(TAG, "JSONException: " + e.getMessage());
        }

        ShowNotification(context);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void ShowNotification(Context context) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        int id = (int) System.currentTimeMillis();
        int mId = 1;
        URL url = null;
        Bitmap bmp = null;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.marker_green)
                        .setContentTitle(STR_Header)
                        .setContentText(STR_Description);
                      /*  .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.myntra));
*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            mBuilder.setSmallIcon(R.drawable.marker_green);
            mBuilder.setColor(Color.RED);
          /*  mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.myntra));*/

        }


        try {
            url = new URL(STR_ImgUrl);

            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        NotificationCompat.BigPictureStyle bigPicStyle
                = new NotificationCompat.BigPictureStyle();
        // Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pepper_icon);
        bigPicStyle.bigPicture(bmp);
        bigPicStyle.setSummaryText(STR_Description);
        mBuilder.setStyle(bigPicStyle);
        Intent resultIntent = new Intent(context, ActivityHome.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ActivityHome.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        // A PendingIntent is used to specify the action which should be performed
        // once the user select the notification.

        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        // Just add a fake action here, max 3
              /*  mBuilder.addAction(R.drawable.ic_action_search, "Search", resultPendingIntent);
                mBuilder.addAction(R.drawable.ic_action_search, "Add", resultPendingIntent);
                mBuilder.addAction(R.drawable.ic_action_search, "Save", resultPendingIntent);*/
        // Hide the notification after its selected

        mBuilder.setAutoCancel(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());

    }

}

