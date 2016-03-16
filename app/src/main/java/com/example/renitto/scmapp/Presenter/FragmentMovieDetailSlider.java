package com.example.renitto.scmapp.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Renitto on 3/14/2016.
 */
public class FragmentMovieDetailSlider extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.movie_details_card, container, false);

        TextView text=(TextView)rootView.findViewById(R.id.tv_mvdc_movie_name);

        int position= getArguments().getInt("position");
        String [] UpcomingMovies = {
                "charlie",
                "don",
                "jumbo",
                "kidu"};
       
        text.setText("Slider no:"+position);

        return rootView;
    }
}