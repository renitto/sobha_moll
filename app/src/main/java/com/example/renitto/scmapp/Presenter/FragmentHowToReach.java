package com.example.renitto.scmapp.Presenter;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renitto.scmapp.R;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Renitto on 2/26/2016.
 */
public class FragmentHowToReach extends Fragment {

    private GoogleMap googleMap;
    TextView TV_hwtr_phone_number,TV_hwt_weblink,TV_hwt_email;

    View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // for starting animation for button

        // setting navigation drawer

        ((ActivityHome)getActivity()).setNavigationDrawerSelected(6);


        getActivity().findViewById(R.id.ll_menu_shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ll_menu_more).setVisibility(View.GONE);

        getActivity().findViewById(R.id.rl_menu_shopping).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_dining).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_entertainment).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_deals).setBackgroundColor(getResources().getColor(R.color.black)); // changing other to black
        getActivity().findViewById(R.id.rl_menu_more).setBackgroundColor(getResources().getColor(R.color.more_color)); // changing other to black


        // view is inflated only if view is null
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        else {
            try {
                rootView = inflater.inflate(R.layout.how_to_reach, container, false);
            } catch (InflateException e) {
        /* map is already there, just return view as it is */
            }
        }


      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            googleMap = ((MapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();

        } else {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        }*/




        TV_hwtr_phone_number = (TextView)rootView.findViewById(R.id.tv_hwtr_phone_number);
        TV_hwt_weblink = (TextView)rootView.findViewById(R.id.tv_hwt_weblink);
        TV_hwt_email = (TextView)rootView.findViewById(R.id.tv_hwt_email);

        TV_hwtr_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // setting permissions
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        // calling to that number
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:+"+"04872379000"));
                        startActivity(callIntent );


                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(getActivity(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };


                new TedPermission(getActivity())
                        .setPermissionListener(permissionlistener)
                        .setRationaleMessage("we need permission to Call from Phone")
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.CALL_PHONE)
                        .check();



            }
        });

        TV_hwt_weblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //chrome custom tab click
                ((ActivityHome)getActivity()).showCustomChromTabs("http://www.sobhacity.co.in/sobha-citymall-thrissur.html");
            }
        });

        TV_hwt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // setting permissions
                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "info@sobhacitymall.com"));
                        intent.putExtra(Intent.EXTRA_SUBJECT, "About Mall");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hi sobha city mall");
                        startActivity(intent);


                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(getActivity(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };


                new TedPermission(getActivity())
                        .setPermissionListener(permissionlistener)
                        .setRationaleMessage("we need permission to read your contact")
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.READ_CONTACTS)
                        .check();





            }
        });


        return rootView;
    }

    // to avoid null reference
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        LatLng latLng = new LatLng(10.5460149,76.17975509999999);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Sobha City Mall")
//                .snippet("Sobha City!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

    }
}