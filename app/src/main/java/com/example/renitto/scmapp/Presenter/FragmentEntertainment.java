package com.example.renitto.scmapp.Presenter;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelEntertainmentBrand;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.Adanimation;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Renitto on 3/11/2016.
 */
public class FragmentEntertainment extends Fragment implements NetworkManager.onServerDataRequestListener , BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    ImageView IV_Entertainment_Banner,IV_entertainment_logo;
    TextView TV_entertainment_brand_desc,TV_entertainment_open_hours,TV_entertainment_mobileno,TV_entertainment_emailid,TV_entertainment_web_address;
    ModelEntertainmentBrand entertainmentBrand;
    SliderLayout mEntertainmentSlider;
    HashMap<String,String> url_maps;
    ConnectionDetector cd;
    Boolean isInternetPresent = false;


    String [] UpcomingMovies;


    LinearLayoutManager layoutManager;

    private final Runnable SCROLLING_RUNNABLE = new Runnable() {

        @Override
        public void run() {
            final int duration = 20;
            final int pixelsToMove = 20;
            upcomingmovie_list.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private RecyclerView upcomingmovie_list;
    //private boolean loading = true;
    private boolean foundTotalPixel = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int totalMovedPixel;
    private int totalPixel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.entertainment_parent,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.entertainment_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        IV_Entertainment_Banner = (ImageView)view.findViewById(R.id.iv_entertainment_banner);
        IV_entertainment_logo = (ImageView)view.findViewById(R.id.iv_entertainment_logo);
        TV_entertainment_brand_desc = (TextView)view.findViewById(R.id.tv_entertainment_brand_desc);
        TV_entertainment_mobileno = (TextView)view.findViewById(R.id.tv_entertainment_mobileno);
        TV_entertainment_emailid = (TextView)view.findViewById(R.id.tv_entertainment_emailid);
        TV_entertainment_web_address = (TextView)view.findViewById(R.id.tv_entertainment_web_address);
        TV_entertainment_open_hours = (TextView)view.findViewById(R.id.tv_entertainment_open_hours);







        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.entertainment_tabs);
        tabLayout.setupWithViewPager(viewPager);



        // home slider start
        mEntertainmentSlider = (SliderLayout)view.findViewById(R.id.entertaiment_offer_slider);




        url_maps = new HashMap<String, String>();


        upcomingmovie_list = (RecyclerView)view.findViewById(R.id.recycler_upcoming_movies);
         layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);



        setEntertainmentData();




        TV_entertainment_mobileno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TV_entertainment_mobileno.getText() != null && !TV_entertainment_mobileno.getText().toString().equals(""))
                {
                    // calling to that number
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+"+TV_entertainment_mobileno.getText().toString()));
                    startActivity(callIntent );
                }
            }
        });

        TV_entertainment_emailid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TV_entertainment_emailid.getText() != null && !TV_entertainment_emailid.getText().toString().equals(""))
                {
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + TV_entertainment_emailid.getText()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(intent);
                }
            }
        });

        TV_entertainment_web_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TV_entertainment_web_address.getText() != null && !TV_entertainment_web_address.getText().toString().equals(""))
                {
                    //chrome custom tab click
                    ((ActivityHome)getActivity()).showCustomChromTabs("http://"+TV_entertainment_web_address.getText().toString());


                }
            }
        });



        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkManager.GetDataFromServer(this, NetworkManager.GET_ENTERTAINMENT_BRAND_CONTENTS, getActivity(), null);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());

                String[] ids={"mollywood","bollywood","hollywood","kollywood","others"};
                String [] names = {"Mollywood" , "Bollywood" , "Hollywood" , "Kollywood" ,"Others"};

        for (int i=0;i<ids.length;i++) {
            FragmentMollywood frag= new FragmentMollywood();
            Bundle args=new Bundle();
            args.putString("id",ids[i]);
            frag.setArguments(args);
            adapter.addFragment(frag, names[i]);
        }

        viewPager.setAdapter(adapter);
    }

    @Override
    public void showData(Object data, int whatToShow) {
        if (data != null)
        {
        entertainmentBrand = (ModelEntertainmentBrand) data;

            setEntertainmentData();

        }
    }

    public void  setEntertainmentData()
    {


        if (entertainmentBrand != null) {
            setSliders(entertainmentBrand);
            //setting home banner here
            Picasso.with(getActivity())
                    .load(entertainmentBrand.getBanner_art())
                    .into(IV_Entertainment_Banner);

            //setting home banner here
            Picasso.with(getActivity())
                    .load(entertainmentBrand.getLogo())
                    .into(IV_entertainment_logo);

            // setting desc
            TV_entertainment_brand_desc.setText(entertainmentBrand.getDescription());

            TV_entertainment_open_hours.setText(entertainmentBrand.getWorking_time());
            TV_entertainment_emailid.setText(entertainmentBrand.getEmail());
            TV_entertainment_mobileno.setText(entertainmentBrand.getContact_number());
            TV_entertainment_web_address.setText(entertainmentBrand.getWebsite());


            UpcomingMovies = entertainmentBrand.getUpcoming();


            upcomingmovie_list.setLayoutManager(layoutManager);
            upcomingmovie_list.setAdapter(new UpcomingMoviesAdapter());


            // marquee effect for the upcoming movie list

            upcomingmovie_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    totalMovedPixel = totalMovedPixel + dx;
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (foundTotalPixel) {
                        if (totalItemCount > 2) {
                            View headerView = layoutManager.getChildAt(0);
                            View itemView = layoutManager.getChildAt(1);

                            if (itemView != null && headerView != null) {
                        /*total visible scrolling part is total pixel's of total item's count and header view*/
                                totalPixel = /*-c.getTop() +*/ ((totalItemCount - 2) * itemView.getWidth()) + (1 * headerView.getWidth());
                                Log.v("...", "Total pixel x!" + totalPixel);
                                foundTotalPixel = false;
                            }
                        }
                    }

                    //if (loading) {
                    //if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    if (!foundTotalPixel && totalMovedPixel >= totalPixel) {
                        // loading = false;
                        Log.v("...", "Last Item Wow !");
                        Log.v("...", "totalMovedPixel !" + totalMovedPixel);

                        // use this to turn auto-scrolling off:
                        //mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                        upcomingmovie_list.setAdapter(null);
                        upcomingmovie_list.setAdapter(new UpcomingMoviesAdapter());
                        pastVisiblesItems = visibleItemCount = totalItemCount = 0;
                        totalMovedPixel = 0;

                    }
                }
                // }
            });
            // use this to turn auto-scrolling on:
            mHandler.post(SCROLLING_RUNNABLE);

        }

    }

    @Override
    public void onErrorResponse(String error) {

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

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


    public class UpcomingMoviesAdapter
            extends RecyclerView.Adapter<UpcomingMoviesAdapter.ViewHolder> {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;
        private static final int TYPE_FOOTER = 2;



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;



            public final ImageView IV_upcoming_movie_image;




            public ViewHolder(View view) {
                super(view);
                mView = view;




                IV_upcoming_movie_image =(ImageView)view.findViewById(R.id.iv_upcoming_movie);





            }


        }




        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.upcoming_movie_item_card, parent, false);

            if (viewType == TYPE_ITEM) {
                //inflate your layout and pass it to view holder
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.upcoming_movie_item_card, parent, false);

            }
            else if (viewType == TYPE_HEADER || viewType == TYPE_FOOTER) {
                //inflate your layout and pass it to view holder
                //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_footer, parent, false);
//                view =   new View(parent.getContext();
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.upcoming_movie_item_card, parent, false);
                DisplayMetrics metrics = parent.getContext().getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                view.setLayoutParams(new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT));

            }


            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {


            Picasso.with(getActivity())
                    .load(UpcomingMovies[position])
                    .resize(getView().getWidth(),getView().getHeight())
                    .onlyScaleDown()
                    .into(holder.IV_upcoming_movie_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });


        }

        @Override
        public int getItemCount() {
            return UpcomingMovies.length;
        }


        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position))
                return TYPE_HEADER;
            else if (isPositionFooter(position))
                return TYPE_FOOTER;
            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

        private boolean isPositionFooter(int position) {
            return position == getItemCount() - 1;
        }


        // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
        public  class ViewHolderItem extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public View mView;
            public ViewHolderItem(View v) {
                super(v);
                mView = v;
            }
        }

        // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
        public  class ViewHolderHeaderOrFooter extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public View mView;
            public ViewHolderHeaderOrFooter(View v) {
                super(v);
                mView = v;
            }
        }

    }

    public void  setSliders(ModelEntertainmentBrand entertainmentBrand){
        for(int i=0;i<entertainmentBrand.offers.length;i++){
            url_maps.put("offers"+i,entertainmentBrand.offers[i]);
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

            mEntertainmentSlider.addSlider(textSliderView);
        }
        mEntertainmentSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mEntertainmentSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mEntertainmentSlider.setCustomAnimation(new Adanimation());
        mEntertainmentSlider.setDuration(4000);
        mEntertainmentSlider.addOnPageChangeListener(this);


        // home slider end


    }


    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mEntertainmentSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onDestroy() {

        mEntertainmentSlider.stopAutoCycle();
        super.onDestroy();
    }

    @Override
    public void onResume() {

        mEntertainmentSlider.startAutoCycle();
        super.onResume();
    }

}