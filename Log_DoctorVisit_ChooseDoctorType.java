package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Log_DoctorVisit_ChooseDoctorType extends AppCompatActivity {


    Button btnBack;

    RecyclerView rvDoctorTypeList;
    ChooseDoctorTypeRecyclerViewAdapter adapter;
    private static String doctorTypeChosen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_doctor_visit_choose_doctor_type);

        btnBack = findViewById(R.id.btnBack_doctor_type);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToChooseVisitType();
            }
        });

        rvDoctorTypeList = findViewById(R.id.rvDoctorTypeList);

        ArrayList<String> doctorTypes = new ArrayList<>();
        doctorTypes.add("Dentist");
        doctorTypes.add("Endocrinologist");
        doctorTypes.add("Family Physician");
        doctorTypes.add("Cardiologist");
        doctorTypes.add("Dermatologist");
        doctorTypes.add("Gastroenterologist");
        doctorTypes.add("General Practitioner");
        doctorTypes.add("Infectious Disease Physician");
        doctorTypes.add("Internal Medicine Physician");
        doctorTypes.add("Nephrologist");
        doctorTypes.add("Neurologist");
        doctorTypes.add("Obstetrician/Gynecologist");
        doctorTypes.add("Oncologist");
        doctorTypes.add("Ophthalmologist");
        doctorTypes.add("Otolaryngologist");
        doctorTypes.add("Pediatrician");
        doctorTypes.add("Psychiatrist");
        doctorTypes.add("Pulmonologist");
        doctorTypes.add("Radiologist");
        doctorTypes.add("Surgeon");

        rvDoctorTypeList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseDoctorTypeRecyclerViewAdapter(getApplicationContext(), doctorTypes);
        rvDoctorTypeList.setAdapter(adapter);

    }

    public static String getDoctorTypeChosen()
    {
        return doctorTypeChosen;
    }

    public static void setDoctorTypeChosen(String v)
    {
        doctorTypeChosen = v;
    }

    public void SendUserToChooseVisitType() {
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseVisitType.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}