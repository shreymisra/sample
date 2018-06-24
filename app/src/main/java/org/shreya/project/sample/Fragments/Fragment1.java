package org.shreya.project.sample.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shreya.project.sample.R;

public class Fragment1 extends Fragment {

    public Fragment1() {
        // Required empty public constructor
    }
   public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

}
