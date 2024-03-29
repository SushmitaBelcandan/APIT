package com.apinnovations.apit.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.apinnovations.apit.reference.SessionManager;
import com.apinnovations.apit.R;

import java.util.ArrayList;

public class PaddyImagesAdapter2 extends RecyclerView.Adapter<PaddyImagesAdapter2.SingleViewHolder> {


    SessionManager session;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Images> imgList;
    private int checkedPosition = 1;
    // List<Images> data= Collections.emptyList();
    private OnImageClickListener onImageClickListener;
    ImageId im_id;

    public PaddyImagesAdapter2(Context context, ArrayList<Images> imgs_list,ImageId img_id) {
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
    public void onBindViewHolder(@NonNull PaddyImagesAdapter2.SingleViewHolder singleViewHolder, int i) {
        singleViewHolder.bind(imgList.get(i));

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private CheckBox cbView;
        private int id;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvPaddyClrName);
            imageView = itemView.findViewById(R.id.ivGrainImg);
            cbView = itemView.findViewById(R.id.cbGrain);
        }

        void bind(final Images employee) {
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

                        im_id.setImageId(id);
                        session.saveCHeckPositionEP(id);
                        //send selected image id to fragment
                      /*  int imgId = getAdapterPosition();
                        Intent intent = new Intent("custom-message");
                        intent.putExtra("process_image_id",id);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);*/
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
}
