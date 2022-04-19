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

public class ViewASymptom extends AppCompatActivity {

    TextView tvViewASymptomHeader, tvFirstExperiencedDateHeader, tvFirstExperiencedDate, tvSeverityHeader, textView4;
    ImageView ivSeverity;

    private static Symptom mySymptom;
    Button btnDelete;
    DBHandler_MySymptoms db;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_a_symptom);
        changeStatusBar(getWindow());
        tvViewASymptomHeader = findViewById(R.id.tvViewASymptomHeader);
        tvFirstExperiencedDateHeader = findViewById(R.id.tvFirstExperiencedDateHeader);
        tvFirstExperiencedDate = findViewById(R.id.tvDuration);
        tvSeverityHeader = findViewById(R.id.tvSeverityHeader);
        textView4 = findViewById(R.id.textView4);

        ivSeverity = findViewById((R.id.ivSeverity));


        tvViewASymptomHeader.setText(getMySymptom().getName());
        tvFirstExperiencedDate.setText(getMySymptom().getDate());

        if(getMySymptom().getSeverity() == 1)
        {
            ivSeverity.setImageResource(R.drawable.symptom_severity1);
        }
        else if(getMySymptom().getSeverity() == 2)
        {
            ivSeverity.setImageResource(R.drawable.symptom_severity2);
        }
        else if(getMySymptom().getSeverity() == 3)
        {
            ivSeverity.setImageResource(R.drawable.symptom_severity3);
        }
        else
        {
            ivSeverity.setImageResource(R.drawable.symptom);
        }


        btnDelete = findViewById(R.id.btnDelete_ViewASymptom);

        db = new DBHandler_MySymptoms(ViewASymptom.this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvViewASymptomHeader.setText("");
                tvFirstExperiencedDate.setText("");
                tvSeverityHeader.setText("");
                textView4.setText("");
                tvFirstExperiencedDateHeader.setText("");
                tvSeverityHeader.setText("");
                ivSeverity.setVisibility(View.INVISIBLE);

                db.delete(mySymptom.getName());
                SendUserToViewDashboard();
            }
        });
    }

    public static Symptom getMySymptom() {
        return mySymptom;
    }

    public static void setMySymptom(Symptom mySymptom) {
        ViewASymptom.mySymptom = mySymptom;
    }

    public void SendUserToViewSymptoms()
    {
        Intent intent = new Intent(this, ViewSymptoms.class);
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
        Intent intent = new Intent(this, ViewSymptoms.class);
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
