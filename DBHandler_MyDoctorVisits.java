package edu.gwu.coviddashboard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHandler_MyDoctorVisits extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "DoctorVisit Log Database";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "myDoctorVisits";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable id for our VISIT TYPE column.
    private static final String VISIT_TYPE_COL = "VisitType";

    // below variable id for our DOCTOR TYPE column.
    private static final String DOCTOR_TYPE_COL = "DoctorType";

    // below variable for our date column.
    private static final String DATE_COL = "VisitDate";

    // below variable for our  description column.
    private static final String DESCRIPTION_COL = "Description";

    // below variable is for our risk factor column.
    private static final String RF_COL = "rf";

    // creating a constructor for our database handler.
    public DBHandler_MyDoctorVisits(Context context) {
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
                + VISIT_TYPE_COL + " TEXT,"
                + DOCTOR_TYPE_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + RF_COL + " DOUBLE)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewDoctorVisit(String VisitType, String DoctorType, String visitDate, String Description, String rf) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(VISIT_TYPE_COL, VisitType);
        values.put(DOCTOR_TYPE_COL, DoctorType);
        values.put(DATE_COL, visitDate);
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
    public ArrayList<DoctorVisit> readDoctorVisits() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorDoctorVisits = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<DoctorVisit> doctorVisitArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorDoctorVisits.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                doctorVisitArrayList.add(new DoctorVisit(cursorDoctorVisits.getString(1),
                        cursorDoctorVisits.getString(2),
                        cursorDoctorVisits.getString(3),
                        cursorDoctorVisits.getString(4),
                        cursorDoctorVisits.getInt(5)));
            } while (cursorDoctorVisits.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorDoctorVisits.close();
        return doctorVisitArrayList;
    }

    public int rf () {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorDoctorVisits = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        int rf = 0;
        // on below line we are creating a new array list.


        // moving our cursor to first position.
        if (cursorDoctorVisits.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                rf+=cursorDoctorVisits.getInt(5);
                Log.d("cursorDoctorVisits", cursorDoctorVisits.getInt(5)+"");

            } while (cursorDoctorVisits.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorDoctorVisits.close();
        return rf;
    }


    // below is the method for deleting our course.
    public void deleteDoctorVisit(String doctorType) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "DoctorType=?", new String[]{doctorType});
        db.close();
    }
}
