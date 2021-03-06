package fragments;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elisacapololo.kiandamuzik.R;
import com.example.elisacapololo.kiandamuzik.api.models.PopularTracks;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;

import widgets.PlayPauseButton;

/**
 * Created by Elisa Capololo on 15-01-2018.
 */

public class NowPlayingCard extends android.support.v4.app.Fragment implements MediaPlayer.OnPreparedListener{
   ImageView albumArt;
   ProgressBar progressBar;
   TextView mSongTitle;
   TextView mArtistTitle;
   PlayPauseButton play;
   ArrayList<PopularTracks> tracks;
   MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_bottom_now_playing_card, container, false);

        albumArt = v.findViewById(R.id.albumArt);
        progressBar = v.findViewById(R.id.progressBar);
        play = v.findViewById(R.id.play);
        mSongTitle = v.findViewById(R.id.mSongTitle);
        mArtistTitle = v.findViewById(R.id.mArtistTitle);
        tracks = getArguments().getParcelableArrayList("popular");
        Picasso.with(this.getContext()).load(tracks.get(0).getTrackCoverArt())
                    .resize(512,512).into(albumArt);
        mSongTitle.setText(tracks.get(0).getTrackTitle());
        mArtistTitle.setText(tracks.get(0).getArtist().get(0).getName());

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(tracks.get(0).getTrackUrl());
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        play.setPlayed(true);
        return v;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }
}
