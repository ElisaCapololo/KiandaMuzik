package com.example.elisacapololo.kiandamuzik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.elisacapololo.kiandamuzik.api.Client;
import com.example.elisacapololo.kiandamuzik.api.ServiceGenerator;
import com.example.elisacapololo.kiandamuzik.api.models.PopularTracks;

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
    List<PopularTracks> popularTracksList;
    LinearLayoutManager linearLayoutManager;
    boolean popularLoaded = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        listaDeMusicaPopular = findViewById(R.id.listaDeMusicaPopular);
        linearLayoutManager = new LinearLayoutManager(this);
        getPopularTracks();


        new initNowPlayControls().execute("");
    }

    private void InitializeRecycleView() {

        listaDeMusicaPopular.setLayoutManager(linearLayoutManager);
        PopularTrackAdapter adapter = new PopularTrackAdapter(this, popularTracksList);
        listaDeMusicaPopular.setAdapter(adapter);
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
        popularTracks.getPopularTracks().enqueue(new Callback<List<PopularTracks>>() {
            @Override
            public void onResponse(Call<List<PopularTracks>> call, Response<List<PopularTracks>> response) {
                if (response.code() == 200){
                    popularTracksList = response.body();
                    popularLoaded = true;
                    Log.d(TAG, "onResponse: " +response.body().size());
                    InitializeRecycleView();
                }else {
                    Log.e(TAG, "onResponse: code=> " +response.code() );
                }
            }

            @Override
            public void onFailure(Call<List<PopularTracks>> call, Throwable t) {
                Log.e(TAG, "onFailure: Ocorreu um erro!");
            }
        });
    }

}
