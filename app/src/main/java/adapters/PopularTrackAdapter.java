package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elisacapololo.kiandamuzik.R;

import models.PopularTrackList;

/**
 * Created by Elisa Capololo on 18-12-2017.
 */

public class PopularTrackAdapter extends RecyclerView.Adapter<PopularTrackAdapter.ViewHolder> {

    private Context mContext;
    private PopularTrackList popularTrack;

    public PopularTrackAdapter(Context mContext, PopularTrackList popularTrack) {
        this.mContext = mContext;
        this.popularTrack = popularTrack;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mpopularTrackCover;
        public TextView mpopularArtistName;
        public TextView mpopularTrackName;
        public ImageView mpopularVerifiedBagde;
        public ImageView mpopularTrackDetails;
        public ViewHolder (View itemView){
            super(itemView);
            //Criamos os itens do nosso list_item layout
            mpopularTrackCover = itemView.findViewById(R.id.trackCover);
            mpopularArtistName = itemView.findViewById(R.id.artistName);
            mpopularTrackDetails = itemView.findViewById(R.id.trackDetails);
            mpopularTrackName = itemView.findViewById(R.id.trackName);
            mpopularVerifiedBagde = itemView.findViewById(R.id.verifiedAccount);
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
        holder.mpopularArtistName.setText(popularTrack.getPopularTracks().get(position).getArtist().getName());
        holder.mpopularTrackName.setText(popularTrack.getPopularTracks().get(position).getaName());
        holder.mpopularTrackCover.setImageResource(popularTrack.getPopularTracks().get(position).getTrackCover());
        if (popularTrack.getPopularTracks().get(position).getArtist().isVerified()){
            holder.mpopularVerifiedBagde.setImageResource(R.drawable.ic_verified_user_black_18dp);
        }else {
            //Não apresentamos o bagde pois o artista não foi verificado
        }

    }

    @Override
    public int getItemCount() {
        return popularTrack.getPopularTracks().size();
    }
}


