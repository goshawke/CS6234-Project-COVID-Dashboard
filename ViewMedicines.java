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

public class ViewMedicines extends AppCompatActivity {

    Button btnBack;
    TextView tvViewMedicinesHeader;
    RecyclerView rvYourMedicineList;
    ViewMedicinesRecyclerViewAdapter adapter;

    ArrayList<Medicine> medicines;
    private DBHandler_MyMedicines dbHandlerMyMedicines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_medicines_list);

        tvViewMedicinesHeader = findViewById(R.id.tvViewMedicinesHeader);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToDashboard();
            }
        });

        rvYourMedicineList = findViewById(R.id.rvYourMedicineList);



        // data to populate the RecyclerView with
        medicines = new ArrayList<Medicine>();
        dbHandlerMyMedicines = new DBHandler_MyMedicines(ViewMedicines.this);

        medicines = dbHandlerMyMedicines.readMedicines();

        // set up the RecyclerView
        rvYourMedicineList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewMedicinesRecyclerViewAdapter(getApplicationContext() , medicines);
        rvYourMedicineList.setAdapter(adapter);

    }

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}
