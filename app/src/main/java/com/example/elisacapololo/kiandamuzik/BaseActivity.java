package com.example.elisacapololo.kiandamuzik;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import fragments.NowPlayingCard;

/**
 * Created by Elisa Capololo on 17-01-2018.
 */

public class BaseActivity extends AppCompatActivity {
    public class initNowPlayControls extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings){
            NowPlayingCard nowPlayingCard = new NowPlayingCard();
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.quick_controls_container, nowPlayingCard).commitAllowingStateLoss();
            return "Executed";
        }
    }
}
