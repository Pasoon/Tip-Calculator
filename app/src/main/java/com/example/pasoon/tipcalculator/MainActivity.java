package com.example.pasoon.tipcalculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Double tipAmount;
    private Double tipPerPerson;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectFragment(item);
            return true;
        }

    };

    private void selectFragment(MenuItem item){
        Fragment frag = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = HomeFragment.newInstance();
                Log.i("yes","HomeFragment HERE!");
                break;
            case R.id.navigation_settings:
                frag = SettingsFragment.newInstance();
                Log.i("yes","SettingsFragment HERE!");
                break;
        }

        if (frag != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        HomeFragment frag = HomeFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, frag, frag.getTag());
        ft.commit();


    }


    public void calculateButtonClicked(View v){
        Log.i("Test", "TIP IT CLICKED!!!!");

        EditText billAmount = (EditText)findViewById(R.id.BillAmount);
        EditText tipPercentage = (EditText)findViewById(R.id.TipPercentage);
        EditText numberOfPpl = (EditText)findViewById(R.id.NumberOfPpl);

        Double billAmountDouble = Double.parseDouble(billAmount.getText().toString());
        Double tipPercentageDouble = Double.parseDouble(tipPercentage.getText().toString());
        int numberOfPplInt = Integer.parseInt(numberOfPpl.getText().toString());

        tipCalculations(billAmountDouble, tipPercentageDouble, numberOfPplInt);

    }

    private void tipCalculations(double billAmount, double tipPercentage, int numberOfPpl){

        tipPercentage = tipPercentage/100;
        tipAmount = billAmount * tipPercentage;

        if(numberOfPpl > 1) {
        tipPerPerson = tipAmount/numberOfPpl;
        }

        System.out.println("tip Amount: "+tipAmount);
        System.out.println("tip per person: "+tipPerPerson);


    }

}
