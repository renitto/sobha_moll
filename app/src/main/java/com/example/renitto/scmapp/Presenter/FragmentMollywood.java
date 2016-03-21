package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
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
public class FragmentMollywood extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mEntertainmentSlider;

    RecyclerView RV_movie_details;
    RecyclerView.LayoutManager moviedetailLayoutManager;

    private static final int NUM_PAGES = 5;

    String [] UpcomingMovies = {
    "https://upload.wikimedia.org/wikipedia/en/archive/0/0b/20160205060042!Kali_Malayalam_Movie_First_Look_Poster.jpg",
    "http://timesofindia.indiatimes.com/photo/50611303.cms",
    "http://img.nowrunning.com/content/movie/2015/Charlie/poster.jpg",
    "http://photos.filmibeat.com/ph-big/2015/11/su-su-sudhi-vathmeekam_1448004904150.jpg"};

    String [] Movie_names= {"Charlie","Vetta","Dooble"};


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

        // for starting animation for button


        View view = inflater.inflate(R.layout.entertainment_fragment,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);





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

        upcomingmovie_list = (RecyclerView)view.findViewById(R.id.recycler_upcoming_movies);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
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


        // Instantiate a ViewPager and a PagerAdapter.
//        mPager = (ViewPager) view.findViewById(R.id.pager);
//        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
//        mPager.setAdapter(mPagerAdapter);
//        return  view;


        // movie detail recycler

        RV_movie_details = (RecyclerView)view.findViewById(R.id.recycler_movie_show_details);
        moviedetailLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RV_movie_details.setLayoutManager(moviedetailLayoutManager);

        MovieShowDetailsAdapter movieShowDetailsAdapter = new MovieShowDetailsAdapter(getActivity(), Movie_names);
        RV_movie_details.setAdapter(movieShowDetailsAdapter);

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

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            FragmentMovieDetailSlider myFrag= new FragmentMovieDetailSlider();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFrag.setArguments(args);
            return myFrag;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }



    public class MovieShowDetailsAdapter
            extends RecyclerView.Adapter<MovieShowDetailsAdapter.ViewHolder> {

        String [] movie_names;



        //type 1 viewholder
        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;


            public final TextView TV_movie_name;





            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV_movie_name = (TextView) view.findViewById(R.id.tv_mvdc_movie_name);







            }


        }


        public MovieShowDetailsAdapter(Context context, String [] movie_names) {

            this.movie_names = movie_names;

        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_details_card, parent, false);




            return new ViewHolder(view);


        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.TV_movie_name.setText(movie_names[position]);










//            Picasso.with(getActivity())
//                    .load(deal_image_urls[position])
//                    .resize(getView().getWidth(),getView().getHeight())
//                    .onlyScaleDown()
//                    .into(holder.IV_deal_image);




            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });







        }

        @Override
        public int getItemCount() {
            return movie_names.length;
        }






    }



}
