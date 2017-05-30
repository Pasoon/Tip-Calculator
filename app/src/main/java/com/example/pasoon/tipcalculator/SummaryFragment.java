package com.example.pasoon.tipcalculator;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * Created by Pasoon on 2017-05-23.
 */

public class SummaryFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String CT = preferences.getString("CurrencyType", "");

//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int width = (int)(dm.widthPixels*0.7);
//        int height = (int)(dm.heightPixels*0.7);
//        getDialog().getWindow().setLayout(width,height);

        Double billAmount = getArguments().getDouble("Bill Amount");
        Double tipAmount = getArguments().getDouble("Tip Amount");
        Double totalAmount = billAmount + tipAmount;
        Double tipPerPerson = getArguments().getDouble("Tip Per Person");
        Double personPays = getArguments().getDouble("Each Person Pays");


        TextView billAmountText = (TextView) rootView.findViewById(R.id.BillAmountValue);
        TextView tipAmountText = (TextView)rootView.findViewById(R.id.TipAmountValue);
        TextView totalAmountText = (TextView)rootView.findViewById(R.id.TotalAmountValue);

        TextView tipPerPersonText = (TextView)rootView.findViewById(R.id.TipPerPersonValue);
        TextView personPaysText = (TextView)rootView.findViewById(R.id.EachPersonPaysValue);

        TextView tipPerPersonCaption = (TextView)rootView.findViewById(R.id.textView7);
        TextView personPaysCaption = (TextView)rootView.findViewById(R.id.textView8);


        if(tipPerPerson != null){
            billAmountText.setText(new DecimalFormat("##.##").format(Double.parseDouble(billAmount.toString()))+CT);
            tipAmountText.setText(new DecimalFormat("##.##").format(Double.parseDouble(tipAmount.toString()))+CT);
            totalAmountText.setText(new DecimalFormat("##.##").format(Double.parseDouble(totalAmount.toString()))+CT);
            tipPerPersonText.setText(new DecimalFormat("##.##").format(Double.parseDouble(tipPerPerson.toString()))+CT);
            personPaysText.setText(new DecimalFormat("##.##").format(Double.parseDouble(personPays.toString()))+CT);
        }

        else{
            billAmountText.setText(billAmount.toString());
            tipAmountText.setText(tipAmount.toString());
            totalAmountText.setText(totalAmount.toString());
            tipPerPersonText.setText("");
            personPaysText.setText("");
        }


        return rootView;
    }



}


