package com.practice.coviddashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_GoToLogSymptom, btnViewSymptoms, btn_GoToLogMedicine, btnViewMedicines, btn_GoToLogDoctorVisit, btnViewDoctorVisits;
    TextView tvNewSymptom, tvNewMedicine, tvNewDoctorVisit;
    Symptom newSymptom;
    Medicine newMedicine;
    DoctorVisit newDoctorVisit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNewSymptom = findViewById(R.id.tvNewSymptom);
        tvNewMedicine = findViewById(R.id.tvNewMedicine);
        tvNewDoctorVisit = findViewById(R.id.tvNewDoctorVisit);


        newSymptom = Log_Symptom_SymptomDate.getNewSymptom();

        newMedicine = Log_Medicine_ChooseDosageAndDuration.getNewMedicine();

        newDoctorVisit = Log_DoctorVisit_ChooseDateAndDescription.getNewDoctorVisit();

        tvNewSymptom.setText("Symptom \nName: " + newSymptom.getName() + "\nSeverity: " + newSymptom.getSeverity() +
                "\nDate First Experienced: " + newSymptom.getDate() + "\nRisk Factor: " + newSymptom.getRisk_factor());

        tvNewMedicine.setText("Medicine \nName: " + newMedicine.getName() + "\nStart Date: " + newMedicine.getDate() +
                "\nDosage: " + newMedicine.getDosage() + "mgs per day\nRisk Factor: " + newMedicine.getRisk_factor());

        tvNewDoctorVisit.setText("Doctor Visit \nVisit Type: " + newDoctorVisit.getVisitType() + "\nDoctor Type: " + newDoctorVisit.getDoctorType() +
                "\nDate of Visit: " + newDoctorVisit.getDateOfVisit() + "\nDescription: " + newDoctorVisit.getDescription());


        btn_GoToLogSymptom = findViewById(R.id.btn_GoToLogSymptom);
        btnViewSymptoms = findViewById(R.id.btnViewSymptoms);

        btn_GoToLogMedicine = findViewById(R.id.btn_GoToLogMedicine);
        btnViewMedicines = findViewById(R.id.btnViewMedicines);

        btn_GoToLogDoctorVisit = findViewById(R.id.btnLogDoctorVisit);
        btnViewDoctorVisits = findViewById(R.id.btnViewDoctorVisits);




        btn_GoToLogSymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToChooseSymptom();
            }
        });


        btnViewSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToViewSymptoms();
            }
        });



        btn_GoToLogMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToChooseMedicine();
            }
        });


        btnViewMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToViewMedicines();
            }
        });


        btn_GoToLogDoctorVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToChooseVisitType();
            }
        });


        btnViewDoctorVisits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToViewDoctorVisits();
            }
        });

    }

    public void SendUserToChooseSymptom()
    {
        Intent intent = new Intent(this, Log_Symptom_ChooseSymptom.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToChooseSymptom

    public void SendUserToViewSymptoms()
    {
        Intent intent = new Intent(this, ViewSymptoms.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToViewSymptoms

    public void SendUserToChooseMedicine()
    {
        Intent intent = new Intent(this, Log_Medicine_ChooseMedicine.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToChooseMedicine

    public void SendUserToViewMedicines()
    {
        Intent intent = new Intent(this, ViewMedicines.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToViewMedicines

    public void SendUserToChooseVisitType()
    {
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseVisitType.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToChooseVisitType

    public void SendUserToViewDoctorVisits()
    {
        Intent intent = new Intent(this, ViewDoctorVisits.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToViewDoctorVisits

}