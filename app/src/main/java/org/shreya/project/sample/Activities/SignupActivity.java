package org.shreya.project.sample.Activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.R;
import org.shreya.project.sample.UserClass;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    EditText fullName,mobile,address,email,password;
    AppCompatButton signup;
    UserClass userClass;
    Gson gson;
    List<UserClass> users=new ArrayList<UserClass>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullName=(EditText)findViewById(R.id.fullName);
        mobile=(EditText)findViewById(R.id.mobile);
        address=(EditText)findViewById(R.id.address);
        email=(EditText)findViewById(R.id.email);
        signup=(AppCompatButton)findViewById(R.id.signup);
        password=(EditText)findViewById(R.id.password);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        userClass=new UserClass();
        gson=new Gson();
        progressBar.setVisibility(View.GONE);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullName.getText().toString().equals("")||email.getText().toString().equals("")||
                        mobile.getText().toString().equals("")||address.getText().toString().equals("")||
                password.getText().toString().equals(""))
                    Toast.makeText(SignupActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                else
                    signupUser();
            }
        });
    }

    @TargetApi(21)
    public void signupUser(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
        fullName.setFocusable(false);
        mobile.setFocusable(false);
        email.setFocusable(false);
        address.setFocusable(false);
        password.setFocusable(false);

        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
        progressBar.setVisibility(View.VISIBLE);
        signup.setEnabled(false);
        signup.setBackgroundColor(getResources().getColor(R.color.lightGrey));

        userClass.setEmail(email.getText().toString());
        userClass.setName(fullName.getText().toString());
        userClass.setPhoneNo(mobile.getText().toString());
        userClass.setAddress(address.getText().toString());
        userClass.setPassword(password.getText().toString());
        DbHandler.setSession(SignupActivity.this,userClass);
        DbHandler.putString(SignupActivity.this,userClass.getPhoneNo(),gson.toJson(userClass));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SignupActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this,MainActivity.class));
            }
        },2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
