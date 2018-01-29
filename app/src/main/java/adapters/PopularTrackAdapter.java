package adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elisacapololo.kiandamuzik.PrincipalActivity;
import com.example.elisacapololo.kiandamuzik.R;
import com.example.elisacapololo.kiandamuzik.TelaArtistaDetalhada;
import com.example.elisacapololo.kiandamuzik.api.Client;
import com.example.elisacapololo.kiandamuzik.api.models.PopularTracks;
import com.squareup.picasso.Picasso;

import java.util.List;

import models.PopularTrackList;

/**
 * Created by Elisa Capololo on 18-12-2017.
 */

public class PopularTrackAdapter extends RecyclerView.Adapter<PopularTrackAdapter.ViewHolder> {

    private static Context mContext;
    //private PopularTrackList popularTrack;
    List<PopularTracks> popularTracksList;

    public PopularTrackAdapter(Context mContext, List<PopularTracks> popularTracksList) {
        this.mContext = mContext;
        //this.popularTrack = popularTrack;
        this.popularTracksList = popularTracksList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mpopularTrackCover;
        public TextView mpopularArtistName;
        public TextView mpopularTrackName;
        public ImageView mpopularVerifiedBagde;
        public ImageView mpopularTrackDetails;
        View.OnClickListener mListener;
        public ViewHolder (View itemView){
            super(itemView);
            //Criamos os itens do nosso list_item layout
            mpopularTrackCover = itemView.findViewById(R.id.trackCover);
            mpopularArtistName = itemView.findViewById(R.id.artistName);
            mpopularTrackDetails = itemView.findViewById(R.id.trackDetails);
            mpopularTrackName = itemView.findViewById(R.id.trackName);
            mpopularVerifiedBagde = itemView.findViewById(R.id.verifiedAccount);
            mListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, PrincipalActivity.class));
                }
            };
        }
    }
    //Metódos obrigatorios quando usamos o RecyclerView

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
       View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
       ViewHolder holder = new ViewHolder(v);
       return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mpopularArtistName.setText(popularTracksList.get(position).getArtist().get(0).getName());
        holder.mpopularTrackName.setText(popularTracksList.get(position).getTrackTitle());
        Picasso.with(mContext).load(popularTracksList.get(position).getTrackCoverArt())
                .resize(512,512).into(holder.mpopularTrackCover);
        holder.mpopularVerifiedBagde.setImageResource(R.drawable.ic_verified_user_black_18dp);
      /*
        holder.mpopularArtistName.setText(popularTrack.getPopularTracks().get(position).getArtist().getName());
        holder.mpopularTrackName.setText(popularTrack.getPopularTracks().get(position).getaName());
        holder.mpopularTrackCover.setImageResource(popularTrack.getPopularTracks().get(position).getTrackCover());
        if (popularTrack.getPopularTracks().get(position).getArtist().isVerified()){
            holder.mpopularVerifiedBagde.setImageResource(R.drawable.ic_verified_user_black_18dp);
        }else {
            //Não apresentamos o bagde pois o artista não foi verificado
        }*/

        Bundle musicData = new Bundle();

        musicData.putString(PopularTracksConstants.ARTIST_TITLE, popularTracksList.get(position).getArtist().get(0).getName());
        musicData.putString(PopularTracksConstants.TRACK_TITLE, popularTracksList.get(position).getTrackTitle());
        musicData.putString(PopularTracksConstants.TRACK_COVER, popularTracksList.get(position).getTrackCoverArt());
        musicData.putString(PopularTracksConstants.ARTIST_DESCRIPTION, popularTracksList.get(position).getArtist().get(0).getDescription());


        /*musicData.putString(PopularTracksConstants.ARTIST_TITLE, popularTrack.getPopularTracks().get(position).getArtist().getName());
        musicData.putString(PopularTracksConstants.TRACK_TITLE, popularTrack.getPopularTracks().get(position).getaName());
        musicData.putInt(PopularTracksConstants.TRACK_COVER, popularTrack.getPopularTracks().get(position).getTrackCover());
        musicData.putString(PopularTracksConstants.ARTIST_DESCRIPTION, popularTrack.getPopularTracks().get(position).getArtist().getDescription());
        */
        final Intent intent = new Intent(mContext, TelaArtistaDetalhada.class);
        intent.putExtras(musicData);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularTracksList.size();
        // popularTrack.getPopularTracks().size();
    }
}


