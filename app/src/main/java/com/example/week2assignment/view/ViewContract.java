package com.example.week2assignment.view;

import com.example.week2assignment.model.SongsApi;
import com.example.week2assignment.model.SongsPojo;

import java.util.List;

public interface ViewContract {
    void populateSongList();
    void onError(String message);
    void bindPresenter();
    void getSongList(SongsPojo dataSet);

}
