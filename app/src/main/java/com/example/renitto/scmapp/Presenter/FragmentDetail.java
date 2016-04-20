package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Intent;
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
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelBrandDetails;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.Adanimation;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 3/4/2016.
 */
public class FragmentDetail extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener ,NetworkManager.onServerDataRequestListener  {

    SliderLayout mDetailheaderSlider,mDetail_promotion_slider;
    ImageView IV_detail_logo;
    RelativeLayout Rl_menucard_id;
    String str_menu_card_url = "http://i50.tinypic.com/16hs22p.jpg";
    ModelBrandDetails brandDetails;
    HashMap<String,String> url_maps,url_maps1;
    private String[] params = new String[1];

    TextView TV_detail_web,TV_detail_email,TV_detail_contact_no,TV_detail_openhours,TV_detail_brand_name,TV_detail_brand_description;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.detail_screen,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_detail_logo = (ImageView)view.findViewById(R.id.iv_detail_logo);

        TV_detail_contact_no = (TextView) view.findViewById(R.id.tv_detail_contact_no);
        TV_detail_web = (TextView) view.findViewById(R.id.tv_detail_web);
        TV_detail_email = (TextView) view.findViewById(R.id.tv_detail_email);
        TV_detail_openhours = (TextView) view.findViewById(R.id.tv_detail_openhours);
        TV_detail_brand_name = (TextView) view.findViewById(R.id.tv_detail_name);
        TV_detail_brand_description = (TextView) view.findViewById(R.id.tv_detail_brand_description);






        Rl_menucard_id = (RelativeLayout) view.findViewById(R.id.rl_menucard_id);





        // detail header slider start
        mDetailheaderSlider = (SliderLayout)view.findViewById(R.id.detail_head_slider);

         url_maps = new HashMap<String, String>();



        // detail promotion slider start
        mDetail_promotion_slider = (SliderLayout)view.findViewById(R.id.detail_promotion_slider);

         url_maps1 = new HashMap<String, String>();




        params[0]=  getArguments().getString("brandid");;


        NetworkManager.GetDataFromServer(this,NetworkManager.GET_BRAND_DETAIL_CONTENTS,getActivity(),params);



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


        TV_detail_brand_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDetail detail = new FragmentDetail();
                FragmentDescription description = new FragmentDescription();
                Bundle args = new Bundle();
                args.putString("detail",TV_detail_brand_description.getText().toString() );
                description.setArguments(args);
                description.setTargetFragment(detail , 0);
                description.show(getActivity().getFragmentManager(), "itemdesc");
            }
        });




        TV_detail_contact_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TV_detail_contact_no.getText() != null && !TV_detail_contact_no.getText().toString().equals(""))
                {
                    // calling to that number
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+"+TV_detail_contact_no.getText().toString()));
                    startActivity(callIntent );
                }
            }
        });

        TV_detail_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TV_detail_email.getText() != null && !TV_detail_email.getText().toString().equals(""))
                {
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + TV_detail_email.getText()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "About "+TV_detail_brand_name.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, "Hello "+TV_detail_brand_name.getText().toString());
                    startActivity(intent);
                }
            }
        });

        TV_detail_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TV_detail_web.getText() != null && !TV_detail_web.getText().toString().equals(""))
                {
                    //chrome custom tab click
                    ((ActivityHome)getActivity()).showCustomChromTabs("http://"+TV_detail_web.getText().toString());


                }
            }
        });






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


    @Override
    public void showData(Object data, int whatToShow) {
        if (data != null)
        {
            brandDetails = (ModelBrandDetails)data;

            //setting home banner here
            Picasso.with(getActivity())
                    .load(brandDetails.getLogo())
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(IV_detail_logo);

            TV_detail_contact_no.setText(brandDetails.getContact_number());
            TV_detail_email.setText(brandDetails.getEmail());
            TV_detail_openhours.setText(brandDetails.getWorking_time());
            TV_detail_web.setText(brandDetails.getWebsite());

            TV_detail_brand_description.setText(brandDetails.getDescription());
            TV_detail_brand_name.setText(brandDetails.getTitle());




            if (brandDetails.banner_slider != null)
            setHeaderSliders(brandDetails);
            if (brandDetails.offer_slider != null)
            setBottomSliders(brandDetails);

//            str_menu_card_url = brandDetails.


        }
    }

    @Override
    public void onErrorResponse(String error) {

    }

    public void  setHeaderSliders(ModelBrandDetails brandDetails){
        for(int i=0;i<brandDetails.banner_slider.length;i++){
            url_maps.put("header"+i,brandDetails.banner_slider[i]);
        }

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
        mDetailheaderSlider.setCustomAnimation(new Adanimation());
        mDetailheaderSlider.setDuration(3000);
        mDetailheaderSlider.addOnPageChangeListener(this);


    }

    public void  setBottomSliders(ModelBrandDetails brandDetails){
        for(int j=0;j<brandDetails.offer_slider.length;j++){
            url_maps.put("bottom"+j,brandDetails.offer_slider[j]);
        }

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
        mDetail_promotion_slider.setCustomAnimation(new Adanimation());
        mDetail_promotion_slider.setDuration(7000);
        mDetail_promotion_slider.addOnPageChangeListener(this);


    }

}
