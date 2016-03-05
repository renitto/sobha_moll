package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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



        // detail header slider start
        mDetailheaderSlider = (SliderLayout)view.findViewById(R.id.detail_head_slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");


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
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");


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
        mDetail_promotion_slider.setDuration(6000);
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
