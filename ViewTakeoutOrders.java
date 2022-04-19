package edu.gwu.coviddashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.ArrayList;

public class ViewTakeoutOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final RecyclerView cardList;
        final ArrayList<ObjectCardList> objectCardListArrayList;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_takeout_orders);
        changeStatusBar(getWindow());
        final DB db = new DB(this);
        cardList = findViewById(R.id.idCardlist);

        // here we have created new array list and added data to it.
        objectCardListArrayList = db.getTakeout();

        // we are initializing our adapter class and passing our arraylist to it.
        final CardListAdapter cardListAdapter = new CardListAdapter(this, objectCardListArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        cardList.setLayoutManager(linearLayoutManager);
        cardList.setAdapter(cardListAdapter);
        Log.d("First", String.valueOf(db.rows_takeout()));
        cardList.addOnItemTouchListener(
                new RecyclerItemClickListener(this, cardList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, final int position) {
                        final String Firstline = objectCardListArrayList.get(position).getCardlist_firstline();
                        final String Secondline = objectCardListArrayList.get(position).getCardlist_secondline();

                        AlertDialog.Builder alert = new AlertDialog.Builder(ViewTakeoutOrders.this);
                        alert.setTitle("Delete");  // Declare the title
                        alert.setMessage("Are you sure you want to delete \n"+Firstline+"?");  // Set message below title
                        alert.setIcon(R.drawable.delete);  // Set the icon at top left
                        alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                objectCardListArrayList.remove(position);
                                db.remove_takeout(Firstline, Secondline);
                                cardListAdapter.notifyDataSetChanged();

                            }
                        });
                        alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // close dialog
                            }
                        });
                        alert.show();

                        //db.rows_takeout();

                        Log.d("First", String.valueOf(db.rows_takeout()));
                        Log.d("Second", Secondline);
                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );}

    public void changeStatusBar(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(R.color.contentStatusBar));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Intent intent= new Intent(ViewTakeoutOrders.this,MainActivity.class);
        startActivity(intent);
    }
}