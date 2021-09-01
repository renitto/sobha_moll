package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.Application;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelBrands;
import com.example.renitto.scmapp.Model.ModelFashion;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.Adanimation;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Renitto on 4/20/2016.
 */
public class Fragment_dining_menus_data extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener , NetworkManager.onServerDataRequestListener  {
    RecyclerView RV_Shopping;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mShoppingSlider;
    ModelFashion dining_modelFashion;
    HashMap<String,String> url_maps;
    ShoppingItemAdapter shoppingItemAdapter;
    ModelBrands dining_brands;
    private String[] params = new String[1];
    Bundle bundle_fashion= new Bundle();
    FragmentDetail fragmentDetail = new FragmentDetail();

    ConnectionDetector cd;
    Boolean isInternetPresent = false;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.shopping_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        // home slider start
        mShoppingSlider = (SliderLayout)view.findViewById(R.id.shoppping_slider);

        url_maps = new HashMap<String, String>();



        cd = new ConnectionDetector(getActivity());

        isInternetPresent = cd.isConnectingToInternet();






        // home slider end


        RV_Shopping = (RecyclerView)view.findViewById(R.id.recycler_shopping_items);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        RV_Shopping.setLayoutManager(mLayoutManager);


        params[0] = getArguments().getString("shopp_id");

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_SHOPPING_FASHION_CONTENTS, getActivity(), params);
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_BRAND_CONTENTS, getActivity(), params);
        }
        else {
            if(Application.getInstance().dining_modelFashion == null && Application.getInstance().dining_brands == null)
            {
                Toast.makeText(getActivity(),"Please check your internet connection and try again !",Toast.LENGTH_LONG).show();
            }
            else
            {
                dining_modelFashion = Application.getInstance().dining_modelFashion;
                dining_brands = Application.getInstance().dining_brands;

                setSliderData();
                setBrandData();

            }
        }











        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public  void  setSliderData()
    {

        if(dining_modelFashion!=null) {
            if (dining_modelFashion.banner_slider != null) {
                mShoppingSlider.setVisibility(View.VISIBLE);
                setSliders(dining_modelFashion);
            } else
                mShoppingSlider.setVisibility(View.GONE);
        }

    }

    public void setBrandData()
    {

        if (dining_brands != null) {
            shoppingItemAdapter = new ShoppingItemAdapter(getActivity(), dining_brands);
            RV_Shopping.setAdapter(shoppingItemAdapter);
        }

    }





    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mShoppingSlider.stopAutoCycle();
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

        if (whatToShow == NetworkManager.GET_SHOPPING_FASHION_CONTENTS ) {
            if (data != null) {

                dining_modelFashion = (ModelFashion) data;
                Application.getInstance().dining_modelFashion=dining_modelFashion;
                setSliderData();

            }
        }
        else if(whatToShow == NetworkManager.GET_BRAND_CONTENTS )
        {
            if (data != null) {

                dining_brands = (ModelBrands)data;

//                for()

                Application.getInstance().dining_brands=dining_brands;
                setBrandData();
            }
        }
    }

    @Override
    public void onErrorResponse(String error) {

    }


    public class ShoppingItemAdapter
            extends RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> {


        ModelBrands brands;



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;



            public final ImageView IV_shopping_item_image;




            public ViewHolder(View view) {
                super(view);
                mView = view;




                IV_shopping_item_image =(ImageView)view.findViewById(R.id.iv_shopping_item);





            }


        }


        public ShoppingItemAdapter(Context context, ModelBrands brands) {

            this.brands = brands;

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



            int rand=  R.color.dining_color;

            Random randomGenerator = new Random();

            int randomInt = randomGenerator.nextInt(5);


            if (randomInt == 0)
            {
                rand=  R.color.shopping_color;
            }
            else if(randomInt == 1)
            {
                rand=  R.color.dining_color;
            }
            else if(randomInt == 2)
            {
                rand=  R.color.deals_color;
            }
            else if(randomInt == 3)
            {
                rand=  R.color.entertainment_color;
            }
            else if(randomInt == 4)
            {
                rand=  R.color.more_color;
            }






            Picasso.with(getActivity())
                    .load(brands.getBrands()[position].getImage())
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .placeholder(rand)
                    .error(rand)
                    .into(holder.IV_shopping_item_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);



                    bundle_fashion.putString("brandid", brands.getBrands()[position].getBrand_id());
                    fragmentDetail.setArguments(bundle_fashion);

                    // calling detail fragment here
                    getActivity(). getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDetail).addToBackStack("Fashion").commit();
                    //replaceFragment(fragmentDetail);

                }
            });







        }

        @Override
        public int getItemCount() {
            return brands.getBrands().length;
        }






    }


    public void  setSliders(ModelFashion fashion){
        for(int i=0;i<fashion.banner_slider.length;i++){
            url_maps.put("fashion"+i,fashion.banner_slider[i]);
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

            mShoppingSlider.addSlider(textSliderView);
        }
        mShoppingSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mShoppingSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mShoppingSlider.setCustomAnimation(new Adanimation());
        mShoppingSlider.setDuration(4000);
        mShoppingSlider.addOnPageChangeListener(this);


    }

    @Override
    public void onDestroy() {

        mShoppingSlider.stopAutoCycle();
        super.onDestroy();
    }

    @Override
    public void onResume() {

        mShoppingSlider.startAutoCycle();
        super.onResume();
    }


}
