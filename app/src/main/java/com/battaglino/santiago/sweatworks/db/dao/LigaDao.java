package com.battaglino.santiago.sweatworks.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.battaglino.santiago.sweatworks.db.entities.Liga;
import com.battaglino.santiago.sweatworks.db.entities.User;

import java.util.List;

/**
 * Created by Santiago Battaglino.
 */
@Dao
public interface LigaDao {

    @Query("select * from ligas")
    LiveData<List<Liga>> loadList();

    @Insert
    void insertList(List<Liga> ligas);
}
