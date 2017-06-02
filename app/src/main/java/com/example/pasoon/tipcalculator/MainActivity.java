package com.example.pasoon.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Double tipAmount;
    private Double tipPerPerson;
    private Double billPerPerson;
    private Double personPays;
    private Fragment frag;
    private int ppl;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectFragment(item);

            return true;
        }

    };

    private void selectFragment(MenuItem item){
        frag = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                frag = HomeFragment.newInstance();
                break;

            case R.id.navigation_stip:
                frag = SuggestTipFragment.newInstance();
                break;

            case R.id.navigation_settings:
                frag = SettingsFragment.newInstance();
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

    //Minimizes keyboard when touch on screen
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void updateSettingsButtonClicked(View v){
        Spinner mSpinner = (Spinner) findViewById(R.id.CurrencySpinner);
        String CT = mSpinner.getSelectedItem().toString();
        EditText DefaultTipPercentage = (EditText) findViewById(R.id.DefaultTipPercentage);
        String DTP = DefaultTipPercentage.getText().toString();

        System.out.println("THIS ONE: "+CT);
        System.out.println("ZARIF: "+DTP);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("CurrencyType", CT);
        editor.putString("DefaultTipPercentage", DTP);
        editor.apply();

        Context context = getApplicationContext();
        CharSequence text = "Settings Updated!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }


    public void calculateButtonClicked(View v){

        EditText billAmount = (EditText)findViewById(R.id.BillAmount);
        EditText tipPercentage = (EditText)findViewById(R.id.TipPercentage);
        EditText numberOfPpl = (EditText)findViewById(R.id.NumberOfPpl);

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

                SummaryFragment frag = new SummaryFragment();
                Bundle args = new Bundle();
                args.putDouble("Bill Amount", billAmountDouble);
                args.putDouble("Tip Amount", tipAmount);
                if(numberOfPplInt > 1){
                    args.putDouble("Tip Per Person", tipPerPerson);
                    args.putDouble("Each Person Pays", personPays);
                }


                frag.setCancelable(true);
                frag.setArguments(args);
                frag.show(getSupportFragmentManager(), frag.getTag());

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

    public void addPersonBtnClicked(View v){
        System.out.println("Here1");
        EditText numberOfPpl = (EditText)findViewById(R.id.NumberOfPpl);
        System.out.println("Here2: "+numberOfPpl);
        String numOfPpl = numberOfPpl.getText().toString();
        System.out.println("Here3: "+numOfPpl);
        if(numOfPpl == "" || numberOfPpl == null){
            numberOfPpl.setText("1");
            ppl=1;
        }
        else{
            ppl = ppl + 1;
            numOfPpl = Integer.toString(ppl);
            numberOfPpl.setText(numOfPpl);
        }
    }

    public void removePersonBtnClicked(View v){
        EditText numberOfPpl = (EditText)findViewById(R.id.NumberOfPpl);
        String numOfPpl = numberOfPpl.getText().toString();
        if(numOfPpl == "0" || numOfPpl == null){

        }

        else{
            ppl = ppl - 1;
            numOfPpl = Integer.toString(ppl);
            numberOfPpl.setText(numOfPpl);
        }

    }

    public void checkOutBtnClicked(View v){

    }

}
