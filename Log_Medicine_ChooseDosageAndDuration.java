package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Log_Medicine_ChooseDosageAndDuration extends AppCompatActivity {
    Button btnBack, btnSave;
    TextView tvChosenMedicine;
    EditText etStartMedicine, etDosage;

    private static Medicine newMedicine;

    private final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
    private static String startDate;
    private static String dosage;

    private DBHandler_MyMedicines dbHandlerMyMedicines; //database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_medicine_dosage_and_duration);

        btnBack = findViewById(R.id.btnBack_dosage_and_duration);
        btnSave = findViewById(R.id.btnSave_dosage_and_duration);

        tvChosenMedicine = findViewById(R.id.tvChosenMedicine_dosage_and_duration);

        etStartMedicine = findViewById(R.id.etStartMedicine_dosage_and_duration);
        etDosage = findViewById(R.id.etDosage_dosage_and_duration);

        tvChosenMedicine.setText(Log_Medicine_ChooseMedicine.getMedicineChosen());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToChooseMedicine();
            }
        });

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandlerMyMedicines = new DBHandler_MyMedicines(Log_Medicine_ChooseDosageAndDuration.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {

                if(etStartMedicine.getText().toString().matches(datePattern))
                {
                    if(Integer.parseInt(etStartMedicine.getText().toString().substring(6, 10)) <= 2022 && Integer.parseInt(etStartMedicine.getText().toString().substring(6, 10)) >= 1918){
                        if(Integer.parseInt(etStartMedicine.getText().toString().substring(0, 2)) >= 0 && Integer.parseInt(etStartMedicine.getText().toString().substring(0, 2)) <= 12){
                            if(Integer.parseInt(etStartMedicine.getText().toString().substring(3, 5)) >= 0 && Integer.parseInt(etStartMedicine.getText().toString().substring(3, 5)) <= 31){
                                setStartDate(etStartMedicine.getText().toString());
                                setDosage(etDosage.getText().toString());
                                newMedicine = new Medicine(Log_Medicine_ChooseMedicine.getMedicineChosen(), getDosage(),
                                        getStartDate(), "empty description", 1);

                                // on below line we are calling a method to add new
                                // course to sqlite data and pass all our values to it.
                                dbHandlerMyMedicines.addNewMedicine(newMedicine.getName(), new Integer(newMedicine.getDosage()).toString(),
                                        newMedicine.getDate(),newMedicine.getDescription(),  String.valueOf(newMedicine.getRisk_factor()));




                                SendUserToDashboard();

                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                                etStartMedicine.setText(null);
                            }
                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                            etStartMedicine.setText(null);
                        }
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                        etStartMedicine.setText(null);
                    }

                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                    etStartMedicine.setText(null);
                }
            }
        });


    }
    public static Medicine getNewMedicine() {
        if(newMedicine != null)
        {
            return newMedicine;
        }
        else return new Medicine("no name", "no dosage", "no date", "no description", -1);

    }

    public static String getStartDate() {
        if(startDate.length() == 0)
        {
            setStartDate("no date provided");
        }
        return startDate;
    }

    public static void setStartDate(String startDate) {
        Log_Medicine_ChooseDosageAndDuration.startDate = startDate;
    }

    public static String getDosage() {
        if(dosage == null)
        {
            setDosage("no dosage provided");
        }
        return dosage;
    }

    public static void setDosage(String dosage) {
        Log_Medicine_ChooseDosageAndDuration.dosage = dosage;
    }



    public void SendUserToChooseMedicine()
    {
        Intent intent = new Intent(this, Log_Medicine_ChooseMedicine.class);
        startActivity(intent);
        finish();
    }//SendUserTMedicineDate

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Date", "Date Value");
        startActivity(intent);
        finish();
    }//SendUserToDashboard


}
