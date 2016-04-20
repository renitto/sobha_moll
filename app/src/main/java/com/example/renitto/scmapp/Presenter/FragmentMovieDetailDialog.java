package com.example.renitto.scmapp.Presenter;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.renitto.scmapp.Model.ModelMovieDetails;
import com.example.renitto.scmapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by Renitto on 4/13/2016.
 */
public class FragmentMovieDetailDialog extends DialogFragment implements Serializable {
    TextView TV_movie_name,TV_movie_synopsis,TV_director,TV_cast,TV_genere;
    ImageView IV_movie_image;
    LinearLayout LL_movie_detail;
    String desc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.movie_detail_book_design,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }





        TV_movie_name = (TextView)view.findViewById(R.id.tv_movie_title);
        TV_movie_synopsis = (TextView)view.findViewById(R.id.tv_synopsis_data);

        TV_director = (TextView)view.findViewById(R.id.tv_director_name);
        TV_cast = (TextView)view.findViewById(R.id.tv_actor);

        TV_genere = (TextView)view.findViewById(R.id.tv_genere);

        IV_movie_image = (ImageView)view.findViewById(R.id.iv_movie_image);

        LL_movie_detail = (LinearLayout)view.findViewById(R.id.ll_movie_data);


        TV_movie_name.setText(getArguments().getString("title"));
        TV_movie_synopsis.setText(getArguments().getString("synopsis"));
        TV_director.setText(getArguments().getString("director"));
        TV_cast.setText(getArguments().getString("cast"));
        TV_genere.setText(getArguments().getString("genre"));


       Picasso.with(getActivity()).load(getArguments().getString("image"))
//               .resize(getView().getMeasuredWidth(),getView().getMeasuredHeight())
//               .onlyScaleDown()
               .into(IV_movie_image, new Callback() {
           @Override
           public void onSuccess() {


               Bitmap photo = ((BitmapDrawable)IV_movie_image.getDrawable()).getBitmap();
               // do your processing here....
               Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                   public void onGenerated(Palette palette) {
                       int mutedLight = palette.getDarkVibrantColor(getActivity().getResources().getColor(android.R.color.black));

                       LL_movie_detail.setBackgroundColor(mutedLight);

                   }
               });


           }

           @Override
           public void onError() {

           }
       });

                  return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setTargetFragment(null, -1);

    }
}

