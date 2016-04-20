package com.example.renitto.scmapp.Utils;

import android.view.View;

import com.daimajia.slider.library.Animations.BaseAnimationInterface;


public class Adanimation implements BaseAnimationInterface {
    @Override
    public void onPrepareCurrentItemLeaveScreen(View view) {
        view.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);

    }

    @Override
    public void onPrepareNextItemShowInScreen(View view) {
        view.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);

    }

    @Override
    public void onCurrentItemDisappear(View view) {
        view.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);

    }

    @Override
    public void onNextItemAppear(View view) {
        view.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);

    }
}
