package edu.gwu.coviddashboard;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHandler_MyFitnessActivities extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "FitnessActivity Log Database";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "myFitnessActivities";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "Name";

    // below variable id for our duration column.
    private static final String DURATION_COL = "Duration";

    // below variable id for our duration column.
    private static final String CAL_BURNED_COL = "Calories";

    // below variable for our date column.
    private static final String DATE_COL = "startDate";

    // below variable for our  description column.
    private static final String DESCRIPTION_COL = "Description";

    // below variable is for our risk factor column.
    private static final String RF_COL = "rf";

    // creating a constructor for our database handler.
    public DBHandler_MyFitnessActivities(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + CAL_BURNED_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + RF_COL + " DOUBLE)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewFitnessActivity(String Name, String Duration, String Calories, String Date, String Description, String rf) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, Name);
        values.put(DURATION_COL, Duration);
        values.put(CAL_BURNED_COL, Calories);
        values.put(DATE_COL, Date);
        values.put(DESCRIPTION_COL, Description);
        values.put(RF_COL, rf);
        Log.d("Values = ", values.toString());

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public ArrayList<FitnessActivity> readFitnessActivities() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorFitnessActivities = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<FitnessActivity> FitnessActivityArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorFitnessActivities.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                FitnessActivityArrayList.add(new FitnessActivity(cursorFitnessActivities.getString(1),
                        cursorFitnessActivities.getString(2),
                        cursorFitnessActivities.getString(3),
                        cursorFitnessActivities.getString(4),
                        cursorFitnessActivities.getString(5),
                        (double) cursorFitnessActivities.getInt(6)));
            } while (cursorFitnessActivities.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorFitnessActivities.close();
        return FitnessActivityArrayList;
    }

    public int rf () {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorFitnessActivities = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int rf = 0;
        // on below line we are creating a new array list.


        // moving our cursor to first position.
        if (cursorFitnessActivities.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                rf+=cursorFitnessActivities.getInt(6);
                Log.d("cursoFitnessActivities", cursorFitnessActivities.getInt(6)+"");

            } while (cursorFitnessActivities.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorFitnessActivities.close();
        return rf;
    }


    // below is the method for deleting our course.
    public void delete(String name) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "Name=?", new String[]{name});
        db.close();
    }

}

