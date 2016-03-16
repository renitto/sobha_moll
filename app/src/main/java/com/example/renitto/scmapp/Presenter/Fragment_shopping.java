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




public class Fragment_shopping extends Fragment {

    ImageView IV_Shopping_Banner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.shopping,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.shopping_viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        IV_Shopping_Banner = (ImageView)view.findViewById(R.id.iv_shopping_banner);

        //setting home banner here
        Picasso.with(getActivity())
                .load("http://www.displaybanner.com/images/sample_banner.jpg")
                .into(IV_Shopping_Banner);


        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.shopping_tabs);
        tabLayout.setupWithViewPager(viewPager);


        String strtext = getArguments().getString("fromhome");
        if (strtext.equals("Fashion")) {
            viewPager.setCurrentItem(0);
        }
        else  if (strtext.equals("Health")) {
            viewPager.setCurrentItem(1);
        }
        else  if (strtext.equals("Electronics")) {
            viewPager.setCurrentItem(2);
        }
        else  if (strtext.equals("Grocery")) {
            viewPager.setCurrentItem(3);
        }


        return view;

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FragmentFashion(), "Fashion");
        adapter.addFragment(new FragmentHealth(), "Health");
        adapter.addFragment(new FragmentElectronics(), "Electronics");
        adapter.addFragment(new FragmentGrocery(), "Grocery");
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
