package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Log_Symptom_SymptomDate extends AppCompatActivity {
    Button btnBack, btnSave;
    TextView tvFirstExperiencedDate, tvChosenSymptom;
    DatePicker etDateFirstExperienced;

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
        changeStatusBar(getWindow());
        btnBack = findViewById(R.id.btnBack_view_symptoms_list);
        btnSave = findViewById(R.id.btnSave);

        //tvFirstExperiencedDate = findViewById(R.id.tvDuration);
        tvChosenSymptom = findViewById(R.id.tvChosenSymptom);

        etDateFirstExperienced = findViewById(R.id.etDateFirstExperienced);

        tvChosenSymptom.setText(Log_Symptom_ChooseSymptom.getSymptomChosen());

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandlerMySymptoms = new DBHandler_MySymptoms(Log_Symptom_SymptomDate.this);




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String Month = String.valueOf(etDateFirstExperienced.getMonth() + 1);
                String Day = String.valueOf(etDateFirstExperienced.getDayOfMonth() + 1);
                if(true)
                {
                    if(Integer.parseInt(Month) < 10){
                        Month = "0" + (etDateFirstExperienced.getMonth() + 1);}
                    else{
                        Month = String.valueOf(etDateFirstExperienced.getMonth() + 1);
                    }

                    if(Integer.parseInt(Day) < 10){
                        Day = "0" + (etDateFirstExperienced.getDayOfMonth());
                    }
                    else{
                        Day = String.valueOf(etDateFirstExperienced.getDayOfMonth());
                    }

                    String Year = String.valueOf(etDateFirstExperienced.getYear());
                    String Date = Month + "/" + Day + "/" + Year;
                    if(Integer.parseInt(Year) <= 2022 && Integer.parseInt(Year) >= 2020){
                        if(Integer.parseInt(Month) >= 0 && Integer.parseInt(Month) <= 12){
                            if(Integer.parseInt(Day) >= 0 && Integer.parseInt(Day) <= 31){
                                setDate_first_experienced(Date);

                                int sev = Log_Symptom_SymptomSeverity.getSeverity();
                                if( sev == 1)
                                {
                                    newSymptom = new Symptom(Log_Symptom_ChooseSymptom.getSymptomChosen(), Log_Symptom_SymptomSeverity.getSeverity(),
                                            getDate_first_experienced(), "empty description", 3);

                                    // on below line we are calling a method to add new
                                    // course to sqlite data and pass all our values to it.
                                    dbHandlerMySymptoms.addNewSymptom(newSymptom.getName(), new Integer(newSymptom.getSeverity()).toString(),
                                            newSymptom.getDate(), newSymptom.getDescription(),  String.valueOf(newSymptom.getRisk_factor()));

                                    SendUserToDashboard();
                                }
                                else if( sev == 2)
                                {
                                    newSymptom = new Symptom(Log_Symptom_ChooseSymptom.getSymptomChosen(), Log_Symptom_SymptomSeverity.getSeverity(),
                                            getDate_first_experienced(), "empty description", 4);

                                    // on below line we are calling a method to add new
                                    // course to sqlite data and pass all our values to it.
                                    dbHandlerMySymptoms.addNewSymptom(newSymptom.getName(), new Integer(newSymptom.getSeverity()).toString(),
                                            newSymptom.getDate(), newSymptom.getDescription(),  String.valueOf(newSymptom.getRisk_factor()));

                                    SendUserToDashboard();
                                }
                                else if( sev == 3)
                                {
                                    newSymptom = new Symptom(Log_Symptom_ChooseSymptom.getSymptomChosen(), Log_Symptom_SymptomSeverity.getSeverity(),
                                            getDate_first_experienced(), "empty description", 5);

                                    // on below line we are calling a method to add new
                                    // course to sqlite data and pass all our values to it.
                                    dbHandlerMySymptoms.addNewSymptom(newSymptom.getName(), new Integer(newSymptom.getSeverity()).toString(),
                                            newSymptom.getDate(), newSymptom.getDescription(),  String.valueOf(newSymptom.getRisk_factor()));

                                    SendUserToDashboard();
                                }
                                else
                                {
                                    newSymptom = new Symptom(Log_Symptom_ChooseSymptom.getSymptomChosen(), Log_Symptom_SymptomSeverity.getSeverity(),
                                            getDate_first_experienced(), "empty description", 1);

                                    // on below line we are calling a method to add new
                                    // course to sqlite data and pass all our values to it.
                                    dbHandlerMySymptoms.addNewSymptom(newSymptom.getName(), new Integer(newSymptom.getSeverity()).toString(),
                                            newSymptom.getDate(), newSymptom.getDescription(),  String.valueOf(newSymptom.getRisk_factor()));

                                    SendUserToDashboard();
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
    public static Symptom getNewSymptom() {
        if(newSymptom != null)
        {
            return newSymptom;
        }
        else return new Symptom("no name", -1, "no date", "no description", 5);

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
        Intent intent= new Intent(Log_Symptom_SymptomDate.this,Log_Symptom_SymptomSeverity.class);
        startActivity(intent);
    }
}
