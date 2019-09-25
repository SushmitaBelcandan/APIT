package com.example.shushmita.apit.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shushmita.apit.R;


import java.util.List;

public class SpinnerProcessAdapter extends ArrayAdapter<ProcessList> {

    LayoutInflater inflater;

    public SpinnerProcessAdapter(Activity context, int resource, int textViewResourceId, List<ProcessList> objects) {
        super(context, resource, textViewResourceId, objects);
        inflater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProcessList processList = getItem(position);

        View rowview = inflater.inflate(R.layout.process_list, null, true);

        TextView tvProcessName = (TextView) rowview.findViewById(R.id.tvProcessName);
        tvProcessName.setText(processList.getTitle());

        return rowview;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.process_list, parent, false);
        }
        ProcessList processList = getItem(position);
        TextView tvProcessName = (TextView) convertView.findViewById(R.id.tvProcessName);
        tvProcessName.setText(processList.getTitle());

        return convertView;
    }
}
