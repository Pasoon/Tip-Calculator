package com.example.pasoon.tipcalculator;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Double tipAmount;
    private Double tipPerPerson;
    private Double billPerPerson;
    private Double personPays;

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
        System.out.println("CHECK THIS" +billAmount);
        EditText tipPercentage = (EditText)findViewById(R.id.TipPercentage);
        System.out.println("CHECK THIS" +billAmount);
        EditText numberOfPpl = (EditText)findViewById(R.id.NumberOfPpl);

        System.out.println("CHECK THIS" +billAmount);

        String bA = billAmount.getText().toString();
        String tP = tipPercentage.getText().toString();
        String nP = numberOfPpl.getText().toString();

        if(nP.matches("")){
            nP = "1";
        }

        if(bA.matches("") || tP.matches("")) {
            Context context = getApplicationContext();
            CharSequence text = "Please fill in all required values.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else{
        Double billAmountDouble = Double.parseDouble(bA);
        Double tipPercentageDouble = Double.parseDouble(tP);
        int numberOfPplInt = Integer.parseInt(nP);

            tipCalculations(billAmountDouble, tipPercentageDouble, numberOfPplInt);

            if(tipAmount != null){

                SummaryFragment frag = SummaryFragment.newInstance();
                Bundle args = new Bundle();
                args.putDouble("Bill Amount", billAmountDouble);
                args.putDouble("Tip Amount", tipAmount);
                if(numberOfPplInt > 1){
                    args.putDouble("Tip Per Person", tipPerPerson);
                    args.putDouble("Each Person Pays", personPays);
                }

                frag.setArguments(args);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, frag, frag.getTag());
                ft.commit();
            }
        }

    }

    private void tipCalculations(double billAmount, double tipPercentage, int numberOfPpl){

        tipPercentage = tipPercentage/100;
        tipAmount = billAmount * tipPercentage;

        if(numberOfPpl > 1) {
            tipPerPerson = tipAmount/numberOfPpl;
            billPerPerson = billAmount/numberOfPpl;
            personPays = tipPerPerson + billPerPerson;

        }

    }


}
