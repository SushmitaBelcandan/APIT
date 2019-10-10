package com.example.shushmita.apit.models;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shushmita.apit.R;
import com.example.shushmita.apit.reference.SessionManager;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.ArrayList;

@NonReusable
@Layout(R.layout.paddy_colors_model)
public class PaddyColorsModels {

    @View(R.id.ivGrainImg)
    public ImageView ivGrainImg;

    @View(R.id.cbGrain)
    public CheckBox cbGrain;

    @View(R.id.tvPaddyClrName)
    public TextView tvPaddyClrName;

    SessionManager session;
    public Context mContext;
    public String colorName, imageUrl;
    public int imgId;
    public int selectedImageId;
    public int ckhCb;

    public PaddyColorsModels(Context contxt, int img_id, String color_name, String img_url) {
        this.mContext = contxt;
        this.imgId = img_id;
        this.colorName = color_name;
        this.imageUrl = img_url;
    }

    @Resolve
    public void onResolved() {

        session = new SessionManager(mContext);
        //-------------paddy colours-------------------------
        if (colorName != null && !colorName.isEmpty() && !colorName.equals("null")) {
            tvPaddyClrName.setText(colorName);
        }
        else
        {
            tvPaddyClrName.setText("");
        }
        //-------------image url------------------------------
        if (imageUrl != null && !imageUrl.isEmpty() && !imageUrl.equals("null")) {
            Glide.with(mContext).load(imageUrl).into(ivGrainImg);
        }
        else
        {
            Glide.with(mContext).load(R.drawable.default_img).into(ivGrainImg);
        }
        //------------checkbox-----------------
       /* cbGrain.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(cbGrain.isChecked())
                {

                }
                else
                {
                    session.selectedImage(imgId,false);
                }
            }
        });
*/
    }

}
