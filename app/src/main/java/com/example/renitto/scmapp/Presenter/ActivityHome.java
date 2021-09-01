package com.example.renitto.scmapp.Presenter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsSession;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.renitto.scmapp.R;
import com.example.renitto.scmapp.Utils.CustomTypefaceSpan;


public class ActivityHome extends AppCompatActivity  {


    RelativeLayout Rl_menu_dining,Rl_menu_entertainment,RL_menu_deals,RL_menu_Shopping,RL_menu_more;
    LinearLayout LL_menu_more,LL_menu_shopping;
    private CustomTabsClient mClient;
    private CustomTabsSession mCustomTabsSession;
    TextView TV_fashion,TV_aboutmall,TV_shoppingplanner,TV_howtoreach,TV_Electronics,TV_Grocery,TV_Health;
    ImageView mHomeImage;
    Bundle bundle_shopping= new Bundle();;
    FrameLayout FL_fragment_container;
    DrawerLayout drawer;
    ActionBar actionBar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // setting toolbar header with custom font


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mHomeImage = (ImageView) toolbar.findViewById(R.id.iv_home);

        mHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LL_menu_shopping.setVisibility(View.GONE);
                LL_menu_more.setVisibility(View.GONE);
                // home icon click here

                // setting navigation drawer selected
                setNavigationDrawerSelected(0);
                //calling home fragment
                replaceFragment(new FragmentHome());

            }
        });

        FL_fragment_container = (FrameLayout)findViewById(R.id.fragment_container);



        RL_menu_deals =(RelativeLayout)findViewById(R.id.rl_menu_deals);
        Rl_menu_dining =(RelativeLayout)findViewById(R.id.rl_menu_dining);
        Rl_menu_entertainment =(RelativeLayout)findViewById(R.id.rl_menu_entertainment);
        RL_menu_Shopping =(RelativeLayout)findViewById(R.id.rl_menu_shopping);
        RL_menu_more =(RelativeLayout)findViewById(R.id.rl_menu_more);

        LL_menu_more = (LinearLayout)findViewById(R.id.ll_menu_more);
        LL_menu_shopping = (LinearLayout)findViewById(R.id.ll_menu_shopping);


        LL_menu_more.setVisibility(View.GONE);
        LL_menu_shopping.setVisibility(View.GONE);

        TV_fashion = (TextView)findViewById(R.id.tv_fashion);
        TV_Health = (TextView)findViewById(R.id.tv_health);
        TV_Electronics = (TextView)findViewById(R.id.tv_electronics);
        TV_Grocery = (TextView)findViewById(R.id.tv_grocery);


        TV_aboutmall = (TextView)findViewById(R.id.tv_about_mall);
        TV_howtoreach = (TextView)findViewById(R.id.tv_howtoreach);
        TV_shoppingplanner = (TextView)findViewById(R.id.tv_shopping_planner);






        if ((getIntent().getExtras())!=null) {

            if ((getIntent().getExtras().getString("offer"))!=null)
            if (getIntent().getExtras().getString("offer").equals("offer")) {
                // calling offer fragment here
                replaceFragment(new FragmentOffers());
            }
        }

        else {
            // calling home fragment here
            replaceFragment(new FragmentHome());
        }







        // hiding shopping list and more list on touching the screen
        FL_fragment_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LL_menu_more.setVisibility(View.GONE);
                LL_menu_shopping.setVisibility(View.GONE);
            }
        });

        // deals menu click

        RL_menu_deals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.deals_color)); // setting base colour
                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                LL_menu_more.setVisibility(View.GONE);
                LL_menu_shopping.setVisibility(View.GONE);


                //calling deals fragment



               replaceFragment(new FragmentOffers());

            }
        });

        // dining menu click

        Rl_menu_dining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.dining_color)); // setting base colour
                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black


                LL_menu_more.setVisibility(View.GONE);
                LL_menu_shopping.setVisibility(View.GONE);

                //calling dining fragment



//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentDining).addToBackStack("Dining").commit();
                replaceFragment(new FragmentDining());
            }
        });

        // entertainment menu click

        Rl_menu_entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.entertainment_color)); // setting base colour
                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black


                LL_menu_more.setVisibility(View.GONE);
                LL_menu_shopping.setVisibility(View.GONE);

                //calling entertainment fragment



                replaceFragment(new FragmentEntertainment());
            }
        });

        RL_menu_Shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Fragment_shopping fragment_shopping = new Fragment_shopping();
                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                LL_menu_shopping.setVisibility(View.GONE);
                LL_menu_more.setVisibility(View.GONE);

                bundle_shopping.putString("fromhome", "Fashion");
                fragment_shopping.setArguments(bundle_shopping);



                replaceFragment(fragment_shopping);

                TV_fashion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment_shopping fragment_shopping1 = new Fragment_shopping();
                        bundle_shopping.putString("fromhome", "Fashion");
                        //calling fashion fragment
                        fragment_shopping1.setArguments(bundle_shopping);
                        replaceFragment(fragment_shopping1);
                        LL_menu_shopping.setVisibility(View.GONE);
                    }
                });

                TV_Health.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment_shopping fragment_shopping2= new Fragment_shopping();
                        bundle_shopping.putString("fromhome", "Health");
                        //calling fashion fragment
                        fragment_shopping2.setArguments(bundle_shopping);
                        replaceFragment(fragment_shopping2);
                        LL_menu_shopping.setVisibility(View.GONE);
                    }
                });

                TV_Electronics.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Fragment_shopping fragment_shopping3= new Fragment_shopping();
                        bundle_shopping.putString("fromhome", "Electronics");
                        //calling fashion fragment
                        fragment_shopping3.setArguments(bundle_shopping);
                        replaceFragment(fragment_shopping3);
                        LL_menu_shopping.setVisibility(View.GONE);
                    }
                });

                TV_Grocery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment_shopping fragment_shopping4= new Fragment_shopping();
                        bundle_shopping.putString("fromhome", "Grocery");
                       //calling fashion fragment
                        fragment_shopping4.setArguments(bundle_shopping);
                        replaceFragment(fragment_shopping4);
                        LL_menu_shopping.setVisibility(View.GONE);
                    }
                });



            }
        });

        RL_menu_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // setting base colour
                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                LL_menu_more.setVisibility(View.VISIBLE);
                LL_menu_shopping.setVisibility(View.GONE);

                TV_aboutmall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //calling about mall fragment


                        replaceFragment(new FragmentAboutMall());

                        LL_menu_more.setVisibility(View.GONE);
                    }
                });

                TV_shoppingplanner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        //calling shopping planner fragment

                        replaceFragment(new FragmentShoppingPlanner());
                        LL_menu_more.setVisibility(View.GONE);
                    }
                });

                TV_howtoreach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        //calling Howtoreach fragment

                        replaceFragment(new FragmentHowToReach());
                        LL_menu_more.setVisibility(View.GONE);
                    }
                });

            }
        });




        // navigation drawer

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);




        }

        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }








    }



    // for checking each fragmnet in back stack
    public void replaceFragment (android.support.v4.app.Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(backStateName).commit();

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


    public  void  showCustomChromTabs(String url)
    {

        if (url != null && !(url.equals(""))) {
            //chrome custom tabs here
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(getSession());
            prepareMenuItems(builder);
            prepareActionButton(builder);

            builder.setToolbarColor(getResources().getColor(R.color.white)).setShowTitle(false);
            builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
            builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);

            builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back_black_24dp));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }

    }

    // for setting custom font for navigation drawer

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Titillium.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    private void prepareMenuItems(CustomTabsIntent.Builder builder) {
//        Intent menuIntent = new Intent();
//        menuIntent.setClass(getApplicationContext(), this.getClass());
//        // Optional animation configuration when the user clicks menu items.
//        Bundle menuBundle = ActivityOptions.makeCustomAnimation(this, android.R.anim.slide_in_left,
//                android.R.anim.slide_out_right).toBundle();
//        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, menuIntent, 0,
//                menuBundle);
//        builder.addMenuItem("Menu entry 1", pi);
    }

    private void prepareActionButton(CustomTabsIntent.Builder builder) {
        // An example intent that sends an email.
//        Intent actionIntent = new Intent(Intent.ACTION_SEND);
//        actionIntent.setType("*/*");
//        actionIntent.putExtra(Intent.EXTRA_EMAIL, "example@example.com");
//        actionIntent.putExtra(Intent.EXTRA_SUBJECT, "example");
//        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, actionIntent, 0);
//        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        builder.setActionButton(icon, "send email", pi);
    }
    private CustomTabsSession getSession() {
        if (mClient == null) {
            mCustomTabsSession = null;
        } else if (mCustomTabsSession == null) {
            mCustomTabsSession = mClient.newSession(new CustomTabsCallback() {
                @Override
                public void onNavigationEvent(int navigationEvent, Bundle extras) {

                }
            });
        }
        return mCustomTabsSession;
    }







    private void setupDrawerContent(final NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        drawer.closeDrawers();


//                        Menu m = navigationView.getMenu();

//                        for (int i = 0; i < m.size(); i++) {
//                            MenuItem mi = m.getItem(i);
//                            if (!(mi.getItemId() == menuItem.getItemId())) {
//                                mi.setCheckable(false);
//
//                            }
//
//                        }
//
//
//                        menuItem.setCheckable(true);
//                        menuItem.setChecked(true);
                        final ActionBar ab = getSupportActionBar();


                        switch (menuItem.getItemId()) {


                            case R.id.nav_home:

                                LL_menu_shopping.setVisibility(View.GONE);
                                LL_menu_more.setVisibility(View.GONE);
                                // home icon click here
                                //calling home fragment
                                replaceFragment(new FragmentHome());

                                break;



                            case R.id.nav_shopping:

                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                                LL_menu_shopping.setVisibility(View.GONE);
                                LL_menu_more.setVisibility(View.GONE);

                                bundle_shopping.putString("fromhome", "Fashion");

                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                                LL_menu_shopping.setVisibility(View.GONE);
                                LL_menu_more.setVisibility(View.GONE);

                                Fragment_shopping fragment_shopping6= new Fragment_shopping();
                                //calling fashion fragment

                                fragment_shopping6.setArguments(bundle_shopping);
                                replaceFragment(fragment_shopping6);
                                getSupportFragmentManager().executePendingTransactions();
                                break;

//                            case R.id.nav_health:
//                                bundle_shopping.putString("fromhome", "Health");
//
//                                Fragment_shopping fragment_shopping7= new Fragment_shopping();
//                                //calling fashion fragment
//
//                                fragment_shopping7.setArguments(bundle_shopping);
//                                replaceFragment(fragment_shopping7);
//                                getSupportFragmentManager().executePendingTransactions();
//                                break;
//
//                            case R.id.nav_electronics:
//                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
//                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//
//                                LL_menu_shopping.setVisibility(View.GONE);
//                                LL_menu_more.setVisibility(View.GONE);
//                                bundle_shopping.putString("fromhome", "Electronics");
//
//                                Fragment_shopping fragment_shopping8= new Fragment_shopping();
//                                //calling fashion fragment
//
//                                fragment_shopping8.setArguments(bundle_shopping);
//                                replaceFragment(fragment_shopping8);
//                                getSupportFragmentManager().executePendingTransactions();
//                                break;
//                            case R.id.nav_grocery:
//                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.shopping_color)); // setting base colour
//                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
//
//                                LL_menu_shopping.setVisibility(View.GONE);
//                                LL_menu_more.setVisibility(View.GONE);
//                                bundle_shopping.putString("fromhome", "Grocery");
//
//                                Fragment_shopping fragment_shopping9= new Fragment_shopping();
//                                //calling fashion fragment
//
//                                fragment_shopping9.setArguments(bundle_shopping);
//                                replaceFragment(fragment_shopping9);
//                                getSupportFragmentManager().executePendingTransactions();
//                                break;

                            case R.id.nav_dining:

                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.dining_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black


                                LL_menu_more.setVisibility(View.GONE);
                                LL_menu_shopping.setVisibility(View.GONE);

                                replaceFragment(new FragmentDining());
                                getSupportFragmentManager().executePendingTransactions();

                                break;
                            case R.id.nav_entertainment:

                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.entertainment_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black


                                LL_menu_more.setVisibility(View.GONE);
                                LL_menu_shopping.setVisibility(View.GONE);

                                replaceFragment(new FragmentEntertainment());
                                getSupportFragmentManager().executePendingTransactions();

                                break;
                            case R.id.nav_deals:

                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.deals_color)); // setting base colour
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                                LL_menu_more.setVisibility(View.GONE);
                                LL_menu_shopping.setVisibility(View.GONE);


                                replaceFragment(new FragmentOffers());
                                getSupportFragmentManager().executePendingTransactions();
                                break;

                            case R.id.nav_aboutmall:

                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                                LL_menu_more.setVisibility(View.GONE);
                                LL_menu_shopping.setVisibility(View.GONE);

                                replaceFragment(new FragmentAboutMall());
                                getSupportFragmentManager().executePendingTransactions();
                                break;
                            case R.id.nav_howtoreach:
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                                LL_menu_more.setVisibility(View.GONE);
                                LL_menu_shopping.setVisibility(View.GONE);

                                replaceFragment(new FragmentHowToReach());
                                getSupportFragmentManager().executePendingTransactions();

                                break;
                            case R.id.nav_shoppingplanner:
                                RL_menu_more.setBackgroundColor(getResources().getColor(R.color.more_color)); // setting base colour
                                RL_menu_deals.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_dining.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                Rl_menu_entertainment.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
                                RL_menu_Shopping.setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black

                                LL_menu_more.setVisibility(View.GONE);
                                LL_menu_shopping.setVisibility(View.GONE);

                                replaceFragment(new FragmentShoppingPlanner());
                                getSupportFragmentManager().executePendingTransactions();
                                break;
                        }
                        return true;
                    }
                });
    }
    public void onBackPressed() {


        FragmentManager fm = getFragmentManager();


        if (fm.getBackStackEntryCount() >1) {

            try {

                fm.popBackStack();




            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }


        }


        else {
            finish();
        }


        return;

    }

    public  void setNavigationDrawerSelected(int position)
    {


        navigationView.getMenu().getItem(position).setChecked(true);
        navigationView.getMenu().getItem(position).setCheckable(true);


        Menu m = navigationView.getMenu();

        for (int i = 0; i < m.size(); i++) {
            if(i!=position) {
                navigationView.getMenu().getItem(i).setCheckable(false);
//                navigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }

}
