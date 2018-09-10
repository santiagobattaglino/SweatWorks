package com.battaglino.santiago.sweatworks.global.services;

import com.battaglino.santiago.sweatworks.db.entities.Result;
import com.battaglino.santiago.sweatworks.db.entities.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Santiago Battaglino.
 */
public interface RandomUserApiService {

    @GET("/api/")
    Observable<Result<User>> getUsers(
            @Query("results") int results,
            @Query("page") int page
    );
}
