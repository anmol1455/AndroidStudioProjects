package com.example.akshitavishwakarma.hostel_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabase mSqLiteDatabase;

    // database name and version
    public static final String DATABASE_NAME = "HostelDB";
    public static final int DATABASE_VERSION = 1;
    // database table name
    public static final String TABLE_NAME_STU = "StudentDetail";

    // database table name
    public static final String TABLE_NAME_FEES = "FeesDetail";

    // database table name
    public static final String TABLE_NAME_STAFF = "StaffDetail";

    // database table name
    public static final String TABLE_NAME_CNA = "AccountDetail";


    // table column names
    public static final String KEY_STUDENT_ID = "student_id";
    public static final String KEY_STUDENT_NAME = "student_name";
    public static final String KEY_STUDENT_DOB = "student_dob";
    public static final String KEY_STUDENT_ADDRESS = "student_address";
    public static final String KEY_STUDENT_MOBILE = "student_mobile";
    public static final String KEY_STUDENT_EMAIL = "student_email";
    public static final String KEY_STUDENT_JOININGDATE = "student_joiningdate";
    public static final String KEY_STUDENT_COLLEGENAME = "student_collegename";
    public static final String KEY_STUDENT_COURSE = "student_course";
    public static final String KEY_STUDENT_FNAME = "student_fname";
    public static final String KEY_STUDENT_FOCUUPATION = "student_focuupation";
    public static final String KEY_STUDENT_PADDRESS = "student_paddress";
    public static final String KEY_STUDENT_FCONTACT = "student_fcontact";

    // table column names
    public static final String KEY_STU_ID = "stu_Id";
    public static final String KEY_SUBMIT_DATE = "submit_Date";
    public static final String KEY_STU_NAME = "stu_Name";
    public static final String KEY_FEES_AMOUNT = "fees_Amount";
    public static final String KEY_RECEIVED_FEES = "received_Fees";
    public static final String KEY_DUE_FEES = "due_Fees";
    public static final String KEY_RECEIPT_NUMBER = "receipt_Number";
    public static final String KEY_RECEIVED_BY = "received_By";

    // table column names
    public static final String KEY_STAFF_ID = "staff_Id";
    public static final String KEY_STAFF_NAME = "staff_Name";
    public static final String KEY_STAFF_ADDRESS = "staff_Address";
    public static final String KEY_STAFF_CONTACT = "staff_Contact";
    public static final String KEY_STAFF_SALARY = "staff_Salary";


    // table column names
    public static final String KEY_ADMIN_ID = "admin_Id";
    public static final String KEY_FIRST_NAME = "first_Name";
    public static final String KEY_LAST_NAME = "last_Name";
    public static final String KEY_MOBILE_NUMBER = "mobile_Number";
    public static final String KEY_USER_ID = "user_Id";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CONFIRM_PASSWORD = "confirm_Password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_NAME_STU + "("
                + KEY_STUDENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_STUDENT_NAME + " TEXT,"
                + KEY_STUDENT_DOB + " TEXT,"
                + KEY_STUDENT_ADDRESS + " TEXT,"
                + KEY_STUDENT_MOBILE + " TEXT,"
                + KEY_STUDENT_EMAIL + " TEXT,"
                + KEY_STUDENT_JOININGDATE + " TEXT,"
                + KEY_STUDENT_COLLEGENAME + " TEXT,"
                + KEY_STUDENT_COURSE + " TEXT,"
                + KEY_STUDENT_FNAME + " TEXT,"
                + KEY_STUDENT_FOCUUPATION + " TEXT,"
                + KEY_STUDENT_PADDRESS + " TEXT,"
                + KEY_STUDENT_FCONTACT + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_STUDENT_TABLE);


        String CREATE_FEES_TABLE = "CREATE TABLE " + TABLE_NAME_FEES + "("
                + KEY_STU_ID + " INTEGER PRIMARY KEY,"
                + KEY_SUBMIT_DATE + " TEXT,"
                + KEY_STU_NAME + " TEXT,"
                + KEY_FEES_AMOUNT + " TEXT,"
                + KEY_RECEIVED_FEES + " TEXT,"
                + KEY_DUE_FEES + " TEXT,"
                + KEY_RECEIPT_NUMBER + " TEXT,"
                + KEY_RECEIVED_BY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_FEES_TABLE);


        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABLE_NAME_STAFF + "("
                + KEY_STAFF_ID + " INTEGER PRIMARY KEY,"
                + KEY_STAFF_NAME + " TEXT,"
                + KEY_STAFF_ADDRESS + " TEXT,"
                + KEY_STAFF_CONTACT + " TEXT,"
                + KEY_STAFF_SALARY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_STAFF_TABLE);


        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_NAME_CNA + "("
                + KEY_ADMIN_ID + " INTEGER PRIMARY KEY,"
                + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT,"
                + KEY_MOBILE_NUMBER + " TEXT,"
                + KEY_USER_ID + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_CONFIRM_PASSWORD + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    /**
     * Method to add new contact in database
     */

    public void addstudent(String studentName, String studentDob, String studentAddress, String studentMobile, String studentEmail, String studentJoiiningdate, String studentCollegename, String studentCourse, String studentFname, String studentFocuupation, String studentPaddress, String studentFcontact) {

        mSqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_STUDENT_NAME, studentName);
        values.put(KEY_STUDENT_DOB, studentDob);
        values.put(KEY_STUDENT_ADDRESS, studentAddress);
        values.put(KEY_STUDENT_MOBILE, studentMobile);
        values.put(KEY_STUDENT_EMAIL, studentEmail);
        values.put(KEY_STUDENT_JOININGDATE, studentJoiiningdate);
        values.put(KEY_STUDENT_COLLEGENAME, studentCollegename);
        values.put(KEY_STUDENT_COURSE, studentCourse);
        values.put(KEY_STUDENT_FNAME, studentFname);
        values.put(KEY_STUDENT_FOCUUPATION, studentFocuupation);
        values.put(KEY_STUDENT_PADDRESS, studentPaddress);
        values.put(KEY_STUDENT_FCONTACT, studentFcontact);


        mSqLiteDatabase.insert(TABLE_NAME_STU, null, values);
        mSqLiteDatabase.close();
    }

    /**
     * Method to add new contact in database
     */

    public void addfees(String submit_Date, String stu_Name, String fees_Amount, String received_Fees, String due_Fees, String receipt_Number, String received_By) {

        mSqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SUBMIT_DATE, submit_Date);
        values.put(KEY_STU_NAME, stu_Name);
        values.put(KEY_FEES_AMOUNT, fees_Amount);
        values.put(KEY_RECEIVED_FEES, received_Fees);
        values.put(KEY_DUE_FEES, due_Fees);
        values.put(KEY_RECEIPT_NUMBER, receipt_Number);
        values.put(KEY_RECEIVED_BY, received_By);


        mSqLiteDatabase.insert(TABLE_NAME_FEES, null, values);
        mSqLiteDatabase.close();
    }


    /**
     * Method to add new contact in database
     */

    public void addstaff(String staff_Name, String staff_Address, String staff_Contact, String staff_Salary) {

        mSqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_STAFF_NAME, staff_Name);
        values.put(KEY_STAFF_ADDRESS, staff_Address);
        values.put(KEY_STAFF_CONTACT, staff_Contact);
        values.put(KEY_STAFF_SALARY, staff_Salary);

        mSqLiteDatabase.insert(TABLE_NAME_STAFF, null, values);
        mSqLiteDatabase.close();
    }


    /**
     * Method to CREATE NEW ACCOUNT in database
     */

    public void createNewAccount(String first_Name, String last_Name, String mobile_Number, String user_Id, String password, String confirm_Password) {

        mSqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_FIRST_NAME, first_Name);
        values.put(KEY_LAST_NAME, last_Name);
        values.put(KEY_MOBILE_NUMBER, mobile_Number);
        values.put(KEY_USER_ID, user_Id);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_CONFIRM_PASSWORD, confirm_Password);

        mSqLiteDatabase.insert(TABLE_NAME_CNA, null, values);
        mSqLiteDatabase.close();
    }

    public int updateData(String tblnm, String col[], String val[], String wherefld, String clause[]) {
        mSqLiteDatabase = this.getWritableDatabase();

        int i = 0;
        int count = 0;
        String qry = "update " + tblnm + " set ";
        String collect = "";
        for (i = 0; i < col.length - 1; i++) {
            collect = collect + col[i] + "='" + val[i] + "',";
            //ContentValues contentValues = new ContentValues();
            //contentValues.put(col[i],val[i]);


        }
        collect = collect + col[i] + "='" + val[i] + "' where " + wherefld + "='" + clause[0] + "'";
        try {
            mSqLiteDatabase.execSQL(qry + collect);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public Cursor chklogin(String tblnm, String wherefld[], String clause[]) {
        mSqLiteDatabase = this.getWritableDatabase();
        String qry = "select * from " + tblnm + " where " + wherefld[0] + "=? and " + wherefld[1] + "=?";
        Cursor c = null;

        try {
            c = mSqLiteDatabase.rawQuery(qry, clause);
        } catch (Exception e) {
        }

        return c;
    }

    /**
     * Method to get all contacts from database
     */

    public ArrayList<StudentList> getAllStudentList() {

        ArrayList<StudentList> StudentDetailArrayList = new ArrayList<StudentList>();

        mSqLiteDatabase = this.getReadableDatabase();

        Cursor mCursor = mSqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_STU + " ORDER BY " + KEY_STUDENT_NAME, null);

//		String rawQuery="SELECT * FROM ContactTable ORDER BY KEY_USER_NAME";
//       Log.d("rawQuery", rawQuery);


        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                StudentList studentList = new StudentList();

                studentList.setStudentId(mCursor.getInt(mCursor.getColumnIndex(KEY_STUDENT_ID)));
                studentList.setStudentName(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_NAME)));
                studentList.setStudentDob(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_DOB)));
                studentList.setStudentAddress(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_ADDRESS)));
                studentList.setStudentMobile(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_MOBILE)));
                studentList.setStudentEmail(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_EMAIL)));
                studentList.setStudentJoiningdate(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_JOININGDATE)));
                studentList.setStudentCollegename(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_COLLEGENAME)));
                studentList.setStudentCourse(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_COURSE)));
                studentList.setStudentFname(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_FNAME)));
                studentList.setStudentFoccupation(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_FOCUUPATION)));
                studentList.setStudentPaddress(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_PADDRESS)));
                studentList.setStudentFcontact(mCursor.getString(mCursor.getColumnIndex(KEY_STUDENT_FCONTACT)));

                StudentDetailArrayList.add(studentList);

            } while (mCursor.moveToNext());

        }
        mSqLiteDatabase.close();
        return StudentDetailArrayList;
    }


    /**
     * Method to get all contacts from database
     */

    public ArrayList<FeesList> getAllFeesList() {

        ArrayList<FeesList> FeesDetailArrayList = new ArrayList<FeesList>();

        mSqLiteDatabase = this.getReadableDatabase();

        Cursor mCursor = mSqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_FEES + " ORDER BY " + KEY_STU_NAME, null);


        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                FeesList feesList = new FeesList();


                feesList.setsubmit_Date(mCursor.getString(mCursor.getColumnIndex(KEY_SUBMIT_DATE)));
                feesList.setstu_Name(mCursor.getString(mCursor.getColumnIndex(KEY_STU_NAME)));
                feesList.setfees_Amount(mCursor.getString(mCursor.getColumnIndex(KEY_FEES_AMOUNT)));
                feesList.setreceived_Fees(mCursor.getString(mCursor.getColumnIndex(KEY_RECEIVED_FEES)));
                feesList.setdue_Fees(mCursor.getString(mCursor.getColumnIndex(KEY_DUE_FEES)));
                feesList.setreceipt_Number(mCursor.getString(mCursor.getColumnIndex(KEY_RECEIPT_NUMBER)));
                feesList.setreceived_By(mCursor.getString(mCursor.getColumnIndex(KEY_RECEIVED_BY)));

                FeesDetailArrayList.add(feesList);

            } while (mCursor.moveToNext());

        }
        mSqLiteDatabase.close();
        return FeesDetailArrayList;
    }


    /**
     * Method to get all contacts from database
     */

    public ArrayList<StaffList> getAllStaffList() {

        ArrayList<StaffList> StaffDetailArrayList = new ArrayList<StaffList>();

        mSqLiteDatabase = this.getReadableDatabase();

        Cursor mCursor = mSqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_STAFF + " ORDER BY " + KEY_STAFF_NAME, null);


        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                StaffList staffList = new StaffList();


                staffList.setstaff_Name(mCursor.getString(mCursor.getColumnIndex(KEY_STAFF_NAME)));
                staffList.setstaff_Address(mCursor.getString(mCursor.getColumnIndex(KEY_STAFF_ADDRESS)));
                staffList.setstaff_Contact(mCursor.getString(mCursor.getColumnIndex(KEY_STAFF_CONTACT)));
                staffList.setstaff_Salary(mCursor.getString(mCursor.getColumnIndex(KEY_STAFF_SALARY)));


                StaffDetailArrayList.add(staffList);

            } while (mCursor.moveToNext());

        }
        mSqLiteDatabase.close();
        return StaffDetailArrayList;
    }


    /**
     * Method to get all contacts from database
     */

    public ArrayList<LoginList> getAllLoginList() {

        ArrayList<LoginList> AccountDetailArrayList = new ArrayList<LoginList>();

        mSqLiteDatabase = this.getReadableDatabase();

        Cursor mCursor = mSqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME_CNA + " ORDER BY " + KEY_FIRST_NAME, null);


        if (mCursor != null && mCursor.moveToFirst()) {
            do {
                LoginList loginList = new LoginList();


                loginList.setfirst_Name(mCursor.getString(mCursor.getColumnIndex(KEY_FIRST_NAME)));
                loginList.setlast_Name(mCursor.getString(mCursor.getColumnIndex(KEY_LAST_NAME)));
                loginList.setmobile_Number(mCursor.getString(mCursor.getColumnIndex(KEY_MOBILE_NUMBER)));
                loginList.setuser_Id(mCursor.getString(mCursor.getColumnIndex(KEY_USER_ID)));
                loginList.setpassword(mCursor.getString(mCursor.getColumnIndex(KEY_PASSWORD)));
                loginList.setconfirm_Password(mCursor.getString(mCursor.getColumnIndex(KEY_CONFIRM_PASSWORD)));


                AccountDetailArrayList.add(loginList);

            } while (mCursor.moveToNext());

        }
        mSqLiteDatabase.close();
        return AccountDetailArrayList;
    }


    /**
     * Method to delete contact from Database
     */
    public void deleteStudent(int studentId) {

        mSqLiteDatabase = this.getWritableDatabase();

        mSqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME_STU + " WHERE " + KEY_STUDENT_ID + "=" + studentId);
        mSqLiteDatabase.close();

    }


    /**
     * Method to delete contact from Database
     */
    public void deletefees(int stu_Id) {

        mSqLiteDatabase = this.getWritableDatabase();

        mSqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME_FEES + " WHERE " + KEY_STU_ID + "=" + stu_Id);
        mSqLiteDatabase.close();

    }


    /**
     * Method to delete contact from Database
     */
    public void deletestaff(int staff_Id) {

        mSqLiteDatabase = this.getWritableDatabase();

        mSqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME_STAFF + " WHERE " + KEY_STAFF_ID + "=" + staff_Id);
        mSqLiteDatabase.close();

    }


    /**
     * Method to update contact in database
     */
    public void updateStudent(int position, String studentName, String studentDob, String studentAddress, String studentMobile, String studentEmail, String studentJoiiningdate, String studentCollegename, String studentCourse, String studentFname, String studentFocuupation, String studentPaddress, String studentFcontact) {

        mSqLiteDatabase = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME_STU + " SET "
                + KEY_STUDENT_NAME + " = '" + studentName + "',"
                + KEY_STUDENT_DOB + " = '" + studentDob + "',"
                + KEY_STUDENT_ADDRESS + " = '" + studentAddress + "',"
                + KEY_STUDENT_MOBILE + " = '" + studentMobile + "',"
                + KEY_STUDENT_EMAIL + " = '" + studentEmail + "',"
                + KEY_STUDENT_JOININGDATE + " = '" + studentJoiiningdate + "',"
                + KEY_STUDENT_COLLEGENAME + " = '" + studentCollegename + "',"
                + KEY_STUDENT_COURSE + " = '" + studentCourse + "',"
                + KEY_STUDENT_FNAME + " = '" + studentFname + "',"
                + KEY_STUDENT_FOCUUPATION + " = '" + studentFocuupation + "',"
                + KEY_STUDENT_PADDRESS + " = '" + studentPaddress + "',"
                + KEY_STUDENT_FCONTACT + " = '" + studentFcontact + "' WHERE "
                + KEY_STUDENT_ID + " = " + position;

        mSqLiteDatabase.execSQL(updateQuery);
        mSqLiteDatabase.close();

    }

    /**
     * Method to update contact in database
     */
    public void updatefees(int position, String submit_Date, String stu_Name, String fees_Amount, String received_Fees, String due_Fees, String receipt_Number, String received_By) {

        mSqLiteDatabase = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME_FEES + " SET "
                + KEY_SUBMIT_DATE + " = '" + submit_Date + "',"
                + KEY_STU_NAME + " = '" + stu_Name + "',"
                + KEY_FEES_AMOUNT + " = '" + fees_Amount + "',"
                + KEY_RECEIVED_FEES + " = '" + received_Fees + "',"
                + KEY_DUE_FEES + " = '" + due_Fees + "',"
                + KEY_RECEIPT_NUMBER + " = '" + receipt_Number + "',"
                + KEY_RECEIVED_BY + " = '" + received_By + "' WHERE "
                + KEY_STU_ID + " = " + position;

        mSqLiteDatabase.execSQL(updateQuery);
        mSqLiteDatabase.close();

    }


    /**
     * Method to update contact in database
     */
    public void updatestaff(int position, String staff_Name, String staff_Address, String staff_Contact, String staff_Salary) {

        mSqLiteDatabase = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME_STAFF + " SET "
                + KEY_STAFF_NAME + " = '" + staff_Name + "',"
                + KEY_STAFF_ADDRESS + " = '" + staff_Address + "',"
                + KEY_STAFF_CONTACT + " = '" + staff_Contact + "',"
                + KEY_STAFF_SALARY + " = '" + staff_Salary + "' WHERE "
                + KEY_STAFF_ID + " = " + position;

        mSqLiteDatabase.execSQL(updateQuery);
        mSqLiteDatabase.close();

    }
}


