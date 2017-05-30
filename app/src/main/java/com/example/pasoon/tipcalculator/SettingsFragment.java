package com.example.pasoon.tipcalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



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
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Spinner CurrencySpinner = (Spinner) rootView.findViewById(R.id.CurrencySpinner);
        String[] items = new String[]{"$", "\u20ac", "\u00a3"};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        CurrencySpinner.setAdapter(adapter);

        Spinner CurrencyType = (Spinner)rootView.findViewById(R.id.CurrencySpinner);
        String CT = CurrencyType.getSelectedItem().toString();

        EditText DefaultTipPercentage = (EditText)rootView.findViewById(R.id.DefaultTipPercentage);
        String DTP = DefaultTipPercentage.getText().toString();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("CurrencyType",CT);
        editor.putString("DefaultTipPercentage",DTP);
        editor.apply();

        return rootView;
    }
}
