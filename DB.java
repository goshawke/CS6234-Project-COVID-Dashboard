package edu.gwu.coviddashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "CovidDashboardDB";

    //Trips Table
    private static String TRIPS_TABLENAME = "tripsTable";
    private static String TRIPS_ID = "ID";
    private static String TRIPS_COUNTRY = "tripsCountry";
    private static String TRIPS_DATE = "tripsDate";


    //private static String CREATE_TRIPS_TABLE = "CREATE TABLE " + TRIPS_TABLENAME + "(" + TRIPS_ID + " INTEGER PRIMARY KEY," + TRIPS_DATE + " TEXT," + TRIPS_COUNTRY + " TEXT)";

    private static final String CREATE_TRIPS_TABLE =
            "CREATE TABLE " + TRIPS_TABLENAME + " (" +
                    TRIPS_ID + " INTEGER PRIMARY KEY," +
                    TRIPS_COUNTRY + " TEXT," +
                    TRIPS_DATE + " TEXT)";

    //Interaction Table
    private static String INTERACTION_TABLENAME = "interactionTable";
    private static String INTERACTION_ID = "ID";
    private static String INTERACTION_PERSON_NAME = "interactionPersonName";
    private static String INTERACTION_DATE = "interactionDate";


    //private static String CREATE_INTERACTIONTABLE = "CREATE TABLE " + INTERACTION_TABLENAME + "(" + INTERACTION_ID + " INTEGER PRIMARY KEY," + INTERACTION_DATE + " TEXT," + INTERACTION_PERSON_NAME + "TEXT)";

    private static final String CREATE_INTERACTIONTABLE =
            "CREATE TABLE " + INTERACTION_TABLENAME + " (" +
                    INTERACTION_ID + " INTEGER PRIMARY KEY," +
                    INTERACTION_PERSON_NAME + " TEXT," +
                    INTERACTION_DATE + " TEXT)";

    //Takeout Table
    private static String TAKEOUT_TABLENAME = "takeoutTable";
    private static String TAKEOUT_ID = "ID";
    private static String TAKEOUT_RESTURANT_NAME = "takeoutResturantName";
    private static String TAKEOUT_DATE = "takeoutDate";


    //private static String CREATE_TAKEOUTTABLE = "CREATE TABLE " + TAKEOUT_TABLENAME + "(" + TAKEOUT_ID + " INTEGER PRIMARY KEY," + TAKEOUT_DATE + " TEXT," + TAKEOUT_RESTURANT_NAME + "TEXT)";

    private static final String CREATE_TAKEOUTTABLE =
            "CREATE TABLE " + TAKEOUT_TABLENAME + " (" +
                    TAKEOUT_ID + " INTEGER PRIMARY KEY," +
                    TAKEOUT_RESTURANT_NAME + " TEXT," +
                    TAKEOUT_DATE + " TEXT)";

    public DB (Context context) {
        super(context,DATABASE_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TRIPS_TABLE);
        sqLiteDatabase.execSQL(CREATE_INTERACTIONTABLE);
        sqLiteDatabase.execSQL(CREATE_TAKEOUTTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertTripsIntoTheDatabase(String tripsCountry,String tripsDate){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TRIPS_COUNTRY, tripsCountry);
        cv.put(TRIPS_DATE, tripsDate);


        long result = db.insert(TRIPS_TABLENAME, null, cv);

        Log.d("TRIPS_DATE", tripsDate + " , TRIPS_COUNTRY - " + tripsCountry + " - . " + cv);

        boolean check = false;

        if(result != -1) {
            return true;
        }

        return check;
    }

    public boolean insertInteractionIntoTheDatabase(String interactionPersonName, String interactionDate){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(INTERACTION_PERSON_NAME, interactionPersonName);
        cv.put(INTERACTION_DATE, interactionDate);


        long result = db.insert(INTERACTION_TABLENAME, null, cv);

        boolean check = false;

        if(result != -1) {
            return true;
        }

        return check;
    }

    public boolean insertTakeoutIntoTheDatabase(String takeoutResturantName, String takeoutDate){

        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TAKEOUT_RESTURANT_NAME, takeoutResturantName);
        cv.put(TAKEOUT_DATE, takeoutDate);


        long result = db.insert(TAKEOUT_TABLENAME, null, cv);

        boolean check = false;

        if(result != -1) {
            return true;
        }

        return check;
    }

    public void remove_trips(String tripsCountry, String tripsDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TRIPS_TABLENAME + " WHERE " + TRIPS_COUNTRY + " = '" + tripsCountry + "' AND " + TRIPS_DATE + " = '" + tripsDate+"'";
        db.execSQL(sql);

        Log.d("remove", tripsCountry.toString());
        Log.d("remove", tripsDate.toString());

        db.close();
    }

    public void remove_interaction(String interactionPersonName, String interactionDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + INTERACTION_TABLENAME + " WHERE " + INTERACTION_PERSON_NAME + " = '" + interactionPersonName + "' AND " + INTERACTION_DATE + " = '" + interactionDate+"'";
        db.execSQL(sql);

        Log.d("remove", interactionPersonName.toString());
        Log.d("remove", interactionDate.toString());

        db.close();
    }

    public void remove_takeout(String takeoutResturantName, String takeoutDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TAKEOUT_TABLENAME + " WHERE " + TAKEOUT_RESTURANT_NAME + " = '" + takeoutResturantName + "' AND " + TAKEOUT_DATE + " = '" + takeoutDate+"'";
        db.execSQL(sql);

        Log.d("remove", takeoutResturantName.toString());
        Log.d("remove", takeoutDate.toString());

        db.close();
    }


    public ArrayList<ObjectCardList> getTrips() {

        ArrayList<ObjectCardList> objectCardListArrayList = new ArrayList<>();

        String selectQuery = "select * from " + TRIPS_TABLENAME ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String tripsDate = cursor.getString(1);
                String tripsCountry = cursor.getString(2);

                ObjectCardList tripsInfo = new ObjectCardList(
                        tripsDate,
                        tripsCountry,
                        R.drawable.trips
                );
                objectCardListArrayList.add(tripsInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return objectCardListArrayList;
    }

    public ArrayList<ObjectCardList> getInteraction() {

        ArrayList<ObjectCardList> objectCardListArrayList = new ArrayList<>();

        String selectQuery = "select * from " + INTERACTION_TABLENAME ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String interactionPersonName = cursor.getString(1);
                String interactionDate = cursor.getString(2);

                ObjectCardList interactionInfo = new ObjectCardList(
                        interactionPersonName,
                        interactionDate,
                        R.drawable.interaction
                );
                objectCardListArrayList.add(interactionInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return objectCardListArrayList;
    }

    public ArrayList<ObjectCardList> getTakeout() {

        ArrayList<ObjectCardList> objectCardListArrayList = new ArrayList<>();

        String selectQuery = "select * from " + TAKEOUT_TABLENAME ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String tripsCountry = cursor.getString(1);
                String tripsDate = cursor.getString(2);

                ObjectCardList tripsInfo = new ObjectCardList(

                        tripsCountry,
                        tripsDate,
                        R.drawable.takeouts
                );
                objectCardListArrayList.add(tripsInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return objectCardListArrayList;
    }
    public int rows_trips(){
        String countQuery = "SELECT  * FROM " + TRIPS_TABLENAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int rows_interaction(){
        String countQuery = "SELECT  * FROM " + INTERACTION_TABLENAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int rows_takeout(){
        String countQuery = "SELECT  * FROM " + TAKEOUT_TABLENAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}
