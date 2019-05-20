package com.example.week2assignment.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week2assignment.R;
import com.example.week2assignment.model.SongsPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private static final String TAG = "RecyclerAdapter";
    private SongsPojo dataSet;
    private Context context;

    public void setDataSet(SongsPojo dataSet,Context context){
        this.context = context;
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private ImageView songImage;
        private TextView songTitle, songArtistName, songCurrency;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            songImage = itemView.findViewById(R.id.iv_song);
            songTitle = itemView.findViewById(R.id.tv_song_name);
            songArtistName = itemView.findViewById(R.id.tv_artist_name);
            songCurrency = itemView.findViewById(R.id.tv_view_price);

            itemView.setOnClickListener(view->playMusic());
        }

        private void playMusic() {
            int pos = getLayoutPosition();

            String musicUrl = dataSet.getResults().get(pos).getPreviewUrl();

            Log.e(TAG, "playMusic: "+musicUrl+" now playing!" );

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(musicUrl),"audio/*");
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater
        .from(viewGroup.getContext()).inflate(
                R.layout.song_item_layout,viewGroup,false
                ));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        recyclerViewHolder.songTitle.setText(dataSet.getResults().get(i).getCollectionName());
        recyclerViewHolder.songArtistName.setText(dataSet.getResults().get(i).getArtistName());
        recyclerViewHolder.songCurrency.setText(String.valueOf(dataSet.getResults().get(i).getTrackPrice()));


        recyclerViewHolder.songCurrency.append(" USD");

        Picasso.get()
                .load(dataSet.getResults().get(i).getArtworkUrl60())
                .error(R.drawable.error_image_generic)
                .into(recyclerViewHolder.songImage);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.getResults().size() : 0;
    }
}
