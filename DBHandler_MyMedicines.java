package com.practice.coviddashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHandler_MyMedicines extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Medicine Log Database";

    // below int is our database version
    private static final int DB_VERSION = 2;

    // below variable is for our table name.
    private static final String TABLE_NAME = "myMedicines";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "Name";

    // below variable id for our dosage column.
    private static final String DOSAGE_COL = "Dosage";

    // below variable for our date column.
    private static final String DATE_COL = "startDate";

    // below variable for our  description column.
    private static final String DESCRIPTION_COL = "Description";

    // below variable is for our risk factor column.
    private static final String RF_COL = "rf";

    // creating a constructor for our database handler.
    public DBHandler_MyMedicines(Context context) {
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
                + DOSAGE_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + RF_COL + " DOUBLE)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewMedicine(String Name, String Dosage, String dateFirstExperienced, String Description, String rf) {

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
        values.put(DOSAGE_COL, Dosage);
        values.put(DATE_COL, dateFirstExperienced);
        values.put(DESCRIPTION_COL, Description);
        values.put(RF_COL, rf);
        Log.d("Value = ", values.toString());

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
    public ArrayList<Medicine> readMedicines() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorMedicines = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Medicine> medicineArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorMedicines.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                medicineArrayList.add(new Medicine(cursorMedicines.getString(1),
                        cursorMedicines.getString(2),
                        cursorMedicines.getString(3),
                        cursorMedicines.getString(4),
                        cursorMedicines.getInt(5)));
            } while (cursorMedicines.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMedicines.close();
        return medicineArrayList;
    }
}

