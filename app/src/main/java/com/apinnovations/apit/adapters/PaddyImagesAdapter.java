package com.apinnovations.apit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.apinnovations.apit.reference.SessionManager;
import com.apinnovations.apit.R;

import java.util.ArrayList;

public class PaddyImagesAdapter extends RecyclerView.Adapter<PaddyImagesAdapter.SingleViewHolder> {

    SessionManager session;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Images> imgList;
    private int checkedPosition = 1;
    ImageId im_id;
    // List<Images> data= Collections.emptyList();

    public PaddyImagesAdapter(Context context, ArrayList<Images> imgs_list, ImageId img_id) {
        this.context = context;
        this.imgList = imgs_list;
        this.im_id = img_id;
    }

    public void setEmployees(ArrayList<Images> images) {
        this.imgList = new ArrayList<>();
        this.imgList = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.paddy_colors_model, viewGroup, false);
        session = new SessionManager(context);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaddyImagesAdapter.SingleViewHolder singleViewHolder, int i) {
        singleViewHolder.bind(imgList.get(i));
        //singleViewHolder.cbView.setClickable(false);
    }


    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private CheckBox cbView;
        private int id = 0;
        private boolean chkbx_ststus = false;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvPaddyClrName);
            imageView = itemView.findViewById(R.id.ivGrainImg);
            cbView = itemView.findViewById(R.id.cbGrain);
        }

        void bind(final Images employee) {

            Integer checkedPositionwith_id = session.getCHeckPosition();
            //cbView.setChecked(true);

            getAdapterItemPosition(checkedPositionwith_id);
            Log.d("checkedPositionwith_id", String.valueOf(getAdapterItemPosition(checkedPositionwith_id)));
            checkedPosition=getAdapterItemPosition(checkedPositionwith_id);
            cbView.setChecked(true);
            chkbx_ststus = session.getChkBxStatus();

            if(chkbx_ststus == true)
            {
                cbView.setClickable(false);
                cbView.setEnabled(false);
            }
            else {
                cbView.setClickable(true);
                cbView.setEnabled(true);
            }



            if (checkedPosition == 1) {
                cbView.setChecked(false);

            } else {
                if (checkedPosition == getAdapterPosition()) {
                    cbView.setChecked(true);
                } else {
                    cbView.setChecked(false);
                }
            }

            textView.setText(employee.getImageName());
            Glide.with(context).load(employee.getImgUrl()).into(imageView);
            id = employee.getImagId();

            cbView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cbView.setChecked(true);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                        //send selected image id to fragment
                        im_id.setImageId(id);
                        session.saveCHeckPosition(id);

                    }
                }
            });
        }
    }

    public Images getSelected() {
        if (checkedPosition != 1) {
            session = new SessionManager(context);
            return imgList.get(checkedPosition);
        }
        return null;
    }

    private int getAdapterItemPosition(Integer id)
    {
        for (int position=0; position<imgList.size(); position++)
            if (imgList.get(position).getImagId() == id)
                return position;
        return -1;
    }

}
