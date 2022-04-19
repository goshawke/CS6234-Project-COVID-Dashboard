package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
//import androidx.annotation.RequiresApi;

public class Log_Medicine_ChooseDosageAndDuration extends AppCompatActivity {
    Button btnBack, btnSave;
    TextView tvChosenMedicine;
    EditText etDosage;
    DatePicker etStartMedicine;
    private static Medicine newMedicine;

    private final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
    private static String startDate;
    private static String dosage;

    private DBHandler_MyMedicines dbHandlerMyMedicines; //database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBar(getWindow());
        setContentView(R.layout.activity_log_medicine_dosage_and_duration);

        btnBack = findViewById(R.id.btnBack_dosage_and_duration);
        btnSave = findViewById(R.id.btnSave_dosage_and_duration);

        tvChosenMedicine = findViewById(R.id.tvChosenMedicine_dosage_and_duration);

        etStartMedicine = findViewById(R.id.etStartMedicine_dosage_and_duration);
        etDosage = findViewById(R.id.etDosage_dosage_and_duration);

        tvChosenMedicine.setText(Log_Medicine_ChooseMedicine.getMedicineChosen());

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandlerMyMedicines = new DBHandler_MyMedicines(Log_Medicine_ChooseDosageAndDuration.this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {

                String Month = String.valueOf(etStartMedicine.getMonth() + 1);
                String Day = String.valueOf(etStartMedicine.getDayOfMonth() + 1);

                if(true)
                {
                    if(Integer.parseInt(Month) < 10){
                        Month = "0" + (etStartMedicine.getMonth() + 1);}
                    else{
                        Month = String.valueOf(etStartMedicine.getMonth() + 1);
                    }

                    if(Integer.parseInt(Day) < 10){
                        Day = "0" + (etStartMedicine.getDayOfMonth());
                    }
                    else{
                        Day = String.valueOf(etStartMedicine.getDayOfMonth());
                    }
                    String Year = String.valueOf(etStartMedicine.getYear());
                    String Date = Month + "/" + Day + "/" + Year;
                    if(Integer.parseInt(Year) <= 2022 && Integer.parseInt(Year) >= 1950){
                        if(Integer.parseInt(Month) >= 0 && Integer.parseInt(Month) <= 12){
                            if(Integer.parseInt(Day) >= 0 && Integer.parseInt(Day) <= 31){
                                if(!etDosage.getText().toString().equals(""))
                                {
                                    setStartDate(Date);

                                    setDosage(etDosage.getText().toString());

                                    String medName = Log_Medicine_ChooseMedicine.getMedicineChosen();
                                    if(medName.equals("Vitamin A") || medName.equals("Vitamin C") || medName.equals("Vitamin d") || medName.equals("Fish Oil") || medName.equals("Multivitamin") || medName.equals("Iron") || medName.equals("Zinc"))
                                    {
                                        newMedicine = new Medicine(Log_Medicine_ChooseMedicine.getMedicineChosen(), getDosage(),
                                                getStartDate(), "empty description", 1);
                                    }
                                    else
                                    {
                                        newMedicine = new Medicine(Log_Medicine_ChooseMedicine.getMedicineChosen(), getDosage(),
                                                getStartDate(), "empty description", 0);
                                    }


                                    // on below line we are calling a method to add new
                                    // course to sqlite data and pass all our values to it.
                                    dbHandlerMyMedicines.addNewMedicine(newMedicine.getName(), new Integer(newMedicine.getDosage()).toString(),
                                            newMedicine.getDate(),newMedicine.getDescription(),  String.valueOf(newMedicine.getRisk_factor()));


                                    SendUserToDashboard();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Please Enter Valid Dosage", Toast.LENGTH_LONG).show();
                                }


                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                                //etStartMedicine.setText(null);
                            }
                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                            //etStartMedicine.setText(null);
                        }
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                        //etStartMedicine.setText(null);
                    }

                }
                else
                {

                    Toast.makeText(getApplicationContext(), "Please Enter Valid Date", Toast.LENGTH_LONG).show();
                    //etStartMedicine.setText(null);
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
    public void changeStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }}

}
