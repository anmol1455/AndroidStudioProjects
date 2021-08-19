package com.example.akshitavishwakarma.hostel_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Fragment frag;
    Button bt_log, bt_create;
    EditText ed1, ed2;

    private Context mContext;
    private AlertDialog builder;
    ImageView logout;
    ImageView i1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialising context
        this.mContext = this;


//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        frag = new welcome();
        getSupportFragmentManager().beginTransaction().add(R.id.container, frag).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();

            builder = new AlertDialog.Builder(mContext)

                    .setTitle("App Close")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Do You Want To Close This App?")

                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                        }

                    })

                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_about) {
            Intent obj1=new Intent(this,About.class);
            startActivity(obj1);
            return true;
        }
        if (id == R.id.action_help) {
            Intent obj1=new Intent(this,Help.class);
            startActivity(obj1);
            return true;
        }
        if (id == R.id.action_settings) {
            Intent obj1=new Intent(this,Setting.class);
            startActivity(obj1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            frag = new welcome();
            // Handle the camera action
        }else if (id == R.id.nav_admin) {
            frag = new _home();

        } else if (id == R.id.nav_user) {
            frag = new user();

        } else if (id == R.id.nav_gallery) {
            frag = new gallery();

        } else if (id == R.id.nav_facility) {
            frag = new facility();

        } else if (id == R.id.nav_salary) {
            frag = new salaryDetail();

        } else if (id == R.id.nav_lfp) {
            frag = new LikeFbPage();

        } else if (id == R.id.nav_share) {
            frag = new share();

        }  else if (id == R.id.nav_room) {
            frag = new roomDetail();

        }  else if (id == R.id.nav_menu) {

        frag = new menuCategory();
        }
        replaceFragment(frag);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();

    }

    @Override
    public void onClick(View v) {

        }

    }
