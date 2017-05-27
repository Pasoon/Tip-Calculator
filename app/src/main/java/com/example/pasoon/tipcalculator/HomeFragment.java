package com.example.pasoon.tipcalculator;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * Created by Pasoon on 2017-05-23.
 */

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        EditText billAmount = (EditText)rootView.findViewById(R.id.BillAmount);
        EditText tipPercentage = (EditText)rootView.findViewById(R.id.TipPercentage);
        EditText numberOfPpl = (EditText)rootView.findViewById(R.id.NumberOfPpl);





        return rootView;
    }
}


