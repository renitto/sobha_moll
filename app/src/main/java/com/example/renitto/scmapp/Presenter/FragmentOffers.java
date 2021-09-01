package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.Application;
import com.example.renitto.scmapp.DAL.DbManager;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelOffer;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.Adanimation;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 2/28/2016.
 */
public class FragmentOffers extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener , NetworkManager.onServerDataRequestListener {
    RecyclerView RV_Deal;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mDealSlider;
    ImageView IV_Deal_Banner;
    ModelOffer offer;
    HashMap<String,String> url_maps;

    DealItemAdapter dealItemAdapter;



    Bundle bundle_fashion= new Bundle();
    FragmentDetail fragmentDetail = new FragmentDetail();


    TextView offers_data;

    DbManager dbManager;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.deal_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // setting navigation drawer

        ((ActivityHome)getActivity()).setNavigationDrawerSelected(4);

        IV_Deal_Banner = (ImageView)view.findViewById(R.id.iv_deal_banner);

        offers_data = (TextView ) view.findViewById(R.id.offers_data);



        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.deals_color)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black



       Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Titillium.otf");
        offers_data.setTypeface(font);





        // home slider start
        mDealSlider = (SliderLayout)view.findViewById(R.id.deal_slider);

        url_maps = new HashMap<String, String>();





        // home slider end


        RV_Deal = (RecyclerView)view.findViewById(R.id.recycler_deal_items);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_Deal.setLayoutManager(mLayoutManager);


        if (offer == null )
        {
            offer = dbManager.getOfferData(getActivity());
        }
        if(offer != null )
        {
            setOfferData();
        }



        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DbManager();

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_OFFER_DETAIL_CONTENTS, getActivity(), null);
        }
        else {
            offer = dbManager.getOfferData(getActivity());
            if(offer == null )
            {
                Toast.makeText(getActivity(),"Please check your internet connection and try again !",Toast.LENGTH_SHORT).show();
            }
        }
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

    @Override
    public void showData(Object data, int whatToShow) {

        if (data!=null)
        {
            offer= (ModelOffer)data;
//            Application.getInstance().offer = offer;
            dbManager.insertToOffer(getActivity(),offer);
            setOfferData();


        }
    }

    public void setOfferData()
    {

        if (offer != null) {
            //setting home banner here
            Picasso.with(getActivity())
                    .load(offer.getBanner_art())
                    .into(IV_Deal_Banner);

            if (offer.offers != null)
                setSliders(offer);

            dealItemAdapter = new DealItemAdapter(getActivity(), offer);
            RV_Deal.setAdapter(dealItemAdapter);
        }


    }

    @Override
    public void onErrorResponse(String error) {

    }


    public class DealItemAdapter
            extends RecyclerView.Adapter<DealItemAdapter.ViewHolder> {

        ModelOffer offer;



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


        public DealItemAdapter(Context context, ModelOffer offer) {

            this.offer = offer;

        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.deals_item_card, parent, false);




            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




//            holder.TV_deal_name.setText(offer[position]);




//            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
//            holder.TV_deal_name.setTypeface(tf, Typeface.NORMAL);






            Picasso.with(getActivity())
                    .load(offer.offers[position].getImage())
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(holder.IV_deal_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);



                    bundle_fashion.putString("brandid", offer.offers[position].getStore_id());
                    fragmentDetail.setArguments(bundle_fashion);

                    // calling detail fragment here
                    getActivity(). getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDetail).addToBackStack("Fashion").commit();
                    //replaceFragment(fragmentDetail);

                }
            });







        }

        @Override
        public int getItemCount() {
            return offer.offers.length;
        }






    }

    public void  setSliders(ModelOffer modelOffer){
        for(int i=0;i<modelOffer.banner_slider.length;i++){
            url_maps.put("fashion"+i,modelOffer.banner_slider[i]);
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

            mDealSlider.addSlider(textSliderView);
        }
        mDealSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDealSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDealSlider.setCustomAnimation(new Adanimation());
        mDealSlider.setDuration(4000);
        mDealSlider.addOnPageChangeListener(this);



    }



}
