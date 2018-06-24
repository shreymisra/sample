package org.shreya.project.sample.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.shreya.project.sample.ColoredSnackbar;
import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.Fragments.Fragment1;
import org.shreya.project.sample.Fragments.Fragment2;
import org.shreya.project.sample.Fragments.Fragment3;
import org.shreya.project.sample.Fragments.Fragment4;
import org.shreya.project.sample.R;

import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private boolean doubleBackToExitPressedOnce = false;
    FrameLayout frameLayout;
    ImageView image;
    NavigationView navigationView;
    TextView name,email;
    ColoredSnackbar coloredSnackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
        image=(ImageView)findViewById(R.id.imageView);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Fragment fragment=null;
        Class fragmentClass=null;
        fragmentClass= Fragment1.class;
        FragmentManager fragmentManager=getSupportFragmentManager();
        try{
            fragment=(Fragment)fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        replaceFragment(fragment);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment f=getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                if(f!=null)
                    updateTitle(f);
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1000);
        }

    }


    private void updateTitle(Fragment fragment){
        String fragClassName=fragment.getClass().getName();
        if(fragClassName.equals(Fragment1.class.getName())){
            navigationView.getMenu().getItem(0).setChecked(true);
            setTitle("Fragment 1");
        }else if(fragClassName.equals(Fragment2.class.getName())){
            navigationView.getMenu().getItem(1).setChecked(true);
            setTitle("Fragment 2");
        }else if(fragClassName.equals(Fragment3.class.getName())){
            navigationView.getMenu().getItem(2).setChecked(true);
            setTitle("Fragment 3");
        }else if(fragClassName.equals(Fragment4.class.getName())){
            navigationView.getMenu().getItem(3).setChecked(true);
            setTitle("Fragment 4");
        }
    }

    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frameLayout, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @TargetApi(16)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!(getSupportFragmentManager().getBackStackEntryCount() == 1)) {
                super.onBackPressed();
            } else {

                if (doubleBackToExitPressedOnce) {
                    this.finishAffinity();
                    return;
                }
                this.doubleBackToExitPressedOnce = true;

                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Press again to exit", Snackbar.LENGTH_SHORT);
                ColoredSnackbar.warning(snackbar).show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment fragment=null;
        Class fragmentClass=Fragment1.class;
        int id = item.getItemId();

        switch(id){
            case R.id.fragment1:fragmentClass=Fragment1.class;
            break;
            case R.id.fragment2:fragmentClass=Fragment2.class;
            break;
            case R.id.fragment3:fragmentClass=Fragment3.class;
            break;
            case R.id.fragment4:fragmentClass=Fragment4.class;
            break;
            case R.id.logout:
                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DbHandler.remove(MainActivity.this,"isLoggedIn");
                                DbHandler.putBoolean(MainActivity.this,"isLoggedIn",false);
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();

                break;
            default:fragmentClass=Fragment1.class;
        }


        try
        {
            fragment=(Fragment)fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        replaceFragment(fragment);
        item.setChecked(true);
        setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
