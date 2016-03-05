package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.renitto.scmapp.R;

public class ActivityHome extends AppCompatActivity {

    Spinner SP_menu_more,SP_menu_shopping;
    RelativeLayout Rl_menu_dining,Rl_menu_entertainment,RL_menu_deals;

    String [] Shopping_menu = {"Shopping","Fashion","Health","Electronics","Grocery"};
    String [] More_menu = {"About Mall","How To Reach ?","Shopping Planner"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

// hide titile from tool bar
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // setting icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.marker_green);


        SP_menu_shopping = (Spinner)findViewById(R.id.sp_menu_shopping);
        SP_menu_more = (Spinner)findViewById(R.id.sp_menu_more);

        RL_menu_deals =(RelativeLayout)findViewById(R.id.rl_menu_deals);
        Rl_menu_dining =(RelativeLayout)findViewById(R.id.rl_menu_dining);
        Rl_menu_entertainment =(RelativeLayout)findViewById(R.id.rl_menu_entertainment);


        //setting values inside shopping spinner
        ArrayAdapter<String> shoppingspinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_simple_spinner, Shopping_menu); //selected item will look like a spinner set from XML
        shoppingspinnerArrayAdapter.setDropDownViewResource(R.layout.shopping_custom_simple_spinner_dropdown_item);
        SP_menu_shopping.setAdapter(shoppingspinnerArrayAdapter);


        //setting values inside more spinner
        ArrayAdapter<String> morespinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_simple_spinner, More_menu); //selected item will look like a spinner set from XML
        morespinnerArrayAdapter.setDropDownViewResource(R.layout.more_custom_simple_spinner_drop_down_item);
        SP_menu_more.setAdapter(morespinnerArrayAdapter);



        //calling home fragment

        FragmentHome fragmentHome = new FragmentHome();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentHome).addToBackStack("Deals").commit();


        // deals menu click

        RL_menu_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.deals_color)); // setting base colour
                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                SP_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black


                //calling deals fragment

        FragmentDeals fragmentDeals = new FragmentDeals();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDeals).addToBackStack("Deals").commit();

            }
        });

        // dining menu click

        Rl_menu_dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.dining_color)); // setting base colour
                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                SP_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                //calling dining fragment

                FragmentDining fragmentDining = new FragmentDining();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDining).addToBackStack("Dining").commit();
            }
        });

        // entertainment menu click

        Rl_menu_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.entertainment_color)); // setting base colour
                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                SP_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                //calling entertainment fragment

                FragmentEntertainment fragmentEntertainment = new FragmentEntertainment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentEntertainment).addToBackStack("Entertainment").commit();
            }
        });


        SP_menu_shopping.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (SP_menu_shopping.getSelectedItem().equals("Shopping"))
                {
                    SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
                    SP_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black


                    Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                    //calling shopping fragment

                    FragmentShopping fragmentShopping = new FragmentShopping();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentShopping).addToBackStack("Shopping").commit();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        SP_menu_more.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if (SP_menu_more.getSelectedItem().equals("About Mall"))
                {
                    SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.black)); // setting base colour
                    SP_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black


                    Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                    //calling about mall fragment

                    FragmentAboutMall fragmentAboutMall = new FragmentAboutMall();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentAboutMall).addToBackStack("Aboutmall").commit();

                }
               else if (SP_menu_more.getSelectedItem().equals("How To Reach ?"))
                {
                    SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.black)); // setting base colour
                    SP_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black

                    Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                    //calling  howtoreach fragment

                    FragmentHowToReach fragmentHowToReach = new FragmentHowToReach();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentHowToReach).addToBackStack("Howtoreach").commit();

                }

                else if (SP_menu_more.getSelectedItem().equals("Shopping Planner"))
                {
                    SP_menu_shopping.setBackgroundColor(getResources().getColor(R.color.black)); // setting base colour
                    SP_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black


                    Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                    Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                    //calling  howtoreach fragment

                    FragmentShoppingPlanner fragmentShoppingPlanner = new FragmentShoppingPlanner();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentShoppingPlanner).addToBackStack("Shoppingplanner").commit();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });







    }


    // click event for icon on toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                //calling home fragment

        FragmentEntertainment fragmentEntertainment = new FragmentEntertainment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentEntertainment).addToBackStack("home").commit();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    // for checking each fragmnet in back stack
    public void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.

            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(backStateName).commit();

        }
    }
}
