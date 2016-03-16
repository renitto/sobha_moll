package com.example.renitto.scmapp.Presenter;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Renitto on 3/7/2016.
 */
public class FragmentMenucard extends DialogFragment {
    ImageView IV_menucard_image;
    Button BT_menucard_close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.menucard,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }



        IV_menucard_image = (ImageView) view.findViewById(R.id.iv_menucard_image);
        BT_menucard_close = (Button) view.findViewById(R.id.bt_menucard_close);

        //setting menu card image here
        Picasso.with(getActivity())
                .load("http://www.sensationslife.in/sites/default/files/sobha-city-mall-1.jpg")

                .into(IV_menucard_image);

        // clos button click

        BT_menucard_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return view;
    }
}
