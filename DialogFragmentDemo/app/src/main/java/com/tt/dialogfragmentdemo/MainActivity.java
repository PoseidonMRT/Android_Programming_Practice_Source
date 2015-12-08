package com.tt.dialogfragmentdemo;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new OrdinaryDialogFragment().show(getFragmentManager(), "dialog_fragment");

        //use FragmentTransaction or FragmentManager start a AlertDialogFragment
        FragmentTransaction ft =  this.getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        AlertDialogFragment alertdFragment = new AlertDialogFragment();
        // Show Alert AlertDialogFragment
//        alertdFragment.show(getFragmentManager(), "Alert Dialog Fragment");
        alertdFragment.show(ft, "Alert Dialog Fragment");
    }
}
