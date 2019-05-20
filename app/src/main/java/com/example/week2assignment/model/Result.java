package com.example.week2assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public Double getTrackPrice() {
        return trackPrice;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }
}
