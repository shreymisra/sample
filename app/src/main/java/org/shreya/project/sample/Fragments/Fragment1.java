package org.shreya.project.sample.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.shreya.project.sample.DbHandler;
import org.shreya.project.sample.R;

public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }
   public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }
    View parentView;
    TextView mobile,email,address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView=inflater.inflate(R.layout.fragment_fragment1, container, false);
        mobile=(TextView)parentView.findViewById(R.id.mobile);
        email=(TextView)parentView.findViewById(R.id.email);
        address=(TextView)parentView.findViewById(R.id.address);
        mobile.setText("+91- "+DbHandler.getString(getContext(),"mobile",""));
        address.setText(DbHandler.getString(getContext(),"address",""));
        email.setText(DbHandler.getString(getContext(),"email",""));

        return parentView;
    }

}
