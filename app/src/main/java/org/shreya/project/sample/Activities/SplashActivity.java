package org.shreya.project.sample.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(DbHandler.getBoolean(SplashActivity.this,"isLoggedIn",false))
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        else
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
    }

}
