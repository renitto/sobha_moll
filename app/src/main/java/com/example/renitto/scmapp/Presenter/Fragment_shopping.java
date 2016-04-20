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
import android.widget.Toast;

import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelFashion;
import com.example.renitto.scmapp.Model.ModelSubCategories;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;




public class Fragment_shopping extends Fragment implements   NetworkManager.onServerDataRequestListener{

    ImageView IV_Shopping_Banner;
    ModelFashion fashion;
    private String[] params = new String[1];
    ModelSubCategories subCategories = new ModelSubCategories();
    ViewPager viewPager;
    TabLayout tabLayout;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.shopping,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tabLayout = (TabLayout)view.findViewById(R.id.shopping_tabs);

         viewPager = (ViewPager)view.findViewById(R.id.shopping_viewpager);
     

        IV_Shopping_Banner = (ImageView)view.findViewById(R.id.iv_shopping_banner);



        setMaincontents();
        setSubCategories();
   
       



        return view;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        params[0] = "34";

        NetworkManager.GetDataFromServer(this, NetworkManager.GET_SHOPPING_FASHION_CONTENTS, getActivity(), params);
        NetworkManager.GetDataFromServer(this,NetworkManager.GET_SUBCATEGORY_CONTENTS,getActivity(),null);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());



        for (int i=0;i<subCategories.shopping.length;i++) {
            FragmentFashion frag = new FragmentFashion();
            Bundle args = new Bundle();
            args.putString("shopp_id", subCategories.shopping[i].getId());
            frag.setArguments(args);
            adapter.addFragment(frag, subCategories.shopping[i].getName());
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showData(Object data, int whatToShow) {
        if (data!=null)
        {
            if(whatToShow==NetworkManager.GET_SHOPPING_FASHION_CONTENTS) {
                fashion = (ModelFashion) data;
                setMaincontents();

            }
            else if(whatToShow==NetworkManager.GET_SUBCATEGORY_CONTENTS)
            {
                subCategories = (ModelSubCategories)data;

                setSubCategories();

            }
        }
    }


    public void setMaincontents()
    {

        if (fashion != null) {
            //setting home banner here
            Picasso.with(getActivity())
                    .load(fashion.banner_art)
                    .into(IV_Shopping_Banner);
        }
    }

    public void setSubCategories()
    {
        if (subCategories.shopping != null)
            if (viewPager != null) {
                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);
                if(subCategories.shopping.length<4)
                {
                    tabLayout.setTabMode(TabLayout.MODE_FIXED);
                }
                else
                {
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                }
            }
    }

    @Override
    public void onErrorResponse(String error) {

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
