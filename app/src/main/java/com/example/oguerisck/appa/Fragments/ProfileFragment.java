package com.example.oguerisck.appa.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oguerisck.appa.R;


public class ProfileFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
    //just change the fragment_dashboard
    //with the fragment you want to inflate
    //like if the class is ProfileFragment it should have R.layout.home_fragment
    //if it is DashboardFragment it should have R.layout.fragment_dashboard
    return inflater.inflate(R.layout.fragment_profile, null);
  }
}

