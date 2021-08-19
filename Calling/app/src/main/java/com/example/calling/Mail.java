package com.example.calling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mail extends AppCompatActivity  {
    Button strtBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        strtBtn=(Button)findViewById(R.id.send);
        strtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();

            }
        });
    }
protected void sendEmail(){
        Log.i("Send Email","");
        String[] to={""};
        String[] cc={""};
    Intent emailIntent= new Intent(Intent.ACTION_SEND);

    emailIntent.setData(Uri.parse("Mailto:"));
    emailIntent.setType("text/plain");
    emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
    emailIntent.putExtra(Intent.EXTRA_CC,cc);
    emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Your Subject");
    emailIntent.putExtra(Intent.EXTRA_TEXT,"Email msg goes here");

    try{
        startActivity(Intent.createChooser(emailIntent,"Send Mail"));
        finish();
        Log.i("Finished sending email","");
    }
    catch (android.content.ActivityNotFoundException ex){
        Toast.makeText(this,"there's no email client installed",Toast.LENGTH_SHORT).show();
    }
}


}
