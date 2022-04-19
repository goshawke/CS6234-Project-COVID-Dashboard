package edu.gwu.coviddashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
//import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ChooseMedicineRecyclerViewAdapter extends RecyclerView.Adapter<ChooseMedicineRecyclerViewHolder> {
    Context c;
    ArrayList<String> data = new ArrayList<>();

    public ChooseMedicineRecyclerViewAdapter(Context c, ArrayList<String> data) {
        super();
        this.data = data;
        this.c = c;
    }

    //WHEN VIEWHOLDER IS BEING CREATED

    @Override
    public ChooseMedicineRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //INFLATE XML AND HOLD IN VIEW
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);

        //HOLDER
        ChooseMedicineRecyclerViewHolder holder =new ChooseMedicineRecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ChooseMedicineRecyclerViewHolder holder, final int position) {
        holder.tvMedicineList.setText(data.get(position));

        //SET THE ITEM CLICK LISTENER
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //Snackbar.make(v, data.get(holder.getAdapterPosition()),Snackbar.LENGTH_SHORT).show();
                Toast.makeText(c.getApplicationContext(),
                        data.get(holder.getAdapterPosition()),
                        Toast.LENGTH_LONG)
                        .show();
                Log_Medicine_ChooseMedicine.setMedicineChosen(data.get(holder.getAdapterPosition()));

                // Send user to Medicine Dosage and Duration
                Intent intent = new Intent(c.getApplicationContext(), Log_Medicine_ChooseDosageAndDuration.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Medicine Chosen", data.get(holder.getAdapterPosition()));
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
