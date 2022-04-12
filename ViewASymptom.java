package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewASymptom extends AppCompatActivity {

    TextView tvViewASymptomHeader, tvFirstExperiencedDateHeader, tvFirstExperiencedDate, tvSeverityHeader;
    ImageView ivSeverity;

    private static Symptom mySymptom;
    Button btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_a_symptom);

        tvViewASymptomHeader = findViewById(R.id.tvViewASymptomHeader);
        tvFirstExperiencedDateHeader = findViewById(R.id.tvFirstExperiencedDateHeader);
        tvFirstExperiencedDate = findViewById(R.id.tvDuration);
        tvSeverityHeader = findViewById(R.id.tvSeverityHeader);

        ivSeverity = findViewById((R.id.ivSeverity));

        btnBack = findViewById(R.id.btnBack_view_symptoms_list);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToViewSymptoms();
            }
        });


        /*
        TODO - get a symptom and populate text boxes
         */

        tvViewASymptomHeader.setText(getMySymptom().getName());
        tvFirstExperiencedDate.setText(getMySymptom().getDate());

        if(getMySymptom().getSeverity() == 1)
        {
            ivSeverity.setImageResource(R.drawable.symptom_severity1);
        }
        else if(getMySymptom().getSeverity() == 2)
        {
            ivSeverity.setImageResource(R.drawable.symptom_severity2);
        }
        else if(getMySymptom().getSeverity() == 3)
        {
            ivSeverity.setImageResource(R.drawable.symptom_severity3);
        }
        else
        {
            ivSeverity.setImageResource(R.drawable.symptom);
        }

    }

    public static Symptom getMySymptom() {
        return mySymptom;
    }

    public static void setMySymptom(Symptom mySymptom) {
        ViewASymptom.mySymptom = mySymptom;
    }

    public void SendUserToViewSymptoms()
    {
        Intent intent = new Intent(this, ViewSymptoms.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}
