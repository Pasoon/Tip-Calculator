package com.example.pasoon.tipcalculator;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String CT = preferences.getString("CurrencyType", "");
        String DTP = preferences.getString("DefaultTipPercentage", "");

        System.out.println("HomeFragment: "+CT);
        System.out.println("HomeFragment: "+DTP);

        EditText billAmount = (EditText)rootView.findViewById(R.id.BillAmount);
        EditText tipPercentage = (EditText)rootView.findViewById(R.id.TipPercentage);
        billAmount.setHint("Bill Amount in "+CT);
        tipPercentage.setText(DTP);

        return rootView;
    }
}


