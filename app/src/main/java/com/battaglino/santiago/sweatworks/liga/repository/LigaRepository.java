package com.battaglino.santiago.sweatworks.liga.repository;

import android.app.Application;

import com.battaglino.santiago.sweatworks.base.repository.UseCaseRepository;
import com.battaglino.santiago.sweatworks.db.AppDatabase;
import com.battaglino.santiago.sweatworks.db.entities.Liga;
import com.battaglino.santiago.sweatworks.global.Constants;
import com.battaglino.santiago.sweatworks.global.services.LOLApiService;

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
public class LigaRepository extends UseCaseRepository<Liga> {

    private AppDatabase mDataBase;
    private LOLApiService mClient;
    private CompositeDisposable mDisposable;

    @Inject
    LigaRepository(Application context, LOLApiService client) {
        super(context);
        this.context = context;
        mClient = client;
        mDisposable = new CompositeDisposable();
    }

    public void initLocalData() {
        mDataBase = AppDatabase.getDatabaseBuilder(context);
        setDataList(mDataBase.ligaModel().loadList());
    }

    @Override
    public void addData(Liga liga) {

    }

    @Override
    public void addDataList(List<Liga> ligas) {
        mDataBase.ligaModel().insertList(ligas);
        setDataList(mDataBase.ligaModel().loadList());
    }

    @Override
    public void requestDataToServer() {
        mClient.getLigas(Constants.SUMMONER_ID, Constants.API_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Liga>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Liga> dataList) {
                        addDataList(dataList);
                        mDisposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
