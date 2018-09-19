package com.battaglino.santiago.sweatworks.user.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.battaglino.santiago.sweatworks.base.mvvm.viewmodel.BaseViewModel;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.user.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Santiago Battaglino.
 */
public class UserGridViewModel extends BaseViewModel<User, UserRepository> {

    @Inject
    UserGridViewModel(Application application, UserRepository repository) {
        super(application);
        this.useCaseRepository = repository;
    }

    public LiveData<List<User>> getUsers() {
        return useCaseRepository.getDataList();
    }

    public LiveData<List<User>> getFavorites() {
        return useCaseRepository.getFavorites();
    }

    public void fetchUsersFromServer(int page) {
        useCaseRepository.fetchUsersFromServer(page);
    }

    public void addFavorite(User user) {
        useCaseRepository.addFavorite(user);
    }
}