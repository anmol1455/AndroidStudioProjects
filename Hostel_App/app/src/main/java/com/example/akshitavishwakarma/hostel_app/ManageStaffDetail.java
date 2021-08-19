package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ManageStaffDetail extends AppCompatActivity implements View.OnClickListener {

    ImageButton bt_staff_detail;
    private Context mcontext;
    private ListView lv_Staff;
    EditText search;
    private ManageStaffDetail.ListViewAdapter mListViewAdapter;
    private ArrayList<StaffList> StaffDetailArrayList = new ArrayList<StaffList>();
    private AlertDialog builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_staff_detail);

        // initialising context
        this.mcontext = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Manage Staffs Detail");

        lv_Staff = (ListView) findViewById(R.id.lv_Staff);
        search = (EditText) findViewById(R.id.search);

        // getting list from db

        StaffDetailArrayList = new DatabaseHelper(mcontext).getAllStaffList();

        // initialising list adapter
        mListViewAdapter = new ManageStaffDetail.ListViewAdapter(this, R.layout.activity_staff_detail, StaffDetailArrayList);

        // setting adapter on listview
        lv_Staff.setAdapter(mListViewAdapter);

        // registering for context menu
        registerForContextMenu(lv_Staff);

        bt_staff_detail = (ImageButton)findViewById(R.id.bt_staff_detail);

        bt_staff_detail.setOnClickListener(this);

        lv_Staff.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                try {


                    Intent intent = new Intent(mcontext, StaffDetail.class);
                    intent.putExtra("staff_Name", StaffDetailArrayList.get(position).getstaff_Name());
                    intent.putExtra("staff_Address", StaffDetailArrayList.get(position).getstaff_Address());
                    intent.putExtra("staff_Contact",StaffDetailArrayList.get(position).getstaff_Contact());
                    intent.putExtra("staff_Salary", StaffDetailArrayList.get(position).getstaff_Salary());

                    mcontext.startActivity(intent);


                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        });


    }


    @Override
    public void onClick(View v) {


        if (v == bt_staff_detail) {
            Intent intent = new Intent(this, staff.class);
            startActivity(intent);
        }
    }





    // creating context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menustaff, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DatabaseHelper databaseHelper= new DatabaseHelper(this);

        switch (item.getItemId()) {
            case R.id.staff_delete:

                // deleting contact from DB
                databaseHelper.deletestaff(StaffDetailArrayList.get(info.position).getstaff_Id());
                StaffDetailArrayList.remove(info.position);
                mListViewAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.staff_update:

                // updaing contact into DB
                Intent intent = new Intent(this, staff.class);
                intent.putExtra("position", StaffDetailArrayList.get(info.position).getstaff_Id());
                intent.putExtra("staff_Name", StaffDetailArrayList.get(info.position).getstaff_Name());
                intent.putExtra("staff_Address",  StaffDetailArrayList.get(info.position).getstaff_Address());
                intent.putExtra("staff_Contact", StaffDetailArrayList.get(info.position).getstaff_Contact());
                intent.putExtra("staff_Salary",  StaffDetailArrayList.get(info.position).getstaff_Salary());

                startActivity(intent);
                finish();
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    public static class ListViewAdapter extends BaseAdapter {

        private Context mcontext;
        private LayoutInflater mLayoutInflater;
        private ArrayList<StaffList> StaffDetailArrayList = null;
        private ArrayList<StaffList> newStaffDetailList;

        public ListViewAdapter(Context context, int resource, ArrayList<StaffList> StaffList) {

            this.mcontext = context;
            this.mLayoutInflater = LayoutInflater.from(mcontext);
            this.newStaffDetailList = new ArrayList<StaffList>();
            this.newStaffDetailList.addAll(StaffList);


            this.StaffDetailArrayList = StaffList;
        }

        @Override
        public int getCount() {
            return StaffDetailArrayList.size();
        }

        @Override
        public StaffList getItem(int position) {
            return StaffDetailArrayList.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ManageStaffDetail.ViewHolder mViewHolder;
            if (convertView == null) {

                mViewHolder = new ManageStaffDetail.ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.custom_list_staff, null);

                // finding views
                mViewHolder.tv_Letter_staff = (TextView) convertView.findViewById(R.id.tv_letter_staff);
                mViewHolder.tv_staff_name = (TextView) convertView.findViewById(R.id.tv_staff_name);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ManageStaffDetail.ViewHolder) convertView.getTag();
            }


            // setting data into views
            String letter = "" +StaffDetailArrayList.get(position).getstaff_Name().charAt(0);
            mViewHolder.tv_Letter_staff.setText(letter);

            Drawable drawable =mcontext.getResources().getDrawable(R.drawable.rounded_circle);
            drawable.setColorFilter(mcontext.getResources().getColor(setLetterColor(letter)), PorterDuff.Mode.SRC_ATOP);
            mViewHolder.tv_Letter_staff.setBackgroundDrawable(drawable);

            // setting data into views
            mViewHolder.tv_Letter_staff.setText("" + StaffDetailArrayList.get(position).getstaff_Name().charAt(0));
            mViewHolder.tv_staff_name.setText(StaffDetailArrayList.get(position).getstaff_Name());


            return convertView;
        }

        /**
         * Method for filtering data
         */
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            StaffDetailArrayList.clear();
            if (charText.length() == 0) {
                StaffDetailArrayList.addAll(newStaffDetailList);
            } else {
                for (StaffList staffList : newStaffDetailList) {
                    if (staffList.getstaff_Name().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        StaffDetailArrayList.add(staffList);
                    }

                }
                notifyDataSetChanged();
            }

        }
    }

    public static class ViewHolder {
        public TextView tv_Letter_staff;
        public TextView tv_staff_name;
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