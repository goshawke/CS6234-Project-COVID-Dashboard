package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LogTrips extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_trips);
        changeStatusBar(getWindow());
        final DB db = new DB(this);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.countries);
        Button save=(Button)findViewById(R.id.save);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("US");
        categories.add("UK");
        categories.add("Japan");
        categories.add("China");
        categories.add("Russia");
        categories.add("Other");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Country = spinner.getSelectedItem().toString();
                String Month = String.valueOf(datePicker.getMonth()+1);
                String Day = String.valueOf(datePicker.getDayOfMonth());
                String Year = String.valueOf(datePicker.getYear());
                String Date = Month + "/" + Day + "/" + Year;
                db.insertTripsIntoTheDatabase(Country, Date);
                Toast.makeText(getApplicationContext(),
                        "Saved",
                        Toast.LENGTH_LONG)
                        .show();
                Intent intent= new Intent(LogTrips.this,MainActivity.class);
                startActivity(intent);
                Log.d("Date",Date);
                Log.d("Country",spinner.getSelectedItem().toString());
                //datePicker.set

                //String sf2=String.format("value is %,.1f",Month);
                //Log.d("Full", sf2);
                //Log.d("Full", String.format("02d-02d-04d", Month, Day, Year));
                Log.d("Month", ("0" + (datePicker.getMonth() + 1)));//String.valueOf();
                Log.d("Day", String.valueOf(datePicker.getDayOfMonth()));
                Log.d("Year", String.valueOf(datePicker.getYear()));
            }
        });
    }

    public void changeStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent= new Intent(LogTrips.this,MainActivity.class);
        startActivity(intent);
    }
}