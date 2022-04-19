package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ViewADoctorVisit extends AppCompatActivity {

    TextView tvVisitTypeHeader, tvDoctorTypeHeader, tvDate, tvDateHeader;
    Button btnDelete;

    private static DoctorVisit myDoctorVisit;
    DBHandler_MyDoctorVisits db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_a_doctor_visit);

        tvVisitTypeHeader = findViewById(R.id.tvVisitTypeHeader_ViewADoctorVisit);
        tvDoctorTypeHeader = findViewById(R.id.tvDoctorTypeHeaderViewADoctorVisit);
        tvDate = findViewById(R.id.tvDate);
        tvDateHeader = findViewById(R.id.tvDateHeader);




        btnDelete = findViewById(R.id.btnDelete_ViewADoctorVisit);

        db = new DBHandler_MyDoctorVisits(ViewADoctorVisit.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDate.setText("");
                tvDoctorTypeHeader.setText("");
                tvVisitTypeHeader.setText("");
                tvDateHeader.setText("");
                db.deleteDoctorVisit(myDoctorVisit.getDoctorType());
                SendUserToViewDashboard();
            }
        });


        tvVisitTypeHeader.setText(getMyDoctorVisit().getVisitType());
        tvDoctorTypeHeader.setText(getMyDoctorVisit().getDoctorType());


        tvDate.setText(getMyDoctorVisit().getDateOfVisit());



    }

    public static DoctorVisit getMyDoctorVisit() {
        return myDoctorVisit;
    }

    public static void setMyDoctorVisit(DoctorVisit myDoctorVisit) {
        ViewADoctorVisit.myDoctorVisit = myDoctorVisit;
    }

    public void SendUserToViewDoctorVisits()
    {
        Intent intent = new Intent(this, ViewDoctorVisits.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard

    public void SendUserToViewDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}



