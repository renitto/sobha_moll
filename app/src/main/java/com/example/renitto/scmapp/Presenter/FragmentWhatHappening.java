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
import android.widget.TextView;

import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Renitto on 3/21/2016.
 */
public class FragmentWhatHappening extends DialogFragment {
    ImageView IV_whathappening_image;
    Button BT_whathappening_close;
    TextView TV_whathappening_title,TV_whathappening_desc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.what_happening,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        TV_whathappening_title = (TextView)view.findViewById(R.id.tv_what_happening_title);
        TV_whathappening_desc = (TextView)view.findViewById(R.id.tv_what_happening_desc);
        IV_whathappening_image = (ImageView) view.findViewById(R.id.iv_whathappening_image);
        BT_whathappening_close = (Button) view.findViewById(R.id.bt_whathappening_close);

        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black

        //setting menu card image here
        Picasso.with(getActivity())
                .load("http://www.sensationslife.in/sites/default/files/sobha-city-mall-1.jpg")

                .into(IV_whathappening_image);

        // clos button click

        BT_whathappening_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return view;
    }
}
