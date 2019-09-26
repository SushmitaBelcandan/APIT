package com.example.shushmita.apit.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import android.widget.Toast;

import com.example.shushmita.apit.R;
import com.example.shushmita.apit.adapters.ProcessList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AfterProcess_Act extends AppCompatActivity {

    private Spinner spnrAPProcessType;
    public static final String[] processArr = new String[]{"Select Type of Process",
            "Parboiling", "Steam Curing Plant", "Drying"};
    List<String> processList;
    final Calendar myCalendar = Calendar.getInstance();
    private LinearLayout llSelectDate, llBatchNo, llDate;
    private TextView  tvBatchNum, tvSelectDate;
    private EditText etUserId;
    private String strBatchNum, strProcessName, strDate;
    private Button btnSubmit;
    //private EditText etAPMoisture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_process_act);

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

        strBatchNum = "null";
        strProcessName = "null";
        strDate = "null";

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
        if(!sdf.equals("null"))
        {
            strDate = sdf.format(myCalendar.getTime());
            llDate.setVisibility(View.GONE);
            tvSelectDate.setText(" " + sdfDate.format(myCalendar.getTime()));
        }
        else
        {
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


                if(!etBatchNo.getText().toString().trim().equals(null) &&
                        !etBatchNo.getText().toString().trim().equals("null") &&
                        !etBatchNo.getText().toString().trim().isEmpty())
                {
                    strBatchNum = etBatchNo.getText().toString();
                    tvBatchNum.setText(strBatchNum);
                    alertDialog.dismiss();
                }
                else
                {
                    strBatchNum = "null";
                    etBatchNo.requestFocus();
                    etBatchNo.setError("Invalid Batch Number");
                }

            }
        });


    }
    public void setUserId()
    {
        //---------------your id dispaly-----------------------
        if(!strBatchNum.equals("null")&&!strProcessName.equals("null")&&!strDate.equals("null"))

        {
            if (strProcessName.equals("Parboiling")) {
                etUserId.setText(strBatchNum + "PB" + strDate);
            } else if (strProcessName.equals("Steam Curing Plant")) {
                etUserId.setText(strBatchNum + "SCP" + strDate);
            } else {
                etUserId.setText(strBatchNum + "D" + strDate);
            }
        }
        else

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
