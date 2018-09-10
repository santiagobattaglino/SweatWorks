package com.battaglino.santiago.sweatworks.db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Santiago Battaglino.
 */
@Parcel
public class Name {

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("first")
    @Expose
    public String first;

    @SerializedName("last")
    @Expose
    public String last;
}
