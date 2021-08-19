package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase mSqLiteDatabase;

    public static final String DATABASE_NAME = "ContactDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "ContactTable";

    public static final String KEY_USER_ID = " user_id ";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_PHONE = "user_no";
    public static final String KEY_USER_EMAIL = "user_email";
    public static final String KEY_USER_ORGANISATION = "user_organisation";
    public static final String KEY_USER_ADDRESS = "user_address";
    public static final String KEY_USER_ADDRESS1 = "user_address1";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACT_TABLE = " CREATE TABLE " + TABLE_NAME + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY , "
                + KEY_USER_NAME + " TEXT ,"
                + KEY_USER_PHONE + " TEXT ,"
                + KEY_USER_EMAIL + " TEXT ,"
                + KEY_USER_ORGANISATION + " TEXT ,"
                + KEY_USER_ADDRESS + " TEXT ,"
                + KEY_USER_ADDRESS1 + " TEXT " + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(String userName, String userMob, String userEmail, String userOrg, String userAdd, String userAdd1) {

        mSqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, userName);
        values.put(KEY_USER_PHONE, userMob);
        values.put(KEY_USER_EMAIL, userEmail);
        values.put(KEY_USER_ORGANISATION, userOrg);
        values.put(KEY_USER_ADDRESS, userAdd);
        values.put(KEY_USER_ADDRESS1, userAdd1);

        mSqLiteDatabase.insert(TABLE_NAME, null, values);
        mSqLiteDatabase.close();
    }

    public ArrayList<UserDetail> getAllContacts() {
        ArrayList<UserDetail> userDetailArrayList = new ArrayList<UserDetail>();
        mSqLiteDatabase = this.getReadableDatabase();


        Cursor mCursor = mSqLiteDatabase.rawQuery(" SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_USER_NAME, null);
//        String rawQuery = "SELECT * FROM ContactTable ORDER BY KEY_USER_NAME";
//        Log.d("rawQUERY", rawQuery);

        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                UserDetail userDetail = new UserDetail();
                userDetail.setUserID(mCursor.getInt(mCursor.getColumnIndex(KEY_USER_ID)));
                userDetail.setUserName(mCursor.getString(mCursor.getColumnIndex(KEY_USER_NAME)));
                userDetail.setUserMobile(mCursor.getString(mCursor.getColumnIndex(KEY_USER_PHONE)));
                userDetail.setUserEmail(mCursor.getString(mCursor.getColumnIndex(KEY_USER_EMAIL)));
                userDetail.setUserOrg(mCursor.getString(mCursor.getColumnIndex(KEY_USER_ORGANISATION)));
                userDetail.setUserAdd(mCursor.getString(mCursor.getColumnIndex(KEY_USER_ADDRESS)));
                userDetail.setUserAdd1(mCursor.getString(mCursor.getColumnIndex(KEY_USER_ADDRESS1)));

                userDetailArrayList.add(userDetail);
            } while (mCursor.moveToNext());

        }
        mSqLiteDatabase.close();
        return userDetailArrayList;
    }

    public void deleteContact(String userID) {

        mSqLiteDatabase = this.getWritableDatabase();

        mSqLiteDatabase.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " + KEY_USER_ID + " = " + userID);
        mSqLiteDatabase.close();
    }

    public void updateContact(int position, String username, String usermobile, String useremail, String userorg, String useraddress, String useraddress1) {
        mSqLiteDatabase = this.getWritableDatabase();

        String updateQuery = " UPDATE " + TABLE_NAME + " SET "
                + KEY_USER_NAME + " = " + username + "',"
                + KEY_USER_PHONE + " = " + usermobile + "',"
                + KEY_USER_EMAIL + " = " + useremail + "',"
                + KEY_USER_ORGANISATION + " = " + userorg + "',"
                + KEY_USER_ADDRESS + " = " + useraddress + "',"
                + KEY_USER_ADDRESS1 + " = " + useraddress1 + "' WHERE "
                + KEY_USER_ID + " = " + position;

        mSqLiteDatabase.execSQL(updateQuery);
        mSqLiteDatabase.close();
    }


}