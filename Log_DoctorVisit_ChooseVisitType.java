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

public class Log_DoctorVisit_ChooseVisitType extends AppCompatActivity {


    Button btnBack;

    RecyclerView rvVisitTypeList;
    ChooseVisitTypeRecyclerViewAdapter adapter;
    private static String visitTypeChosen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_doctor_visit_choose_visit_type);

        btnBack = findViewById(R.id.btnBack_visit_type);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToDashboard();
            }
        });

        rvVisitTypeList = findViewById(R.id.rvVisitTypeList);

        ArrayList<String> visitTypes = new ArrayList<>();
        visitTypes.add("Check-up Visit");
        visitTypes.add("Consultation");
        visitTypes.add("Emergency Visit");
        visitTypes.add("Follow-up Visit");
        visitTypes.add("General Visit");
        visitTypes.add("Surgery");
        visitTypes.add("Testing Visit");

        rvVisitTypeList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseVisitTypeRecyclerViewAdapter(getApplicationContext(), visitTypes);
        rvVisitTypeList.setAdapter(adapter);

    }

    public static String getVisitTypeChosen()
    {
        return visitTypeChosen;
    }

    public static void setVisitTypeChosen(String v)
    {
        visitTypeChosen = v;
    }

    public void SendUserToDashboard() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}