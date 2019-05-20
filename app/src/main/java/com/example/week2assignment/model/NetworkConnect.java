package com.example.week2assignment.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.week2assignment.presenter.Presenter;

import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnect {
    private static final String TAG = "NetworkConnect";
    //search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
    private static final String BASE_URL = "https://itunes.apple.com/";
    private static NetworkConnect instance;
    private static Presenter presenter;
    SongsApi api;

    public static NetworkConnect getInstance(){
        if (instance == null){
            instance = new NetworkConnect();
        }
        return instance;
    }

    public void setPresenter(Presenter presenter){
        instance.presenter = presenter;
    }

    public void initRetrofit(String term, Cache cache){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SongsApi.class);

        api.getSongs(term).enqueue(new Callback<SongsPojo>() {
            @Override
            public void onResponse(Call<SongsPojo> call, Response<SongsPojo> response) {
                Log.d(TAG, "onResponse: "+response.body());

                if (response.isSuccessful()&& response!= null)
                    presenter.netSucess(response.body());
            }

            @Override
            public void onFailure(Call<SongsPojo> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                presenter.netFailure(t.getMessage());
            }
        });
    }


}
