package org.shreya.project.sample.Activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.shreya.project.sample.ColoredSnackbar;
import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.R;
import org.shreya.project.sample.UserClass;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    AppCompatButton login;
    TextView signup;
    Gson gson;
    ProgressBar progressBar;

    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(AppCompatButton)findViewById(R.id.login);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        signup=(TextView)findViewById(R.id.signup);
        gson=new Gson();
        progressBar.setVisibility(View.GONE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("")||password.getText().toString().equals(""))
                    Toast.makeText(LoginActivity.this,"Please enter both the fields",Toast.LENGTH_SHORT).show();
                else
                    loginUser();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

    }
    @TargetApi(21)
    public void loginUser(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
        username.setFocusable(false);
        password.setFocusable(false);

        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
        progressBar.setVisibility(View.VISIBLE);
        login.setEnabled(false);
        login.setBackgroundColor(getResources().getColor(R.color.lightGrey));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!DbHandler.contains(LoginActivity.this,username.getText().toString()))
                {
                    reset();
                    Toast.makeText(LoginActivity.this,"User doesn't exist.Please Sign Up.",Toast.LENGTH_SHORT).show();
                }
                else{
                    UserClass userClass=gson.fromJson(DbHandler.getString(LoginActivity.this,username.getText().toString().trim(),""),UserClass.class);
                    if(password.getText().toString().equals(userClass.getPassword()))
                    {
                        reset();
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        DbHandler.setSession(LoginActivity.this,userClass);
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                    else{
                        reset();
                        Toast.makeText(LoginActivity.this,"Wrong Username or Password",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        },2000);


    }
    public void reset(){
        progressBar.setVisibility(View.GONE);
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
        login.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.finishAffinity();
            return;
        }

        doubleBackToExitPressedOnce = true;

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Press Again To Exit", Snackbar.LENGTH_SHORT);
        ColoredSnackbar.warning(snackbar).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
