package com.example.elisacapololo.kiandamuzik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import adapters.PopularTrackAdapter;
import models.Album;
import models.Artista;
import models.PopularTrackList;
import models.Track;

public class PrincipalActivity extends BaseActivity {

    RecyclerView listaDeMusicaPopular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listaDeMusicaPopular = findViewById(R.id.listaDeMusicaPopular);
        setTitle("Neru Americano");
        Artista ps = new Artista(1, "Neru Americano", "Cantor desde 2017, começou a carreira como animador de festas",
                                 "Kuduro", R.drawable.big_shaq_track, false);
        Track selfie = new Track();
        Album capaDura = new Album(1, "CapaDura", ps.getId(), "2017", "1.000kz");
        selfie.setAlbum(capaDura);
        selfie.setArtist(ps);
        selfie.setTrackCover(R.drawable.fs);
        selfie.setId(1);
        selfie.setaName("CapaDura");

        ArrayList<Track> tracksPopular = new ArrayList<>();
        tracksPopular.add(selfie);
        PopularTrackList pTrackList = new PopularTrackList(1, ps.getId(), tracksPopular);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaDeMusicaPopular.setLayoutManager(linearLayoutManager);
        PopularTrackAdapter adapter = new PopularTrackAdapter(this, pTrackList);
        listaDeMusicaPopular.setAdapter(adapter);
        new initNowPlayControls().execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_acerca){
            startActivity(new Intent(this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
