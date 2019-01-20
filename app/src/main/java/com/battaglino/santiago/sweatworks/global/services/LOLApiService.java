package com.battaglino.santiago.sweatworks.global.services;

import com.battaglino.santiago.sweatworks.db.entities.Liga;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Santiago Battaglino.
 */
public interface LOLApiService {

    @GET("/lol/league/v4/positions/by-summoner/LGQWMD8CPwDndhObffAtIzoGMRj0Yqf6NOkPS-QiyMzy/")
    Observable<List<Liga>> getLigas(
            @Query("api_key") String apiKey
    );
}
