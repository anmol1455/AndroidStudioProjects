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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ManageFeesDetail extends AppCompatActivity implements View.OnClickListener {

    ImageButton bt_fees_add;
   private Context mcontext;
    private ListView lv_Fees;
    EditText search;
    private ManageFeesDetail.ListViewAdapter mListViewAdapter;
    private ArrayList<FeesList> FeesDetailArrayList = new ArrayList<FeesList>();
    private AlertDialog builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_fees_detail);

        // initialising context
        this.mcontext = this;


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Manage Fees Detail");

        lv_Fees = (ListView) findViewById(R.id.lv_Fees);
        search = (EditText) findViewById(R.id.search);

        // getting list from db

        FeesDetailArrayList = new DatabaseHelper(mcontext).getAllFeesList();

        // initialising list adapter
        mListViewAdapter = new ManageFeesDetail.ListViewAdapter(this, R.layout.activity_fees_detail, FeesDetailArrayList);

        // setting adapter on listview
        lv_Fees.setAdapter(mListViewAdapter);

        // registering for context menu
        registerForContextMenu(lv_Fees);

        bt_fees_add = (ImageButton) findViewById(R.id.bt_fees_add);

        bt_fees_add.setOnClickListener(this);

        lv_Fees.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                try {


                    Intent intent = new Intent(mcontext, FeesDetail.class);
                    intent.putExtra("submit_Date", FeesDetailArrayList.get(position).getsubmit_Date());
                    intent.putExtra("stu_Name", FeesDetailArrayList.get(position).getstu_Name());
                    intent.putExtra("fees_Amount", FeesDetailArrayList.get(position).getfees_Amount());
                    intent.putExtra("received_Fees", FeesDetailArrayList.get(position).getreceived_Fees());
                    intent.putExtra("due_Fees", FeesDetailArrayList.get(position).getdue_Fees());
                    intent.putExtra("receipt_Number", FeesDetailArrayList.get(position).getreceipt_Number());
                    intent.putExtra("received_By", FeesDetailArrayList.get(position).getreceived_By());

                    mcontext.startActivity(intent);
                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        if (v == bt_fees_add) {
            Intent intent = new Intent(getApplicationContext(), fees.class);
            startActivity(intent);
        }
    }


    // creating context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menufees, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        switch (item.getItemId()) {
            case R.id.fee_delete:

                // deleting contact from DB
                databaseHelper.deletefees(FeesDetailArrayList.get(info.position).getstu_Id());
                FeesDetailArrayList.remove(info.position);
                mListViewAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.fee_update:

                // updaing contact into DB
                Intent intent = new Intent(this, fees.class);
                intent.putExtra("position", FeesDetailArrayList.get(info.position).getstu_Id());
                intent.putExtra("submit_Date", FeesDetailArrayList.get(info.position).getsubmit_Date());
                intent.putExtra("stu_Name", FeesDetailArrayList.get(info.position).getstu_Name());
                intent.putExtra("fees_Amount", FeesDetailArrayList.get(info.position).getfees_Amount());
                intent.putExtra("received_Fees", FeesDetailArrayList.get(info.position).getreceived_Fees());
                intent.putExtra("due_Fees", FeesDetailArrayList.get(info.position).getdue_Fees());
                intent.putExtra("receipt_Number", FeesDetailArrayList.get(info.position).getreceipt_Number());
                intent.putExtra("received_By", FeesDetailArrayList.get(info.position).getreceived_By());

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
        private ArrayList<FeesList> FeesDetailArrayList = null;
        private ArrayList<FeesList> newFeesDetailList;

        public ListViewAdapter(Context context, int resource, ArrayList<FeesList> FeesList) {

            this.mcontext = context;
            this.mLayoutInflater = LayoutInflater.from(mcontext);
            this.newFeesDetailList = new ArrayList<FeesList>();
            this.newFeesDetailList.addAll(FeesList);


            this.FeesDetailArrayList = FeesList;
        }

        @Override
        public int getCount() {
            return FeesDetailArrayList.size();
        }

        @Override
        public FeesList getItem(int position) {
            return FeesDetailArrayList.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ManageFeesDetail.ViewHolder mViewHolder;
            if (convertView == null) {

                mViewHolder = new ManageFeesDetail.ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.custom_list_fees, null);

                // finding views
                mViewHolder.tv_Letter = (TextView) convertView.findViewById(R.id.tv_letter);
                mViewHolder.tv_stu_name = (TextView) convertView.findViewById(R.id.tv_stu_name);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ManageFeesDetail.ViewHolder) convertView.getTag();
            }


            // setting data into views
            String letter = "" + FeesDetailArrayList.get(position).getstu_Name().charAt(0);
            mViewHolder.tv_Letter.setText(letter);

            Drawable drawable =mcontext.getResources().getDrawable(R.drawable.rounded_circle);
            drawable.setColorFilter(mcontext.getResources().getColor(setLetterColor(letter)), PorterDuff.Mode.SRC_ATOP);
            mViewHolder.tv_Letter.setBackgroundDrawable(drawable);

            // setting data into views
            mViewHolder.tv_Letter.setText("" + FeesDetailArrayList.get(position).getstu_Name().charAt(0));
            mViewHolder.tv_stu_name.setText(FeesDetailArrayList.get(position).getstu_Name());


            return convertView;
        }

        /**
         * Method for filtering data
         */
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            FeesDetailArrayList.clear();
            if (charText.length() == 0) {
                FeesDetailArrayList.addAll(newFeesDetailList);
            } else {
                for (FeesList feesList : newFeesDetailList) {
                    if (feesList.getstu_Name().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        FeesDetailArrayList.add(feesList);
                    }

                }
                notifyDataSetChanged();
            }

        }
    }

    public static class ViewHolder {
        public TextView tv_Letter;
        public TextView tv_stu_name;
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


