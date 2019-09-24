package com.example.shushmita.apit.fragment_activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shushmita.apit.R;

public class DashBoardFragmentEProcess extends Fragment {

    private static final int MRB_ID = 100;
    private static final int DRB_ID = 200;
    private RadioGroup rdioGrpModels, rdioGrpDryMethod, rdoGrpProcess;
    private String[] models = {"Model 1", "Model 2", "Model 3", "Model 4", "Model 5"};
    private String[] drying = {"Batch Wise LSU Dryer", "Mixed Wise Continuous Dryer"};
    private TextView tvModelName, tvDryingMethod;
    private LinearLayout llModels, llDryingMethods;
    private RelativeLayout rlProcess;
    private Button btnApply;

    public DashBoardFragmentEProcess() {
        //required public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.e_process_frag, container, false);

        rlProcess = view.findViewById(R.id.rlProcess);

        llModels = view.findViewById(R.id.llModels);
        llDryingMethods = view.findViewById(R.id.llDryingMethods);

        rdioGrpModels = view.findViewById(R.id.rdioGrpModels);
        rdioGrpDryMethod = view.findViewById(R.id.rdioGrpDryMethod);
        rdoGrpProcess = view.findViewById(R.id.rdoGrpProcess);

        tvModelName = view.findViewById(R.id.tvModelName);
        tvDryingMethod = view.findViewById(R.id.tvDryingMethod);


        rdoGrpProcess.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtnParboiling) {
                    rdioGrpModels.clearCheck();
                    rdioGrpModels.removeAllViews();
                    rdioGrpDryMethod.clearCheck();
                    rdioGrpDryMethod.removeAllViews();
                    checkItems();
                } else if (checkedId == R.id.rdoBtnSteamCuring) {
                    rdioGrpModels.clearCheck();
                    rdioGrpModels.removeAllViews();
                    rdioGrpDryMethod.clearCheck();
                    rdioGrpDryMethod.removeAllViews();
                    //checkItems();
                } else {
                    rdioGrpModels.clearCheck();
                    rdioGrpModels.removeAllViews();
                    rdioGrpDryMethod.clearCheck();
                    rdioGrpDryMethod.removeAllViews();
                    checkItems();
                }
            }
        });
        //Event Apply
        btnApply = view.findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFragment = new ParboilingModel1To3Fragment();//background color should set on parent layout of new fragment
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.rlProcess, mFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }

    public void checkItems() {
        //models----------------------------
        if (models.length != 0) {
            llModels.setVisibility(View.VISIBLE);
            addModels(models);

        } else {
            llModels.setVisibility(View.GONE);
        }
        //drying------------------------------
        if (drying.length != 0) {
            llDryingMethods.setVisibility(View.VISIBLE);
            addDryers(drying);
        } else {
            llDryingMethods.setVisibility(View.GONE);
        }
    }

    /*  public void selectProcess() {
          rdioGrpModels.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  if (rdioGrpModels.getCheckedRadioButtonId() == -1) {

                      Toast.makeText(getActivity(), "Select Model Name", Toast.LENGTH_SHORT).show();
                  } else {
                      final RadioButton id = (RadioButton) getView().findViewById(rdioGrpModels.getId());
                      if(checkedId == 100)
                      {
                          selectDryingMethod();
                      }
                      else if(checkedId == 101)
                      {
                          selectDryingMethod();
                      }
                      else
                      {
                          Toast.makeText(getActivity(),"test application",Toast.LENGTH_SHORT).show();
                      }

                  }
              }
          });

      }
      public void selectDryingMethod()
      {
          rdioGrpDryMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  if(checkedId == 200)
                  {
                      Fragment mFragment = new ModuleFragment();//background color should set on parent layout of new fragment
                      android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                      ft.replace(R.id.llPraboilModel1to3, mFragment);
                      ft.addToBackStack(null);
                      ft.commit();
                  }
                  else
                  {
                      Toast.makeText(getActivity(),"Select drying method",Toast.LENGTH_SHORT).show();
                  }
              }
          });
      }
  */
    public void addModels(String[] modelArr) {
        for (int i = 0; i < modelArr.length; i++) {
            AppCompatRadioButton radioButton = new AppCompatRadioButton(getActivity());
            radioButton.setId(MRB_ID + i);
            radioButton.setText(modelArr[i]);
            radioButton.setTextSize(14);
            radioButton.setTextColor(getResources().getColor(R.color.black_shade2));

            Typeface font = Typeface.create("sans-serif", Typeface.NORMAL); //font style setting
            radioButton.setTypeface(font);
            rdioGrpModels.addView(radioButton);
        }

    }

    public void addDryers(String[] dryingArr) {
        for (int k = 0; k < dryingArr.length; k++) {
            AppCompatRadioButton radioButton = new AppCompatRadioButton(getActivity());
            radioButton.setId(DRB_ID + k);
            radioButton.setText(String.valueOf(dryingArr));
            radioButton.setTextSize(14);
            radioButton.setTextColor(getResources().getColor(R.color.black_shade2));

            Typeface font = Typeface.create("sans-serif", Typeface.NORMAL); //font style setting
            radioButton.setTypeface(font);
            rdioGrpDryMethod.addView(radioButton);
        }
    }
}
