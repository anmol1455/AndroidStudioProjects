package com.example.list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView list;
    TextView text;
    String[] listitem;
    Button bt1, bt2, bt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        text = (TextView) findViewById(R.id.text2);

        bt1 = (Button) findViewById(R.id.btn1);
        bt2 = (Button) findViewById(R.id.btn2);
        bt3 = (Button) findViewById(R.id.btn3);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

        listitem = getResources().getStringArray(R.array.array_tech);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listitem);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Toast.makeText(getApplicationContext(), "position is"+position, Toast.LENGTH_SHORT).show();

//                String team = ((TextView) view).getText().toString();
//                Toast.makeText(getApplicationContext(), team, Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
//                intent.putExtra("team", team);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == bt1) {
            AlertDialog.Builder demo = new AlertDialog.Builder(this);
            demo.setTitle("Exit App");
            demo.setMessage("Really wanna exit the app??");
            demo.setCancelable(false);
            demo.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            demo.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alert = demo.create();
            alert.show();
        }

        if (v == bt2) {
            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.THEME_TRADITIONAL);
            progressDialog.setCancelable(false);
            progressDialog.incrementProgressBy(10);
            progressDialog.setButton("Stop progress", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.dismiss();
                }
            });
            progressDialog.show();
        }

       /* if (v == bt3) {
            Dialog dialog = new Dialog(MainActivity.this);
            //   dialog.setContentView(R.layout.custom_dialog);
            dialog.setTitle("Custom_dialog");
            TextView text = (TextView) dialog.findViewById(R.id.tv1);
            text.setText("Here is custom dioalg");
            ImageView imageView = (ImageView) dialog.findViewById(R.id.img);
            imageView.setImageResource(R.mipmap.ic_launcher);
            dialog.show();
        }*/
    }
}