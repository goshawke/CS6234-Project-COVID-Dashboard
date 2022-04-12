package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Log_DoctorVisit_ChooseDateAndDescription extends AppCompatActivity {

    Button btnBack, btnSave;
    TextView tvChosenVisitType, tvChosenDoctorType;
    EditText etDateOfVisit, etDescription;

    private static DoctorVisit newDoctorVisit;

    private final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
    private static String date_of_visit;
    private static String description;

    private DBHandler_MyDoctorVisits dbHandlerMyDoctorVisits; //database

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_doctor_visit_choose_date_and_description);

        btnBack = findViewById(R.id.btnBack_date_and_description);
        btnSave = findViewById(R.id.btnSave_date_and_description);

        tvChosenVisitType = findViewById(R.id.tvChosenVisitType);
        tvChosenDoctorType = findViewById(R.id.tvChosenDoctorType);

        etDateOfVisit = findViewById(R.id.etDateOfVisit);
        etDescription = findViewById(R.id.etDescription);

        tvChosenVisitType.setText(Log_DoctorVisit_ChooseVisitType.getVisitTypeChosen());
        tvChosenDoctorType.setText(Log_DoctorVisit_ChooseDoctorType.getDoctorTypeChosen());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToChooseDoctorType();
            }
        });

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandlerMyDoctorVisits = new DBHandler_MyDoctorVisits(Log_DoctorVisit_ChooseDateAndDescription.this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etDateOfVisit.getText().toString().matches(datePattern))
                {
                    if(Integer.parseInt(etDateOfVisit.getText().toString().substring(6, 10)) <= 2022 && Integer.parseInt(etDateOfVisit.getText().toString().substring(6, 10)) >= 2020){
                        if(Integer.parseInt(etDateOfVisit.getText().toString().substring(0, 2)) >= 0 && Integer.parseInt(etDateOfVisit.getText().toString().substring(0, 2)) <= 12){
                            if(Integer.parseInt(etDateOfVisit.getText().toString().substring(3, 5)) >= 0 && Integer.parseInt(etDateOfVisit.getText().toString().substring(3, 5)) <= 31){
                                setDate_of_visit(etDateOfVisit.getText().toString());
                                setDescriptionHelper(etDescription.getText().toString());
                                newDoctorVisit = new DoctorVisit(Log_DoctorVisit_ChooseVisitType.getVisitTypeChosen(),
                                        Log_DoctorVisit_ChooseDoctorType.getDoctorTypeChosen(), getDate_of_visit(), getDescriptionHelper(), 1);


                                dbHandlerMyDoctorVisits.addNewDoctorVisit(newDoctorVisit.getVisitType(), newDoctorVisit.getDoctorType(), newDoctorVisit.getDate(),newDoctorVisit.getDescription(),  String.valueOf(newDoctorVisit.getRisk_factor()));


                                SendUserToDashboard();
                            }
                            else
                            {


                                Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                                etDateOfVisit.setText(null);
                            }
                        }
                        else
                        {


                            Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                            etDateOfVisit.setText(null);
                        }
                    }
                    else
                    {


                        Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                        etDateOfVisit.setText(null);
                    }
                }
                else
                {


                    Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                    etDateOfVisit.setText(null);
                }
            }
        });
    }

    public static DoctorVisit getNewDoctorVisit() {
        if(newDoctorVisit != null)
        {
            return newDoctorVisit;
        }
        else return new DoctorVisit("no visit type", "no doctor type",
                "no date", "no description", -1);

    }
    public static String getDate_of_visit() {
        if(date_of_visit.length() == 0)
        {
            setDate_of_visit("no date provided");
        }
        return date_of_visit;
    }


    public static void setDate_of_visit(String date) {
        Log_DoctorVisit_ChooseDateAndDescription.date_of_visit = date;
    }

    public static String getDescriptionHelper(){
        if(description == null)
        {
            setDescriptionHelper("no description provided");
        }
        return description;
    }

    public static void setDescriptionHelper(String d)
    {
        Log_DoctorVisit_ChooseDateAndDescription.description = d;
    }


    public void SendUserToChooseDoctorType()
    {
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseDoctorType.class);
        startActivity(intent);
        finish();
    }//SendUserToChooseDoctorType

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Date of Visit", getDate_of_visit());
        startActivity(intent);
        finish();
    }//SendUserToDashboard

}
