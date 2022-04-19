package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewDoctorVisits extends AppCompatActivity {

    Button btnBack;
    TextView tvViewDoctorVisitsHeader;
    RecyclerView rvYourDoctorVisitsList;
    ViewDoctorVisitsRecyclerViewAdapter adapter;

    ArrayList<DoctorVisit> doctorVisits;
    private  DBHandler_MyDoctorVisits dbHandlerMyDoctorVisits;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBar(getWindow());
        setContentView(R.layout.activity_view_doctor_visits_list);


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
