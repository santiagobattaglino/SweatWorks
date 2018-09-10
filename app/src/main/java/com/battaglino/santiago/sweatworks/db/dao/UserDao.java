package com.battaglino.santiago.sweatworks.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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
}
