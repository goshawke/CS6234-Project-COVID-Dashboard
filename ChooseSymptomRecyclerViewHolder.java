package edu.gwu.coviddashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

//import androidx.recyclerview.widget.RecyclerView;

public class ChooseSymptomRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvSymptomList;
    ChooseSymptomRecyclerViewAdapter.ItemClickListener itemClickListener;
    public ChooseSymptomRecyclerViewHolder(View itemView) {
        super(itemView);
        //ASSIGNING VIEWS
        tvSymptomList = (TextView) itemView.findViewById(R.id.tvList);
        itemView.setOnClickListener(this);
    }
    //WHEN CLICKED
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }

    //SHALL BE CALLED OUTSIDE
    public void setItemClickListener(ChooseSymptomRecyclerViewAdapter.ItemClickListener ic)

    {
        this.itemClickListener=ic;
    }


}
