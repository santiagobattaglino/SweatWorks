package com.battaglino.santiago.sweatworks.db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Santiago Battaglino.
 */
@Parcel
public class MiniSeries {

    @SerializedName("target")
    @Expose
    public Integer target;
    @SerializedName("wins")
    @Expose
    public Integer wins;
    @SerializedName("losses")
    @Expose
    public Integer losses;
    @SerializedName("progress")
    @Expose
    public String progress;

    @ParcelConstructor
    public MiniSeries() {

    }

    @Override
    public String toString() {
        return "MiniSeries{" +
                "target=" + target +
                ", wins=" + wins +
                ", losses=" + losses +
                ", progress='" + progress + '\'' +
                '}';
    }
}
