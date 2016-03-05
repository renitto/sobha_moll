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
 * Created by Renitto on 3/3/2016.
 */
public class FragmentEntertainment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mEntertainmentSlider;
    ImageView IV_Entertainment_Banner,IV_entertainment_logo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.entertainment_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_Entertainment_Banner = (ImageView)view.findViewById(R.id.iv_entertainment_banner);
        IV_entertainment_logo = (ImageView)view.findViewById(R.id.iv_entertainment_logo);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.displaybanner.com/images/sample_banner.jpg")
                .into(IV_Entertainment_Banner);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://static.dnaindia.com/sites/default/files/2015/07/20/357394-inox-twitter.jpeg")
                .into(IV_entertainment_logo);



        // home slider start
        mEntertainmentSlider = (SliderLayout)view.findViewById(R.id.entertaiment_offer_slider);

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

            mEntertainmentSlider.addSlider(textSliderView);
        }
        mEntertainmentSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mEntertainmentSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mEntertainmentSlider.setCustomAnimation(new DescriptionAnimation());
        mEntertainmentSlider.setDuration(4000);
        mEntertainmentSlider.addOnPageChangeListener(this);


        // home slider end




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
