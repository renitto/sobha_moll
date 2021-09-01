package com.example.renitto.scmapp.Presenter;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelHomeEvent;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by Renitto on 4/22/2016.
 */
public class FragmentHomeSliderDetail extends DialogFragment implements Serializable , NetworkManager.onServerDataRequestListener {

    TextView TV_Home_slider_desc,TV_Home_slider_title;
    ImageView IV_Home_slider_image;
    LinearLayout LL_home_slider_desc;
    private String[] params = new String[1];
    ModelHomeEvent homeEvent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.home_slider_detail,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        TV_Home_slider_desc = (TextView)view.findViewById(R.id.tv_home_slider_desc);

        TV_Home_slider_title = (TextView)view.findViewById(R.id.tv_home_slider_title);

        IV_Home_slider_image = (ImageView)view.findViewById(R.id.iv_home_slider_image);

        LL_home_slider_desc = (LinearLayout)view.findViewById(R.id.L_homeslider_desc);

        TV_Home_slider_title.setText(Html.fromHtml(getArguments().getString("title")));




        Picasso.with(getActivity()).load(getArguments().getString("image"))
//               .resize(getView().getMeasuredWidth(),getView().getMeasuredHeight())
//               .onlyScaleDown()
                .into(IV_Home_slider_image, new Callback() {
                    @Override
                    public void onSuccess() {


                        Bitmap photo = ((BitmapDrawable)IV_Home_slider_image.getDrawable()).getBitmap();
                        // do your processing here....
                        Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                            public void onGenerated(Palette palette) {
                                int mutedLight = palette.getDarkVibrantColor(getActivity().getResources().getColor(android.R.color.black));

                                LL_home_slider_desc.setBackgroundColor(mutedLight);

                            }
                        });


                    }

                    @Override
                    public void onError() {

                    }
                });

        params[0] = getArguments().getString("id");

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_HOME_EVENTS, getActivity(), params);
        }
        else {
            Toast.makeText(getActivity(),"Please check your internet connection and try again !",Toast.LENGTH_LONG).show();
        }




        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setTargetFragment(null, -1);

    }


    @Override
    public void showData(Object data, int whatToShow) {
        homeEvent = (ModelHomeEvent) data;
        if (homeEvent != null)
        {
            setHomeEventDesc();
        }
    }

    public  void setHomeEventDesc()
    {
        TV_Home_slider_desc.setText(Html.fromHtml(homeEvent.getContents()));
    }

    @Override
    public void onErrorResponse(String error) {

    }
}
