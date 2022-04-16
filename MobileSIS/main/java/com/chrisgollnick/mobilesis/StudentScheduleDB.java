package com.chrisgollnick.mobilesis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.Log;

public class StudentScheduleDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "studentschedule.db";
    public static final int VERSION = 1;


    public StudentScheduleDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static final class StudentsTable {
        public static final String TABLE_NAME = "students_table";
        public static final String COL_1 = "_id";
        public static final String COL_2 = "FIRSTNAME";
        public static final String COL_3 = "LASTNAME";
        public static final String COL_4 = "YOG";
    }

    public static final class ScheduleTable {
        public static final String TABLE_NAME = "schedule_table";
        public static final String COL_1 = "_id";
        public static final String COL_2 = "A";
        public static final String COL_3 = "B";
        public static final String COL_4 = "C";
        public static final String COL_5 = "D";
        public static final String COL_6 = "E";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + StudentsTable.TABLE_NAME + " (" +
                StudentsTable.COL_1 + " INTEGER primary key, " +
                StudentsTable.COL_2 + " TEXT, " +
                StudentsTable.COL_3 + " TEXT, " +
                StudentsTable.COL_4 + " INTEGER) ");

        db.execSQL("create table " + ScheduleTable.TABLE_NAME + " (" +
                ScheduleTable.COL_1 + " INTEGER primary key, " +
                ScheduleTable.COL_2 + " TEXT, " +
                ScheduleTable.COL_3 + " TEXT, " +
                ScheduleTable.COL_4 + " TEXT, " +
                ScheduleTable.COL_5 + " TEXT, " +
                ScheduleTable.COL_6 + " TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("drop table if exists " + StudentsTable.TABLE_NAME);
        onCreate(db);

        db.execSQL("drop table if exists " + ScheduleTable.TABLE_NAME);
        onCreate(db);
    }
    /*
    ************************************************************************************
    *****************************Student Data Methods***********************************
    ************************************************************************************
    */

    //Add Student
    public boolean CreateStudent(String idNum, String first, String last, String yoGrad) {
        int studentID = Integer.parseInt(idNum);
        int yog = Integer.parseInt(yoGrad);

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentScheduleDB.StudentsTable.COL_1, studentID);
        contentValues.put(StudentScheduleDB.StudentsTable.COL_2, first);
        contentValues.put(StudentScheduleDB.StudentsTable.COL_3, last);
        contentValues.put(StudentScheduleDB.StudentsTable.COL_4, yog);

        Long resultI = db.insert(StudentScheduleDB.StudentsTable.TABLE_NAME, null, contentValues);
        if (resultI == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Get Student Data
    public String GetFirstName(String ID) {
        int studentID = Integer.parseInt(ID);
        String fname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.StudentsTable.TABLE_NAME + " Where id = ?", new String[] {(ID)});
        if (cursor.moveToFirst()) {
            do {
                fname = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return fname;
    }

    public String GetLastName(String ID) {
        int studentID = Integer.parseInt(ID);
        String lname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.StudentsTable.TABLE_NAME + " Where id = ?", new String[] {(ID)});
        if (cursor.moveToFirst()) {
            do {
                lname = cursor.getString(3);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lname;
    }

    public int GetYOG(String ID) {
        int studentID = Integer.parseInt(ID);
        int yoG = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.StudentsTable.TABLE_NAME + " Where id = ?", new String[] {(ID)});
        if (cursor.moveToFirst()) {
            do {
                yoG = cursor.getInt(4);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return yoG;
    }

    //Update Student Data
    public boolean UpdateFirstName (String ID, String first) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.StudentsTable.COL_2, first);
        int rowsUpdated = db.update(StudentScheduleDB.StudentsTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }

    public boolean UpdateLastName (String ID, String last) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.StudentsTable.COL_3, last);
        int rowsUpdated = db.update(StudentScheduleDB.StudentsTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }

    public boolean UpdateYOG (String ID, int yog) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.StudentsTable.COL_4, yog);
        int rowsUpdated = db.update(StudentScheduleDB.StudentsTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }


    //Delete Student
    public boolean deleteStudent(String studentID) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(StudentScheduleDB.StudentsTable.TABLE_NAME, StudentScheduleDB.StudentsTable.COL_1 + " = ?",
                new String[] { studentID });
        return rowsDeleted > 0;
    }

    /*
     ************************************************************************************
     ****************************Schedule Data Methods***********************************
     ************************************************************************************
     */


    //Add Schedule
    public boolean CreateNewSchedule (String id, String A, String B, String C, String D, String E) {
        int studentID = Integer.parseInt(id);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentScheduleDB.ScheduleTable.COL_1, studentID);
        contentValues.put(StudentScheduleDB.ScheduleTable.COL_2, A);
        contentValues.put(StudentScheduleDB.ScheduleTable.COL_3, B);
        contentValues.put(StudentScheduleDB.ScheduleTable.COL_4, C);
        contentValues.put(StudentScheduleDB.ScheduleTable.COL_5, D);
        contentValues.put(StudentScheduleDB.ScheduleTable.COL_6, E);

        Long resultJ = db.insert(StudentScheduleDB.ScheduleTable.TABLE_NAME, null, contentValues);
        if(resultJ == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Get Schedule

    public String GetA(String ID) {
        int studentID = Integer.parseInt(ID);
        String aBlock = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.ScheduleTable.TABLE_NAME + " Where id = ?", new String[] {(ID)});
        if (cursor.moveToFirst()) {
            do {
                aBlock = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return aBlock;
    }

    public String GetB(String ID) {
        int studentID = Integer.parseInt(ID);
        String bBlock = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.ScheduleTable.TABLE_NAME + " Where id = ?", new String[]{(ID)});
        if (cursor.moveToFirst()) {
            do {
                bBlock = cursor.getString(3);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bBlock;
    }

    public String GetC(String ID) {
        int studentID = Integer.parseInt(ID);
        String cBlock = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.ScheduleTable.TABLE_NAME + " Where id = ?", new String[]{(ID)});
        if (cursor.moveToFirst()) {
            do {
                cBlock = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cBlock;
    }

    public String GetD(String ID) {
        int studentID = Integer.parseInt(ID);
        String dBlock = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.ScheduleTable.TABLE_NAME + " Where id = ?", new String[]{(ID)});
        if (cursor.moveToFirst()) {
            do {
                dBlock = cursor.getString(5);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dBlock;
    }

    public String GetE(String ID) {
        int studentID = Integer.parseInt(ID);
        String eBlock = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + StudentScheduleDB.ScheduleTable.TABLE_NAME + " Where id = ?", new String[]{(ID)});
        if (cursor.moveToFirst()) {
            do {
                eBlock = cursor.getString(6);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return eBlock;
    }

    //Update Schedule
    public boolean UpdateA (String ID, String A) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.ScheduleTable.COL_2, A);
        int rowsUpdated = db.update(StudentScheduleDB.ScheduleTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }

    public boolean UpdateB (String ID, String B) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.ScheduleTable.COL_3, B);
        int rowsUpdated = db.update(StudentScheduleDB.ScheduleTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdateC (String ID, String C) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.ScheduleTable.COL_4, C);
        int rowsUpdated = db.update(StudentScheduleDB.ScheduleTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdateD (String ID, String D) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.ScheduleTable.COL_5, D);
        int rowsUpdated = db.update(StudentScheduleDB.ScheduleTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdateE (String ID, String E) {
        int studentID = Integer.parseInt(ID);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentScheduleDB.ScheduleTable.COL_6, E);
        int rowsUpdated = db.update(StudentScheduleDB.ScheduleTable.TABLE_NAME, values, "_id = ?", new String[] {(ID)});
        return rowsUpdated > 0;
    }


    //Delete Schedule
    public boolean deleteSchedule(String studentID) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(StudentScheduleDB.ScheduleTable.TABLE_NAME, StudentScheduleDB.ScheduleTable.COL_1 + " = ?",
                new String[] { studentID });
        return rowsDeleted > 0;
    }



}



