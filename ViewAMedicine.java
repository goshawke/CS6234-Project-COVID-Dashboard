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

public class ViewAMedicine extends AppCompatActivity {
    
    TextView tvViewAMedicineHeader, tvDuration, tvDurationHeader, tvDosage, tvDosageHeader;
    Button btnBack;

    private static Medicine myMedicine;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_a_medicine);
        
        tvViewAMedicineHeader = findViewById(R.id.tvViewAMedicineHeader);
        tvDuration = findViewById(R.id.tvDuration);
        tvDurationHeader = findViewById(R.id.tvDurationHeader);
        tvDosage = findViewById(R.id.tvDosage);
        tvDosageHeader = findViewById(R.id.tvDosageHeader);
        
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToViewMedicines();
            }
        });


        /*
        TODO - get a medicine and populate text boxes
         */

        tvViewAMedicineHeader.setText(getMyMedicine().getName());


        LocalDate start = LocalDate.of(Integer.parseInt(myMedicine.getDate().substring(6, 10)), Integer.parseInt(myMedicine.getDate().substring(0,2)), Integer.parseInt(myMedicine.getDate().substring(3,5)));
        LocalDate today = LocalDate.now();
        long duration = ChronoUnit.DAYS.between(start, today);

        tvDuration.setText(duration + " days");

        tvDosage.setText(getMyMedicine().getDosage() + "mgs per day");


    }

    public static Medicine getMyMedicine() {
        return myMedicine;
    }

    public static void setMyMedicine(Medicine myMedicine) {
        ViewAMedicine.myMedicine = myMedicine;
    }

    public void SendUserToViewMedicines()
    {
        Intent intent = new Intent(this, ViewMedicines.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
    




}



