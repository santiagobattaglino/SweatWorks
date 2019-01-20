package com.battaglino.santiago.sweatworks.liga.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.battaglino.santiago.sweatworks.base.mvvm.viewmodel.BaseViewModel;
import com.battaglino.santiago.sweatworks.db.entities.Liga;
import com.battaglino.santiago.sweatworks.liga.repository.LigaRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Santiago Battaglino.
 */
public class LigaViewModel extends BaseViewModel<Liga, LigaRepository> {

    @Inject
    LigaViewModel(Application application, LigaRepository repository) {
        super(application);
        this.useCaseRepository = repository;
    }

    public LiveData<List<Liga>> getLigas() {
        return useCaseRepository.getDataList();
    }

    public void requestDataToServer() {
        useCaseRepository.requestDataToServer();
    }
}
