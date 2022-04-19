package edu.gwu.coviddashboard;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAFitnessActivity extends AppCompatActivity {

    TextView tvViewAFitnessActivityHeader, tvCaloriesBurned, tvDuration, tvDate, textView4, tvCaloriesBurned_header, tvDuration_header, tvDate_header;

    private static FitnessActivity myFitnessActivity;
    Button btnDelete;
    DBHandler_MyFitnessActivities db;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_a_fitness_activity);
        changeStatusBar(getWindow());
        tvViewAFitnessActivityHeader = findViewById(R.id.tvViewAFitnessActivityHeader);
        tvViewAFitnessActivityHeader.setText(getMyFitnessActivity().getName());

        tvCaloriesBurned = findViewById(R.id.tvCaloriesBurned);
        tvDuration = findViewById(R.id.tvDuration);
        tvDate = findViewById(R.id.tvDate);
        tvCaloriesBurned_header = findViewById(R.id.tvCaloriesBurned_header);
        tvDuration_header = findViewById(R.id.tvDuration_header);
        tvDate_header = findViewById(R.id.tvDate_header);
        textView4 = findViewById(R.id.textView4);



        tvCaloriesBurned.setText(getMyFitnessActivity().getCaloriesBurned());
        tvDuration.setText(getMyFitnessActivity().getDuration());
        tvDate.setText(getMyFitnessActivity().getDate());

        btnDelete = findViewById(R.id.btnDelete_ViewAFitnessActivity);

        db = new DBHandler_MyFitnessActivities(ViewAFitnessActivity.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDate.setText("");
                tvCaloriesBurned.setText("");
                tvDuration.setText("");
                tvViewAFitnessActivityHeader.setText("");
                textView4.setText("");
                tvCaloriesBurned_header.setText("");
                tvDuration_header.setText("");
                tvDate_header.setText("");

                db.delete(myFitnessActivity.getName());
                SendUserToViewDashboard();
            }
        });




    }

    public static FitnessActivity getMyFitnessActivity() {
        return myFitnessActivity;
    }

    public static void setMyFitnessActivity(FitnessActivity myFitnessActivity) {
        ViewAFitnessActivity.myFitnessActivity = myFitnessActivity;
    }

    public void SendUserToViewFitnessActivities()
    {
        Intent intent = new Intent(this, ViewFitnessActivities.class);
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
        Intent intent = new Intent(this, ViewFitnessActivities.class);
        startActivity(intent);
        finish();
    }

    public void SendUserToViewDashboard()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }//SendUserToDashboard
}


