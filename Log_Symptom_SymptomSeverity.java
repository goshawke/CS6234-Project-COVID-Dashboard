package com.practice.coviddashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Log_Symptom_SymptomSeverity extends AppCompatActivity {

    Button btnBack, btnNext;
    TextView tvChooseSeverity, tvChosenSymptom;
    ImageView ivSymptomSeverity1, ivSymptomSeverity2, ivSymptomSeverity3;
    SeekBar sbSymptomSeverity;


    private static int severity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_symptoms_symptom_severity);

        btnBack = findViewById(R.id.btnBack_view_symptoms_list);
        btnNext = findViewById(R.id.btnNext);

        tvChooseSeverity = findViewById(R.id.tvChooseSeverity);
        tvChosenSymptom = findViewById(R.id.tvChosenSymptom);

        ivSymptomSeverity1 = findViewById(R.id.ivSymptomSeverity1);
        ivSymptomSeverity2 = findViewById(R.id.ivSymptomSeverity2);
        ivSymptomSeverity3 = findViewById(R.id.ivSymptomSeverity3);

        sbSymptomSeverity = findViewById(R.id.sbSymptomSeverity);

        tvChosenSymptom.setText(Log_Symptom_ChooseSymptom.getSymptomChosen());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToChooseSymptom();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSeverity(sbSymptomSeverity.getProgress());

                if (sbSymptomSeverity.getProgress() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Select Symptom Severity", Toast.LENGTH_LONG).show();
                } else {
                    setSeverity(sbSymptomSeverity.getProgress());
                    SendUserToSymptomDate();
                }
            }
        });


        sbSymptomSeverity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if (progress == 0) {
                    ivSymptomSeverity1.setVisibility(View.INVISIBLE);
                    ivSymptomSeverity2.setVisibility(View.INVISIBLE);
                    ivSymptomSeverity3.setVisibility(View.INVISIBLE);
                }
                if (progress == 1) {
                    ivSymptomSeverity1.setVisibility(View.VISIBLE);
                    ivSymptomSeverity2.setVisibility(View.INVISIBLE);
                    ivSymptomSeverity3.setVisibility(View.INVISIBLE);
                }
                if (progress == 2) {
                    ivSymptomSeverity1.setVisibility(View.INVISIBLE);
                    ivSymptomSeverity2.setVisibility(View.VISIBLE);
                    ivSymptomSeverity3.setVisibility(View.INVISIBLE);
                }
                if (progress == 3) {
                    ivSymptomSeverity1.setVisibility(View.INVISIBLE);
                    ivSymptomSeverity2.setVisibility(View.INVISIBLE);
                    ivSymptomSeverity3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("SymptomSeverity seekbar", "seekbar touch started!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("SymptomSeverity seekbar", "seekbar touch stopped!");
            }
        });
    }

    public static int getSeverity() {
        return severity;
    }

    public static void setSeverity(int severity) {
        Log_Symptom_SymptomSeverity.severity = severity;
    }

    public void SendUserToSymptomDate()
    {
        Intent intent = new Intent(this, Log_Symptom_SymptomDate.class);
        intent.putExtra("Symptom Severity", sbSymptomSeverity.getProgress());
        startActivity(intent);
    }//SendUserTSymptomDate

    public void SendUserToChooseSymptom()
    {
        Intent intent = new Intent(this, Log_Symptom_ChooseSymptom.class);
        startActivity(intent);
        finish();
    }//SendUserToChooseSymptom
}