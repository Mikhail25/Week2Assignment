package com.example.week2assignment.presenter;


import com.example.week2assignment.model.SongsPojo;
import com.example.week2assignment.view.ViewContract;

import java.util.List;

import okhttp3.Cache;

public interface PresenterContract {
    void bind(ViewContract view);
    void unBind();
    void initNetConnection(String term,Cache cache);
    void netSucess(SongsPojo dataSet);
    void netFailure(String error);
}
