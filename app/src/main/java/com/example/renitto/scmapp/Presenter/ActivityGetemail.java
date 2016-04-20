package com.example.renitto.scmapp.Presenter;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renitto.scmapp.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Renitto on 3/13/2016.
 */
public class ActivityGetemail extends AppCompatActivity {

    Button BT_gt_email_getin,BT_gt_skip;
    EditText  ET_get_email;
    String possibleEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_email);

        BT_gt_email_getin = (Button)findViewById(R.id.bt_get_mail_getin);
        BT_gt_skip = (Button)findViewById(R.id.bt_get_mail_skip);
        ET_get_email = (EditText) findViewById(R.id.et_get_email);




        // skipping to home

        BT_gt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityGetemail.this, ActivityHome.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();

            }
        });



        // setting permission

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

                // getting email address automatically
                Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
                Account[] accounts = AccountManager.get(ActivityGetemail.this).getAccounts();
                for (Account account : accounts) {
                    if (emailPattern.matcher(account.name).matches()) {
                        possibleEmail = account.name;

                    }
                }

                if (possibleEmail != null)
                    ET_get_email.setText(possibleEmail);

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(ActivityGetemail.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };


        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("we need permission to read your contact")
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();







    }
}

