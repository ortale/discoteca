package com.joseortale.ortalesoft.discoteca.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.joseortale.ortalesoft.discoteca.R;
import com.joseortale.ortalesoft.discoteca.view.fragments.AlbumsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlbumsFragment fragment = AlbumsFragment.newInstance();
        setFragment(fragment);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rootFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 0) {
            super.onBackPressed();
        } else  {
            getFragmentManager().popBackStack();
        }
    }
}