package com.sparity.revestarpubnub.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.sparity.revestarpubnub.R;
import com.sparity.revestarpubnub.app.fragments.FragmentHome;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar(R.id.toolbar, "Messages");

        FragmentTransaction ft;
        FragmentHome fragmentHome = new FragmentHome();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout, fragmentHome).commit();
    }
}
