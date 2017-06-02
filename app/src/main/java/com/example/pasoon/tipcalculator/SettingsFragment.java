package com.example.pasoon.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by Pasoon on 2017-05-23.
 */

public class SettingsFragment extends Fragment {

    public static com.example.pasoon.tipcalculator.SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"licon.ttf");

        TextView SettingsTitle = (TextView) rootView.findViewById(R.id.SettingsText);
        TextView CurrencyTypeTitle = (TextView) rootView.findViewById(R.id.CurrencyTypeText);
        TextView DefaultTipPercentageTitle = (TextView) rootView.findViewById(R.id.DefaultTipPercentageText);

        SettingsTitle.setTypeface(myTypeface);
        SettingsTitle.setTextSize(70);
        CurrencyTypeTitle.setTypeface(myTypeface);
        CurrencyTypeTitle.setTextSize(50);
        DefaultTipPercentageTitle.setTypeface(myTypeface);
        DefaultTipPercentageTitle.setTextSize(50);



        Spinner CurrencySpinner = (Spinner) rootView.findViewById(R.id.CurrencySpinner);
        String[] items = new String[]{"$", "\u20ac", "\u00a3"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        CurrencySpinner.setAdapter(adapter);

        Spinner CurrencyType = (Spinner)rootView.findViewById(R.id.CurrencySpinner);
        String CT = CurrencyType.getSelectedItem().toString();

        EditText DefaultTipPercentage = (EditText)rootView.findViewById(R.id.DefaultTipPercentage);
        String DTP = DefaultTipPercentage.getText().toString();
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("CurrencyType",CT);
        editor.putString("DefaultTipPercentage",DTP);
        editor.apply();

        System.out.println(CT);
        System.out.println(DTP);

        return rootView;
    }
}
