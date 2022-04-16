package com.chrisgollnick.mobilesis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassDB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "class.db";
    public static final int VERSION = 1;

    public ClassDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    public static final class ClassTable {
        public static final String TABLE_NAME = "class_table";
        public static final String COL_1 = "_id";
        public static final String COL_2 = "ClassID";
        public static final String COL_3 = "ClassName";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ClassTable.TABLE_NAME + " (" +
                ClassTable.COL_1 + " INTEGER primary key autoincrement, " +
                ClassTable.COL_2 + " TEXT, " +
                ClassTable.COL_3 + " TEXT) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + ClassTable.TABLE_NAME);
            onCreate(db);
    }

    public boolean AddClass(String classid, String classname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(ClassTable.COL_2,classid);
        contentvalues.put(ClassTable.COL_3, classname);

        long result = db.insert(ClassTable.TABLE_NAME,null,contentvalues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String GetClass(String ID) {
        String classID = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + ClassDB.ClassTable.TABLE_NAME + " Where id = ?", new String[] {(ID)});
        if (cursor.moveToFirst()) {
            do {
                classID = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return classID;
    }

    public boolean UpdateClassID (int ID, String cID) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassDB.ClassTable.COL_2, cID);
        int rowsUpdated = db.update(ClassDB.ClassTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }

    public boolean UpdateClassName (int ID, String cName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassDB.ClassTable.COL_3, cName);
        int rowsUpdated = db.update(ClassDB.ClassTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }

    public boolean RemoveClass(String classid) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(ClassTable.TABLE_NAME, ClassTable.COL_2 + " = ?",
                new String[] { classid });
        return rowsDeleted > 0;
    }



}
