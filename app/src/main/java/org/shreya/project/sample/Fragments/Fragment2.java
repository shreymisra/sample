package org.shreya.project.sample.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.shreya.project.sample.Adapters.UsersAdapter;
import org.shreya.project.sample.R;


public class Fragment2 extends Fragment {

    RecyclerView recyclerView;
    View parentView;
  public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView=inflater.inflate(R.layout.fragment_fragment2, container, false);
        recyclerView=(RecyclerView)parentView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new UsersAdapter(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return parentView;
    }


}
