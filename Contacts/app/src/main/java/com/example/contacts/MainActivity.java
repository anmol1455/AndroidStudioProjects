package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private Context mcontext;
    private ListView listView;
    private ListViewAdapter mListViewAdapter;
    private ArrayList<UserDetail> userDetailArrayList = new ArrayList<UserDetail>();
    private SearchView se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.mcontext = this;
        listView = (ListView) findViewById(R.id.contactlist);
        se = (SearchView) findViewById(R.id.search);

        userDetailArrayList = new DatabaseHelper(mcontext).getAllContacts();

        // initialising list adapter
        mListViewAdapter = new ListViewAdapter(this, R.layout.activity_contact_details, userDetailArrayList);

        // setting adapter on listview
        listView.setAdapter(mListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(mcontext, ContactDetails.class);
                intent.putExtra("userName", userDetailArrayList.get(position).getUserName());
                intent.putExtra("userPhone", userDetailArrayList.get(position).getUserMobile());
                intent.putExtra("userEmail", userDetailArrayList.get(position).getUserEmail());
                intent.putExtra("userOrgan", userDetailArrayList.get(position).getUserOrg());
                intent.putExtra("userAdd", userDetailArrayList.get(position).getUserAdd());
                intent.putExtra("userAdd1", userDetailArrayList.get(position).getUserAdd1());

                mcontext.startActivity(intent);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent obj = new Intent(mcontext, Detail.class);
                startActivity(obj);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListViewAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private ArrayList<UserDetail> userDetailArrayList = null;
        private ArrayList<UserDetail> newUserDetailList;

        public ListViewAdapter(Context context, int resource, ArrayList<UserDetail> userDetails) {

            this.mContext = context;
            this.mLayoutInflater = LayoutInflater.from(mContext);
            this.newUserDetailList = new ArrayList<UserDetail>();
            this.newUserDetailList.addAll(userDetails);

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

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {

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
////            mViewHolder.tv_Letter.setOnClickListener(new View.OnClickListener() {
////
////                @Override
////                public void onClick(View v) {
////
////                    Intent intent = new Intent(mContext, ContactDetails.class);
////                    intent.putExtra("userName", userDetailArrayList.get(position).getUserName());
////                    intent.putExtra("userEmail", userDetailArrayList.get(position).getUserEmail());
////                    intent.putExtra("userPhone", userDetailArrayList.get(position).getUserMobile());
////                    intent.putExtra("userDOB", userDetailArrayList.get(position).getUserDOB());
////
////                    mContext.startActivity(intent);
////                }
////            });
            return convertView;
        }
    }

    public class ViewHolder {

        public TextView tv_Letter;
        public TextView tv_UserName;
        public TextView tv_UserMobile;
    }
}
