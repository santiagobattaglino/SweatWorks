package com.battaglino.santiago.sweatworks.db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Santiago Battaglino.
 */
@Parcel
public class Picture {

    @SerializedName("large")
    @Expose
    public String large;

    @SerializedName("medium")
    @Expose
    public String medium;

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

    @ParcelConstructor
    public Picture() {

    }
}
