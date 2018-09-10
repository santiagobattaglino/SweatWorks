package com.battaglino.santiago.sweatworks.user.repository;

import android.app.Application;
import android.util.Log;

import com.battaglino.santiago.sweatworks.base.repository.UseCaseRepository;
import com.battaglino.santiago.sweatworks.db.AppDatabase;
import com.battaglino.santiago.sweatworks.db.entities.Result;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.global.services.RandomUserApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Santiago Battaglino.
 */
public class UserRepository extends UseCaseRepository<User> {

    private AppDatabase mDataBase;
    private RandomUserApiService mClient;
    private CompositeDisposable mDisposable;

    @Inject
    UserRepository(Application context, RandomUserApiService client) {
        super(context);
        this.context = context;
        mClient = client;
        mDisposable = new CompositeDisposable();
    }

    public void initLocalData() {
        mDataBase = AppDatabase.getInMemoryDatabase(context);
        setDataList(mDataBase.userModel().loadList());
    }

    @Override
    public void addData(User user) {

    }

    @Override
    public void addDataList(List<User> users) {
        mDataBase.userModel().insertAll(users);
        setDataList(mDataBase.userModel().loadList());
    }

    @Override
    public void requestDataToServer() {
        mClient.getUsers(50, 1)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(Result<User> dataListFromServer) {
                        addDataList(dataListFromServer.results);
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("repo", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("repo", "onComplete");
                    }
                });
    }
}
