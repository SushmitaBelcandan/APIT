package com.example.shushmita.apit.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.ProcessList;
import com.example.shushmita.apit.adapters.SpinnerProcessAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AfterProcess_Act extends AppCompatActivity {

    private Spinner spnrAPProcessType;
    public static final String[] processArr = new String[]{"Select Type of Process",
            "Parboiling", "Steam curing Plant", "Drying"};
    List<String> processList;
    final Calendar myCalendar = Calendar.getInstance();
    private LinearLayout llSelectDate, llBatchNo;
    private TextView tvUserId, tvBatchNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_process_act);

        spnrAPProcessType = findViewById(R.id.spnrAPProcessType);
        llSelectDate = findViewById(R.id.llSelectDate);
        llBatchNo = findViewById(R.id.llBatchNo);
        tvUserId = findViewById(R.id.tvUserId);
        tvBatchNum = findViewById(R.id.tvBatchNum);

        processList = new ArrayList<String>();
        for (int i = 0; i < processArr.length; i++) {

            ProcessList item = new ProcessList(processArr[i]);
            //add item into arraylist
            //  processList.add(item);
            processList.add(processArr[i]);
        }
/*
        SpinnerProcessAdapter adapter = new SpinnerProcessAdapter(AfterProcess_Act.this,
                R.layout.process_list, R.id.tvProcessName, processList);
        spnrAPProcessType.setAdapter(adapter);*/
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, processList);
        dataAdapter.setDropDownViewResource(R.layout.process_list);
        spnrAPProcessType.setAdapter(dataAdapter);
        spnrAPProcessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.WHITE); //Change selected text color
                ((TextView) view).setTextAppearance(AfterProcess_Act.this, R.style.after_process_2c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //select date
        datePicker();
        llBatchNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
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
                new DatePickerDialog(AfterProcess_Act.this, R.style.MyThemeOverlay, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "ddMMyyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvUserId.setText(" " + sdf.format(myCalendar.getTime()));
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

        //submit batch no.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvBatchNum.setText(etBatchNo.getText().toString());
            }
        });


        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
        //cancel batch no.
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    //------------------------------------------------------------------------------------------------------
}
