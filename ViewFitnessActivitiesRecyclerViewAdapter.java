package edu.gwu.coviddashboard;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class ViewFitnessActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<ViewFitnessActivitiesRecyclerViewHolder> {
    Context c;
    ArrayList<FitnessActivity> FitnessActivities = new ArrayList<FitnessActivity>();

    public ViewFitnessActivitiesRecyclerViewAdapter(Context c, ArrayList<FitnessActivity> FitnessActivities) {
        super();
        this.FitnessActivities = FitnessActivities;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED
    @NonNull
    @Override
    public ViewFitnessActivitiesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_fitness_activity, null);

        //HOLDER
        ViewFitnessActivitiesRecyclerViewHolder holder =new ViewFitnessActivitiesRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewFitnessActivitiesRecyclerViewHolder holder, final int position) {
        holder.tvFitnessActivityList.setText(FitnessActivities.get(position).getName());
        holder.tvDate.setText(FitnessActivities.get(position).getDate());

        //SET THE ITEM CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v, FitnessActivities.get(holder.getAdapterPosition()).getName(),Snackbar.LENGTH_SHORT).show();

                ViewAFitnessActivity.setMyFitnessActivity(FitnessActivities.get(holder.getAdapterPosition()));
                // Send user to ViewAFitnessActivity
                Intent intent = new Intent(c.getApplicationContext(), ViewAFitnessActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("FitnessActivity Chosen", FitnessActivities.get(holder.getAdapterPosition()).getName());
                c.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return FitnessActivities.size();
    }

    public interface ItemClickListener {
        void onItemClick(View v,int pos);
    }

}
