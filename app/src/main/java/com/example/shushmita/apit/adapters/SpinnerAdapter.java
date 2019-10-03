package com.example.shushmita.apit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shushmita.apit.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> custTypeNameArr;
    ArrayList<Integer> custTypeIdArr;
    LayoutInflater inflater;
    private int hidingItemIndex;


    public SpinnerAdapter(Context applicationContext, ArrayList<String> cust_type_name, ArrayList<Integer> cust_type_id,int hidingItemIndex) {

        this.context = applicationContext;
        this.custTypeNameArr = cust_type_name;
        this.custTypeIdArr = cust_type_id;
        this.hidingItemIndex = hidingItemIndex;
        inflater = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return custTypeNameArr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.cust_dropdown_items, null);
        TextView names = (TextView) view.findViewById(R.id.tvCustType);
        names.setText(custTypeNameArr.get(i));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_spnr_dropdown_view, null);
        if (position == hidingItemIndex) {
            TextView names = (TextView) convertView.findViewById(R.id.tvCustTypeDropDown);
            names.setVisibility(View.GONE);
            convertView = names;
        } else {
            TextView names = (TextView) convertView.findViewById(R.id.tvCustTypeDropDown);
            names.setText(custTypeNameArr.get(position));
        }
        return convertView;
    }
}