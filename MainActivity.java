package edu.gwu.coviddashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Color;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {
    int i;
    WaveLoadingView mWaveLoadingView;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    final DB db = new DB(this);
    final DBHandler_MyDoctorVisits MyDoctorVisits = new DBHandler_MyDoctorVisits(this);
    final DBHandler_MyMedicines MyMedicines = new DBHandler_MyMedicines(this);
    final DBHandler_MySymptoms MySymptoms = new DBHandler_MySymptoms(this);
    final DBHandler_MyFitnessActivities MyFitnessActivities = new DBHandler_MyFitnessActivities(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBar(getWindow());
        setContentView(R.layout.activity_main);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);

        long ab = MyDoctorVisits.rf();
        Log.d("a MainActivity", ab+"");
        int a = (db.rows_trips()*3)+(db.rows_interaction()*2)+(db.rows_takeout())+MyDoctorVisits.rf()+MyMedicines.rf()+MySymptoms.rf()+MyFitnessActivities.rf();

        if(a>100){
            a=100;
        }
        i = 100;
        if(i - a > 100)
        {
            i=100;
        }
        else if(a<=100){
            i=100-a;
        }

        //mWaveLoadingView.setCenterTitleColor(Color.WHITE);
        mWaveLoadingView.setCenterTitle(i+"%");
        mWaveLoadingView.setBottomTitleSize(18);
        mWaveLoadingView.setProgressValue(0);
        mWaveLoadingView.setBorderWidth(5);
        mWaveLoadingView.setAmplitudeRatio(100);
        //mWaveLoadingView.setWaveBgColor(Color.parseColor("#FFFFFF"));

        if (i>=90 && i<=100){
            mWaveLoadingView.setTopTitle("You Are Safe");
            mWaveLoadingView.setWaveColor(Color.parseColor("#44DD2F"));
            mWaveLoadingView.setBorderColor(Color.parseColor("#44DD2F"));
        }
        else if (i>=40 && i<=89){
            mWaveLoadingView.setTopTitle("You Should Do COVID Test");
            mWaveLoadingView.setWaveColor(Color.parseColor("#C8DD2F"));
            mWaveLoadingView.setBorderColor(Color.parseColor("#C8DD2F"));
        }
        else if (i>=0 && i<=39){
            mWaveLoadingView.setTopTitle("Do COVID Test Now");
            mWaveLoadingView.setWaveColor(Color.parseColor("#DD442F"));
            mWaveLoadingView.setBorderColor(Color.parseColor("#DD442F"));
        }
        else if (i<=0){
            mWaveLoadingView.setTopTitle("Do COVID Test Now");
            mWaveLoadingView.setWaveColor(Color.parseColor("#DD442F"));
            mWaveLoadingView.setBorderColor(Color.parseColor("#DD442F"));
        }
        mWaveLoadingView.setTopTitleStrokeColor(Color.WHITE);
        mWaveLoadingView.setTopTitleStrokeWidth(3);
        mWaveLoadingView.setWaterLevelRatio(0.2f);
        mWaveLoadingView.setAnimDuration(3000);
        mWaveLoadingView.pauseAnimation();
        mWaveLoadingView.resumeAnimation();
        mWaveLoadingView.cancelAnimation();
        mWaveLoadingView.startAnimation();
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < i) {
                    progressStatus += 2;
                    handler.post(new Runnable() {
                        public void run() {
                            mWaveLoadingView.setProgressValue(progressStatus);
                            String x=String.valueOf(i);
                        }
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        String x=String.valueOf(i);


    }

    public void Trips(View view){

        String[] Activity = {"Log Trips", "View Trips"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){
                    startActivity(new Intent(MainActivity.this,LogTrips.class));
                }
                else if(activity == 1){
                    startActivity(new Intent(MainActivity.this,ViewTrips.class));
                }
            }
        });
        builder.show();
    }

    public void Interaction(View view){

        String[] Activity = {"Log Interaction", "View Interaction"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){
                    startActivity(new Intent(MainActivity.this,LogInteraction.class));
                }
                else if(activity == 1){
                    startActivity(new Intent(MainActivity.this,ViewInteraction.class));
                }
            }
        });
        builder.show();
    }

    public void TakeoutOrders(View view){

        String[] Activity = {"Log Takeout Orders", "View Takeout Orders"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){
                    startActivity(new Intent(MainActivity.this,LogTakeoutOrders.class));
                }
                else if(activity == 1){
                    startActivity(new Intent(MainActivity.this,ViewTakeoutOrders.class));
                }
            }
        });
        builder.show();
    }

    public void Fitbit(View view){

        String[] Activity = {"Sync Fitbit", "View Fitness Activities"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){

                    mWaveLoadingView.cancelAnimation();

                    DBHandler_MyFitnessActivities dbHandlerMyFitnessActivities = new DBHandler_MyFitnessActivities(MainActivity.this);

                    FitnessActivity newFitnessActivity = new FitnessActivity("Run", "30 Minutes", "500", "04/12/2022", "You went for a run", -1.0);
                    dbHandlerMyFitnessActivities.addNewFitnessActivity(newFitnessActivity.getName(), newFitnessActivity.getDuration(), newFitnessActivity.getCaloriesBurned(),
                            newFitnessActivity.getDate(), newFitnessActivity.getDescription(),  String.valueOf(newFitnessActivity.getRf()));

                    newFitnessActivity = new FitnessActivity("Basketball", "2 Hours", "700", "04/15/2022", "You played in a basketball game", -1.0);
                    dbHandlerMyFitnessActivities.addNewFitnessActivity(newFitnessActivity.getName(), newFitnessActivity.getDuration(), newFitnessActivity.getCaloriesBurned(),
                            newFitnessActivity.getDate(), newFitnessActivity.getDescription(),  String.valueOf(newFitnessActivity.getRf()));

                    newFitnessActivity = new FitnessActivity("Walk", "45 Minutes", "200", "04/16/2022", "You went for a walk", -1.0);
                    dbHandlerMyFitnessActivities.addNewFitnessActivity(newFitnessActivity.getName(), newFitnessActivity.getDuration(), newFitnessActivity.getCaloriesBurned(),
                            newFitnessActivity.getDate(), newFitnessActivity.getDescription(),  String.valueOf(newFitnessActivity.getRf()));

                    newFitnessActivity = new FitnessActivity("Biking", "55 Minutes", "375", "04/17/2022", "You went for a bike ride", -1.0);
                    dbHandlerMyFitnessActivities.addNewFitnessActivity(newFitnessActivity.getName(), newFitnessActivity.getDuration(), newFitnessActivity.getCaloriesBurned(),
                            newFitnessActivity.getDate(), newFitnessActivity.getDescription(),  String.valueOf(newFitnessActivity.getRf()));

                    mWaveLoadingView.pauseAnimation();

                    int a = (db.rows_trips()*3)+(db.rows_interaction()*2)+(db.rows_takeout())+MyDoctorVisits.rf()+MyMedicines.rf()+MySymptoms.rf()+MyFitnessActivities.rf();i = 100;
                    if(a>100){
                        a=100;
                    }
                    if(i - a > 100)
                    {
                        i=100;
                    }
                    else if(a<=100){
                        i=100-a;
                    }


                    //mWaveLoadingView.setCenterTitleColor(Color.WHITE);
                    mWaveLoadingView.setCenterTitle(i+"%");
                    mWaveLoadingView.setBottomTitleSize(18);
                    mWaveLoadingView.setProgressValue(0);
                    mWaveLoadingView.setBorderWidth(5);
                    mWaveLoadingView.setAmplitudeRatio(100);
                    //mWaveLoadingView.setWaveBgColor(Color.parseColor("#FFFFFF"));

                    if (i>=90 && i<=100){
                        mWaveLoadingView.setTopTitle("You Are Safe");
                        mWaveLoadingView.setWaveColor(Color.parseColor("#44DD2F"));
                        mWaveLoadingView.setBorderColor(Color.parseColor("#44DD2F"));
                    }
                    else if (i>=40 && i<=89){
                        mWaveLoadingView.setTopTitle("You Should Do COVID Test");
                        mWaveLoadingView.setWaveColor(Color.parseColor("#C8DD2F"));
                        mWaveLoadingView.setBorderColor(Color.parseColor("#C8DD2F"));
                    }
                    else if (i>=0 && i<=39){
                        mWaveLoadingView.setTopTitle("Do COVID Test Now");
                        mWaveLoadingView.setWaveColor(Color.parseColor("#DD442F"));
                        mWaveLoadingView.setBorderColor(Color.parseColor("#DD442F"));
                    }
                    else if (i<=0){
                        mWaveLoadingView.setTopTitle("Do COVID Test Now");
                        mWaveLoadingView.setWaveColor(Color.parseColor("#DD442F"));
                        mWaveLoadingView.setBorderColor(Color.parseColor("#DD442F"));
                    }
                    mWaveLoadingView.setTopTitleStrokeColor(Color.WHITE);
                    mWaveLoadingView.setTopTitleStrokeWidth(3);
                    mWaveLoadingView.setWaterLevelRatio(0.2f);
                    mWaveLoadingView.setAnimDuration(3000);

                    mWaveLoadingView.resumeAnimation();
                    mWaveLoadingView.cancelAnimation();
                    mWaveLoadingView.startAnimation();

                    progressStatus = 0;

                    new Thread(new Runnable() {
                        public void run() {

                            while (progressStatus < i) {
                                progressStatus += 2;
                                handler.post(new Runnable() {
                                    public void run() {
                                        mWaveLoadingView.setProgressValue(progressStatus);
                                        String x=String.valueOf(i);
                                    }
                                });
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                }
                else if(activity == 1){
                    startActivity(new Intent(MainActivity.this,ViewFitnessActivities.class));
                }
            }
        });
        builder.show();
    }

    public void Symptoms(View view){

        String[] Activity = {"Log Symptoms", "View Symptoms"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){
                    Intent intent = new Intent(MainActivity.this, Log_Symptom_ChooseSymptom.class);
                    intent.putExtra("context", "MainActivity");
                    startActivity(intent);
                }
                else if(activity == 1){
                    Intent intent = new Intent(MainActivity.this, ViewSymptoms.class);
                    intent.putExtra("context", "MainActivity");
                    startActivity(intent);
                }
            }
        });
        builder.show();
    }

    public void Medicines(View view){

        String[] Activity = {"Log Medicines", "View Medicines"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){
                    Intent intent = new Intent(MainActivity.this, Log_Medicine_ChooseMedicine.class);
                    intent.putExtra("context", "MainActivity");
                    startActivity(intent);
                }
                else if(activity == 1){
                    Intent intent = new Intent(MainActivity.this, ViewMedicines.class);
                    intent.putExtra("context", "MainActivity");
                    startActivity(intent);
                }
            }
        });
        builder.show();
    }
    public void DoctorVisits(View view){

        String[] Activity = {"Log Doctor Visits", "View Doctor Visits"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Service");
        builder.setItems(Activity, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int activity) {
                if(activity == 0){
                    Intent intent = new Intent(MainActivity.this, Log_DoctorVisit_ChooseVisitType.class);
                    intent.putExtra("context", "MainActivity");
                    startActivity(intent);
                }
                else if(activity == 1){
                    Intent intent = new Intent(MainActivity.this, ViewDoctorVisits.class);
                    intent.putExtra("context", "MainActivity");
                    startActivity(intent);
                }
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }

    public void SendUserToChooseSymptom()
    {
        Intent intent = new Intent(this, Log_Symptom_ChooseSymptom.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToChooseSymptom

    public void SendUserToViewSymptoms()
    {
        Intent intent = new Intent(this, ViewSymptoms.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToViewSymptoms

    public void SendUserToChooseMedicine()
    {
        Intent intent = new Intent(this, Log_Medicine_ChooseMedicine.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToChooseMedicine

    public void SendUserToViewMedicines()
    {
        Intent intent = new Intent(this, ViewMedicines.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToViewMedicines

    public void SendUserToChooseVisitType()
    {
        Intent intent = new Intent(this, Log_DoctorVisit_ChooseVisitType.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToChooseVisitType

    public void SendUserToViewDoctorVisits()
    {
        Intent intent = new Intent(this, ViewDoctorVisits.class);
        intent.putExtra("context", "MainActivity");
        startActivity(intent);
    }//SendUserToViewDoctorVisits

    public void changeStatusBar(Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}