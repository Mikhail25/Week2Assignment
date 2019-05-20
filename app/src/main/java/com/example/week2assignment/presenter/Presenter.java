package com.example.week2assignment.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.example.week2assignment.model.NetworkConnect;
import com.example.week2assignment.model.SongsApi;
import com.example.week2assignment.model.SongsPojo;
import com.example.week2assignment.view.ViewContract;

import java.util.List;

import okhttp3.Cache;

public class Presenter implements PresenterContract{
    private ViewContract viewContract;
    private List<SongsPojo> dataSet;

    @Override
    public void bind(ViewContract view) {
        this.viewContract = view;
    }

    @Override
    public void unBind() {
        viewContract = null;
        dataSet = null;
    }

    @Override
    public void initNetConnection(String term,Cache cache) {
        NetworkConnect network = NetworkConnect.getInstance();
        network.setPresenter(this);
        network.initRetrofit(term, cache);
    }

    @Override
    public void netSucess(SongsPojo dataSet) {
        viewContract.getSongList(dataSet);
    }


    @Override
    public void netFailure(String error) {
        viewContract.onError(error);
    }




}
