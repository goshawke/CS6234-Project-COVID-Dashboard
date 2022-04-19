package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Log_DoctorVisit_ChooseDateAndDescription extends AppCompatActivity {

    Button btnBack, btnSave;
    TextView tvChosenVisitType, tvChosenDoctorType;
    EditText etDescription;
    DatePicker etDateOfVisit;

    private static DoctorVisit newDoctorVisit;

    private final String datePattern = "\\d{2}/\\d{2}/\\d{4}";
    private static String date_of_visit;
    private static String description;

    private DBHandler_MyDoctorVisits dbHandlerMyDoctorVisits; //database

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_doctor_visit_choose_date_and_description);
        changeStatusBar(getWindow());

        btnSave = findViewById(R.id.btnSave_date_and_description);

        tvChosenVisitType = findViewById(R.id.tvChosenVisitType);
        tvChosenDoctorType = findViewById(R.id.tvChosenDoctorType);

        etDateOfVisit = findViewById(R.id.etDateOfVisit);
        //etDescription = findViewById(R.id.etDescription);

        tvChosenVisitType.setText(Log_DoctorVisit_ChooseVisitType.getVisitTypeChosen());
        tvChosenDoctorType.setText(Log_DoctorVisit_ChooseDoctorType.getDoctorTypeChosen());



        // creating a new dbhandler class
        // and passing our context to it.
        dbHandlerMyDoctorVisits = new DBHandler_MyDoctorVisits(Log_DoctorVisit_ChooseDateAndDescription.this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String Month = String.valueOf(etDateOfVisit.getMonth() + 1);
                String Day = String.valueOf(etDateOfVisit.getDayOfMonth() + 1);
                if(true)
                {
                    if(Integer.parseInt(Month) < 10){
                        Month = "0" + (etDateOfVisit.getMonth() + 1);}
                    else{
                        Month = String.valueOf(etDateOfVisit.getMonth() + 1);
                    }

                    if(Integer.parseInt(Day) < 10){
                        Day = "0" + (etDateOfVisit.getDayOfMonth());
                    }
                    else{
                        Day = String.valueOf(etDateOfVisit.getDayOfMonth());
                    }

                    String Year = String.valueOf(etDateOfVisit.getYear());
                    String Date = Month + "/" + Day + "/" + Year;
                    if(Integer.parseInt(Year) <= 2022 && Integer.parseInt(Year) >= 2020){
                        if(Integer.parseInt(Month) >= 0 && Integer.parseInt(Month) <= 12){
                            if(Integer.parseInt(Day) >= 0 && Integer.parseInt(Day) <= 31){
                                setDate_of_visit(Date);
                                setDescriptionHelper("");
                                //setDescriptionHelper(etDescription.getText().toString());
                                newDoctorVisit = new DoctorVisit(Log_DoctorVisit_ChooseVisitType.getVisitTypeChosen(),
                                        Log_DoctorVisit_ChooseDoctorType.getDoctorTypeChosen(), getDate_of_visit(), getDescriptionHelper(), -1);


                                dbHandlerMyDoctorVisits.addNewDoctorVisit(newDoctorVisit.getVisitType(), newDoctorVisit.getDoctorType(), newDoctorVisit.getDate(),newDoctorVisit.getDescription(),  String.valueOf(newDoctorVisit.getRisk_factor()));


                                SendUserToDashboard();

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

    public static DoctorVisit getNewDoctorVisit() {
        if(newDoctorVisit != null)
        {
            return newDoctorVisit;
        }
        else return new DoctorVisit("no visit type", "no doctor type",
                "no date", "no description", -1);

    }
    public static String getDate_of_visit() {
        if(date_of_visit.length() == 0)
        {
            setDate_of_visit("no date provided");
        }
        return date_of_visit;
    }


    public static void setDate_of_visit(String date) {
        Log_DoctorVisit_ChooseDateAndDescription.date_of_visit = date;
    }

    public static String getDescriptionHelper(){
        if(description == null)
        {
            setDescriptionHelper("no description provided");
        }
        return description;
    }

    public static void setDescriptionHelper(String d)
    {
        Log_DoctorVisit_ChooseDateAndDescription.description = d;
    }


    public void SendUserToChooseDoctorType()
    {
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseDoctorType.class);
        startActivity(intent);
        finish();
    }//SendUserToChooseDoctorType

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Date of Visit", getDate_of_visit());
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
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseDoctorType.class);
        startActivity(intent);
        finish();
    }

}
