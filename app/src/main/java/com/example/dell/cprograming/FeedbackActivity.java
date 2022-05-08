package com.example.dell.cprograming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener{

   private EditText email,name,feedbacK;
   private Button clearButton,sentButton;
   private ImageButton backButton;
   String bnst="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        backButton = findViewById(R.id.backButtonId);
        clearButton = findViewById(R.id.clearbuttonId);
        sentButton = findViewById(R.id.sentbuttonId);
        email = findViewById(R.id.emailId);
        name = findViewById(R.id.nameId);
        feedbacK = findViewById(R.id.feedbackId);

        backButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        sentButton.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        bnst = bundle.getString("tag");

    }
    @Override
    public void onBackPressed() {
        if(bnst.equals("about")){
            Intent intent = new Intent(getApplicationContext(),aboutPage.class);
            startActivity(intent);
            finish();
        }
        if(bnst.equals("main")){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButtonId){
             if(bnst.equals("about")){
                Intent intent = new Intent(getApplicationContext(),aboutPage.class);
                startActivity(intent);
                finish();
            }
            if(bnst.equals("main")){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
        if(v.getId()==R.id.clearbuttonId){
            email.setText("");
            name.setText("");
            feedbacK.setText("");
        }
        if(v.getId()==R.id.sentbuttonId){
           String emailtxt,nametxt,feedbacktxt;
            emailtxt = email.getText().toString().trim();
            nametxt = name.getText().toString().trim();
            feedbacktxt = feedbacK.getText().toString().trim();
            if(!emailtxt.equals("")&&!nametxt.equals("")&&!feedbacktxt.equals("")){
                if(!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()){
                    Toast.makeText(this,"Enter any valid email address",Toast.LENGTH_SHORT).show();
                    email.setError("Enter any valid email address");
                    email.requestFocus();
                    return;
                }
                else{
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/email");
                        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"shawonmahmodul12@gmail.com","jackjisan510@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback from app");
                        intent.putExtra(Intent.EXTRA_TEXT,"Name: "+nametxt+"\nEmail: "+emailtxt+"\n\n Message: \n"+feedbacktxt);
                        startActivity(Intent.createChooser(intent,"Feedback with"));
                    }catch(Exception e){
                        Toast.makeText(this,"Exception: "+e,Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else{
                String tst="";
                int ck=0;
                if(emailtxt.equals("")){ tst += "Pleass, Enter your email"; email.setError("Enter your email");if(ck==0){email.requestFocus();}ck=1;}
                if(nametxt.equals("")){
                    name.setError("Enter your name");
                   if(ck==0) {tst += "Pleass, Enter you name";ck=1;name.requestFocus();}
                   else{ if(feedbacktxt.equals("")){tst += ", name";} else{tst += " & name";} }
                }
                if(feedbacktxt.equals("")){ if(ck==0) {tst += "Pleass, Enter your feedback";feedbacK.requestFocus();} else{tst += " & feedback";} feedbacK.setError("Enter your feedback"); }
                Toast.makeText(this, tst, Toast.LENGTH_SHORT).show();
            }
        }
    }
}