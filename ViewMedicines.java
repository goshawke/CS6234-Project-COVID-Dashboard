package edu.gwu.coviddashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
/*
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;*/

import java.util.ArrayList;

public class ViewMedicines extends AppCompatActivity {

    Button btnBack;
    TextView tvViewMedicinesHeader;
    RecyclerView rvYourMedicineList;
    ViewMedicinesRecyclerViewAdapter adapter;

    ArrayList<Medicine> medicines;
    private DBHandler_MyMedicines dbHandlerMyMedicines;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_medicines_list);
        changeStatusBar(getWindow());
        rvYourMedicineList = findViewById(R.id.rvYourFitnessActivityList);



        // data to populate the RecyclerView with
        medicines = new ArrayList<Medicine>();
        dbHandlerMyMedicines = new DBHandler_MyMedicines(ViewMedicines.this);

        medicines = dbHandlerMyMedicines.readMedicines();

        // set up the RecyclerView
        rvYourMedicineList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewMedicinesRecyclerViewAdapter(getApplicationContext() , medicines);
        rvYourMedicineList.setAdapter(adapter);

        final DBHandler_MyMedicines db = new DBHandler_MyMedicines(this);

        rvYourMedicineList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rvYourMedicineList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                        final String Firstline = medicines.get(position).getName();
                        final String Secondline = medicines.get(position).getDosage();

                        AlertDialog.Builder alert = new AlertDialog.Builder(ViewMedicines.this);
                        alert.setTitle("Delete");  // Declare the title
                        alert.setMessage("Are you sure you want to delete \n"+Firstline+"?");  // Set message below title
                        alert.setIcon(R.drawable.delete);  // Set the icon at top left
                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                medicines.remove(position);
                                db.delete(Firstline, Secondline);
                                adapter.notifyDataSetChanged();

                            }
                        });
                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // close dialog
                            }
                        });
                        alert.show();



                        Log.d("First", Firstline);
                        Log.d("Second", Secondline);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );}

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard

    public void changeStatusBar(Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent= new Intent(ViewMedicines.this,MainActivity.class);
        startActivity(intent);
    }
}
