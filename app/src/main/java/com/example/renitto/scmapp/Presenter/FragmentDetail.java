package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 3/4/2016.
 */
public class FragmentDetail extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {

    SliderLayout mDetailheaderSlider,mDetail_promotion_slider;
    ImageView IV_detail_logo;
    RelativeLayout Rl_menucard_id;
    String str_menu_card_url = "http://i50.tinypic.com/16hs22p.jpg";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.detail_screen,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_detail_logo = (ImageView)view.findViewById(R.id.iv_detail_logo);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://i0.wp.com/insiderlouisville.com/wp-content/uploads/2015/03/new-logo.png")
                .into(IV_detail_logo);



        Rl_menucard_id = (RelativeLayout) view.findViewById(R.id.rl_menucard_id);

        Rl_menucard_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(str_menu_card_url.contains(".pdf"))
                //chrome custom tab click
                ((ActivityHome)getActivity()).showCustomChromTabs(str_menu_card_url);

                else
                {
                    FragmentDetail fd = new FragmentDetail();
                    FragmentMenucard fragmentMenucard = new FragmentMenucard();
                    fragmentMenucard.setTargetFragment(fd , 0);
                    fragmentMenucard.show(getActivity().getFragmentManager(), "menucardDialogFragment");
                }
            }
        });



        // detail header slider start
        mDetailheaderSlider = (SliderLayout)view.findViewById(R.id.detail_head_slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("bucket chicken", "http://s1.stabroeknews.com/images/2016/01/KFC13.jpg");
        url_maps.put("pack", "https://kfc.com.au/media/1042/group_giantfeast.jpg");



        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDetailheaderSlider.addSlider(textSliderView);
        }
        mDetailheaderSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDetailheaderSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDetailheaderSlider.setCustomAnimation(new DescriptionAnimation());
        mDetailheaderSlider.setDuration(3000);
        mDetailheaderSlider.addOnPageChangeListener(this);


        // detail header slider end


        // detail promotion slider start
        mDetail_promotion_slider = (SliderLayout)view.findViewById(R.id.detail_promotion_slider);

        HashMap<String,String> url_maps1 = new HashMap<String, String>();
        url_maps1.put("monday offer", "http://pasolinks.com/wp-content/uploads/2013/01/KFC-Tuesday-Treats.jpg");
        url_maps1.put("offer2", "http://yummychitchat.com/wp-content/uploads/2013/08/2013-0826-KFC-promotion-e1377438176643.jpg");



        for(String name : url_maps1.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps1.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDetail_promotion_slider.addSlider(textSliderView);
        }
        mDetail_promotion_slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDetail_promotion_slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDetail_promotion_slider.setCustomAnimation(new DescriptionAnimation());
        mDetail_promotion_slider.setDuration(7000);
        mDetail_promotion_slider.addOnPageChangeListener(this);


        // home promotion end






        return view;

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }



}
