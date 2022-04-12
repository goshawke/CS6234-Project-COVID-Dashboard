package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ViewADoctorVisit extends AppCompatActivity {

    TextView tvVisitTypeHeader, tvDoctorTypeHeader, tvDate, tvDescription;
    Button btnBack;

    private static DoctorVisit myDoctorVisit;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_a_doctor_visit);

        tvVisitTypeHeader = findViewById(R.id.tvVisitTypeHeader_ViewADoctorVisit);
        tvDoctorTypeHeader = findViewById(R.id.tvDoctorTypeHeaderViewADoctorVisit);
        tvDate = findViewById(R.id.tvDate);
        tvDescription = findViewById(R.id.tvDescription__ViewADoctorVisit);


        btnBack = findViewById(R.id.btnBack_ViewADoctorVisit);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToViewDoctorVisits();
            }
        });


        /*
        TODO - get a doctorVisit and populate text boxes
         */

        tvVisitTypeHeader.setText(getMyDoctorVisit().getVisitType());
        tvDoctorTypeHeader.setText(getMyDoctorVisit().getDoctorType());


        tvDate.setText(getMyDoctorVisit().getDateOfVisit());

        tvDescription.setText(getMyDoctorVisit().getDescription());


    }

    public static DoctorVisit getMyDoctorVisit() {
        return myDoctorVisit;
    }

    public static void setMyDoctorVisit(DoctorVisit myDoctorVisit) {
        ViewADoctorVisit.myDoctorVisit = myDoctorVisit;
    }

    public void SendUserToViewDoctorVisits()
    {
        Intent intent = new Intent(this, ViewDoctorVisits.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard





}



