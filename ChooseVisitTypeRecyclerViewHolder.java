package edu.gwu.coviddashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ChooseVisitTypeRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvList;
    ChooseVisitTypeRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ChooseVisitTypeRecyclerViewHolder(View itemView) {
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
    public void setItemClickListener(ChooseVisitTypeRecyclerViewAdapter.ItemClickListener ic)

    {
        this.itemClickListener=ic;
    }


}
