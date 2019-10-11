package com.example.shushmita.apit.fragment_activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;

public class DashBoardFragmentEProcess extends Fragment {

    private static final int MRB_ID = 100;
    private static final int DRB_ID = 200;
    private RadioGroup rdioGrpModels, rdioGrpDryMethod, rdoGrpProcess, rdioGrpModelsSteam;
    private String[] models = {"Model 1", "Model 2", "Model 3", "Model 4", "Model 5"};
    private String[] drying = {"Batch Wise LSU Dryer", "Mixed Wise Continuous Dryer"};
    private TextView tvModelName, tvDryingMethod, tvModelNameSteam;
    private LinearLayout llModels, llDryingMethods, llModelsSteam;
    private RelativeLayout rlProcess;
    private RadioButton rdoBtnBatchWise, rdoBtnMixedWise;
    private RadioButton rdoBtnSteamModel2, rdoBtnSteamModel1;
    private RadioButton rdoBtnPModel5, rdoBtnPModel4, rdoBtnPModel3, rdoBtnPModel2, rdoBtnPModel1;
    private RadioButton rdoBtnParboiling, rdoBtnSteamCuring, rdoBtnDrying;
    private Button btnApply;
    private String str_prcs_id;
    private String str_model_id;
    private String str_dry_method_id;
    private int i_prcs_id, i_model_id, i_dry_method_id;
    private String parboil_model1 = "PARBOILING Model-123";
    private String batch_wise_tr = "BATCH WISE LSU DRYER";
    private String soaking_tank = "Soaking Tank";
    private String batch_wise_scnd = "Batch Wise LSU Dryer";
    private String mixed_wise_tr = "MIXED WISE CONTINUOUS DRYER";
    private String mixed_wise_scnd = "Mixed Wise Continuous Dryer";
    private String soaking_tnk_with_final_stmng_tnk = "Soaking Tank With Final Steaming Tank";
    private String parboil_model2 = "PARBOILING - Model 2";
    private String parboil_model3 = "PARBOILING - Model 3";
    private String soaking_tank_with_cooker = "Soaking Tank With Cooker";
    private String parboil_model4 = "PARBOILING - Model 4";
    private String soaking_tank_with_pre_and_post_steaming_tank = "Soaking Tank With Pre-Steaming Tank and Post-Steaming";
    private String parboil_model5 = "PARBOILING - Model 5";
    private String soaking_tank_with_pre_and_cooker = "Soaking Tank With Pre-Steaming Tank and Cooker";
    private String steam_model1 = "STEAM CURING PLANT Model - 1";
    private String steam_tank_capcty = "Steaming Tank Capacity";
    private String steam_model2 = "STEAM CURING PLANT Model - 1";
    private String steam_tank_capcty2 = "Tank Capacity With Ageing Tank";



    Handler handler;

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
        llModelsSteam = view.findViewById(R.id.llModelsSteam);

        rdioGrpModels = view.findViewById(R.id.rdioGrpModels);
        rdioGrpDryMethod = view.findViewById(R.id.rdioGrpDryMethod);
        rdoGrpProcess = view.findViewById(R.id.rdoGrpProcess);
        rdioGrpModelsSteam = view.findViewById(R.id.rdioGrpModelsSteam);

        tvModelName = view.findViewById(R.id.tvModelName);
        tvDryingMethod = view.findViewById(R.id.tvDryingMethod);
        tvModelNameSteam = view.findViewById(R.id.tvModelNameSteam);

        rdoBtnParboiling = view.findViewById(R.id.rdoBtnParboiling);
        rdoBtnSteamCuring = view.findViewById(R.id.rdoBtnSteamCuring);
        rdoBtnDrying = view.findViewById(R.id.rdoBtnDrying);


        rdoBtnBatchWise = view.findViewById(R.id.rdoBtnBatchWise);
        rdoBtnMixedWise = view.findViewById(R.id.rdoBtnMixedWise);

        rdoBtnSteamModel2 = view.findViewById(R.id.rdoBtnSteamModel2);
        rdoBtnSteamModel1 = view.findViewById(R.id.rdoBtnSteamModel1);

        rdoBtnPModel5 = view.findViewById(R.id.rdoBtnPModel5);
        rdoBtnPModel4 = view.findViewById(R.id.rdoBtnPModel4);
        rdoBtnPModel3 = view.findViewById(R.id.rdoBtnPModel3);
        rdoBtnPModel2 = view.findViewById(R.id.rdoBtnPModel2);
        rdoBtnPModel1 = view.findViewById(R.id.rdoBtnPModel1);
        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {

                handler.postDelayed(this, 3000);
            }
        };

        handler.postDelayed(r, 3000);

        getProcess();

        //Event Apply
        btnApply = view.findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForms();
            }
        });
        return view;

    }

    public String getProcess() {
        rdoGrpProcess.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtnParboiling) {

                    str_prcs_id = "1";
                    llModels.setVisibility(View.VISIBLE);
                    llModelsSteam.setVisibility(View.GONE);
                    llDryingMethods.setVisibility(View.VISIBLE);
                    getParboilingModels();
                    getDryingMethods();
                    return;

                } else if (checkedId == R.id.rdoBtnSteamCuring) {

                    str_prcs_id = "2";
                    llModelsSteam.setVisibility(View.VISIBLE);
                    llModels.setVisibility(View.GONE);
                    llDryingMethods.setVisibility(View.VISIBLE);
                    getSteamModels();
                    getDryingMethods();
                    return;

                } else {
                    str_prcs_id = "3";
                    llModelsSteam.setVisibility(View.GONE);
                    llModels.setVisibility(View.GONE);
                    llDryingMethods.setVisibility(View.VISIBLE);
                    getDryingMethods();
                    return;
                }

            }
        });
        return str_prcs_id;
    }

    public String getParboilingModels() {


        rdioGrpModels.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtnPModel1) {
                    str_model_id = "1";
                    return;

                } else if (checkedId == R.id.rdoBtnPModel2) {
                    str_model_id = "2";
                    return;

                } else if (checkedId == R.id.rdoBtnPModel3) {

                    str_model_id = "3";
                    return;

                } else if (checkedId == R.id.rdoBtnPModel4) {

                    str_model_id = "4";
                    return;

                } else {

                    str_model_id = "5";
                    return;

                }
            }
        });
        return str_model_id;
    }

    public String getSteamModels() {

        rdioGrpModelsSteam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtnSteamModel1) {
                    str_model_id = "1";
                    return;

                } else {
                    str_model_id = "2";
                    return;
                }
            }
        });
        return str_model_id;
    }

    public String getDryingMethods() {

        rdioGrpDryMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rdoBtnBatchWise) {
                    str_dry_method_id = "1";
                    return;

                } else {
                    str_dry_method_id = "2";
                    return;
                }
            }
        });
        return str_dry_method_id;
    }

    public void getForms() {

        if (str_prcs_id.equals("1")) {

            if (str_model_id.equals("1")) {
                if (str_dry_method_id.equals("1")) {
                    //parboiling-model1-batchwise

                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m1d1");
                    bundle.putString("title1", parboil_model1);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", soaking_tank);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //parboiling-model1-mixedwise

                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m1d2");
                    bundle.putString("title1", parboil_model1);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", soaking_tank);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else if (str_model_id.equals("2")) {
                if (str_dry_method_id.equals("1")) {
                    //parboiling-model2-batchwise

                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m2d1");
                    bundle.putString("title1", parboil_model2);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", soaking_tnk_with_final_stmng_tnk);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //parboiling-model2-mixedwise

                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m2d2");
                    bundle.putString("title1", parboil_model2);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", soaking_tnk_with_final_stmng_tnk);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else if (str_model_id.equals("3")) {
                if (str_dry_method_id.equals("1")) {
                    //parboiling-model3-batchwise


                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m3d1");
                    bundle.putString("title1", parboil_model3);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", soaking_tank_with_cooker);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //parboiling-model3-mixedwise


                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m3d2");
                    bundle.putString("title1", parboil_model3);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", soaking_tank_with_cooker);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else if (str_model_id.equals("4")) {
                if (str_dry_method_id.equals("1")) {
                    //parboiling-model4-batchwise


                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m4d1");
                    bundle.putString("title1", parboil_model4);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", soaking_tank_with_pre_and_post_steaming_tank);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //parboiling-model4-mixedwise


                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m4d2");
                    bundle.putString("title1", parboil_model4);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", soaking_tank_with_pre_and_post_steaming_tank);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else if (str_model_id.equals("5")) {
                if (str_dry_method_id.equals("1")) {
                    //parboiling-model5-batchwise

                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m5d1");
                    bundle.putString("title1", parboil_model5);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", soaking_tank_with_pre_and_cooker);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //parboiling-model5-mixedwise

                    Fragment mFragment = new ParboilingModelEPFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p1m5d2");
                    bundle.putString("title1", parboil_model5);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", soaking_tank_with_pre_and_cooker);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please select Model", Toast.LENGTH_SHORT).show();
            }
        } else if (str_prcs_id.equals("2")) {

            if (str_model_id.equals("1")) {
                if (str_dry_method_id.equals("1")) {
                    //steam-model1-batchwise

                    Fragment mFragment = new SteamCuringFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p2m1d1");
                    bundle.putString("title1", steam_model1);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", steam_tank_capcty);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //steam-model1-mixedwise

                    Fragment mFragment = new SteamCuringFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p2m1d2");
                    bundle.putString("title1", steam_model1);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", steam_tank_capcty);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else if (str_model_id.equals("2")) {
                if (str_dry_method_id.equals("1")) {
                    //steam-model2-batchwise

                    Fragment mFragment = new SteamCuringFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p2m2d1");
                    bundle.putString("title1", steam_model2);
                    bundle.putString("title2", batch_wise_tr);
                    bundle.putString("title3", steam_tank_capcty2);
                    bundle.putString("title4", batch_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else if (str_dry_method_id.equals("2")) {
                    //steam-model2-mixedwise


                    Fragment mFragment = new SteamCuringFragment();//background color should set on parent layout of new fragment
                    android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                    Bundle bundle = new Bundle();
                    bundle.putString("id", "p2m2d2");
                    bundle.putString("title1", steam_model2);
                    bundle.putString("title2", mixed_wise_tr);
                    bundle.putString("title3", steam_tank_capcty2);
                    bundle.putString("title4", mixed_wise_scnd);
                    mFragment.setArguments(bundle);
                    ft.replace(R.id.rlProcess, mFragment);
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "Please select Model", Toast.LENGTH_SHORT).show();
            }

        } else if (str_prcs_id.equals("3")) {

            if (str_dry_method_id.equals("1")) {
                //batchwise

                Fragment mFragment = new DryingBatchWiseFragment();//background color should set on parent layout of new fragment
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("id", "p3d1");
                bundle.putString("title1", batch_wise_scnd);
                mFragment.setArguments(bundle);
                ft.replace(R.id.rlProcess, mFragment);
                ft.addToBackStack(null);
                ft.commit();

            } else if (str_dry_method_id.equals("2")) {
                //mixedwise

                Fragment mFragment = new DryingBatchWiseFragment();//background color should set on parent layout of new fragment
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("id", "p3d2");
                bundle.putString("title1", mixed_wise_scnd);
                mFragment.setArguments(bundle);
                ft.replace(R.id.rlProcess, mFragment);
                ft.addToBackStack(null);
                ft.commit();

            } else {
                Toast.makeText(getActivity(), "Please select Drying Method", Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(getActivity(), "Please select Process", Toast.LENGTH_SHORT).show();

        }
    }

}
