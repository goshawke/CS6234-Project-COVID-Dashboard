package edu.gwu.coviddashboard;

import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

public class ViewMedicinesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvMedicineList, tvMedicineDosage;
    ViewMedicinesRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ViewMedicinesRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvMedicineList = (TextView) itemView.findViewById(R.id.tvCard_FitnessActivity);
        tvMedicineDosage = (TextView) itemView.findViewById(R.id.tvCard_Date);
        itemView.setOnClickListener(this);
    }
    //WHEN CLICKED
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    //SHALL BE CALLED OUTSIDE
    public void setItemClickListener(ViewMedicinesRecyclerViewAdapter.ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }


}
