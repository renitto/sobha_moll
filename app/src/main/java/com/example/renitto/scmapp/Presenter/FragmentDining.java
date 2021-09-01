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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.renitto.scmapp.Application;
import com.example.renitto.scmapp.DAL.DbManager;
import com.example.renitto.scmapp.DAL.NetworkManager;
import com.example.renitto.scmapp.Model.ModelFashion;
import com.example.renitto.scmapp.Model.ModelSubCategories;
import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Renitto on 2/29/2016.
 */
public class FragmentDining extends Fragment implements   NetworkManager.onServerDataRequestListener{

    ImageView IV_Shopping_Banner;
    ModelFashion dining_fashion;
    private String[] params = new String[1];
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    ModelSubCategories.Dining[] dining;
    ViewPager viewPager;
    TabLayout tabLayout;
    DbManager dbManager= new DbManager();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.shopping,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // setting navigation drawer

        ((ActivityHome)getActivity()).setNavigationDrawerSelected(2);

         tabLayout = (TabLayout)view.findViewById(R.id.shopping_tabs);
         viewPager = (ViewPager)view.findViewById(R.id.shopping_viewpager);


        IV_Shopping_Banner = (ImageView)view.findViewById(R.id.iv_shopping_banner);


        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.dining_color)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black






        if (dining_fashion == null  )
            dining_fashion = dbManager.getDining(getActivity());
        if(dining == null )
            dining = dbManager.getSubcategories(getActivity(),params[0]);

        if(dining_fashion != null )
            setMaincontent();

        if(dining != null)
            setSubcontents();








        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        params[0] = "54";

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()) {
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_SHOPPING_FASHION_CONTENTS, getActivity(), params);
            NetworkManager.GetDataFromServer(this, NetworkManager.GET_SUBCATEGORY_CONTENTS, getActivity(), null);
        }
        else {

            dining_fashion = dbManager.getDining(getActivity());
            dining = dbManager.getSubcategories(getActivity(),params[0]);

            if(dining_fashion == null && dining == null)
            {

                Toast.makeText(getActivity(),"Please check your internet connection and try again !",Toast.LENGTH_SHORT).show();
            }

        }


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());



        for (int i=0;i<dining.length;i++) {
            FragmentFashion frag = new FragmentFashion();
            Bundle args = new Bundle();
            args.putString("shopp_id", dining[i].getId());
            frag.setArguments(args);
            adapter.addFragment(frag, Html.fromHtml(dining[i].getName()).toString());
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public void showData(Object data, int whatToShow) {
        if (data!=null)
        {

            if(whatToShow==NetworkManager.GET_SHOPPING_FASHION_CONTENTS) {
                dining_fashion = (ModelFashion) data;
                dbManager.insertDining(getActivity(),dining_fashion);
                setMaincontent();

            }
            else if(whatToShow==NetworkManager.GET_SUBCATEGORY_CONTENTS)
            {
                dining = ((ModelSubCategories)data).dining;
                dbManager.insertSubacategories(getActivity(),dining,params[0]);
                setSubcontents();


            }
        }
    }

    public void setMaincontent()
    {
        if (dining_fashion != null) {
            //setting home banner here
            Picasso.with(getActivity())
                    .load(dining_fashion.banner_art)
                    .into(IV_Shopping_Banner);
        }
    }

    public void  setSubcontents()
    {
        if (dining != null)
            if (viewPager != null) {
                setupViewPager(viewPager);
                tabLayout.setupWithViewPager(viewPager);
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
