package com.apinnovations.apit.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.apinnovations.apit.adapters.DashBoardAdpaters;
import com.apinnovations.apit.reference.SessionManager;
import com.apinnovations.apit.retrofit_models.APIClient;
import com.apinnovations.apit.retrofit_models.APIInterface;
import com.apinnovations.apit.retrofit_models.EnqFormStatusModel;
import com.apinnovations.apit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard_Act extends AppCompatActivity {

    APIInterface apiInterface;
    SessionManager sessionManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        sessionManager = new SessionManager(getApplicationContext());
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("About Us"));
        tabLayout.addTab(tabLayout.newTab().setText("Get a Quote"));
        tabLayout.addTab(tabLayout.newTab().setText("E-Process"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //---add margins between tab items-------
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            if (i != 2) {
                View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
                p.setMargins(0, 0, 1, 0);
                tab.requestLayout();
            }
        }
        getEnquiryFormStatus(sessionManager.getUsrId());
        //----------------------
        DashBoardAdpaters dbAdapters = new DashBoardAdpaters(this, getSupportFragmentManager(), tabLayout.getTabCount());
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

    //--------------------refersh admin status -----------------------
    public void getEnquiryFormStatus(String usr_id) {

        EnqFormStatusModel enqFStausModel = new EnqFormStatusModel(usr_id);
        final Call<EnqFormStatusModel> enqFormStatusModelCall = apiInterface.getEnqFormStatus(enqFStausModel);
        enqFormStatusModelCall.enqueue(new Callback<EnqFormStatusModel>() {
            @Override
            public void onResponse(Call<EnqFormStatusModel> call, Response<EnqFormStatusModel> response) {
                EnqFormStatusModel enqFormStatusResources = response.body();
                List<EnqFormStatusModel.EnqFormRespDatum> formStatus = enqFormStatusResources.response;
                for (EnqFormStatusModel.EnqFormRespDatum formStatusList : formStatus) {
                    if (formStatusList.status.equals("1")) {
                        //  Toast.makeText(DashBoard_Act.this, formStatusList.status, Toast.LENGTH_SHORT).show();
                    } else if (formStatusList.status.equals("2")) {
                        // Toast.makeText(DashBoard_Act.this, formStatusList.status, Toast.LENGTH_SHORT).show();
                    } else {
                        //do nothing
                        // sessionManager.clearEnquiryForm();
                    }
                }
            }

            @Override
            public void onFailure(Call<EnqFormStatusModel> call, Throwable t) {

            }
        });


    }
}