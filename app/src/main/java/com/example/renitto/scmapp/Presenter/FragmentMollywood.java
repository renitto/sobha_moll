package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.renitto.scmapp.Model.ModelMovieDetails;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

/**
 * Created by Renitto on 3/3/2016.
 */
public class FragmentMollywood extends  Fragment implements   NetworkManager.onServerDataRequestListener {



        RecyclerView RV_movie_details;
        RecyclerView.LayoutManager moviedetailLayoutManager;
        MovieShowDetailsAdapter movieShowDetailsAdapter;


       private String[] params = new String[1];

        Boolean isInternetPresent = false;
        ConnectionDetector cd;
        ModelMovieDetails movieDetails ;




     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.entertainment_fragment,
        container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);





        // movie detail recycler

        RV_movie_details = (RecyclerView)view.findViewById(R.id.recycler_movie_show_details);
        moviedetailLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RV_movie_details.setLayoutManager(moviedetailLayoutManager);


         setEntertainmentDetails();




        return view;

        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        params[0] = getArguments().getString("id");
        NetworkManager.GetDataFromServer(this,NetworkManager.GET_MOVIE_DETAIL_CONTENTS,getActivity(),params);

    }

    @Override
public void showData(Object data, int whatToShow) {

        if (data!= null)
        {
        movieDetails = (ModelMovieDetails) data;

            setEntertainmentDetails();


        }
        }

    public void setEntertainmentDetails()
    {
        if (movieDetails != null) {
            movieShowDetailsAdapter = new MovieShowDetailsAdapter(getActivity(), movieDetails);
            RV_movie_details.setAdapter(movieShowDetailsAdapter);
        }
    }

@Override
public void onErrorResponse(String error) {

        }







public class MovieShowDetailsAdapter
        extends RecyclerView.Adapter<MovieShowDetailsAdapter.ViewHolder> {

    ModelMovieDetails movieDetails;



    //type 1 viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;


        public final TextView TV_movie_name;
        public final TextView TV_movie_synopsis;
        public final ImageView IV_movie;
        public final TextView BT_Book_now;

        public final TextView TV_screen1;
        public final TextView TV_screen2;
        public final TextView TV_screen3;
        public final TextView TV_screen4;

        public final TextView TV_mvcd_movie_timings;







        public ViewHolder(View view) {
            super(view);
            mView = view;


            TV_movie_name = (TextView) view.findViewById(R.id.tv_mvdc_movie_name);

            TV_movie_synopsis = (TextView) view.findViewById(R.id.tv_mvdc_movie_synopsis);

            IV_movie = (ImageView)view.findViewById(R.id.iv_moviedetail);

            BT_Book_now = (TextView) view.findViewById(R.id.bt_book_now);

            TV_screen1 = (TextView) view.findViewById(R.id.tv_screen1);
            TV_screen2 = (TextView) view.findViewById(R.id.tv_screen2);
            TV_screen3 = (TextView) view.findViewById(R.id.tv_screen3);
            TV_screen4 = (TextView) view.findViewById(R.id.tv_screen4);

            TV_mvcd_movie_timings = (TextView) view.findViewById(R.id.tv_mvcd_movie_timings);







        }


    }


    public MovieShowDetailsAdapter(Context context, ModelMovieDetails modelMovieDetails) {

        this.movieDetails = modelMovieDetails;

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_details_card, parent, false);




        return new ViewHolder(view);


    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {




        holder.TV_movie_name.setText(movieDetails.getAvailable()[position].getTitle());
        holder.TV_movie_synopsis.setText(movieDetails.getAvailable()[position].getSynopsis());

        holder.TV_movie_synopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentMollywood mollywood = new FragmentMollywood();
//                FragmentDescription description = new FragmentDescription();
//                Bundle args = new Bundle();
//                args.putString("detail",movieDetails.getAvailable()[position].getSynopsis() );
//                description.setArguments(args);
//                description.setTargetFragment(mollywood , 0);
//                description.show(getActivity().getFragmentManager(), "moviedescription");

                FragmentMollywood mollywood = new FragmentMollywood();
                FragmentMovieDetailDialog fragmentDescription = new FragmentMovieDetailDialog();
                Bundle args = new Bundle();
                args.putString("title",movieDetails.getAvailable()[position].getTitle());
                args.putString("image",movieDetails.getAvailable()[position].getImage());
                args.putString("synopsis",movieDetails.getAvailable()[position].getSynopsis());
                args.putString("director",movieDetails.getAvailable()[position].getDetails_director());
                args.putString("cast",movieDetails.getAvailable()[position].getDetails_cast());
                args.putString("genre",movieDetails.getAvailable()[position].getDetails_genre());

                fragmentDescription.setArguments(args);
                fragmentDescription.setTargetFragment(mollywood , 0);
                fragmentDescription.show(getActivity().getFragmentManager(), "moviedescription");

            }
        });
        Picasso.with(getActivity())
                .load(movieDetails.getAvailable()[position].getImage())
                .resize(getView().getWidth(),getView().getHeight())
                .onlyScaleDown()
                .into(holder.IV_movie);

        holder.BT_Book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //chrome custom tab click
                ((ActivityHome)getActivity()).showCustomChromTabs(movieDetails.getAvailable()[position].getBook_now());

            }
        });

        holder.TV_mvcd_movie_timings.setText(movieDetails.getAvailable()[position].getScreen()[0].getTiming());
        holder.TV_screen1.setTextColor(getResources().getColor(R.color.white));
        holder.TV_screen1.setBackgroundColor(getResources().getColor(R.color.black));

      if(movieDetails.getAvailable()[position].getScreen().length == 4)
      {
          holder.TV_screen4.setText(movieDetails.getAvailable()[position].getScreen()[3].getScreen());
          holder.TV_screen3.setText(movieDetails.getAvailable()[position].getScreen()[2].getScreen());
          holder.TV_screen2.setText(movieDetails.getAvailable()[position].getScreen()[1].getScreen());
          holder.TV_screen1.setText(movieDetails.getAvailable()[position].getScreen()[0].getScreen());
      }
        else if (movieDetails.getAvailable()[position].getScreen().length == 3)
        {
            holder.TV_screen4.setVisibility(View.GONE);
            holder.TV_screen3.setText(movieDetails.getAvailable()[position].getScreen()[2].getScreen());
            holder.TV_screen2.setText(movieDetails.getAvailable()[position].getScreen()[1].getScreen());
            holder.TV_screen1.setText(movieDetails.getAvailable()[position].getScreen()[0].getScreen());
        }
      else if (movieDetails.getAvailable()[position].getScreen().length == 2)
      {
          holder.TV_screen4.setVisibility(View.GONE);
          holder.TV_screen3.setVisibility(View.GONE);
          holder.TV_screen2.setText(movieDetails.getAvailable()[position].getScreen()[1].getScreen());
          holder.TV_screen1.setText(movieDetails.getAvailable()[position].getScreen()[0].getScreen());
      }
      else if (movieDetails.getAvailable()[position].getScreen().length == 1)
      {
          holder.TV_screen4.setVisibility(View.GONE);
          holder.TV_screen3.setVisibility(View.GONE);
          holder.TV_screen2.setVisibility(View.GONE);
          holder.TV_screen1.setText(movieDetails.getAvailable()[position].getScreen()[0].getScreen());
      }
      else if (movieDetails.getAvailable()[position].getScreen().length == 0)
      {
          holder.TV_screen4.setVisibility(View.GONE);
          holder.TV_screen3.setVisibility(View.GONE);
          holder.TV_screen2.setVisibility(View.GONE);
          holder.TV_screen1.setVisibility(View.GONE);
      }

        holder.TV_screen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.TV_mvcd_movie_timings.setText(movieDetails.getAvailable()[position].getScreen()[0].getTiming());
                holder.TV_screen1.setTextColor(getResources().getColor(R.color.white));
                holder.TV_screen1.setBackgroundColor(getResources().getColor(R.color.black));

                holder.TV_screen2.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen2.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen3.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen3.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen4.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen4.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));


            }
        });

        holder.TV_screen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.TV_mvcd_movie_timings.setText(movieDetails.getAvailable()[position].getScreen()[1].getTiming());
                holder.TV_screen2.setTextColor(getResources().getColor(R.color.white));
                holder.TV_screen2.setBackgroundColor(getResources().getColor(R.color.black));

                holder.TV_screen1.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen1.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen3.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen3.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen4.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen4.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));


            }
        });

        holder.TV_screen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.TV_mvcd_movie_timings.setText(movieDetails.getAvailable()[position].getScreen()[2].getTiming());
                holder.TV_screen3.setTextColor(getResources().getColor(R.color.white));
                holder.TV_screen3.setBackgroundColor(getResources().getColor(R.color.black));

                holder.TV_screen2.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen2.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen1.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen1.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen4.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen4.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));


            }
        });

        holder.TV_screen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.TV_mvcd_movie_timings.setText(movieDetails.getAvailable()[position].getScreen()[3].getTiming());
                holder.TV_screen4.setTextColor(getResources().getColor(R.color.white));
                holder.TV_screen4.setBackgroundColor(getResources().getColor(R.color.black));

                holder.TV_screen2.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen2.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen3.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen3.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));

                holder.TV_screen1.setTextColor(getResources().getColor(R.color.movie_time_text));
                holder.TV_screen1.setBackgroundColor(getResources().getColor(R.color.movie_time_bg));


            }
        });









        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentMollywood mollywood = new FragmentMollywood();
                FragmentMovieDetailDialog fragmentDescription = new FragmentMovieDetailDialog();
                Bundle args = new Bundle();
                args.putString("title",movieDetails.getAvailable()[position].getTitle());
                args.putString("image",movieDetails.getAvailable()[position].getImage());
                args.putString("synopsis",movieDetails.getAvailable()[position].getSynopsis());
                args.putString("director",movieDetails.getAvailable()[position].getDetails_director());
                args.putString("cast",movieDetails.getAvailable()[position].getDetails_cast());
                args.putString("genre",movieDetails.getAvailable()[position].getDetails_genre());

                fragmentDescription.setArguments(args);
                fragmentDescription.setTargetFragment(mollywood , 0);
                fragmentDescription.show(getActivity().getFragmentManager(), "moviedescription");


            }
        });







    }

    @Override
    public int getItemCount() {
        return movieDetails.getAvailable().length;
    }






}



}
