package com.example.saurabhjain.contactdemoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv_ContactView;
    private EditText et_Search;
    private Context mContext;
    private ListViewAdapter mListViewAdapter;
    private ArrayList<UserDetail> userDetailArrayList = new ArrayList<UserDetail>();
    private AlertDialog builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialising context
        this.mContext = this;

        // finding views
        lv_ContactView = (ListView) findViewById(R.id.listview);
//        btn_AddContact = (Button) findViewById(R.id.btn_add_contact);
        FloatingActionButton fab = findViewById(R.id.btn_add_contact);
        et_Search = (EditText) findViewById(R.id.search);

        // getting list from db
        userDetailArrayList = new DatabaseHelper(mContext).getAllContacts();

        // initialising list adapter
        mListViewAdapter = new ListViewAdapter(this, R.layout.activity_contact_details, userDetailArrayList);

        // setting adapter on listview
        lv_ContactView.setAdapter(mListViewAdapter);

        // registering for context menu
        registerForContextMenu(lv_ContactView);

        // setting click listener on button
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AddContact.class));
                finish();

            }
        });

        lv_ContactView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mContext, ContactDetails.class);
                intent.putExtra("userName", userDetailArrayList.get(position).getUserName());
                intent.putExtra("userEmail", userDetailArrayList.get(position).getUserEmail());
                intent.putExtra("userPhone", userDetailArrayList.get(position).getUserMobile());
                intent.putExtra("userDOB", userDetailArrayList.get(position).getUserDOB());

                mContext.startActivity(intent);
            }
        });

        // setting text watcher listener on edittext
        et_Search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String text = et_Search.getText().toString().toLowerCase(Locale.getDefault());
                mListViewAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            }
        });
    }

    // creating context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        switch (item.getItemId()) {
            case R.id.i_delete:

                // deleting contact from DB
                databaseHelper.deleteContact(userDetailArrayList.get(info.position).getUserID());
                userDetailArrayList.remove(info.position);
                mListViewAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.i_update:

                // updaing contact into DB
                Intent intent = new Intent(this, AddContact.class);
                intent.putExtra("position", userDetailArrayList.get(info.position).getUserID());
                intent.putExtra("userName", userDetailArrayList.get(info.position).getUserName());
                intent.putExtra("userEmail", userDetailArrayList.get(info.position).getUserEmail());
                intent.putExtra("userPhone", userDetailArrayList.get(info.position).getUserMobile());
                intent.putExtra("userDOB", userDetailArrayList.get(info.position).getUserDOB());
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
        private ArrayList<UserDetail> userDetailArrayList = null;
        private ArrayList<UserDetail> newUserDetailList;

        public ListViewAdapter(Context context, int resource, ArrayList<UserDetail> userDetails) {

            this.mContext = context;
            this.mLayoutInflater = LayoutInflater.from(mContext);
            this.newUserDetailList = new ArrayList<UserDetail>();
            this.newUserDetailList.addAll(userDetails);


//            Collections.sort(userDetails, new Comparator<UserDetail>() {
//                @Override
//                public int compare(UserDetail o1, UserDetail o2) {
//                    return o1.getUserName().compareTo(o2.getUserName());
//                }
//            });

            this.userDetailArrayList = userDetails;
        }

        @Override
        public int getCount() {
            return userDetailArrayList.size();
        }

        @Override
        public UserDetail getItem(int position) {
            return userDetailArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            MainActivity.ViewHolder mViewHolder;
            if (convertView == null) {

                mViewHolder = new MainActivity.ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.custom_list_item, null);

                // finding views
                mViewHolder.tv_Letter = (TextView) convertView.findViewById(R.id.tv_letter);
                mViewHolder.tv_UserName = (TextView) convertView.findViewById(R.id.tv_user_name);
                mViewHolder.tv_UserMobile = (TextView) convertView.findViewById(R.id.tv_user_mobile);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (MainActivity.ViewHolder) convertView.getTag();
            }

            // setting data into views
            mViewHolder.tv_Letter.setText("" + userDetailArrayList.get(position).getUserName().charAt(0));
            mViewHolder.tv_UserName.setText(userDetailArrayList.get(position).getUserName());
            mViewHolder.tv_UserMobile.setText(userDetailArrayList.get(position).getUserMobile());

//            // setting on click listener on textview
//            mViewHolder.tv_Letter.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent = new Intent(mContext, ContactDetails.class);
//                    intent.putExtra("userName", userDetailArrayList.get(position).getUserName());
//                    intent.putExtra("userEmail", userDetailArrayList.get(position).getUserEmail());
//                    intent.putExtra("userPhone", userDetailArrayList.get(position).getUserMobile());
//                    intent.putExtra("userDOB", userDetailArrayList.get(position).getUserDOB());
//
//                    mContext.startActivity(intent);
//                }
//            });
            return convertView;
        }

        /**
         * Method for filtering data
         */
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            userDetailArrayList.clear();
            if (charText.length() == 0) {
                userDetailArrayList.addAll(newUserDetailList);
            } else {
                for (UserDetail userDetail : newUserDetailList) {
                    if (userDetail.getUserName().toLowerCase(Locale.getDefault()).startsWith(charText)) {
                        userDetailArrayList.add(userDetail);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {

        builder = new AlertDialog.Builder(mContext)

                .setTitle("App Close")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("Are you sure want to exit App?")

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder {
        public TextView tv_Letter;
        public TextView tv_UserName;
        public TextView tv_UserMobile;
    }
}