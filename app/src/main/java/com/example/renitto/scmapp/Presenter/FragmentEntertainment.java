package com.example.renitto.scmapp.Presenter;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.renitto.scmapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renitto on 3/11/2016.
 */
public class FragmentEntertainment extends Fragment {

    ImageView IV_Entertainment_Banner,IV_entertainment_logo;
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

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.displaybanner.com/images/sample_banner.jpg")
                .into(IV_Entertainment_Banner);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://static.dnaindia.com/sites/default/files/2015/07/20/357394-inox-twitter.jpeg")
                .into(IV_entertainment_logo);


        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.entertainment_tabs);
        tabLayout.setupWithViewPager(viewPager);




        return view;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FragmentMollywood(), "Mollywood");
//        adapter.addFragment(new FragmentBollyWood(), "Bollywood");
//        adapter.addFragment(new FragmentHollywood(), "Hollywood");
//        adapter.addFragment(new FragmentKollywood(), "Kollywood");
//        adapter.addFragment(new FragmentBollyWood(), "Others");
        viewPager.setAdapter(adapter);
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

}