package com.practice.coviddashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Log_Symptom_ChooseSymptom extends AppCompatActivity  {

    Button btnBack;
    TextView tvLogSymptom, tvChooseSymptom;
    RecyclerView rvSymptomList;
    ChooseSymptomRecyclerViewAdapter adapter;
    private static String symptomChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_symptom_choose_symptom);

        btnBack = findViewById(R.id.btnBack_view_symptoms_list);

        tvLogSymptom = findViewById(R.id.tvLogSymptom);
        tvChooseSymptom = findViewById(R.id.tvChooseSymptom);

        rvSymptomList = findViewById(R.id.rvSymptomList);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToDashboard();
            }
        });




        // data to populate the RecyclerView with
        ArrayList<String> symptoms = new ArrayList<>();
        symptoms.add("Fever");
        symptoms.add("Chills");
        symptoms.add("Sore Throat");
        symptoms.add("Cough");
        symptoms.add("Shortness of Breath");
        symptoms.add("Fatigue");
        symptoms.add("Body Aches");
        symptoms.add("Headache");
        symptoms.add("Loss of Taste or Smell");
        symptoms.add("Runny Nose or Congestion");
        symptoms.add("Nausea");
        symptoms.add("Diarrhea");

        // set up the RecyclerView
        rvSymptomList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseSymptomRecyclerViewAdapter(getApplicationContext(), symptoms);
        rvSymptomList.setAdapter(adapter);
    }

    public static String getSymptomChosen()
    {
        return symptomChosen;
    }

    public static void setSymptomChosen(String s)
    {
        symptomChosen = s;
    }

    public void SendUserToSymptomSeverity()
    {
        Intent intent = new Intent(this, Log_Symptom_SymptomSeverity.class);
        intent.putExtra("Symptom Chosen", getSymptomChosen());
        startActivity(intent);
    }//SendUserToSymptomSeverity

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}
