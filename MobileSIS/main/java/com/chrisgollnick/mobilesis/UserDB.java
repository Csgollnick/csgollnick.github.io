package com.chrisgollnick.mobilesis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.Log;

public class UserDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final int VERSION = 1;


    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static final class UsersTable {
        public static final String TABLE_NAME = "users_table";
        public static final String COL_1 = "_id";
        public static final String COL_2 = "USERNAME";
        public static final String COL_3 = "PASSWORD";
        public static final String COL_4 = "ROLE";
        public static final String COL_5 = "FIRSTNAME";
        public static final String COL_6 = "LASTNAME";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UsersTable.TABLE_NAME + " (" +
                UsersTable.COL_1 + " INTEGER primary key, " +
                UsersTable.COL_2 + " TEXT, " +
                UsersTable.COL_3 + " TEXT, " +
                UsersTable.COL_4 + " TEXT, " +
                UsersTable.COL_5 + " TEXT, " +
                UsersTable.COL_6 + " TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("drop table if exists " + UsersTable.TABLE_NAME);
        onCreate(db);
    }




    /*
    * ***********************
    * Authentication Methods
    * ***********************
    * */
    //Method to check for username and password combinations for authentication

    public String checkUserPasswordRole(String username, String password) {

        String role ="";
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * From " + UsersTable.TABLE_NAME + " where username = ? and password = ?";
        Cursor cursorUP = db.rawQuery(sql, new String[]{username, password});
        if (cursorUP.getCount() > 0) {
            boolean auth = true;
            if(cursorUP.moveToFirst()) {
                do {
                    role = cursorUP.getString(4);
                } while (cursorUP.moveToNext());

            }

        } else {
            boolean auth = false;
            role = "INVALID";
        }
        return role;
    }

    /*
    * **********************
    * Creation Data Methods
    * **********************
    * */

    //Method to add to database
    public boolean insertData(Integer id, String firstName, String lastName, String password, String role) {
        SQLiteDatabase db = getWritableDatabase();

        char firstletter = firstName.charAt(0);
        String letter = String.valueOf(firstletter);
        String username = letter + lastName;

        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersTable.COL_1, id);
        contentValues.put(UsersTable.COL_2, username);
        contentValues.put(UsersTable.COL_3, password);
        contentValues.put(UsersTable.COL_4, role);
        contentValues.put(UsersTable.COL_5, firstName);
        contentValues.put(UsersTable.COL_6, lastName);


        long result = db.insert(UsersTable.TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Method to check for user ID existence
    public boolean checkUser(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME + " WHERE id = ?", new String[]{Integer.toString(id)});
        if (cursorUser.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CheckAdminRegisterCode (String code) {
        boolean found = false;
        String[] codes = {"YA9Z6MBPNJYP","W8X6ZBKUGXP7","9V76SY6F7RYG",
                "N974QGDYFW78","RXZQ7SYDMY8E","94H9UU664U4R",
                "3WKM2Y8EP8YE","C9BG2F9QPY44","SY5PJQFDEYMP",
                "QX3HX3NBDA3N","Z4QGDBZFYC3W","KA6RM84HAN2B","29Y96UT2B2RH",
                "H74LR8Y6P5ZM","BF2LUJXQ6VJH","VBDQPJ3KEU7A",
                "SJWMMUNP39LD","HHHA68M53988","5ABVSFLJYATD",
                "9FK62S25WLC9"};
        for(int i = 0; i < 19; i++) {
            if(codes[i] == code) {
                found = true;
                break;
            } else {
                found = false;
            }
        }
        return found;
    }


    /*
    *****************************
    * Get/View The Data Methods
    * ***************************
    * */
    //get firstname
    public String GetfirstName(int ID) {
        String fname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
        if (cursor.moveToFirst()) {
            do {
                fname = cursor.getString(5);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return fname;
    }
    //get lastname
    public String GetlastName(int ID) {
        String lname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
        if (cursor.moveToFirst()) {
            do {
                lname = cursor.getString(6);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lname;
    }

    //get username
    public String GetuserName(int ID) {
        String uname = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
        if (cursor.moveToFirst()) {
            do {
                uname = cursor.getString(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return uname;
    }

    //get role
    public String GetRole(int ID) {
        String role = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * From " + UsersTable.TABLE_NAME + " Where id = ?", new String[] {Integer.toString(ID)});
        if (cursor.moveToFirst()) {
            do {
                role = cursor.getString(5);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return role;
    }

    /*
    ************************
    * Update Data Methods
    * **********************
    * */
    public boolean UpdateFirstName (int ID, String firstName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersTable.COL_5, firstName);
        int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdateLastName (int ID, String lastName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersTable.COL_6, lastName);
        int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdateUserName (int ID, String userName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersTable.COL_2, userName);
        int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdatePassword (int ID, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersTable.COL_3, password);
        int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }
    public boolean UpdateRole (int ID, String role) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UsersTable.COL_4, role);
        int rowsUpdated = db.update(UsersTable.TABLE_NAME, values, "_id = ?", new String[] {Integer.toString(ID)});
        return rowsUpdated > 0;
    }

    /*
     * *****************
     * Delete by ID
     * *****************
     * */
    public boolean deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(UsersTable.TABLE_NAME, UsersTable.COL_1 + " = ?",
                new String[] { Integer.toString(id) });
        return rowsDeleted > 0;
    }

}