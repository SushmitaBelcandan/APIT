package com.example.shushmita.apit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.DashBoardAdpaters;

public class DashBoard_Act extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("About Us"));
        tabLayout.addTab(tabLayout.newTab().setText("Get a Quote"));
        tabLayout.addTab(tabLayout.newTab().setText("E-Process"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //---add margins between tab items-------
        for(int i=0; i < tabLayout.getTabCount(); i++) {
            if(i !=2) {
                View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
                p.setMargins(0, 0, 1, 0);
                tab.requestLayout();
            }
        }
        //----------------------
        DashBoardAdpaters dbAdapters = new DashBoardAdpaters(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(dbAdapters);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}