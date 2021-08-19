package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ManageStudentDetail extends AppCompatActivity implements View.OnClickListener {

    ImageButton fab_stu;
    EditText search;
    private Context mContext;
    private ListView lv_StudentView;
    private ListViewAdapter mListViewAdapter;
    private ArrayList<StudentList> StudentDetailArrayList = new ArrayList<StudentList>();
    private AlertDialog builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student_detail2);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Manage Students Detail");

        // initialising context
        this.mContext = this;

        lv_StudentView = (ListView) findViewById(R.id.lv_StudentView);
        search = (EditText) findViewById(R.id.search);

        // getting list from db
        StudentDetailArrayList = new DatabaseHelper(mContext).getAllStudentList();

        // initialising list adapter
        mListViewAdapter = new ListViewAdapter(this, R.layout.activity_student_detail, StudentDetailArrayList);

        // setting adapter on listview
        lv_StudentView.setAdapter(mListViewAdapter);

        // registering for context menu
        registerForContextMenu(lv_StudentView);

        fab_stu = (ImageButton) findViewById(R.id.fab_stu);
        fab_stu.setOnClickListener(this);

        lv_StudentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                try {


                    Intent intent = new Intent(mContext, StudentDetail.class);
                    intent.putExtra("studentName", StudentDetailArrayList.get(position).getStudentName());
                    intent.putExtra("studentDob", StudentDetailArrayList.get(position).getStudentDob());
                    intent.putExtra("studentAddress", StudentDetailArrayList.get(position).getStudentAddress());
                    intent.putExtra("studentMobile", StudentDetailArrayList.get(position).getStudentMobile());
                    intent.putExtra("studentEmail", StudentDetailArrayList.get(position).getStudentEmail());
                    intent.putExtra("studentJoiningdate", StudentDetailArrayList.get(position).getStudentJoiningdate());
                    intent.putExtra("studentCollegename", StudentDetailArrayList.get(position).getStudentCollegename());
                    intent.putExtra("studentCourse", StudentDetailArrayList.get(position).getStudentCourse());
                    intent.putExtra("studentFname", StudentDetailArrayList.get(position).getStudentFname());
                    intent.putExtra("studentFoccupation", StudentDetailArrayList.get(position).getStudentFoccupation());
                    intent.putExtra("studentPaddress", StudentDetailArrayList.get(position).getStudentPaddress());
                    intent.putExtra("studentFcontact", StudentDetailArrayList.get(position).getStudentFcontact());

                    mContext.startActivity(intent);
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == fab_stu) {
            Intent intent = new Intent(getApplicationContext(), student.class);
            startActivity(intent);
        }

    }

    // creating context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menustu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        switch (item.getItemId()) {
            case R.id.i_delete:

                // deleting contact from DB
                databaseHelper.deleteStudent(StudentDetailArrayList.get(info.position).getStudentId());
                StudentDetailArrayList.remove(info.position);
                mListViewAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.i_update:

                // updaing contact into DB
                Intent intent = new Intent(this, student.class);
                intent.putExtra("position", StudentDetailArrayList.get(info.position).getStudentId());
                intent.putExtra("studentName", StudentDetailArrayList.get(info.position).getStudentName());
                intent.putExtra("studentDob", StudentDetailArrayList.get(info.position).getStudentDob());
                intent.putExtra("studentAddress", StudentDetailArrayList.get(info.position).getStudentAddress());
                intent.putExtra("studentMobile", StudentDetailArrayList.get(info.position).getStudentMobile());
                intent.putExtra("studentEmail", StudentDetailArrayList.get(info.position).getStudentEmail());
                intent.putExtra("studentJoiningdate", StudentDetailArrayList.get(info.position).getStudentJoiningdate());
                intent.putExtra("studentCollegename", StudentDetailArrayList.get(info.position).getStudentCollegename());
                intent.putExtra("studentCourse", StudentDetailArrayList.get(info.position).getStudentCourse());
                intent.putExtra("studentFname", StudentDetailArrayList.get(info.position).getStudentFname());
                intent.putExtra("studentFoccuption", StudentDetailArrayList.get(info.position).getStudentFoccupation());
                intent.putExtra("studentPaddress", StudentDetailArrayList.get(info.position).getStudentPaddress());
                intent.putExtra("studentFcontact", StudentDetailArrayList.get(info.position).getStudentFcontact());

                startActivity(intent);
                finish();
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    public static class ListViewAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private ArrayList<StudentList> StudentDetailArrayList = null;
        private ArrayList<StudentList> newStudentDetailList;

        public ListViewAdapter(Context context, int resource, ArrayList<StudentList> StudentList) {

            this.mContext = context;
            this.mLayoutInflater = LayoutInflater.from(mContext);
            this.newStudentDetailList = new ArrayList<StudentList>();
            this.newStudentDetailList.addAll(StudentList);


//            Collections.sort(userDetails, new Comparator<UserDetail>() {
//                @Override
//                public int compare(UserDetail o1, UserDetail o2) {
//                    return o1.getUserName().compareTo(o2.getUserName());
//                }
//            });

            this.StudentDetailArrayList = StudentList;
        }

        @Override
        public int getCount() {
            return StudentDetailArrayList.size();
        }

        @Override
        public StudentList getItem(int position) {
            return StudentDetailArrayList.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ManageStudentDetail.ViewHolder mViewHolder;
            if (convertView == null) {

                mViewHolder = new ManageStudentDetail.ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.custom_list_student, null);

                // finding views
                mViewHolder.tv_Letter = (TextView) convertView.findViewById(R.id.tv_letter);
                mViewHolder.tv_student_name = (TextView) convertView.findViewById(R.id.tv_student_name);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ManageStudentDetail.ViewHolder) convertView.getTag();
            }


            // setting data into views
            String letter = "" + StudentDetailArrayList.get(position).getStudentName().charAt(0);
            mViewHolder.tv_Letter.setText(letter);

            Drawable drawable = mContext.getResources().getDrawable(R.drawable.rounded_circle);
            drawable.setColorFilter(mContext.getResources().getColor(setLetterColor(letter)), PorterDuff.Mode.SRC_ATOP);
            mViewHolder.tv_Letter.setBackgroundDrawable(drawable);

            // setting data into views
            mViewHolder.tv_Letter.setText("" + StudentDetailArrayList.get(position).getStudentName().charAt(0));
            mViewHolder.tv_student_name.setText(StudentDetailArrayList.get(position).getStudentName());


            return convertView;
        }

        /**
         * Method for filtering data
         */
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            StudentDetailArrayList.clear();
            if (charText.length() == 0) {
                StudentDetailArrayList.addAll(newStudentDetailList);
            } else {
                for (StudentList studentList : newStudentDetailList) {
                    if (studentList.getStudentName().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        StudentDetailArrayList.add(studentList);
                    }

                }
                notifyDataSetChanged();
            }

        }
    }

    public static class ViewHolder {
        public TextView tv_Letter;
        public TextView tv_student_name;
    }


    /** setting color of alphabets according to text */
    private static int setLetterColor(String letter) {

        if (letter.equalsIgnoreCase("A"))
            return R.color.aplha_A;
        else if (letter.equalsIgnoreCase("B"))
            return R.color.aplha_B;
        else if (letter.equalsIgnoreCase("C"))
            return R.color.aplha_C;
        else if (letter.equalsIgnoreCase("D"))
            return R.color.aplha_D;
        else if (letter.equalsIgnoreCase("E"))
            return R.color.aplha_E;
        else if (letter.equalsIgnoreCase("F"))
            return R.color.aplha_F;
        else if (letter.equalsIgnoreCase("G"))
            return R.color.aplha_G;
        else if (letter.equalsIgnoreCase("H"))
            return R.color.aplha_H;
        else if (letter.equalsIgnoreCase("I"))
            return R.color.aplha_I;
        else if (letter.equalsIgnoreCase("J"))
            return R.color.aplha_J;
        else if (letter.equalsIgnoreCase("K"))
            return R.color.aplha_K;
        else if (letter.equalsIgnoreCase("L"))
            return R.color.aplha_L;
        else if (letter.equalsIgnoreCase("M"))
            return R.color.aplha_M;
        else if (letter.equalsIgnoreCase("N"))
            return R.color.aplha_N;
        else if (letter.equalsIgnoreCase("O"))
            return R.color.aplha_O;
        else if (letter.equalsIgnoreCase("P"))
            return R.color.aplha_P;
        else if (letter.equalsIgnoreCase("Q"))
            return R.color.aplha_Q;
        else if (letter.equalsIgnoreCase("R"))
            return R.color.aplha_R;
        else if (letter.equalsIgnoreCase("S"))
            return R.color.aplha_S;
        else if (letter.equalsIgnoreCase("T"))
            return R.color.aplha_T;
        else if (letter.equalsIgnoreCase("U"))
            return R.color.aplha_U;
        else if (letter.equalsIgnoreCase("U"))
            return R.color.aplha_V;
        else if (letter.equalsIgnoreCase("W"))
            return R.color.aplha_W;
        else if (letter.equalsIgnoreCase("X"))
            return R.color.aplha_X;
        else if (letter.equalsIgnoreCase("Y"))
            return R.color.aplha_Y;
        else if (letter.equalsIgnoreCase("Z"))
            return R.color.aplha_Z;
        else
            return R.color.aplha_Z;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return true;
        }
    }
}