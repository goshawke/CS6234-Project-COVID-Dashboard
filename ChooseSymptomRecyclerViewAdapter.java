package edu.gwu.coviddashboard;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ChooseSymptomRecyclerViewAdapter extends RecyclerView.Adapter<ChooseSymptomRecyclerViewHolder> {
    Context c;
    ArrayList<String> data = new ArrayList<>();

    public ChooseSymptomRecyclerViewAdapter(Context c, ArrayList<String> data) {
        super();
        this.data = data;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED

    @Override
    public ChooseSymptomRecyclerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);

        //HOLDER
        ChooseSymptomRecyclerViewHolder holder =new ChooseSymptomRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ChooseSymptomRecyclerViewHolder holder, final int position) {
        holder.tvSymptomList.setText(data.get(position));

        //SET THE ITEM CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Snackbar.make(v, data.get(holder.getAdapterPosition()),Snackbar.LENGTH_SHORT).show();


                Log_Symptom_ChooseSymptom.setSymptomChosen(data.get(holder.getAdapterPosition()));

                // Send user to Symptom Severity
                Intent intent = new Intent(c.getApplicationContext(), Log_Symptom_SymptomSeverity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Symptom Chosen", data.get(holder.getAdapterPosition()));
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
