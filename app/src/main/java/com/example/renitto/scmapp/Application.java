package com.example.renitto.scmapp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.renitto.scmapp.Model.ModelBrands;
import com.example.renitto.scmapp.Model.ModelEntertainmentBrand;
import com.example.renitto.scmapp.Model.ModelFashion;
import com.example.renitto.scmapp.Model.ModelGeneralQuery;
import com.example.renitto.scmapp.Model.ModelHomeContent;
import com.example.renitto.scmapp.Model.ModelMovieDetails;
import com.example.renitto.scmapp.Model.ModelOffer;
import com.example.renitto.scmapp.Model.ModelSubCategories;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.splunk.mint.Mint;

/**
 * Created by Renitto on 3/1/2016.
 */
public class Application extends android.app.Application {


    public static final String TAG = Application.class
            .getSimpleName();
    private static Application mInstance;
    private RequestQueue mRequestQueue;
    public ModelFashion modelFashion;
    public ModelBrands brands;

    public ModelFashion dining_modelFashion;
    public ModelBrands dining_brands;

    public ModelOffer offer;

    public ModelFashion fashion;
    public ModelSubCategories subCategories;

    public ModelFashion dining_fashion;
    public ModelSubCategories dining_subCategories;

    public  ModelHomeContent myHomeContent;
    public ModelEntertainmentBrand entertainmentBrand;
    public ModelMovieDetails movieDetails ;
    public ModelGeneralQuery generalQuery;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

        // splunkmint for logs
        Mint.enableDebugLog();
        Mint.initAndStartSession(this, "db74393c");


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
//        req.setShouldCache(false); // not adding to cache
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
