package com.example.elisacapololo.kiandamuzik;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elisacapololo.kiandamuzik.api.Client;
import com.example.elisacapololo.kiandamuzik.api.ServiceGenerator;
import com.example.elisacapololo.kiandamuzik.api.models.PopularTracks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adapters.PopularTrackAdapter;
import models.Album;
import models.Artista;
import models.PopularTrackList;
import models.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalActivity extends BaseActivity {
    private static final String TAG = "PrincipalActivity";

    RecyclerView listaDeMusicaPopular;
    ArrayList<PopularTracks> popularTracksList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    ProgressBar progressBar;
    TextView aprincipalArtistName, principalTrackTitle;
    ImageView imagemArtist;
    Bundle songInfo;
    boolean popularLoaded = true;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        listaDeMusicaPopular = findViewById(R.id.listaDeMusicaPopular);
        progressBar = findViewById(R.id.progressBar2);
        aprincipalArtistName = findViewById(R.id.aprincipalArtistName);
        principalTrackTitle = findViewById(R.id.principalTrackTitle);
        imagemArtist = findViewById(R.id.imagemArtist);
        linearLayoutManager = new LinearLayoutManager(this);
        getPopularTracks();


    }


    private void InitializeRecycleView() {

        listaDeMusicaPopular.setLayoutManager(linearLayoutManager);
        PopularTrackAdapter adapter = new PopularTrackAdapter(this, popularTracksList);
        listaDeMusicaPopular.setAdapter(adapter);
        aprincipalArtistName.setText(popularTracksList.get(0).getArtist().get(0).getName());
        principalTrackTitle.setText(popularTracksList.get(0).getTrackTitle());
        Picasso.with(context).load(popularTracksList.get(0).getTrackCoverArt())
                .resize(512,512).into(imagemArtist);
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

    public void getPopularTracks(){
        Client.PopularTracks popularTracks = ServiceGenerator.createService(Client.PopularTracks.class);
        popularTracks.getPopularTracks().enqueue(new Callback<ArrayList<PopularTracks>>() {
            @Override
            public void onResponse(Call<ArrayList<PopularTracks>> call, Response<ArrayList<PopularTracks>> response) {
                progressBar.animate();
                if (response.code() == 200){
                    popularTracksList = response.body();
                    popularLoaded = true;
                    Log.d(TAG, "onResponse: " +response.body().size());
                    InitializeRecycleView();
                    progressBar.setVisibility(View.GONE);
                    songInfo = new Bundle();
                    songInfo.putParcelableArrayList("popular", popularTracksList);
                    new initNowPlayControls().doInBackground(songInfo);
                } else {
                    Log.e(TAG, "onResponse: code=> " +response.code() );
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PopularTracks>> call, Throwable t) {
                Log.e(TAG, "onFailure: Ocorreu um erro!");
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
