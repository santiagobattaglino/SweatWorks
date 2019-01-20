package com.battaglino.santiago.sweatworks.liga.mvvm.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.mvvm.view.BaseView;
import com.battaglino.santiago.sweatworks.db.entities.Liga;
import com.battaglino.santiago.sweatworks.liga.activity.LigaActivity;
import com.battaglino.santiago.sweatworks.liga.adapter.LigaAdapter;
import com.battaglino.santiago.sweatworks.liga.mvvm.viewmodel.LigaViewModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class LigaView extends BaseView<LigaActivity, LigaViewModel>
        implements LigaAdapter.OnViewHolderClick, MaterialSearchView.OnQueryTextListener {

    @BindView(R.id.search_view)
    public MaterialSearchView mSearchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private LigaAdapter mAdapter;

    private List<Liga> mLigas = new ArrayList<>();

    public LigaView(LigaActivity activity, LigaViewModel viewModel) {
        super(activity, viewModel);
        ButterKnife.bind(this, activity);

        setUpNavigation(toolbar);
        setUpSearchView();
        setUpList();
    }

    @Override
    protected void subscribeUiToLiveData() {
        subscribeLigas();
    }

    @Override
    protected void showDataInUi() {

    }

    @Override
    public void onClick(View view, int position, Liga liga) {

    }

    private void subscribeLigas() {
        baseViewModel.getLigas().observe(baseActivity.get(), ligas -> {
            if (ligas == null || ligas.size() <= 0) {
                baseViewModel.requestDataToServer();
            } else {
                mLigas = ligas;
                mAdapter.addAll(mLigas);
            }
        });
    }

    private void setUpList() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(baseActivity.get(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new LigaAdapter(baseActivity.get(), this, mLigas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUpSearchView() {
        mSearchView.setEllipsize(true);
        mSearchView.setOnQueryTextListener(this);
    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
