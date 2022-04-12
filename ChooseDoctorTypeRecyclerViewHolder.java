package com.practice.coviddashboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ChooseDoctorTypeRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvList;
    ChooseDoctorTypeRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ChooseDoctorTypeRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvList = (TextView) itemView.findViewById(R.id.tvList);
        itemView.setOnClickListener(this);
    }
    //WHEN CLICKED
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    //SHALL BE CALLED OUTSIDE
    public void setItemClickListener(ChooseDoctorTypeRecyclerViewAdapter.ItemClickListener ic)

    {
        this.itemClickListener=ic;
    }


}
