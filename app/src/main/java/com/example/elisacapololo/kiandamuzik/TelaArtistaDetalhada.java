package com.example.elisacapololo.kiandamuzik;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.ArtistTrackAdapter;
import adapters.PopularTracksConstants;
import models.Album;
import models.ArtistTrackList;
import models.Artista;
import models.Track;

public class TelaArtistaDetalhada extends AppCompatActivity {
    ImageView capaArtista;
    TextView nomeDoArtista;
    TextView descrisaoArtista;

    RecyclerView listaDeMusicas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaDeMusicas = findViewById(R.id.listaDeMusicas);
        setTitle("Força Suprema");
        capaArtista = findViewById(R.id.capaArtista);
        nomeDoArtista = findViewById(R.id.nomeDoArtista);
        descrisaoArtista = findViewById(R.id.descrisaoArtista);

        Bundle musicData = getIntent().getExtras();
        if (musicData != null){
            capaArtista.setImageResource(musicData.getInt(PopularTracksConstants.TRACK_COVER));
            setTitle(musicData.getString(PopularTracksConstants.ARTIST_TITLE));
            nomeDoArtista.setText(musicData.getString(PopularTracksConstants.ARTIST_TITLE));
            descrisaoArtista.setText(musicData.getString(PopularTracksConstants.ARTIST_DESCRIPTION));
        }

        ArtistTrackList fsTrackList = getArtistTrackList();
        inicializarRecycleView(fsTrackList);
    }

    private void inicializarRecycleView(ArtistTrackList fsTrackList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaDeMusicas.setLayoutManager(linearLayoutManager);
        ArtistTrackAdapter adapter = new ArtistTrackAdapter(this, fsTrackList);
        listaDeMusicas.setAdapter(adapter);
    }

    @NonNull
    private ArtistTrackList getArtistTrackList() {
        Artista fs = new Artista( 1, "Força Suprema", "descripton",
                                  "HipHop",  R.drawable.header,  true );
        Track urna = new Track();
        Album caveira = new Album( 1, "Caveira", fs.getId(),  "2017", "500,00kz");
        urna.setAlbum(caveira);
        urna.setArtist(fs);
        urna.setTrackCover(R.drawable.fs);
        urna.setId(1);
        urna.setaName("Urna");

        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(urna);
        return new ArtistTrackList(1, fs.getId(), tracks);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
