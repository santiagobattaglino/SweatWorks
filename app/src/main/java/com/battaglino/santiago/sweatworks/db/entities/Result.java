package com.battaglino.santiago.sweatworks.db.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Santiago Battaglino.
 */
public class Result<T> {

    @SerializedName("results")
    @Expose
    public List<T> results;
}
