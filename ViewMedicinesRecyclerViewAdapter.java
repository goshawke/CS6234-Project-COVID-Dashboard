package edu.gwu.coviddashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;
import android.support.v7.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;

//import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ViewMedicinesRecyclerViewAdapter extends RecyclerView.Adapter<ViewMedicinesRecyclerViewHolder> {
    Context c;
    ArrayList<Medicine> medicines = new ArrayList<Medicine>();

    public ViewMedicinesRecyclerViewAdapter(Context c, ArrayList<Medicine> medicines) {
        super();
        this.medicines = medicines;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED

    @Override
    public ViewMedicinesRecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_fitness_activity, null);

        //HOLDER
        ViewMedicinesRecyclerViewHolder holder =new ViewMedicinesRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewMedicinesRecyclerViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvMedicineList.setText(medicines.get(position).getName());
        holder.tvMedicineDosage.setText(medicines.get(position).getDosage() + "mgs");

        //SET THE ITEM CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(final View v, int pos) {

           /*     Snackbar.make(v, medicines.get(holder.getAdapterPosition()).getName(),Snackbar.LENGTH_SHORT).show();

                ViewAMedicine.setMyMedicine(medicines.get(holder.getAdapterPosition()));
                // Send user to ViewAMedicine
                Intent intent = new Intent(c.getApplicationContext(), ViewAMedicine.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Medicine Chosen", medicines.get(holder.getAdapterPosition()).getName());
                c.startActivity(intent);*/
            }

        });
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }

}
