package com.practice.coviddashboard;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewSymptomsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvSymptomList;
    ViewSymptomsRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ViewSymptomsRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvSymptomList = (TextView) itemView.findViewById(R.id.tvViewSymptomList);
        itemView.setOnClickListener(this);
    }
    //WHEN CLICKED
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    //SHALL BE CALLED OUTSIDE
    public void setItemClickListener(ViewSymptomsRecyclerViewAdapter.ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }


}
