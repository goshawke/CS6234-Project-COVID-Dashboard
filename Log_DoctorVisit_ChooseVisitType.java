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
import android.widget.AdapterView;
import android.widget.Button;


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
        changeStatusBar(getWindow());

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}