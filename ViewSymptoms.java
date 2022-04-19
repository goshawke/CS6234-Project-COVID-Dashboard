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

public class ViewSymptoms extends AppCompatActivity {

    Button btnBack;
    TextView tvViewSymptomsHeader;
    RecyclerView rvYourSymptomList;
    ViewSymptomsRecyclerViewAdapter adapter;

    ArrayList<Symptom> symptoms;
    private DBHandler_MySymptoms dbHandlerMySymptoms;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBar(getWindow());
        setContentView(R.layout.activity_view_symptoms_list);

        tvViewSymptomsHeader = findViewById(R.id.tvViewSymptomsHeader);


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
