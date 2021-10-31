package com.example.dimaandorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String mLanguageCode = "en";

    private EditText emailText;
    private EditText passwordText;
    private Button btnStartGame;

    static final String USER_MAIL_KEY= "MAIL";
    static final String USER_LEVEL_KEY= "LEVEL";
    static final String USER_SCORE_KEY= "SCORE";
    private boolean isEnglish=true;


    private String userEmail;
    private String userPassword;
    private AppUser appUser;

    static DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.txtemail);
        passwordText = findViewById(R.id.txtpass);
        btnStartGame=findViewById(R.id.btnStartGame);
        db = new DatabaseHandler(this);


        findViewById(R.id.btnChangHeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Change Application level locale
                if (isEnglish){
                    isEnglish=false;
                     LocaleHelper.setLocale(MainActivity.this, "he");
                }else{
                    isEnglish=true;
                    LocaleHelper.setLocale(MainActivity.this, "en");
                }

                //It is required to recreate the activity to reflect the change in UI.
                recreate();
            }
        });




    }

    public void startGame(View view) {

        Intent firstIntent = new Intent(MainActivity.this,GameActivity.class);
        firstIntent.putExtra(USER_MAIL_KEY,appUser.getEmail());
        firstIntent.putExtra(USER_SCORE_KEY,appUser.getScore());
        firstIntent.putExtra(USER_LEVEL_KEY,appUser.getLevel());

        MainActivity.this.startActivity(firstIntent);


    }

    public void logIn(View view) {
        userEmail=this.emailText.getText().toString();
        userPassword=this.emailText.getText().toString();

        Boolean isExist=this.db.checkIfExist(userEmail);
        if (isExist){
            this.appUser=db.getAppUser(userEmail);
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.LogInGood),Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"score: "+appUser.getScore(),Toast.LENGTH_SHORT).show();

            btnStartGame.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.Pulse)
                    .duration(400)
                    .repeat(1)
                    .playOn(btnStartGame);

        }
        else{
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.AccountNot),Toast.LENGTH_SHORT).show();
        }


    }


    public void signUp(View view) {
        userEmail=this.emailText.getText().toString();
        userPassword=this.emailText.getText().toString();

        AppUser newUser=new AppUser(userEmail,userPassword,"0","0");
        Intent firstIntent = new Intent(MainActivity.this,tutorial.class);
        MainActivity.this.startActivity(firstIntent);
        db.addAppUser(newUser);
        this.appUser=newUser;
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.SignUpGood),Toast.LENGTH_SHORT).show();
        btnStartGame.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.Pulse)
                .duration(400)
                .repeat(1)
                .playOn(btnStartGame);

    }

    public void goToTopScoreActivity(View view) {
        Intent firstIntent = new Intent(MainActivity.this,TopScore.class);
       this.startActivity(firstIntent);

    }
}