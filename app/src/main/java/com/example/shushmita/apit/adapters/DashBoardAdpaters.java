package com.example.shushmita.apit.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shushmita.apit.fragment_activities.DashBoardFragmentAbtUs;
import com.example.shushmita.apit.fragment_activities.DashBoardFragmentEProcess;
import com.example.shushmita.apit.fragment_activities.DashBoardFragmentGetaQuote;
import com.example.shushmita.apit.fragment_activities.ModuleFragment;

public class DashBoardAdpaters extends FragmentPagerAdapter {

    private Context mContext;
    int mTotalTabs;


    @Override
    public Fragment getItem(int i) {
       switch (i)
       {
           case 0:
               DashBoardFragmentAbtUs dbAboutUs = new DashBoardFragmentAbtUs();
               return dbAboutUs;

           case 1:
               DashBoardFragmentGetaQuote dbGetAQuote = new DashBoardFragmentGetaQuote();
               return dbGetAQuote;

           case 2:
               DashBoardFragmentEProcess dbEProcess = new DashBoardFragmentEProcess();
               return dbEProcess;

               default:
                   return null;
       }
    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }

    public DashBoardAdpaters(Context context, FragmentManager fm, int totalTabs)
    {
        super(fm);
        this.mContext = context;
        this.mTotalTabs = totalTabs;
    }
}
