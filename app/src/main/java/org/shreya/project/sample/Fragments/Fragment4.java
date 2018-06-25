package org.shreya.project.sample.Fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.shreya.project.sample.Activities.LoginActivity;
import org.shreya.project.sample.Activities.SignupActivity;
import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.R;
import org.shreya.project.sample.UserClass;
import org.w3c.dom.Text;

public class Fragment4 extends Fragment {


    public Fragment4() {
        // Required empty public constructor
    }
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
        return fragment;
    }
    View parentView;
    EditText mobile,email,address,password;
    TextView name;
    ProgressBar progressBar;
    AppCompatButton update;
    UserClass userClass;

    Gson gson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView=inflater.inflate(R.layout.fragment_fragment4, container, false);
        mobile=(EditText)parentView.findViewById(R.id.mobile);
        email=(EditText)parentView.findViewById(R.id.email);
        address=(EditText)parentView.findViewById(R.id.address);
        password=(EditText)parentView.findViewById(R.id.password);
        name=(TextView)parentView.findViewById(R.id.name);
        update=(AppCompatButton)parentView.findViewById(R.id.update);

        progressBar=(ProgressBar)parentView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        gson=new Gson();
        name.setText(DbHandler.getString(getContext(),"name",""));
        email.setText(DbHandler.getString(getContext(),"email",""));
        mobile.setText(DbHandler.getString(getContext(),"mobile",""));
        address.setText(DbHandler.getString(getContext(),"address",""));
        password.setText(DbHandler.getString(getContext(),"password",""));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")||
                        mobile.getText().toString().equals("")||address.getText().toString().equals("")||
                        password.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    updateInfo();
                }
            }
        });
        return parentView;
    }

    @TargetApi(21)
    public void updateInfo(){
        mobile.setFocusable(false);
        email.setFocusable(false);
        address.setFocusable(false);
        password.setFocusable(false);

        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
        progressBar.setIndeterminate(true);
        progressBar.setIndeterminateTintMode(PorterDuff.Mode.SRC_ATOP);
        progressBar.setVisibility(View.VISIBLE);

        update.setEnabled(false);
        update.setText("Updating ...");
        update.setBackgroundColor(getResources().getColor(R.color.lightGrey));

         userClass=new UserClass();
        userClass.setName(DbHandler.getString(getContext(),"name",""));
        userClass.setPassword(password.getText().toString());
        userClass.setAddress(address.getText().toString());
        userClass.setEmail(email.getText().toString());
        userClass.setPhoneNo(mobile.getText().toString());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // DbHandler.remove(getContext(),DbHandler.getString(getContext(),"mobile","")+"xyz");
                DbHandler.putString(getContext(),mobile.getText().toString()+"xyz",gson.toJson(userClass));
                DbHandler.updateInfo(getContext(),userClass);
                Toast.makeText(getContext(), "Information Updated Succesfully", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                update.setText("Update Information");
                update.setEnabled(true);
                update.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                reset();
            }
        },3000);

    }

    public void reset(){
        mobile.setFocusable(true);
        mobile.setFocusableInTouchMode(true);
        email.setFocusable(true);
        email.setFocusableInTouchMode(true);
        address.setFocusable(true);
        address.setFocusableInTouchMode(true);
        password.setFocusable(true);
        password.setFocusableInTouchMode(true);
    }
}
