package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewSymptoms extends AppCompatActivity {

    Button btnBack;
    TextView tvViewSymptomsHeader;
    RecyclerView rvYourSymptomList;
    ViewSymptomsRecyclerViewAdapter adapter;

    ArrayList<Symptom> symptoms;
    private DBHandler_MySymptoms dbHandlerMySymptoms;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_symptoms_list);

        tvViewSymptomsHeader = findViewById(R.id.tvViewSymptomsHeader);
        btnBack = findViewById(R.id.btnBack_view_symptoms_list);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToDashboard();
            }
        });

        rvYourSymptomList = findViewById(R.id.rvYourSymptomList);



        // data to populate the RecyclerView with
        symptoms = new ArrayList<Symptom>();
        dbHandlerMySymptoms = new DBHandler_MySymptoms(ViewSymptoms.this);

        symptoms = dbHandlerMySymptoms.readSymptoms();


        // set up the RecyclerView
        rvYourSymptomList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewSymptomsRecyclerViewAdapter(getApplicationContext() , symptoms);
        rvYourSymptomList.setAdapter(adapter);

    }

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}
