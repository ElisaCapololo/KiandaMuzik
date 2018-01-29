package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.elisacapololo.kiandamuzik.R;

import org.jetbrains.annotations.Nullable;

import widgets.PlayPauseButton;

/**
 * Created by Elisa Capololo on 15-01-2018.
 */

public class NowPlayingCard extends android.support.v4.app.Fragment{
   ImageView albumArt;
   ProgressBar progressBar;
   TextView mSongTitle;
   TextView mArtistTitle;
   PlayPauseButton play;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_bottom_now_playing_card, container, false);

        albumArt = v.findViewById(R.id.albumArt);
        progressBar = v.findViewById(R.id.progressBar);
        play = v.findViewById(R.id.play);
        mSongTitle = v.findViewById(R.id.mSongTitle);
        mArtistTitle = v.findViewById(R.id.mArtistTitle);

        return v;
    }
}
