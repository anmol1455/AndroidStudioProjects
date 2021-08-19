package com.example.saurabhjain.contactdemoapp;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public SQLiteDatabase mSqLiteDatabase;
	
	// database name and version
	public static final String DATABASE_NAME = "ContactDB";
	public static final int DATABASE_VERSION = 1;
	// database table name
	public static final String TABLE_NAME = "ContactTable";

	// table column names
	public static final String KEY_USRE_ID = "user_id";
	public static final String KEY_USER_NAME = "user_name";
	public static final String KEY_USER_MOBILE = "user_mobile";
	public static final String KEY_USER_EMAIL = "user_email";
	public static final String KEY_USER_DOB = "user_dob";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME , null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
				+ KEY_USRE_ID + " INTEGER PRIMARY KEY,"
				+ KEY_USER_NAME + " TEXT,"
				+ KEY_USER_MOBILE + " TEXT,"
				+ KEY_USER_EMAIL + " TEXT,"
				+ KEY_USER_DOB + " TEXT" +")";
		sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) 
	{

	}

	/**
	 *  Method to add new contact in database
	 * */
	
	public void addContact(String userName, String userMobile, String userEmail, String userDob) {

		mSqLiteDatabase = this.getWritableDatabase(); 

		ContentValues values = new ContentValues();  
		values.put(KEY_USER_NAME, userName);
		values.put(KEY_USER_MOBILE, userMobile);
		values.put(KEY_USER_EMAIL, userEmail);
		values.put(KEY_USER_DOB, userDob);

		mSqLiteDatabase.insert(TABLE_NAME, null, values);
		mSqLiteDatabase.close();
	}

	/**
	 * Method to get all contacts from database
	 * */
	
	public ArrayList<UserDetail> getAllContacts() {

		ArrayList<UserDetail> userDetailArrayList = new ArrayList<UserDetail>();

		mSqLiteDatabase = this.getReadableDatabase();

		Cursor mCursor =  mSqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_USER_NAME,  null );

//		String rawQuery="SELECT * FROM ContactTable ORDER BY KEY_USER_NAME";
//       Log.d("rawQuery", rawQuery);
        if (mCursor != null && mCursor.moveToFirst()) {
			do {
				UserDetail userDetail=new UserDetail();
				userDetail.setUserID(mCursor.getInt(mCursor.getColumnIndex(KEY_USRE_ID)));
				userDetail.setUserName(mCursor.getString(mCursor.getColumnIndex(KEY_USER_NAME)));
				userDetail.setUserMobile(mCursor.getString(mCursor.getColumnIndex(KEY_USER_MOBILE)));
				userDetail.setUserEmail(mCursor.getString(mCursor.getColumnIndex(KEY_USER_EMAIL)));
				userDetail.setUserDOB(mCursor.getString(mCursor.getColumnIndex(KEY_USER_DOB)));

				userDetailArrayList.add(userDetail);

			} while(mCursor.moveToNext());

		}
		mSqLiteDatabase.close();
		return userDetailArrayList;
	}

	/**
	 * Method to delete contact from Database
	 * */
	public void deleteContact(int userID) {

		mSqLiteDatabase = this.getWritableDatabase(); 

		mSqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_USRE_ID + "=" + userID); 
		mSqLiteDatabase.close();

	}

	/**
	 * Method to update contact in database
	 * */
	public void updateContact(int position, String username, String userEmail, String userNumber, String userDOB) {

		mSqLiteDatabase=this.getWritableDatabase();

		String updateQuery = "UPDATE " + TABLE_NAME +" SET "
				+ KEY_USER_NAME + " = '"+ username +"'," 
				+ KEY_USER_MOBILE + " = '" + userNumber +"',"
				+ KEY_USER_EMAIL + " = '" + userEmail + "',"
				+ KEY_USER_DOB + " = '" + userDOB + "' WHERE " 
				+ KEY_USRE_ID + " = " + position;

		mSqLiteDatabase.execSQL(updateQuery);
		mSqLiteDatabase.close();

	}
}