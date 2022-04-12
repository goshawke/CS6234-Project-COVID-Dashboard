package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Log_Symptom_SymptomDate extends AppCompatActivity {
    Button btnBack, btnSave;
    TextView tvFirstExperiencedDate, tvChosenSymptom;
    EditText etDateFirstExperienced;

    private static Symptom newSymptom;

    private final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
    private static String date_first_experienced;
//
    private DBHandler_MySymptoms dbHandlerMySymptoms; //database
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_symptom_symptom_date);

        btnBack = findViewById(R.id.btnBack_view_symptoms_list);
        btnSave = findViewById(R.id.btnSave);

        tvFirstExperiencedDate = findViewById(R.id.tvDuration);
        tvChosenSymptom = findViewById(R.id.tvChosenSymptom);

        etDateFirstExperienced = findViewById(R.id.etDateFirstExperienced);

        tvChosenSymptom.setText(Log_Symptom_ChooseSymptom.getSymptomChosen());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToSymptomSeverity();
            }
        });


        // creating a new dbhandler class
        // and passing our context to it.
        dbHandlerMySymptoms = new DBHandler_MySymptoms(Log_Symptom_SymptomDate.this);




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etDateFirstExperienced.getText().toString().matches(datePattern))
                {
                    if(Integer.parseInt(etDateFirstExperienced.getText().toString().substring(6, 10)) <= 2022 && Integer.parseInt(etDateFirstExperienced.getText().toString().substring(6, 10)) >= 2018){
                        if(Integer.parseInt(etDateFirstExperienced.getText().toString().substring(0, 2)) >= 0 && Integer.parseInt(etDateFirstExperienced.getText().toString().substring(0, 2)) <= 12){
                            if(Integer.parseInt(etDateFirstExperienced.getText().toString().substring(3, 5)) >= 0 && Integer.parseInt(etDateFirstExperienced.getText().toString().substring(3, 5)) <= 31){
                                setDate_first_experienced(etDateFirstExperienced.getText().toString());
                                newSymptom = new Symptom(Log_Symptom_ChooseSymptom.getSymptomChosen(), Log_Symptom_SymptomSeverity.getSeverity(),
                                        getDate_first_experienced(), "empty description", 1);

                                // on below line we are calling a method to add new
                                // course to sqlite data and pass all our values to it.
                                dbHandlerMySymptoms.addNewSymptom(newSymptom.getName(), new Integer(newSymptom.getSeverity()).toString(),
                                        newSymptom.getDate(), newSymptom.getDescription(),  String.valueOf(newSymptom.getRisk_factor()));

                                SendUserToDashboard();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                                etDateFirstExperienced.setText(null);

                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                            etDateFirstExperienced.setText(null);
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                        etDateFirstExperienced.setText(null);

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                    etDateFirstExperienced.setText(null);

                }

            }
        });


    }
    public static Symptom getNewSymptom() {
        if(newSymptom != null)
        {
            return newSymptom;
        }
        else return new Symptom("no name", -1, "no date", "no description", -1);

    }

    public static String getDate_first_experienced() {
        if(date_first_experienced.length() == 0)
        {
            setDate_first_experienced("no date provided");
        }
        return date_first_experienced;
    }

    public static void setDate_first_experienced(String date) {
        Log_Symptom_SymptomDate.date_first_experienced = date;
    }


    public void SendUserToSymptomSeverity()
    {
        Intent intent = new Intent(this, Log_Symptom_SymptomSeverity.class);
        startActivity(intent);
        finish();
    }//SendUserTSymptomSeverity

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Date First Experienced", getDate_first_experienced());
        startActivity(intent);
        finish();
    }//SendUserToDashboard


}
