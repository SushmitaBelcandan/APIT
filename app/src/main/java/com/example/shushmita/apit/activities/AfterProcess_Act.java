package com.example.shushmita.apit.activities;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.ProcessList;
import com.example.shushmita.apit.fragment_activities.AfterProcessFragment;
import com.example.shushmita.apit.fragment_activities.DashBoardFragmentAbtUs;
import com.example.shushmita.apit.fragment_activities.DashBoardFragmentEProcess;
import com.example.shushmita.apit.fragment_activities.DashBoardFragmentGetaQuote;

import de.hdodenhof.circleimageview.CircleImageView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AfterProcess_Act extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drwLayout;
    NavigationView nav_viewProfile;
    View headerView;
    CircleImageView ivProfilePic;
    List<String> processList;
    private Spinner spnrAPProcessType;
    private LinearLayout llSelectDate, llBatchNo, llDate, llProfileDesc, llEditProfile;
    private TextView tvBatchNum, tvSelectDate, tvName, tvEmail, tvMobileNo;
    private EditText etUserId;
    private String strBatchNum, strProcessName, strDate;
    private Button btnSubmit;
    //private EditText etAPMoisture;
    private ImageButton ibToggleIcon;

    final Calendar myCalendar = Calendar.getInstance();
    public static final String[] processArr = new String[]{"Select Type of Process",
            "Parboiling", "Steam Curing Plant", "Drying"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_process_act);

        drwLayout = findViewById(R.id.drwLayout);
        spnrAPProcessType = findViewById(R.id.spnrAPProcessType);
        llSelectDate = findViewById(R.id.llSelectDate);
        llBatchNo = findViewById(R.id.llBatchNo);
        llDate = findViewById(R.id.llDate);
        etUserId = findViewById(R.id.etUserId);
        etUserId.setEnabled(false); //disable write option
        tvBatchNum = findViewById(R.id.tvBatchNum);
        tvSelectDate = findViewById(R.id.tvSelectDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        // etAPMoisture = findViewById(R.id.etAPMoisture);
        //-----------------------left nav----------
        nav_viewProfile = (NavigationView) findViewById(R.id.nav_viewProfile);
        nav_viewProfile.setNavigationItemSelectedListener(this);
        setNavMenuItemThemeColors();
        headerView = nav_viewProfile.getHeaderView(0);
        tvName = headerView.findViewById(R.id.tvName);
        tvEmail = headerView.findViewById(R.id.tvEmail);
        tvMobileNo = headerView.findViewById(R.id.tvMobileNo);
        llProfileDesc = headerView.findViewById(R.id.llProfileDesc);
        llEditProfile = headerView.findViewById(R.id.llEditProfile);
        ivProfilePic = headerView.findViewById(R.id.ivProfilePic);
        ibToggleIcon = findViewById(R.id.ibToggleIcon);
        ibToggleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drwLayout.openDrawer(Gravity.LEFT);
            }
        });
        //-----------------------------------------------------------
        strBatchNum = "null";
        strProcessName = "null";
        strDate = "null";
        //----------------------------------spinner select process---------------------------------------------
        processList = new ArrayList<String>();
        for (int i = 0; i < processArr.length; i++) {

            ProcessList item = new ProcessList(processArr[i]);
            processList.add(processArr[i]);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, processList);
        dataAdapter.setDropDownViewResource(R.layout.process_list);
        spnrAPProcessType.setAdapter(dataAdapter);
        spnrAPProcessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.WHITE); //Change selected text color
                ((TextView) view).setTextAppearance(AfterProcess_Act.this, R.style.after_process_2c);
                strProcessName = spnrAPProcessType.getSelectedItem().toString(); //selected string
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                strProcessName = "null";
            }
        });
        //-------------------------------------------------------------------------------------------------------
        //select date---------------------------
        datePicker();
        llBatchNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        //--------------------------display your id------------------------------------------
       /* etAPMoisture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    setUserId();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        //--------------------------------------------------------------------

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drwLayout);
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }
    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.menuleft_after_process:
                fragment = new AfterProcessFragment();
                break;
            case R.id.menuleft_yeild_mod:
                fragment = new DashBoardFragmentEProcess();
                break;
            case R.id.menuleft_mass_blnc:
                fragment = new DashBoardFragmentGetaQuote();
                break;
            case R.id.menuleft_process_batch:
                fragment = new DashBoardFragmentAbtUs();
                break;
            case R.id.menuleft_notifi:
                fragment = new AfterProcessFragment();
                break;
            case R.id.menuleft_logout:
                fragment = new AfterProcessFragment();
                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.llFragContainer, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drwLayout);
        drawer.closeDrawer(Gravity.START);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(menuItem.getItemId());
        //make this method blank
        return true;
    }
    public void setNavMenuItemThemeColors() {
        //Setting default colors for menu item Text and Icon
        int navDefaultTextColor = Color.parseColor("#0C53A0");
        int navDefaultIconColor = Color.parseColor("#0C53A0");
        int navActiveIconColor = Color.parseColor("#4A4A4A");

        //Defining ColorStateList for menu item Text
        ColorStateList navMenuTextList = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked},
                        new int[]{-android.R.attr.state_checked}

                },
                new int[]{
                        navDefaultTextColor,
                        navDefaultTextColor
                }
        );

        //Defining ColorStateList for menu item Icon
        ColorStateList navMenuIconList = new ColorStateList(
                new int[][]{

                        new int[]{android.R.attr.state_checked},
                        new int[]{-android.R.attr.state_checked}
                },
                new int[]{
                        navActiveIconColor,
                        navDefaultIconColor
                }
        );

        nav_viewProfile.setItemTextColor(navMenuTextList);
        nav_viewProfile.setItemIconTintList(navMenuIconList);
    }

    //----------------------------------------------------------select date methods-------------------------------------
    public void datePicker() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        llSelectDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog dpd = new DatePickerDialog(AfterProcess_Act.this, R.style.MyThemeOverlay, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });
    }

    private void updateLabel() {
        String myFormat = "ddMMyyyy";
        String dateFormat = "dd/MM/yyyy";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormat, Locale.US);
        if (!sdf.equals("null")) {
            strDate = sdf.format(myCalendar.getTime());
            llDate.setVisibility(View.GONE);
            tvSelectDate.setText(" " + sdfDate.format(myCalendar.getTime()));
        } else {
            strDate = "null";
        }


    }

    //-----------------------------------------------------------------------------------------------------
    //----------------------------------------------enter batch number-------------------------------------
    public void showDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View promptsView = LayoutInflater.from(this).inflate(R.layout.batch_num_dialog, viewGroup, false);
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AfterProcess_Act.this, R.style.AlertDialogStyle);
        alertBuilder.setView(promptsView);

        final EditText etBatchNo = promptsView.findViewById(R.id.etBatchNo);
        TextView tvBatchNoDialog = promptsView.findViewById(R.id.tvBatchNoDialog);
        Button btnSubmit = promptsView.findViewById(R.id.btnSubmit);
        Button btnCancel = promptsView.findViewById(R.id.btnCancel);


        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
        //cancel batch no.
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //submit batch no.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  strBatchNum = etBatchNo.getText().toString();


                if (!etBatchNo.getText().toString().trim().equals(null) &&
                        !etBatchNo.getText().toString().trim().equals("null") &&
                        !etBatchNo.getText().toString().trim().isEmpty()) {
                    strBatchNum = etBatchNo.getText().toString();
                    tvBatchNum.setText(strBatchNum);
                    alertDialog.dismiss();
                } else {
                    strBatchNum = "null";
                    etBatchNo.requestFocus();
                    etBatchNo.setError("Invalid Batch Number");
                }

            }
        });


    }

    public void setUserId() {
        //---------------your id dispaly-----------------------
        if (!strBatchNum.equals("null") && !strProcessName.equals("null") && !strDate.equals("null"))

        {
            if (strProcessName.equals("Parboiling")) {
                etUserId.setText(strBatchNum + "PB" + strDate);
            } else if (strProcessName.equals("Steam Curing Plant")) {
                etUserId.setText(strBatchNum + "SCP" + strDate);
            } else {
                etUserId.setText(strBatchNum + "D" + strDate);
            }
        } else

        {
            if (strBatchNum.equals("null")) {
                Toast.makeText(getApplicationContext(), "Please Enter Batch Number", Toast.LENGTH_SHORT).show();
                etUserId.setText("XXXXXXXXXX");

            } else if (strProcessName.equals("null")) {
                Toast.makeText(getApplicationContext(), "Please Select Process Name", Toast.LENGTH_SHORT).show();
                etUserId.setText("XXXXXXXXXX");
            } else {
                Toast.makeText(getApplicationContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
                etUserId.setText("XXXXXXXXXX");
            }
        }
    }
    //------------------------------------------------------------------------------------------------------
}
