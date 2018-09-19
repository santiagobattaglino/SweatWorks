package com.battaglino.santiago.sweatworks.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.battaglino.santiago.sweatworks.db.entities.User;

import java.util.List;

/**
 * Created by Santiago Battaglino.
 */
@Dao
public interface UserDao {

    @Query("select * from users")
    LiveData<List<User>> loadList();

    @Insert
    void insertAll(List<User> users);

    @Query("select * from users where isFavorite = 1")
    LiveData<List<User>> loadListFavorites();

    @Query("select * from users where name like :query")
    LiveData<User> loadUserBySuggestion(String query);

    @Update
    void updateUser(User user);
}
