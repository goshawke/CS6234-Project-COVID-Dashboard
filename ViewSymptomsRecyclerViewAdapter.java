package com.practice.coviddashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ViewSymptomsRecyclerViewAdapter extends RecyclerView.Adapter<ViewSymptomsRecyclerViewHolder> {
    Context c;
    ArrayList<Symptom> symptoms = new ArrayList<Symptom>();

    public ViewSymptomsRecyclerViewAdapter(Context c, ArrayList<Symptom> symptoms) {
        super();
        this.symptoms = symptoms;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED
    @NonNull
    @Override
    public ViewSymptomsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_view_symptoms, null);

        //HOLDER
        ViewSymptomsRecyclerViewHolder holder =new ViewSymptomsRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSymptomsRecyclerViewHolder holder, final int position) {
        holder.tvSymptomList.setText(symptoms.get(position).getName());

        //SET THE ITEM CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v, symptoms.get(holder.getAdapterPosition()).getName(),Snackbar.LENGTH_SHORT).show();

                ViewASymptom.setMySymptom(symptoms.get(holder.getAdapterPosition()));
                // Send user to ViewASymptom
                Intent intent = new Intent(c.getApplicationContext(), ViewASymptom.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Symptom Chosen", symptoms.get(holder.getAdapterPosition()).getName());
                c.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }

}
