package com.example.renitto.scmapp.Presenter;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.TextView;

import com.example.renitto.scmapp.R;


/**
 * Created by Renitto on 3/28/2016.
 */
public class FragmentDescription extends DialogFragment {
    TextView TV_movie_desc;
    String desc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.desc_entertainment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

       desc = getArguments().getString("detail");

        TV_movie_desc = (TextView)view.findViewById(R.id.movie_desc_dialog);

     TV_movie_desc.setText(Html.fromHtml(desc));

        // clos button click
//
//        BT_whathappening_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dismiss();
//            }
//        });

        return view;
    }
}

