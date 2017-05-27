package com.example.pasoon.tipcalculator;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Pasoon on 2017-05-23.
 */

public class SummaryFragment extends Fragment {

    public static SummaryFragment newInstance() {
        SummaryFragment fragment = new SummaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);

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
            billAmountText.setText(billAmount.toString());
            tipAmountText.setText(tipAmount.toString());
            totalAmountText.setText(totalAmount.toString());
            tipPerPersonText.setText(tipPerPerson.toString());
            personPaysText.setText(personPays.toString());
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


