package com.example.renitto.scmapp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Renitto on 3/1/2016.
 */
public class Application extends android.app.Application {


    public static final String TAG = Application.class
            .getSimpleName();
    private static Application mInstance;
    private RequestQueue mRequestQueue;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

        // parse settings here
        Parse.initialize(this, "0MLJ23Cb3mB1EmEh7bsCqse4A26PCeIAcaIy4FsV", "d4eC5yOLyoWrpRjkgVnKMyt680ofuNFTq6q2foEb");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
    public static synchronized Application getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
