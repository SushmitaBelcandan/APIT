package com.example.shushmita.apit.adapters;

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
import com.example.shushmita.apit.R;

import java.util.ArrayList;

public class PaddyImagesAdapter extends RecyclerView.Adapter<PaddyImagesAdapter.SingleViewHolder> {


    private Context context;
    private ArrayList<Images> img_id;
    private ArrayList<Images> img_name;
    private ArrayList<Images> img_url;
    private int checkedPosition = 1;

    public PaddyImagesAdapter(Context context, ArrayList<Images> imgsId, ArrayList<Images> imgsName, ArrayList<Images> imgsUrl) {
        this.context = context;
        this.img_id = imgsId;
        this.img_name = imgsName;
        this.img_url = imgsUrl;
    }

    public void setEmployees(ArrayList<Images> employees,ArrayList<Images> imgsName, ArrayList<Images> imgsUrl) {
        this.img_id = new ArrayList<>();
        this.img_id = employees;
        this.img_name = new ArrayList<>();
        this.img_name = imgsName;
        this.img_url = new ArrayList<>();
        this.img_url  = imgsUrl;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.paddy_colors_model, viewGroup, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaddyImagesAdapter.SingleViewHolder singleViewHolder, int i) {
        singleViewHolder.bind(img_id.get(i));
    }

    @Override
    public int getItemCount() {
        return img_id.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private CheckBox cbView;

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


            cbView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cbView.setChecked(true);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();

                    }
                }
            });
        }
    }

    public Images getSelected() {
        if (checkedPosition != 1) {
            return img_id.get(checkedPosition);
        }
        return null;
    }
}
