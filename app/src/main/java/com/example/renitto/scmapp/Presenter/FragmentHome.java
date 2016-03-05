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
import android.widget.RelativeLayout;
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
 * Created by Renitto on 2/26/2016.
 */
public class FragmentHome extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {
    RecyclerView RV_Category;
    RecyclerView.LayoutManager mLayoutManager;
    SliderLayout mHomeSlider;
    ImageView IV_home_Banner;
    String [] category_names = {"SHOPPING", "DINING", "ENTERTAINMENT","DEALS"};


    String[] category_image_urls = {"http://www.wholesale7.net/images/201304/goods_img/78852_P_1365300434620.jpg",
            "http://salishlodge.com/images/masthead_dining_06.jpg",
            "http://cinemalive.in/wp-content/uploads/2015/12/Monsoon-Mangoes-Release-Date.jpg",
            "http://www.fundoosale.com/saleAdImage/levis-end-of-season-sale-jan-3-2014.jpg"

    };

    int[] categorycolors;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.home_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        IV_home_Banner = (ImageView)view.findViewById(R.id.iv_home_banner);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.displaybanner.com/images/sample_banner.jpg")
                .into(IV_home_Banner);



        // home slider start
        mHomeSlider = (SliderLayout)view.findViewById(R.id.home_slider);

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

            mHomeSlider.addSlider(textSliderView);
        }
        mHomeSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mHomeSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mHomeSlider.setCustomAnimation(new DescriptionAnimation());
        mHomeSlider.setDuration(4000);
        mHomeSlider.addOnPageChangeListener(this);


        // home slider end


        RV_Category = (RecyclerView)view.findViewById(R.id.recycler_home_category);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_Category.setLayoutManager(mLayoutManager);

        categorycolors = this.getResources().getIntArray(R.array.category_colors);

        CategoryItemAdapter categoryItemAdapter = new CategoryItemAdapter(getActivity(), category_names);
//        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(searchresultadapter);
//        scaleAdapter.setInterpolator(new BounceInterpolator());
//        scaleAdapter.setDuration(1000);
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




//            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
//            holder.TV_deal_name.setTypeface(tf, Typeface.NORMAL);


            holder.RV_home_category.setBackgroundColor(categorycolors[position]);



            Picasso.with(getActivity())
                    .load(category_image_urls[position])
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(holder.IV_category_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (category_name[position].equals("SHOPPING"))
                    {

                        getActivity().findViewById(R.id.sp_menu_shopping).setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling shopping fragment

                        FragmentShopping fragmentShopping = new FragmentShopping();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentShopping).addToBackStack("Shopping").commit();

                    }
                    else  if (category_name[position].equals("DINING"))
                    {
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.dining_color)); // setting base colour
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling dining fragment

                        FragmentDining fragmentDining = new FragmentDining();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDining).addToBackStack("Dining").commit();

                    }
                    else  if (category_name[position].equals("ENTERTAINMENT"))
                    {



                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.entertainment_color)); // setting base colour
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling entertainment fragment

                        FragmentEntertainment fragmentEntertainment = new FragmentEntertainment();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentEntertainment).addToBackStack("Entertainment").commit();
                    }
                    else  if (category_name[position].equals("DEALS"))
                    {
                        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.deals_color)); // setting base colour
                        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                        //calling deals fragment

                        FragmentDeals fragmentDeals = new FragmentDeals();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDeals).addToBackStack("Deals").commit();

                    }



                }
            });







        }

        @Override
        public int getItemCount() {
            return category_name.length;
        }






    }


}
