package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
/*
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;*/

import java.util.ArrayList;

public class ViewFitnessActivities extends AppCompatActivity {

    Button btnBack;
    TextView tvViewFitnessActivitiesHeader;
    RecyclerView rvYourFitnessActivityList;
    ViewFitnessActivitiesRecyclerViewAdapter adapter;

    ArrayList<FitnessActivity> FitnessActivities;
    private DBHandler_MyFitnessActivities dbHandlerMyFitnessActivities;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_fitness_activities);
        changeStatusBar(getWindow());
        rvYourFitnessActivityList = findViewById(R.id.rvYourFitnessActivityList);



        // data to populate the RecyclerView with
        FitnessActivities = new ArrayList<FitnessActivity>();
        dbHandlerMyFitnessActivities = new DBHandler_MyFitnessActivities(ViewFitnessActivities.this);

        FitnessActivities = dbHandlerMyFitnessActivities.readFitnessActivities();

        // set up the RecyclerView
        rvYourFitnessActivityList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewFitnessActivitiesRecyclerViewAdapter(getApplicationContext() , FitnessActivities);
        rvYourFitnessActivityList.setAdapter(adapter);

    }

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void changeStatusBar(Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
