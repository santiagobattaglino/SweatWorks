package com.battaglino.santiago.sweatworks.base.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.battaglino.santiago.sweatworks.base.repository.UseCaseRepository;

/**
 * Created by Santiago Battaglino.
 *
 * @typeparam T This represents your model entity
 * @typeparam R This represents your repository that manages your T elements
 */

public abstract class BaseViewModel<T, R extends UseCaseRepository<T>> extends AndroidViewModel {

    protected R useCaseRepository;

    public BaseViewModel(Application application) {
        super(application);
    }
}
