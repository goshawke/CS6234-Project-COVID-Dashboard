package edu.gwu.coviddashboard;

import android.view.View;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
public class ChooseMedicineRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvMedicineList;
    ChooseMedicineRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ChooseMedicineRecyclerViewHolder(View itemView) {
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
    public void setItemClickListener(ChooseMedicineRecyclerViewAdapter.ItemClickListener ic)

    {
        this.itemClickListener=ic;
    }


}
