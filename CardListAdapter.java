package edu.gwu.coviddashboard;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.Viewholder> {

    private Context context;
    private ArrayList<ObjectCardList> objectCardListArrayList;

    // Constructor
    public CardListAdapter(Context context, ArrayList<ObjectCardList> objectCardListArrayList) {
        this.context = context;
        this.objectCardListArrayList = objectCardListArrayList;
    }


    @Override
    public CardListAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_doctor_visit, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(CardListAdapter.Viewholder holder, int position) {
        ObjectCardList cardlistObject = objectCardListArrayList.get(position);
        /*holder.firstline.setText(cardlistObject.getCardlist_firstline());
        holder.secondline.setText("" + cardlistObject.getCardlist_secondline());
        holder.imageCardlist.setImageResource(cardlistObject.getCardlist_image());*/

        holder.tvCard_VisitType.setText("");
        holder.tvCard_DoctorType.setText(cardlistObject.getCardlist_firstline());
        holder.tvCard_VisitDate.setText("" + cardlistObject.getCardlist_secondline());

    }

    @Override
    public int getItemCount() {
        return objectCardListArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        //private ImageView imageCardlist;
        //private TextView firstline, secondline;
        private TextView tvCard_VisitType, tvCard_DoctorType, tvCard_VisitDate;
        public Viewholder( View itemView) {
            super(itemView);
            tvCard_VisitType = (TextView) itemView.findViewById(R.id.tvCard_VisitType);
            tvCard_DoctorType = (TextView) itemView.findViewById(R.id.tvCard_DoctorType);
            tvCard_VisitDate = (TextView) itemView.findViewById(R.id.tvCard_VisitDate);
            /*
            imageCardlist = itemView.findViewById(R.id.idImageCardlist);
            firstline = itemView.findViewById(R.id.idFirstline);
            secondline = itemView.findViewById(R.id.idSecondline);*/
        }
    }
}
