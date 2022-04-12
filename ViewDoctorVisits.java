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

public class ViewDoctorVisits extends AppCompatActivity {

    Button btnBack;
    TextView tvViewDoctorVisitsHeader;
    RecyclerView rvYourDoctorVisitsList;
    ViewDoctorVisitsRecyclerViewAdapter adapter;

    ArrayList<DoctorVisit> doctorVisits;
    private  DBHandler_MyDoctorVisits dbHandlerMyDoctorVisits;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_doctor_visits_list);

        tvViewDoctorVisitsHeader = findViewById(R.id.tvViewDoctorVisitsHeader);
        btnBack = findViewById(R.id.btnBack_ViewDoctorVisits);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToDashboard();
            }
        });

        rvYourDoctorVisitsList = findViewById(R.id.rvYourDoctorVisitsList);


        // data to populate the RecyclerView with
        doctorVisits = new ArrayList<DoctorVisit>();
        dbHandlerMyDoctorVisits = new DBHandler_MyDoctorVisits(ViewDoctorVisits.this);

        doctorVisits = dbHandlerMyDoctorVisits.readDoctorVisits();

        // set up the RecyclerView
        rvYourDoctorVisitsList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewDoctorVisitsRecyclerViewAdapter(getApplicationContext() , doctorVisits);
        rvYourDoctorVisitsList.setAdapter(adapter);

    }

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}
