package com.practice.coviddashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.coviddashboard.ChooseMedicineRecyclerViewAdapter;
import com.practice.coviddashboard.MainActivity;
import com.practice.coviddashboard.R;

import java.util.ArrayList;

public class Log_Medicine_ChooseMedicine extends AppCompatActivity {

    Button btnBack;
    TextView tvLogMedicine, tvChooseMedicine;
    RecyclerView rvMedicineList;
    ChooseMedicineRecyclerViewAdapter adapter;
    private static String medicineChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_medicine_choose_medicine);

        btnBack = findViewById(R.id.btnBack);

        tvLogMedicine = findViewById(R.id.tvLogMedicine);
        tvChooseMedicine = findViewById(R.id.tvChooseMedicine);

        rvMedicineList = findViewById(R.id.rvMedicineList);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SendUserToDashboard();
            }
        });




        // data to populate the RecyclerView with
        ArrayList<String> medicines = new ArrayList<>();
        medicines.add("Acetaminophen");
        medicines.add("Albuterol");
        medicines.add("Asprin");
        medicines.add("Atorvastatin");
        medicines.add("Calcium");
        medicines.add("Fish Oil");
        medicines.add("Fluticasone");
        medicines.add("Gabapentin");
        medicines.add("Hydrocodone");
        medicines.add("Levathyroxine");
        medicines.add("Lisinipril");
        medicines.add("Losartan");
        medicines.add("Ibuprofen");
        medicines.add("Iron");
        medicines.add("Magnesium");
        medicines.add("Metformin");
        medicines.add("Multivitamin");
        medicines.add("Omeproazole");
        medicines.add("Potassium");
        medicines.add("Simvastatin");
        medicines.add("Vitamin A");
        medicines.add("Vitamin B");
        medicines.add("Vitamin C");
        medicines.add("Vitamin D");
        medicines.add("Zinc");


        // set up the RecyclerView
        rvMedicineList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseMedicineRecyclerViewAdapter(getApplicationContext(), medicines);
        rvMedicineList.setAdapter(adapter);
    }

    public static String getMedicineChosen()
    {
        return medicineChosen;
    }

    public static void setMedicineChosen(String s)
    {
        medicineChosen = s;
    }

    public void SendUserToDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}
