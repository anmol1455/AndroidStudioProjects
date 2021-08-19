package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button add,subs,multy,divi;
    EditText ed1, ed2;
    TextView tvresult;
    int num1,num2,ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = (EditText) findViewById(R.id.edit1);
        ed2= (EditText) findViewById(R.id.edit2);
        tvresult=(TextView) findViewById(R.id.result);
        add= (Button) findViewById(R.id.sum);
        subs=(Button) findViewById(R.id.sub);
        multy=(Button) findViewById(R.id.mult);
        divi=(Button) findViewById(R.id.div);
        add.setOnClickListener(this);
        subs.setOnClickListener(this);
        multy.setOnClickListener(this);
        divi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       String v1=ed1.getText().toString();
        String v2=ed2.getText().toString();
        num1=Integer.parseInt(v1);
        num2=Integer.parseInt(v2);
        if(view==add)
        {
            ans=num1+num2;
            tvresult.setText(Integer.toString(ans));
            Toast.makeText(this,String.valueOf(ans),Toast.LENGTH_LONG).show();
        }
        if(view==subs)
        {
            ans=num1-num2;
            tvresult.setText(Integer.toString(ans));
            Toast.makeText(this,String.valueOf(ans),Toast.LENGTH_LONG).show();
        }
        if(view==multy)
        {
            ans=num1*num2;
            tvresult.setText(Integer.toString(ans));
            Toast.makeText(this,String.valueOf(ans),Toast.LENGTH_LONG).show();
        }
        if(view==divi) {
            try {


                ans = num1 / num2;
                tvresult.setText(Integer.toString(ans));
                Toast.makeText(this, String.valueOf(ans), Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
