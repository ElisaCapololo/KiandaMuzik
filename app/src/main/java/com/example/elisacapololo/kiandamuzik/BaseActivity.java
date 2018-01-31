package com.example.elisacapololo.kiandamuzik;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fragments.NowPlayingCard;

/**
 * Created by Elisa Capololo on 17-01-2018.
 */

public class BaseActivity extends AppCompatActivity {
    public class initNowPlayControls extends AsyncTask<Bundle, Void, String> {
        @Override
        protected String doInBackground(Bundle... bundles){
            NowPlayingCard nowPlayingCard = new NowPlayingCard();
            nowPlayingCard.setArguments(bundles[0]);
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.quick_controls_container, nowPlayingCard).commitAllowingStateLoss();
            return "Executed";
        }
    }
}
