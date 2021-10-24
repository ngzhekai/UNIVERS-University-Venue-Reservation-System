package com.zhekai.universityvenuemanagementsystem;

import static com.zhekai.universityvenuemanagementsystem.LoginActivity.UserId;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "records.db";
    public static final String TABLE_NAME = "Reservation_Table";
    public static final String COL_1 = "RESERVATION_ID";
    public static final String COL_2 = "PHONE_NUMBER";
    public static final String COL_3 = "VENUE_ID";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "START_TIME";
    public static final String COL_6 = "END_TIME";
    public static final String COL_7 = "USER_ID";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + " TEXT NOT NULL, " +
                COL_3 + " TEXT NOT NULL, " +
                COL_4 + " TEXT NOT NULL, " +
                COL_5 + " TEXT NOT NULL, " +
                COL_6 + " TEXT NOT NULL, " +
                COL_7 + " TEXT NOT NULL)";


        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add reservation
    public boolean addReservation (Work work1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(COL_1, work.getReservation());
        cv.put(COL_2, work1.getPhonenumber());
        cv.put(COL_3, work1.getVenueID());
        cv.put(COL_4, work1.getDate());
        cv.put(COL_5, work1.getStartTime());
        cv.put(COL_6, work1.getEndtime());
        cv.put(COL_7, work1.getUsername());
        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1)
            return false;
        else
            return true;
    }

    //delete reservation
    public boolean deleteReservation(Work work) {
        //  find customerModel in the database. if it found, delete it and return true.
        //   if it is not found, return false

        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + work.getReservation();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst())
            return true;
        else
            return false;
    }

//    public List<Work> getAll() {
//        try {
//            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
//            List<Work> works = new ArrayList<>();
//            if (cursor.moveToNext()) {
//                works = new ArrayList<Work>();
//                do {
//                    Work work = new Work();
//                    work.setReservation(cursor.getInt(0));
//                    work.setPhonenumber(cursor.getString(1));
//                    work.setVenueID(cursor.getString(2));
//                    work.setDate(cursor.getString(3));
//                    work.setStartTime(cursor.getString(4));
//                    work.setEndtime(cursor.getString(5));
//                } while (cursor.moveToNext());
//            }
//            return works;
//        } catch (Exception e) {
//            return null;
//        }
//
//    }

    public List<Work> getEveryone() {
        List<Work> returnList = new ArrayList<>();
        String queryString;
        if (UserId.toString().equals("admin")) {
            // get data from the database
            queryString = "SELECT * FROM " + TABLE_NAME;
        }else{
            // get data from the database
            queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_7 + " = " + "'" + UserId + "'";
        }


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //  loop through the cursor (result set) and create new work objects. Put them into the return list.
            do {
                int reservationID = cursor.getInt(0);
                String phoneNumber = cursor.getString(1);
                String venueID = cursor.getString(2);
                String date = cursor.getString(3);
                String startTime = cursor.getString(4);
                String endTime = cursor.getString(5);
                String userID = cursor.getString(6);

                Work work = new Work(reservationID, phoneNumber, venueID, date, startTime, endTime, userID);
                returnList.add(work);

            } while (cursor.moveToNext());

        }
        else {
            //  failure, do not add anything to the list.
        }

        //  close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }
}
