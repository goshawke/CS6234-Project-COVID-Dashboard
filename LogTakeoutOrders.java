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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LogTakeoutOrders extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_takeout_orders);
        changeStatusBar(getWindow());
        final DB db = new DB(this);


        // Spinner element
        Button save=(Button)findViewById(R.id.save);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final EditText resturantName = (EditText) findViewById(R.id.resturantName);
        // Spinner click listener

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ResturantName = String.valueOf(resturantName.getText());
                String Month = String.valueOf(datePicker.getMonth()+1);
                String Day = String.valueOf(datePicker.getDayOfMonth());
                String Year = String.valueOf(datePicker.getYear());
                String Date = Month + "/" + Day + "/" + Year;
                db.insertTakeoutIntoTheDatabase(ResturantName, Date);
                Toast.makeText(getApplicationContext(),
                        "Saved",
                        Toast.LENGTH_LONG)
                        .show();
                Intent intent= new Intent(LogTakeoutOrders.this,MainActivity.class);
                startActivity(intent);
                Log.d("Date",Date);
                Log.d("Resturant Name",String.valueOf(resturantName.getText()));
                Log.d("Month", String.valueOf(datePicker.getMonth()));
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
        Intent intent= new Intent(LogTakeoutOrders.this,MainActivity.class);
        startActivity(intent);
    }
}