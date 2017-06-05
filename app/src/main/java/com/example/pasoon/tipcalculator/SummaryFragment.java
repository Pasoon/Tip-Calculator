package com.example.pasoon.tipcalculator;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


/**
 * Created by Pasoon on 2017-05-23.
 */

public class SummaryFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"licon.ttf");

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        
        String CT = preferences.getString("CurrencyType", "");

        Double billAmount = getArguments().getDouble("Bill Amount");
        Double tipAmount = getArguments().getDouble("Tip Amount");
        Double totalAmount = billAmount + tipAmount;
        Double tipPerPerson = getArguments().getDouble("Tip Per Person");
        Double personPays = getArguments().getDouble("Each Person Pays");
        Button done = (Button)rootView.findViewById(R.id.OkBtn);
        done.setTypeface(myTypeface);
        done.setTextSize(50);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        TextView title = (TextView) rootView.findViewById(R.id.SummaryTitle);
        title.setTypeface(myTypeface);
        title.setTextSize(70);

        TextView billAmountHeader = (TextView) rootView.findViewById(R.id.textViewBA);
        TextView tipAmountHeader = (TextView) rootView.findViewById(R.id.textViewTA);
        TextView totalAmountHeader = (TextView) rootView.findViewById(R.id.textViewTotal);
        TextView tipPerPersonHeader = (TextView) rootView.findViewById(R.id.textViewTPP);
        TextView personPaysHeader = (TextView) rootView.findViewById(R.id.textViewEPP);

        TextView billAmountText = (TextView) rootView.findViewById(R.id.BillAmountValue);
        TextView tipAmountText = (TextView)rootView.findViewById(R.id.TipAmountValue);
        TextView totalAmountText = (TextView)rootView.findViewById(R.id.TotalAmountValue);
        TextView tipPerPersonText = (TextView)rootView.findViewById(R.id.TipPerPersonValue);
        TextView personPaysText = (TextView)rootView.findViewById(R.id.EachPersonPaysValue);

        billAmountHeader.setTypeface(myTypeface);
        billAmountHeader.setTextSize(35);
        tipAmountHeader.setTypeface(myTypeface);
        tipAmountHeader.setTextSize(35);
        totalAmountHeader.setTypeface(myTypeface);
        totalAmountHeader.setTextSize(35);
        tipPerPersonHeader.setTypeface(myTypeface);
        tipPerPersonHeader.setTextSize(35);
        personPaysHeader.setTypeface(myTypeface);
        personPaysHeader.setTextSize(35);

        billAmountText.setTypeface(myTypeface);
        billAmountText.setTextSize(35);
        tipAmountText.setTypeface(myTypeface);
        tipAmountText.setTextSize(35);
        totalAmountText.setTypeface(myTypeface);
        totalAmountText.setTextSize(35);
        tipPerPersonText.setTypeface(myTypeface);
        tipPerPersonText.setTextSize(35);
        personPaysText.setTypeface(myTypeface);
        personPaysText.setTextSize(35);




        if(tipPerPerson != 0){
            billAmountText.setText("................"+new DecimalFormat("##.##").format(Double.parseDouble(billAmount.toString()))+CT);
            tipAmountText.setText("..................."+new DecimalFormat("##.##").format(Double.parseDouble(tipAmount.toString()))+CT);
            totalAmountText.setText("..................................."+new DecimalFormat("##.##").format(Double.parseDouble(totalAmount.toString()))+CT);
            tipPerPersonText.setText(" "+new DecimalFormat("##.##").format(Double.parseDouble(tipPerPerson.toString()))+CT);
            personPaysText.setText(new DecimalFormat("##.##").format(Double.parseDouble(personPays.toString()))+CT);
        }

        else{
            billAmountText.setText("................"+new DecimalFormat("##.##").format(Double.parseDouble(billAmount.toString()))+CT);
            tipAmountText.setText("..................."+new DecimalFormat("##.##").format(Double.parseDouble(tipAmount.toString()))+CT);
            totalAmountText.setText("..................................."+new DecimalFormat("##.##").format(Double.parseDouble(totalAmount.toString()))+CT);
            tipPerPersonHeader.setText("");
            personPaysHeader.setText("");
            tipPerPersonText.setText("");
            personPaysText.setText("");
        }


        return rootView;
    }



}


