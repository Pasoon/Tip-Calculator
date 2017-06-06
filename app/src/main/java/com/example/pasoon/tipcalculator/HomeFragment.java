package com.example.pasoon.tipcalculator;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"licon.ttf");

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String CT = preferences.getString("CurrencyType", "");
        String DTP = preferences.getString("DefaultTipPercentage", "");

        System.out.println("HomeFragment: "+CT);
        System.out.println("HomeFragment: "+DTP);

        EditText billAmount = (EditText)rootView.findViewById(R.id.BillAmount);
        EditText tipPercentage = (EditText)rootView.findViewById(R.id.TipPercentage);
        EditText numberOfPpl = (EditText)rootView.findViewById(R.id.NumberOfPpl);
        Button addPerson = (Button) rootView.findViewById(R.id.addPersonBtn);
        Button removePerson = (Button) rootView.findViewById(R.id.removePersonBtn);
        Button tipIt = (Button)rootView.findViewById(R.id.CalculateBtn);



        addPerson.setTypeface(myTypeface);
        removePerson.setTypeface(myTypeface);
        addPerson.setTextSize(60);
        removePerson.setTextSize(60);

        tipIt.setTypeface(myTypeface);
        tipIt.setTextSize(60);

        billAmount.setTypeface(myTypeface);
        tipPercentage.setTypeface(myTypeface);
        numberOfPpl.setTypeface(myTypeface);

        billAmount.setTextSize(40);
        tipPercentage.setTextSize(30);
        numberOfPpl.setTextSize(30);


        billAmount.setHint("Bill Amount in "+CT);
        tipPercentage.setText(DTP);

        return rootView;
    }
}


