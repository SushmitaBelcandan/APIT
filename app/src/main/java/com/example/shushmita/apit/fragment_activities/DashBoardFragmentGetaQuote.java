package com.example.shushmita.apit.fragment_activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shushmita.apit.R;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragmentGetaQuote extends Fragment {

    private TextView tvSoilBearingCapacity;
    private EditText etSoilBearingCapacity;
    private LinearLayout llAddGrainVarities;
    LayoutInflater inflater;
    private LinearLayout llGrainDetailContainer;

    EditText etGrainVarity;
    List<View> allEds = new ArrayList<View>();
    int IdNum = 1;

    public DashBoardFragmentGetaQuote() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.get_a_quote_frag, container, false);
        tvSoilBearingCapacity = view.findViewById(R.id.tvSoilBearingCapacity);
        tvSoilBearingCapacity.setText(Html.fromHtml("Soil Bearing Capacity ( t/m<sup><small>2</small></sup> )"));

        etSoilBearingCapacity = view.findViewById(R.id.etSoilBearingCapacity);
        etSoilBearingCapacity.setHint(Html.fromHtml("Enter Soil Bearing Capacity in t/m<sup><small>2</small></sup>"));

        llGrainDetailContainer = view.findViewById(R.id.llGrainDetailContainer);
        llAddGrainVarities = view.findViewById(R.id.llAddGrainVarities);
        llAddGrainVarities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* etGrainVarity= new EditText(new ContextThemeWrapper(getActivity(), R.style.GrainVerityStyle), null, 0);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                allEds.add(etGrainVarity);
                etGrainVarity.setId(IdNum);
                etGrainVarity.setHint("Grain Varities");
                ((LinearLayout) view.findViewById(R.id.llGrainDetailContainer)).addView(etGrainVarity);
                IdNum++;*/
                onAddField(v);

            }
        });
        return view;

    }

    public void onAddField(View v) {
        inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field_grain_varities, null);
        final LinearLayout llRemoveGrains = rowView.findViewById(R.id.llRemoveGrains);
        final LinearLayout llAddGrains = rowView.findViewById(R.id.llAddGrains);
        llRemoveGrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("--------delete item-------","----------delete item--------");
                onDelete(v);

            }
        });
        llAddGrains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddField(v);
            }
        });
        // Add the new row before the add field button.
        allEds.add(rowView);
        llGrainDetailContainer.addView(rowView, llGrainDetailContainer.getChildCount() - 1);
        if (allEds.size() > 1) {
            llRemoveGrains.setVisibility(View.GONE);
            llAddGrains.setVisibility(View.VISIBLE);
        }
    }

    public void onDelete(View v) {
        LinearLayout linearChild = (LinearLayout) v.getParent().getParent().getParent().getParent();//getting rootparent
        linearChild.setVisibility(View.GONE);
    }
}
