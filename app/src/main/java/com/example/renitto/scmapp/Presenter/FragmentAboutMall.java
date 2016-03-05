package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Renitto on 2/26/2016.
 */
public class FragmentAboutMall extends Fragment {

    ImageView IV_about_banner;
    TextView TV_aboutmall_howtoreach;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.about_mall,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_about_banner = (ImageView) view.findViewById(R.id.iv_about_us_image);
        TV_aboutmall_howtoreach = (TextView)view.findViewById(R.id.tv_aboutmall_howtoreach);

        TV_aboutmall_howtoreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().findViewById(R.id.sp_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // setting base colour
                getActivity().findViewById(R.id.sp_menu_more).setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black


                getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                //calling  how to reach fragment

                FragmentHowToReach  fragmentHowToReach= new FragmentHowToReach();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentHowToReach).addToBackStack("Aboutmall").commit();
            }
        });

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.sensationslife.in/sites/default/files/sobha-city-mall-1.jpg")

                .into(IV_about_banner);
        return view;
    }
}
