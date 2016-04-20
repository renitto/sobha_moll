package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelGeneralQuery;
import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Renitto on 2/26/2016.
 */
public class FragmentAboutMall extends Fragment implements NetworkManager.onServerDataRequestListener {

    ImageView IV_about_banner;
    TextView TV_aboutmall_howtoreach,TV_about_us_description;
    ModelGeneralQuery generalQuery;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.about_mall,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_about_banner = (ImageView) view.findViewById(R.id.iv_about_us_image);
        TV_aboutmall_howtoreach = (TextView)view.findViewById(R.id.tv_aboutmall_howtoreach);
        TV_about_us_description = (TextView)view.findViewById(R.id.tv_about_us_description);

        TV_aboutmall_howtoreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                //calling  how to reach fragment

                ((ActivityHome)getActivity()).replaceFragment(new FragmentHowToReach());
            }
        });


        setAboutData();



        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager.GetDataFromServer(this,NetworkManager.GET_GENERALQUERY_CONTENTS,getActivity(),null);
    }

    @Override
    public void showData(Object data, int whatToShow) {

         generalQuery = (ModelGeneralQuery) data;
        setAboutData();


    }

    public void setAboutData()
    {
        if (generalQuery != null) {
            //setting home banner here
            Picasso.with(getActivity())
                    .load(generalQuery.getImage())

                    .into(IV_about_banner);

            TV_about_us_description.setText(generalQuery.getDescription());
        }
    }

    @Override
    public void onErrorResponse(String error) {

    }
}
