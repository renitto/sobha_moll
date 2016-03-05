package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 2/28/2016.
 */
public class FragmentDeals extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {
    RecyclerView RV_Deal;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mDealSlider;
    ImageView IV_Deal_Banner;
    String [] deal_names = {"Flat40% off malabar gold and diamonds" , "KFC super wednesdays", "PVR offer"};


    String[] deal_image_urls = {"http://www.franchisezing.com/franchise/wp-content/uploads/2015/04/Malabar-Gold-and-Diamonds.jpg",
            "http://bestonlineoffer.in/wp-content/uploads/2015/07/KFC-So-Good-Wednesday-Offer.png",
            "http://giftsmate.blob.core.windows.net/uploads/2014/04/0000228_inox_cinemas-e1421393774283.jpeg",

    };



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.deal_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_Deal_Banner = (ImageView)view.findViewById(R.id.iv_deal_banner);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.displaybanner.com/images/sample_banner.jpg")
                .into(IV_Deal_Banner);



        // home slider start
        mDealSlider = (SliderLayout)view.findViewById(R.id.deal_slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://images.iimg.in/c/55fbecbdc67118a7f78b45c5-4-501-0-1442573505/google/summer-surprise-myntra-hot-deals-online-desidime.img?crop=1");
        url_maps.put("2", "http://1.bp.blogspot.com/-Axwxy5ObRIc/Vid6hPGsJhI/AAAAAAAAIXw/r2nr7a8lvqI/s1600/myntra.jpg");


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

            mDealSlider.addSlider(textSliderView);
        }
        mDealSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDealSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDealSlider.setCustomAnimation(new DescriptionAnimation());
        mDealSlider.setDuration(4000);
        mDealSlider.addOnPageChangeListener(this);


        // home slider end


        RV_Deal = (RecyclerView)view.findViewById(R.id.recycler_deal_items);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_Deal.setLayoutManager(mLayoutManager);



        DealItemAdapter dealItemAdapter = new DealItemAdapter(getActivity(), deal_names);
//        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(searchresultadapter);
//        scaleAdapter.setInterpolator(new BounceInterpolator());
//        scaleAdapter.setDuration(1000);
        RV_Deal.setAdapter(dealItemAdapter);




        return view;

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDealSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

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




    public class DealItemAdapter
            extends RecyclerView.Adapter<DealItemAdapter.ViewHolder> {

        String [] deal_name;



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;


            public final TextView TV_deal_name;
            public final ImageView IV_deal_image;




            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV_deal_name = (TextView) view.findViewById(R.id.tv_deal_desc);

                IV_deal_image =(ImageView)view.findViewById(R.id.iv_deal_image);





            }


        }


        public DealItemAdapter(Context context, String [] deal_name) {

            this.deal_name = deal_name;

        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deals_item_card, parent, false);




            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.TV_deal_name.setText(deal_name[position]);




//            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
//            holder.TV_deal_name.setTypeface(tf, Typeface.NORMAL);






            Picasso.with(getActivity())
                    .load(deal_image_urls[position])
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(holder.IV_deal_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });







        }

        @Override
        public int getItemCount() {
            return deal_name.length;
        }






    }


}
