package org.shreya.project.sample.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
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
        userClass=new UserClass();
        gson=new Gson();
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

    public void signupUser(){

        userClass.setEmail(email.getText().toString());
        userClass.setName(fullName.getText().toString());
        userClass.setPhoneNo(mobile.getText().toString());
        userClass.setAddress(address.getText().toString());
        DbHandler.setSession(SignupActivity.this,userClass);
        DbHandler.putString(SignupActivity.this,userClass.getPhoneNo(),gson.toJson(userClass));
        Toast.makeText(SignupActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignupActivity.this,MainActivity.class));
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(SignupActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this,MainActivity.class));
            }
        },500);
*/
    }
}
