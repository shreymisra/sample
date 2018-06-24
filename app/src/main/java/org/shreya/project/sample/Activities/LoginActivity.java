package org.shreya.project.sample.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.R;
import org.shreya.project.sample.UserClass;
import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    AppCompatButton login;
    TextView signup;
    Gson gson;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(AppCompatButton)findViewById(R.id.login);
        signup=(TextView)findViewById(R.id.signup);
        gson=new Gson();
        progressDialog=new ProgressDialog(getApplicationContext());
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
    public void loginUser(){
        progressDialog.setTitle("Authenticating");
        progressDialog.setMessage("Please Wait...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        if(!DbHandler.contains(LoginActivity.this,username.getText().toString()))
        {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity.this,"User doesn't exist.Please Sign Up.",Toast.LENGTH_SHORT).show();
        }
        else{
            UserClass userClass=gson.fromJson(DbHandler.getString(LoginActivity.this,username.getText().toString().trim(),""),UserClass.class);
            if(password.getText().toString().equals(userClass.getPassword()))
            {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                DbHandler.setSession(LoginActivity.this,userClass);
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
            else{
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,"Wrong Username or Password",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
