package com.practice.coviddashboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewDoctorVisitsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvCard_VisitType, tvCard_DoctorType, tvCard_VisitDate;
    ViewDoctorVisitsRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ViewDoctorVisitsRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvCard_VisitType = (TextView) itemView.findViewById(R.id.tvCard_VisitType);
        tvCard_DoctorType = (TextView) itemView.findViewById(R.id.tvCard_DoctorType);
        tvCard_VisitDate = (TextView) itemView.findViewById(R.id.tvCard_VisitDate);
        itemView.setOnClickListener(this);
    }
    //WHEN CLICKED
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    //SHALL BE CALLED OUTSIDE
    public void setItemClickListener(ViewDoctorVisitsRecyclerViewAdapter.ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }


}
