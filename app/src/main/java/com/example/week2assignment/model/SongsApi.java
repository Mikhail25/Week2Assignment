package com.example.week2assignment.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongsApi {

    @GET("search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<SongsPojo> getRockSong();

    @GET("search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<SongsPojo> getClassicSong();

    @GET("search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<SongsPojo> getPopSong();

    @GET("search?media=music&amp;amp;entity=song&amp;amp;limit=50")
    Call<SongsPojo> getSongs(@Query("term") String songType);
}
