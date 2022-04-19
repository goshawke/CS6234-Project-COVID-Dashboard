package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


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
        changeStatusBar(getWindow());


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

    public void changeStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseVisitType.class);
        startActivity(intent);
        finish();
    }

}