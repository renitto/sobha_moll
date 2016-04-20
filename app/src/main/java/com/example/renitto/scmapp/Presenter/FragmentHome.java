package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelHomeContent;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 2/26/2016.
 */
public class FragmentHome extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, NetworkManager.onServerDataRequestListener  {

    @Override
    public void showData(Object data, int whatToShow) {

    }

    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    Typeface font;

    TextView tv_whats_happening;

    public void  setSliders(ModelHomeContent homeContent){
        for(int i=0;i<homeContent.banner_slider.length;i++){
            url_maps.put(homeContent.banner_slider[i].title,homeContent.banner_slider[i].image);
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

            mHomeSlider.addSlider(textSliderView);
        }
        mHomeSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mHomeSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mHomeSlider.setCustomAnimation(new DescriptionAnimation());
        mHomeSlider.setDuration(4000);
        mHomeSlider.addOnPageChangeListener(this);
        //setting home banner here
        Picasso.with(getActivity())
                .load(homeContent.banner_art)
                .into(IV_home_Banner);

         category_image_urls[0] = homeContent.tile_images.getShopping();
         category_image_urls[1] = homeContent.tile_images.getDining();
         category_image_urls[2] = homeContent.tile_images.getEntertainment();
         category_image_urls[3] = homeContent.tile_images.getOffers();
    }





    RecyclerView RV_Category;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mHomeSlider;
    ImageView IV_home_Banner;  HashMap<String,String> url_maps;
    String [] category_names = {"SHOPPING", "DINING", "ENTERTAINMENT","DEALS"};


    String[] category_image_urls = new String[4] ;

    int[] categorycolors;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.home_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_home_Banner = (ImageView)view.findViewById(R.id.iv_home_banner);

        tv_whats_happening = (TextView)view.findViewById(R.id.whts_happening);


        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Titillium.otf");
        tv_whats_happening.setTypeface(font);





        // home slider start
        mHomeSlider = (SliderLayout)view.findViewById(R.id.home_slider);

         url_maps = new HashMap<String, String>();


        cd = new ConnectionDetector(getActivity());

        isInternetPresent = cd.isConnectingToInternet();


        if (isInternetPresent)
        NetworkManager.GetDataFromServer(this,NetworkManager.GET_HOME_CONTENTS,getActivity(),null);
        else
            Toast.makeText(getActivity(),"Please check your internet connection and try again !",Toast.LENGTH_LONG).show();


        // home slider end
        RV_Category = (RecyclerView)view.findViewById(R.id.recycler_home_category);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_Category.setLayoutManager(mLayoutManager);
        categorycolors = this.getResources().getIntArray(R.array.category_colors);
        CategoryItemAdapter categoryItemAdapter = new CategoryItemAdapter(getActivity(), category_names);
        RV_Category.setAdapter(categoryItemAdapter);




        return view;

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mHomeSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

//        FragmentHome home = new FragmentHome();
//        FragmentWhatHappening whatHappening = new FragmentWhatHappening();
//        Bundle args = new Bundle();
//        args.putString("img",movieDetails.getAvailable()[position].getSynopsis() );
//        whatHappening.setArguments(args);
//        whatHappening.setTargetFragment(home , 0);
//        whatHappening.show(getActivity().getFragmentManager(), "WhatHappeningDialogFragment");

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
    public void onErrorResponse(String error) {

    }


    public class CategoryItemAdapter
            extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

        String [] category_name;



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;


            public final TextView TV_category_name;
            public final ImageView IV_category_image;
            public  final RelativeLayout RV_home_category;



            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV_category_name = (TextView) view.findViewById(R.id.tv_home_category_name);

                IV_category_image =(ImageView)view.findViewById(R.id.iv_home_category_image);

                RV_home_category= (RelativeLayout)view.findViewById(R.id.rv_home_category);



            }


        }


        public CategoryItemAdapter(Context context, String [] category_name) {

            this.category_name = category_name;

        }

        @Override
        public int getItemViewType(int position) {
            // Just as an example, return 0 or 2 depending on position
            // Note that unlike in ListView adapters, types don't have to be contiguous
            //odd or even recycler identifying here.
            return position % 2 * 2;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_item_type_two, parent, false);

            switch (viewType) {
                case 0:
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.home_item_type_one, parent, false);

                    break;

                case 2:
                    view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.home_item_type_two, parent, false);

                    break;
            }


            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.TV_category_name.setText(category_name[position]);
            holder.TV_category_name.setTypeface(font);




//            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
//            holder.TV_deal_name.setTypeface(tf, Typeface.NORMAL);


            holder.RV_home_category.setBackgroundColor(categorycolors[position]);



            Picasso.with(getActivity()).load(category_image_urls[position])
//                    .placeholder(R.color.black)
//                    .error(R.color.colorPrimary)
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(holder.IV_category_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (category_name[position].equals("SHOPPING"))
                    {
//                        Bundle bundle_shopping=new Bundle();
//                        bundle_shopping.putString("fromhome", "Fashion");

                        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

                        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.shopping_color)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling shopping fragment

//                        Fragment_shopping fragment_shopping= new Fragment_shopping();
                        //calling fashion fragment

//                        fragment_shopping.setArguments(bundle_shopping);

                        replaceFragment(new Fragment_shopping());

                    }
                    else  if (category_name[position].equals("DINING"))
                    {
                        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

                        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.dining_color)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling dining fragment

                       replaceFragment(new FragmentDining());

                    }
                    else  if (category_name[position].equals("ENTERTAINMENT"))
                    {



                        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

                        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.entertainment_color)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling entertainment fragment

                        replaceFragment(new FragmentMollywood());
                    }
                    else  if (category_name[position].equals("DEALS"))
                    {
                        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

                        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.deals_color)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling deals fragment

                        replaceFragment(new FragmentOffers());

                    }



                }
            });







        }

        @Override
        public int getItemCount() {
            return category_name.length;
        }






    }

    // for checking each fragmnet in back stack
    public void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.

            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(backStateName).commit();

        }
    }

    @Override
    public void onDestroy() {

        mHomeSlider.stopAutoCycle();
        super.onDestroy();
    }

    @Override
    public void onResume() {

        mHomeSlider.startAutoCycle();
        super.onResume();
    }
}
