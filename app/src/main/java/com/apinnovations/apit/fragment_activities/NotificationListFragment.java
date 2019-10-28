package com.apinnovations.apit.fragment_activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apinnovations.apit.R;
import com.apinnovations.apit.models.NotificationModels;
import com.mindorks.placeholderview.PlaceHolderView;

public class NotificationListFragment extends Fragment {

    ProgressDialog progressdialog;

    private PlaceHolderView recycler_notify;
    private TextView tvEmptyNotifications;

    public NotificationListFragment() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.notifi_list_holder, container, false);

        progressdialog = new ProgressDialog(getActivity());
        progressdialog.setMessage("Please Wait....");

        try {
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        recycler_notify = view.findViewById(R.id.recycler_notify);
        tvEmptyNotifications = view.findViewById(R.id.tvEmptyNotifications);

        recycler_notify.setBackgroundColor(Color.parseColor("#FFFFFF"));
        recycler_notify.addView(new NotificationModels(getContext()));
        recycler_notify.addView(new NotificationModels(getContext()));
        recycler_notify.addView(new NotificationModels(getContext()));
        recycler_notify.addView(new NotificationModels(getContext()));
        recycler_notify.addView(new NotificationModels(getContext()));
        progressdialog.dismiss();
        return view;

    }
}
