package com.example.dell.cprograming;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class questionShow extends AppCompatActivity implements View.OnClickListener{

    private int score,dx,tm,ck=1;
    private String m_Text = "";
    private RadioGroup radioGroup;
    private RadioButton genderradioButton,option1,option2,option3,option4;
    private ImageView imagefckView;
    private ImageButton imageButton,settingBtn;
    private TextView textView,qsnView,timer,imageViewf;
    private WebView webView;
    private Button submitButton,continueButton,saveButton,saveChange;
    private CountDownTimer countDownTimer;
    private Switch soundSwitch,vibrationSwitch;
    private LinearLayout mainLaout,timerLaout,settinglaout;
    private int counter;
    private int STRT_TIME = 61000;
    private int ON_CREATE=0;
    private int currentTime = STRT_TIME;
    private boolean onOf=false;
    private int soundValue,vibratvalue,alart=0;
    private String imgTx;
    private String qen="",op1="",op2="",op3="",op4="";

    database myDatabase;
    Vibrator vibrator;
    MediaPlayer mp;
    question qsn = new question();
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_show);

        mp = MediaPlayer.create(this, R.raw.first);
        myDatabase = new database(this);
        SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        score = sharedPreferences.getInt("score",0);
        soundValue = sharedPreferences.getInt("sound",1);
        vibratvalue = sharedPreferences.getInt("vibrat",1);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        option1 = (RadioButton)findViewById(R.id.option1Id);
        option2 = (RadioButton)findViewById(R.id.option2Id);
        option3 = (RadioButton)findViewById(R.id.option3Id);
        option4 = (RadioButton)findViewById(R.id.option4Id);
        imageButton = findViewById(R.id.backButtonId);
        settingBtn = findViewById(R.id.settingButtonId);
        webView = findViewById(R.id.webViewiD);
        imageViewf = findViewById(R.id.imageViewdff);
        imagefckView = findViewById(R.id.imageFckViewId);
        textView = findViewById(R.id.scoreViewId);
        submitButton = findViewById(R.id.submitebuttonId);
        continueButton = findViewById(R.id.continuebuttonId);
        qsnView = findViewById(R.id.questionViewId);
        saveButton = findViewById(R.id.SaveScoreButtonId);
        timer = findViewById(R.id.timerId);
        mainLaout = findViewById(R.id.mainlayought);
        timerLaout = findViewById(R.id.timerLayought);
        settinglaout = findViewById(R.id.settingLayought);
        saveChange = findViewById(R.id.saveChangBtn);
        soundSwitch = findViewById(R.id.soundSwitchId);
        vibrationSwitch = findViewById(R.id.vibrationSwitchId);

        ON_CREATE = 1;
        setValues();
        setText();

        submitButton.setOnClickListener(this);
        continueButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        imageButton.setOnClickListener((View.OnClickListener) this);
        settingBtn.setOnClickListener(this);
        saveChange.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onStart();
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(timerLaout.getVisibility()==View.VISIBLE){
            if(ON_CREATE==0){
                if(alart==0){
                    if(onOf){ countDownTimer.cancel();onOf=false;}
                    timer();
                }
            }
            else{ON_CREATE = 0;}
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onStop();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (mp.isPlaying()) { mp.stop();}
        if(onOf){ countDownTimer.cancel();onOf=false;}
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("time",counter);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        if(timerLaout.getVisibility()==View.GONE){
            VisibleUnvasible();
        }
        else{
            if (mp.isPlaying()) { mp.stop();}
            if(onOf){ countDownTimer.cancel();onOf=false;}
            Intent intent = new Intent(questionShow.this,QuizePage.class);
            startActivity(intent);
            finish();
        }
    }


    public void setValues(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
        int ff = sharedPreferences.getInt("index",-1);
        if(ff!=-1&&ck==1){
            dx = sharedPreferences.getInt("index",0);
            counter = sharedPreferences.getInt("time",0);
            ck=0;
        }
        else{
            int cdx = dx;
            dx = rand.nextInt(qsn.getSize());
            if(Math.abs(cdx-dx)<5)setValues();
            currentTime = STRT_TIME;
            counter = 0;
            ck = 0;
        }
    }


    public void setText(){
        radioGroup.clearCheck();
        int ind = rand.nextInt(qsn.dxSize());
           ((RadioButton) radioGroup.getChildAt(0)).setText(qsn.getString(dx,qsn.ospn(ind,0)));
           ((RadioButton) radioGroup.getChildAt(1)).setText(qsn.getString(dx,qsn.ospn(ind,1)));
           ((RadioButton) radioGroup.getChildAt(2)).setText(qsn.getString(dx,qsn.ospn(ind,2)));
           ((RadioButton) radioGroup.getChildAt(3)).setText(qsn.getString(dx,qsn.ospn(ind,3)));
        textView.setText("Score: "+score);
        qsnView.setText(qsn.getString(dx,0));
        imgTx = qsn.getString(dx,1);
        setImage(imgTx);
       if(onOf){ countDownTimer.cancel();onOf=false;}
        timer();

    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.backButtonId){
            if(onOf){ countDownTimer.cancel();onOf=false;}
            if (mp.isPlaying()) { mp.stop();}
            Intent intent = new Intent(questionShow.this,QuizePage.class);
            startActivity(intent);
            finish();
        }
        if(v.getId()==R.id.submitebuttonId){
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if(selectedId==-1){
                Toast.makeText(questionShow.this,"Nothing selected", Toast.LENGTH_SHORT).show();
            }
            else{
                genderradioButton = (RadioButton) findViewById(selectedId);
                if(genderradioButton.getText()==qsn.getString(dx,6)){
                    score += (10-(counter/10));
                    setValues();
                    SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("index",dx);
                    editor.putInt("score",score);
                    editor.commit();
                    setText();
                }
                else{wrongCorect(genderradioButton.getText().toString(),qsn.getString(dx,6));over();}
            }
        }
        if (v.getId() == R.id.continuebuttonId) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("If you skip, you gate -5 points.");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    score -= 5;
                    setValues();
                    SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("index",dx);
                    editor.putInt("score",score);
                    editor.commit();
                    setText();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.setCancelable(false);
            builder.show();
        }
        if(v.getId()==R.id.SaveScoreButtonId){
            if (mp.isPlaying()) { mp.stop();}
            if(onOf){ countDownTimer.cancel();onOf=false;}
            saveData();
        }
        if(v.getId()==R.id.settingButtonId){
            VisibleUnvasible();
        }
        if(v.getId()==R.id.saveChangBtn){
            if(soundSwitch.isChecked()){soundValue=1;}
            else {soundValue=0;}
            if(vibrationSwitch.isChecked()){vibratvalue=1;}
            else{vibratvalue=0;}
            SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("sound",soundValue);
            editor.putInt("vibrat",vibratvalue);
            editor.commit();
            VisibleUnvasible();
        }
    }


    public void wrongCorect(String s, String c){
        if (option1.getText().toString().equals(s)){ option1.setTextColor(Color.parseColor("#FFE11010"));}
        if (option2.getText().toString().equals(s)){ option2.setTextColor(Color.parseColor("#FFE11010"));}
        if (option3.getText().toString().equals(s)){ option3.setTextColor(Color.parseColor("#FFE11010"));}
        if (option4.getText().toString().equals(s)){ option4.setTextColor(Color.parseColor("#FFE11010"));}
        if (option1.getText().toString().equals(c)){ option1.setTextColor(Color.parseColor("#FF6AE61E")); }
        if (option2.getText().toString().equals(c)){ option2.setTextColor(Color.parseColor("#FF6AE61E")); }
        if (option3.getText().toString().equals(c)){ option3.setTextColor(Color.parseColor("#FF6AE61E")); }
        if (option4.getText().toString().equals(c)){ option4.setTextColor(Color.parseColor("#FF6AE61E"));}
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("continue",0);
        editor.commit();
        alart = 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your name:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                m_Text.trim();
                if(m_Text.equals("")){Toast.makeText(getApplicationContext(),"Enter your name",Toast.LENGTH_SHORT).show();saveData();}
                else{
                    long rowid = myDatabase.insertDataToTable(m_Text,score);
                    if(rowid==-1){
                        Toast.makeText(getApplicationContext(),"Data is not saved! try again.",Toast.LENGTH_SHORT).show();
                        saveData();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Data is saved successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(questionShow.this,QuizePage.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(questionShow.this,QuizePage.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setGravity(Gravity.TOP);
        alertDialog.show();
       // builder.show();
    }


    public void over(){
        SharedPreferences sharedPreferences = getSharedPreferences("startingvalue",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("continue",0);
        editor.commit();
        alart = 1;
        if(onOf){ countDownTimer.cancel();onOf=false;}

        if(vibratvalue==1)vibrator.vibrate(400);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want save your score?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { saveData();}
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(questionShow.this,QuizePage.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setGravity(Gravity.TOP);
        alertDialog.show();
        //builder.show();
    }

    public void timer(){
        countDownTimer = new CountDownTimer(currentTime, 1000){
            @Override
            public void onTick(long millisUntilFinished){
                if(counter==60){if(onOf){ countDownTimer.cancel();onOf=false;}onFinish();}
                if((60-counter)>=10){timer.setText(String.valueOf(60-counter));counter++;}
                if((60-counter)<10){timer.setText("0"+String.valueOf(60-counter));counter++;}
                if(soundValue==1){while(!mp.isPlaying())mp.start();}
            }
            @Override
            public  void onFinish(){onOf = false;timer.setText("00");over();}
        };
        countDownTimer.start();
        onOf = true;
    }

    public void VisibleUnvasible(){
        if(timerLaout.getVisibility()==View.VISIBLE){
            timerLaout.setVisibility(View.GONE);
            settinglaout.setVisibility(View.VISIBLE);
            if(!imgTx.equals("")){
                webView.setVisibility(View.GONE);
                imageViewf.setVisibility(View.VISIBLE);
            }
            mainLaout.setBackgroundColor(Color.parseColor("#FFA88A81"));//FFA88A81
            submitButton.setBackgroundResource(R.drawable.button_disable);
            continueButton.setBackgroundResource(R.drawable.button_disable);
            submitButton.setEnabled(false);
            continueButton.setEnabled(false);
            radioGroup.setEnabled(false);
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);
            qen = qsnView.getText().toString();
            op1 = option1.getText().toString();
            op2 = option2.getText().toString();
            op3 = option3.getText().toString();
            op4 = option4.getText().toString();
            qsnView.setText("Question..?");
            option1.setText("Option 1");
            option2.setText("Option 2");
            option3.setText("Option 3");
            option4.setText("Option 4");
            if(soundValue==1){soundSwitch.setChecked(true);}
            else{soundSwitch.setChecked(false);}
            if(vibratvalue==1){vibrationSwitch.setChecked(true);}
            else {vibrationSwitch.setChecked(false);}

            if (mp.isPlaying()) { mp.stop();}
            if(onOf){ countDownTimer.cancel();onOf=false;}
        }
        else{
            timerLaout.setVisibility(View.VISIBLE);
            settinglaout.setVisibility(View.GONE);
            if(!imgTx.equals("")){
                webView.setVisibility(View.VISIBLE);
                imageViewf.setVisibility(View.GONE);
            }
            mainLaout.setBackgroundColor(Color.parseColor("#FF803118"));
            submitButton.setBackgroundResource(R.drawable.button_shape);
            continueButton.setBackgroundResource(R.drawable.button_red);
            submitButton.setEnabled(true);
            continueButton.setEnabled(true);
            radioGroup.setEnabled(true);
            option1.setEnabled(true);
            option2.setEnabled(true);
            option3.setEnabled(true);
            option4.setEnabled(true);
            qsnView.setText(qen);
            option1.setText(op1);
            option2.setText(op2);
            option3.setText(op3);
            option4.setText(op4);

            if(onOf){ countDownTimer.cancel();onOf=false;}
            if (mp.isPlaying()) { mp.stop();}
            timer();
        }

    }

    public void setImage(String s){
        if(s==""){
           webView.setVisibility(View.GONE);
            imagefckView.setVisibility(View.VISIBLE);
        }
        else {
           webView.setVisibility(View.VISIBLE);
            imagefckView.setVisibility(View.GONE);

           webView.getSettings().setJavaScriptEnabled(true);
           webView.loadUrl("file:///android_asset/"+s);
        }
    }
}
