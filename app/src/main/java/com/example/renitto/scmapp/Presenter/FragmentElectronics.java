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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 3/12/2016.
 */
public class FragmentElectronics extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {
    RecyclerView RV_Shopping;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mShoppingSlider;



    String[] brand_image_urls = {"https://www.lgcns.com/Content/20100825_V1/Images/img_lgcns_symbol.gif",
            "http://techfuture.co.uk/wp-content/uploads/2013/06/mtrlg.jpg",
            "http://images.forbes.com/media/lists/companies/samsung-electronics_416x416.jpg",
            "https://media.alienwarearena.com/media/Alienware-Logo-15.jpg"

    };



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.shopping_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        // home slider start
        mShoppingSlider = (SliderLayout)view.findViewById(R.id.shoppping_slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("iphone", "http://cdn.theatlantic.com/assets/media/img/posts/2014/11/apple_iphone6/a71a7d922.jpg");
        url_maps.put("xbox", "http://ichef-1.bbci.co.uk/news/624/media/images/75494000/jpg/_75494497_ed676842-73ff-42e0-807f-8092ef07be6e.jpg");



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
        mShoppingSlider.setCustomAnimation(new DescriptionAnimation());
        mShoppingSlider.setDuration(4000);
        mShoppingSlider.addOnPageChangeListener(this);


        // home slider end


        RV_Shopping = (RecyclerView)view.findViewById(R.id.recycler_shopping_items);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        RV_Shopping.setLayoutManager(mLayoutManager);



        ShoppingItemAdapter shoppingItemAdapter = new ShoppingItemAdapter(getActivity(), brand_image_urls);
//        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(searchresultadapter);
//        scaleAdapter.setInterpolator(new BounceInterpolator());
//        scaleAdapter.setDuration(1000);
        RV_Shopping.setAdapter(shoppingItemAdapter);




        return view;

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




    public class ShoppingItemAdapter
            extends RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> {

        String [] shopping_item_name;



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


        public ShoppingItemAdapter(Context context, String [] shopping_item_name) {

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
                    .into(holder.IV_shopping_item_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    // calling detail fragment here

                    ((ActivityHome)getActivity()).replaceFragment(new FragmentDetail());

                }
            });







        }

        @Override
        public int getItemCount() {
            return brand_image_urls.length;
        }






    }


}
