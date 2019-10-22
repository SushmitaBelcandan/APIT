package com.apinnovations.apit.models;

import android.content.Context;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.apinnovations.apit.adapters.CustomExpandableListAdapter;
import com.apinnovations.apit.fragment_activities.ModuleFragment;
import com.apinnovations.apit.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Layout(R.layout.custom_list_activity)
public class ExpandListModels {

    @View(R.id.expandableListView)
    public ExpandableListView expandableListView;

    Context mContext;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public ExpandListModels(Context contxt)
    {
        this.mContext = contxt;
    }
    @Resolve
    public void onResolved()
    {
        expandableListDetail = ModuleFragment.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(mContext, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(mContext,
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
               // expandableListView.setBackgroundColor(Color.parseColor("#F9CE18"));
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(mContext,
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();
                //expandableListView.setBackgroundColor(Color.parseColor("#0c53a0"));

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, android.view.View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, expandableListTitle.get(groupPosition)
                        + " -> "
                        + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
