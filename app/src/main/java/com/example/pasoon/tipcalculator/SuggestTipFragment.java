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
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.pasoon.tipcalculator.R.id.SuggestTip;


/**
 * Created by Pasoon on 2017-05-23.
 */

public class SuggestTipFragment extends Fragment {

    public static SuggestTipFragment newInstance() {
        SuggestTipFragment fragment = new SuggestTipFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stip, container, false);

        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        final TextView SuggestedTip = (TextView)rootView.findViewById(SuggestTip);
        RatingBar rb = (RatingBar) rootView.findViewById(R.id.ratingbar);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                    int TipPercentage = (int) (rating*2)+10;
                    String TP = Integer.toString(TipPercentage);
                    SuggestedTip.setText(TP+"%");

                    editor.putString("DefaultTipPercentage",TP);
                    editor.apply();


            }
        });




        return rootView;
    }
}


