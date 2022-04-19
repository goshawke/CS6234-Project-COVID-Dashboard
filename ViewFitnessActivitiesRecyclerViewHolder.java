package edu.gwu.coviddashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

//import androidx.recyclerview.widget.RecyclerView;

public class ViewFitnessActivitiesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvFitnessActivityList, tvDate;
    ViewFitnessActivitiesRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ViewFitnessActivitiesRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvFitnessActivityList = (TextView) itemView.findViewById(R.id.tvCard_FitnessActivity);
        tvDate = (TextView) itemView.findViewById(R.id.tvCard_Date);
        itemView.setOnClickListener(this);
    }
    //WHEN CLICKED
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    //SHALL BE CALLED OUTSIDE
    public void setItemClickListener(ViewFitnessActivitiesRecyclerViewAdapter.ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }


}
