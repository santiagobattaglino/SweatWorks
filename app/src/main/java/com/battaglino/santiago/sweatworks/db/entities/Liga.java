package com.battaglino.santiago.sweatworks.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import io.reactivex.annotations.NonNull;

/**
 * Created by Santiago Battaglino.
 */
@Parcel
@Entity(tableName = "ligas", indices = @Index(value = {"uid"}))
public class Liga {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("uid")
    @Expose
    public int uid;

    @SerializedName("leagueId")
    @Expose
    public String leagueId;

    @SerializedName("leagueName")
    @Expose
    public String leagueName;
    @SerializedName("queueType")
    @Expose
    public String queueType;
    @SerializedName("position")
    @Expose
    public String position;
    @SerializedName("tier")
    @Expose
    public String tier;
    @SerializedName("rank")
    @Expose
    public String rank;
    @SerializedName("leaguePoints")
    @Expose
    public Integer leaguePoints;
    @SerializedName("wins")
    @Expose
    public Integer wins;
    @SerializedName("losses")
    @Expose
    public Integer losses;
    @SerializedName("veteran")
    @Expose
    public Boolean veteran;
    @SerializedName("inactive")
    @Expose
    public Boolean inactive;
    @SerializedName("freshBlood")
    @Expose
    public Boolean freshBlood;
    @SerializedName("hotStreak")
    @Expose
    public Boolean hotStreak;
    @SerializedName("miniSeries")
    @Expose
    public MiniSeries miniSeries;
    @SerializedName("summonerId")
    @Expose
    public String summonerId;
    @SerializedName("summonerName")
    @Expose
    public String summonerName;

    @ParcelConstructor
    public Liga() {

    }

    @Override
    public String toString() {
        return "Liga{" +
                "leagueId='" + leagueId + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", queueType='" + queueType + '\'' +
                ", position='" + position + '\'' +
                ", tier='" + tier + '\'' +
                ", rank='" + rank + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", veteran=" + veteran +
                ", inactive=" + inactive +
                ", freshBlood=" + freshBlood +
                ", hotStreak=" + hotStreak +
                ", miniSeries=" + miniSeries +
                ", summonerId='" + summonerId + '\'' +
                ", summonerName='" + summonerName + '\'' +
                '}';
    }
}
