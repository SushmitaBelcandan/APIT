package com.apinnovations.apit.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;

import com.apinnovations.apit.R;
import com.apinnovations.apit.activities.AfterProcess_Act;
import com.apinnovations.apit.fragment_activities.NotificationDetailFragment;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.notification_list_items_model)
public class NotificationModels extends Fragment{

    @View(R.id.itemIconNotifiB)
    public ImageView itemIconNotifiB;

    @View(R.id.itemIconNotifiG)
    public ImageView itemIconNotifiG;

    @View(R.id.timeNotifi)
    public TextView timeNotifi;

    @View(R.id.tvTitleNotifi)
    public TextView tvTitleNotifi;

    @View(R.id.tvNotifiDesc)
    public TextView tvNotifiDesc;


    public Context mContext;

    public NotificationModels() {
        //required public constructor
    }


    @SuppressLint("ValidFragment")
    public NotificationModels(Context context) {
        this.mContext = context;
    }

    public void onResolved() {
        tvTitleNotifi.setText("Title1");
        tvNotifiDesc.setText("Description");
    }

    @Click(R.id.notyfi_lt)
    public void detailedFragment() {
        Fragment mFragment = new NotificationDetailFragment();//background color should set on parent layout of new fragment
        FragmentTransaction ft =  ((AfterProcess_Act)mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.llFragContainer, mFragment);
        ft.commit();

    }
}
