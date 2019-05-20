package com.example.week2assignment.view;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.week2assignment.R;
import com.example.week2assignment.model.SongsPojo;
import com.example.week2assignment.presenter.Presenter;

import okhttp3.Cache;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassicFragment extends Fragment implements ViewContract, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ClassicFragment";
    private Presenter presenter;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private static final String TERM = "classick";
    SwipeRefreshLayout swipeClassicMusic;

    public ClassicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(
                R.layout.fragment_classic, container, false);

        swipeClassicMusic= rootView.findViewById(R.id.swipeClassicMusic);
        swipeClassicMusic.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_dark,
                android.R.color.holo_purple,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_dark
        );
        swipeClassicMusic.setOnRefreshListener(this);

        recyclerView = rootView.findViewById(R.id.rv_classic_songs);
        adapter = new RecyclerAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        bindPresenter();
        populateSongList();

        return rootView;
    }

    @Override
    public void populateSongList() {

        if (hasNetwork()) {
            long cacheLength = (5 * 1026 * 1026);
            Cache myCache = new Cache(getActivity().getCacheDir(), cacheLength);

            Log.d(TAG, "populateSongList: Populating list");
            presenter.initNetConnection(TERM, myCache);
        }else{
            onError("No network found");
            swipeClassicMusic.setRefreshing(false);
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void bindPresenter() {
        presenter = new Presenter();
        presenter.bind(this);
    }

    @Override
    public void getSongList(SongsPojo dataSet) {
        Log.d(TAG, "getSongList: Got all the songs!");

        swipeClassicMusic.setRefreshing(false);
        adapter.setDataSet(dataSet,getContext());
    }

    public boolean hasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnected();
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getContext(),"Refreshing...",Toast.LENGTH_SHORT).show();
        populateSongList();
    }
}
