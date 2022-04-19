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

public class Log_Symptom_ChooseSymptom extends AppCompatActivity {

    Button btnBack;
    TextView tvLogSymptom, tvChooseSymptom;
    RecyclerView rvSymptomList;
    ChooseSymptomRecyclerViewAdapter adapter;
    private static String symptomChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_symptom_choose_symptom);
        changeStatusBar(getWindow());
        btnBack = findViewById(R.id.btnBack_view_symptoms_list);

        //tvLogSymptom = findViewById(R.id.tvLogSymptom);


        rvSymptomList = findViewById(R.id.rvSymptomList);







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
        Intent intent= new Intent(Log_Symptom_ChooseSymptom.this,MainActivity.class);
        startActivity(intent);
    }
}
