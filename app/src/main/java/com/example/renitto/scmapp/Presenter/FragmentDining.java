package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * Created by Renitto on 2/29/2016.
 */
public class FragmentDining extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {
    RecyclerView RV_Dining;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mDiningSlider;
    ImageView IV_Dining_Banner;


    String[] brand_image_urls = {"https://pbs.twimg.com/profile_images/648460751863083010/yOGdridr.png",
            "https://nokiatheone.files.wordpress.com/2013/10/cafe-coffee-day-app-on-windows-phone.png",
            "http://file.answcdn.com/answ-cld/image/upload/v1/tk/brand_image/0a1a0124/72e6a9f817baccc5bc7d97d003b36117df01eecd.jpeg",
            "http://www.webdesigncore.com/wp-content/uploads/2011/12/foodlogodesign19.jpg",
            "http://dic.academic.ru/pictures/wiki/files/75/KFC_logo.png",

    };



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.dining_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_Dining_Banner = (ImageView)view.findViewById(R.id.iv_dining_banner);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.displaybanner.com/images/sample_banner.jpg")
                .into(IV_Dining_Banner);



        // home slider start
        mDiningSlider = (SliderLayout)view.findViewById(R.id.dining_slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://www.seattleorganicrestaurants.com/vegan-whole-foods/images/Food-Guidelines.jpg");
        url_maps.put("2", "http://images.indiatvnews.com/lifestylelifestyle/2014/food-fact1.jpg");


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

            mDiningSlider.addSlider(textSliderView);
        }
        mDiningSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDiningSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDiningSlider.setCustomAnimation(new DescriptionAnimation());
        mDiningSlider.setDuration(4000);
        mDiningSlider.addOnPageChangeListener(this);


        // home slider end


        RV_Dining = (RecyclerView)view.findViewById(R.id.recycler_dining_items);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        RV_Dining.setLayoutManager(mLayoutManager);



        final DiningItemAdapter diningItemAdapter = new DiningItemAdapter(getActivity(), brand_image_urls);
//        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(searchresultadapter);
//        scaleAdapter.setInterpolator(new BounceInterpolator());
//        scaleAdapter.setDuration(1000);
        RV_Dining.setAdapter(diningItemAdapter);



        // smooth scroll for recyclerview
//          handler = new Handler();
//          runnable = new Runnable() {
//              int position =0;
//            public void run() {
//                if( position >= diningItemAdapter.getItemCount()){
//                    position = 0;
//                }else{
//                    position = position+1;
//                }
//                RV_Dining.scrollToPosition(position);
//                handler.postDelayed(runnable, 10000);
//            }
//        };

//        RV_Dining.post(new Runnable() {
//            @Override
//            public void run() {
//                //call smooth scroll
//                RV_Dining.smoothScrollToPosition(diningItemAdapter.getItemCount()-1);
//            }
//        });

        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
//        if (handler!= null) {
//            handler.removeCallbacks(runnable);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDiningSlider.stopAutoCycle();
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




    public class DiningItemAdapter
            extends RecyclerView.Adapter<DiningItemAdapter.ViewHolder> {

        String [] shopping_item_name;



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;



            public final ImageView IV_dining_item_image;




            public ViewHolder(View view) {
                super(view);
                mView = view;




                IV_dining_item_image =(ImageView)view.findViewById(R.id.iv_shopping_item);





            }


        }


        public DiningItemAdapter(Context context, String [] shopping_item_name) {

            this.shopping_item_name = shopping_item_name;

        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shopping_item, parent, false);




            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {









//            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
//            holder.TV_deal_name.setTypeface(tf, Typeface.NORMAL);






            Picasso.with(getActivity())
                    .load(brand_image_urls[position])
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(holder.IV_dining_item_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // calling detail fragment here



                    FragmentDetail fragmentDetail= new FragmentDetail();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDetail).addToBackStack("Details").commit();

                }
            });







        }

        @Override
        public int getItemCount() {
            return brand_image_urls.length;
        }






    }


}
