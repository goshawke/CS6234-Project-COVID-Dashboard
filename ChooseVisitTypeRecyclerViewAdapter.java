package com.practice.coviddashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ChooseVisitTypeRecyclerViewAdapter extends RecyclerView.Adapter<ChooseVisitTypeRecyclerViewHolder> {
    Context c;
    ArrayList<String> data = new ArrayList<>();

    public ChooseVisitTypeRecyclerViewAdapter(Context c, ArrayList<String> data) {
        super();
        this.data = data;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED
    @NonNull
    @Override
    public ChooseVisitTypeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);

        //HOLDER
        ChooseVisitTypeRecyclerViewHolder holder =new ChooseVisitTypeRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseVisitTypeRecyclerViewHolder holder, final int position) {
        holder.tvList.setText(data.get(position));

        //SET THE ITEM CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v, data.get(holder.getAdapterPosition()),Snackbar.LENGTH_SHORT).show();
                Log_DoctorVisit_ChooseVisitType.setVisitTypeChosen(data.get(holder.getAdapterPosition()));

                // Send user to VisitType Dosage and Duration
                Intent intent = new Intent(c.getApplicationContext(), Log_DoctorVisit_ChooseDoctorType.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("VisitType Chosen", data.get(holder.getAdapterPosition()));
                c.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }

}
