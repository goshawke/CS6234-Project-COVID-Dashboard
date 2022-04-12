package com.practice.coviddashboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewMedicinesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvMedicineList;
    ViewMedicinesRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ViewMedicinesRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvMedicineList = (TextView) itemView.findViewById(R.id.tvList);
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
