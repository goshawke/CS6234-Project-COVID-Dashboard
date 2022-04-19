package edu.gwu.coviddashboard;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ViewDoctorVisitsRecyclerViewAdapter extends RecyclerView.Adapter<ViewDoctorVisitsRecyclerViewHolder> {
    Context c;
    ArrayList<DoctorVisit> doctorVisits = new ArrayList<DoctorVisit>();

    public ViewDoctorVisitsRecyclerViewAdapter(Context c, ArrayList<DoctorVisit> doctorVisits) {
        super();
        this.doctorVisits = doctorVisits;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED

    @Override
    public ViewDoctorVisitsRecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_doctor_visit, null);

        //HOLDER
        ViewDoctorVisitsRecyclerViewHolder holder = new ViewDoctorVisitsRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewDoctorVisitsRecyclerViewHolder holder, final int position) {
        holder.tvCard_VisitType.setText(doctorVisits.get(position).getVisitType());
        holder.tvCard_DoctorType.setText(doctorVisits.get(position).getDoctorType());
        holder.tvCard_VisitDate.setText(doctorVisits.get(position).getDateOfVisit());
        //SET THE ITEM CLICK LISTENER

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v, doctorVisits.get(holder.getAdapterPosition()).getDoctorType(),Snackbar.LENGTH_SHORT).show();


                ViewADoctorVisit.setMyDoctorVisit(doctorVisits.get(holder.getAdapterPosition()));
                // Send user to ViewADoctorVisit
                Intent intent = new Intent(c.getApplicationContext(), ViewADoctorVisit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("DoctorVisit Chosen", doctorVisits.get(holder.getAdapterPosition()).getVisitType());
                c.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return doctorVisits.size();
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }

}
