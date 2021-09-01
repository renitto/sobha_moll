package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renitto.scmapp.Application;
import com.example.renitto.scmapp.DAL.DbManager;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelGeneralQuery;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

/**
 * Created by Renitto on 2/26/2016.
 */
public class FragmentAboutMall extends Fragment implements NetworkManager.onServerDataRequestListener {

    ImageView IV_about_banner;
    TextView TV_aboutmall_howtoreach,TV_about_us_description;
    ModelGeneralQuery generalQuery;
    DbManager dbManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.about_mall,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // setting navigation drawer

        ((ActivityHome)getActivity()).setNavigationDrawerSelected(5);

        IV_about_banner = (ImageView) view.findViewById(R.id.iv_about_us_image);
        TV_aboutmall_howtoreach = (TextView)view.findViewById(R.id.tv_aboutmall_howtoreach);
        TV_about_us_description = (TextView)view.findViewById(R.id.tv_about_us_description);



        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black


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


        if (generalQuery == null )
        {
            generalQuery = dbManager.getAboutMall(getActivity());
        }
        if(generalQuery != null )
        {
            setAboutData();
        }



        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager =  new DbManager();

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_GENERALQUERY_CONTENTS, getActivity(), null);
        }
        else {
            generalQuery = dbManager.getAboutMall(getActivity());
            if(generalQuery == null )
            {
                Toast.makeText(getActivity(),"Please check your internet connection and try again !",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void showData(Object data, int whatToShow) {

         generalQuery = (ModelGeneralQuery) data;
       dbManager.insertAboutMall(getActivity(),generalQuery); ;
        setAboutData();


    }

    public void setAboutData()
    {
        if (generalQuery != null) {


            ViewTreeObserver vto = IV_about_banner.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    IV_about_banner.getViewTreeObserver().removeOnPreDrawListener(this);
                    int   logo_height = IV_about_banner.getMeasuredHeight();
                    int   logo_width = IV_about_banner.getMeasuredWidth();
                    //setting home banner here
                    Picasso.with(getActivity())
                            .load(generalQuery.getImage())
                            .placeholder(R.drawable.preview_movie_im)
                            .error(R.drawable.preview_movie_im)
                            .resize(logo_width,logo_height)
                            .onlyScaleDown()
                            .into(IV_about_banner);



                    return true;
                }
            });


            TV_about_us_description.setText(Html.fromHtml(generalQuery.getDescription()));
        }
    }

    @Override
    public void onErrorResponse(String error) {

    }
}
