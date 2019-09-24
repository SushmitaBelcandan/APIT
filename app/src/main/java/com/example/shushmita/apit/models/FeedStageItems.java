package com.example.shushmita.apit.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.feed_stagedetails_items)
public class FeedStageItems {

    @View(R.id.cnsLItems)
    public LinearLayout cnsLItems;

    @View(R.id.btnCapacity)
    public Button btnCapacity;

    @View(R.id.btnGetaQuote)
    public Button btnGetaQuote;

    Context mContext;

    public FeedStageItems(Context context) {
        this.mContext = context;
    }

    @Resolve
    public void onResolved() {
        btnCapacity.setText("Capacity 42 tonn");
    }

    @Click(R.id.btnCapacity)
    public void getCapacityDetails() {
        btnCapacity.setBackgroundResource(R.drawable.yellow_roundcrnr);
        btnCapacity.setTextColor(Color.parseColor("#000000"));
        LayoutInflater li = LayoutInflater.from(mContext);
        android.view.View promptsView = li.inflate(R.layout.capacity_desc, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext, R.style.AlertDialogStyle);
        alertDialogBuilder.setView(promptsView);

        // set the custom dialog components - text, image and button
        TextView tvTitle = (TextView) promptsView.findViewById(R.id.tvTitle);
        LinearLayout llClose = (LinearLayout) promptsView.findViewById(R.id.llClose);
        TextView tvStorageNum = (TextView) promptsView.findViewById(R.id.tvStorageNum);
        TextView tvBins = (TextView) promptsView.findViewById(R.id.tvBins);
        TextView tvBucketElevators = (TextView) promptsView.findViewById(R.id.tvBucketElevators);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(680, 600); //Controlling width and height.

        llClose.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                alertDialog.dismiss();
                btnCapacity.setBackgroundResource(R.drawable.blue_border_rounded_cor);
                btnCapacity.setTextColor(Color.parseColor("#ffffff"));
            }
        });


    }
}
