package com.example.renitto.scmapp;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Renitto on 3/1/2016.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // parse settings here
        Parse.initialize(this, "0MLJ23Cb3mB1EmEh7bsCqse4A26PCeIAcaIy4FsV", "d4eC5yOLyoWrpRjkgVnKMyt680ofuNFTq6q2foEb");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
