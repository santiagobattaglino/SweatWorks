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

    @BindView(R.id.recyclerviewHorizontal)
    RecyclerView recyclerviewHorizontal;

    @BindView(R.id.favTitle)
    TextView favTitle;

    private LigaAdapter mAdapter;
    //private UserAdapter mAdapterFavorites;

    //private List<User> mUsers = new ArrayList<>();
    //private List<User> mUsersFavorites = new ArrayList<>();

    private List<Liga> mLigas = new ArrayList<>();

    public LigaView(LigaActivity activity, LigaViewModel viewModel) {
        super(activity, viewModel);
        ButterKnife.bind(this, activity);
        setUpNavigation(toolbar);
        setUpSearchView();
        //setUpFavoritesGrid();
        //setUpGrid();
        setUpList();
    }

    @Override
    protected void subscribeUiToLiveData() {
        subscribeLigas();
        //subscribeFavorites();
    }

    @Override
    protected void showDataInUi() {

    }

    @Override
    public void onClick(View view, int position, Liga liga) {
        //openUserDetail(user);
    }

    private void subscribeLigas() {
        baseViewModel.getLigas().observe(baseActivity.get(), ligas -> {
            if (ligas == null || ligas.size() <= 0) {
                baseViewModel.requestDataToServer();
            } else {
                mLigas = ligas;
                mAdapter.addAll(mLigas);
                //String[] suggestions = User.getDataSource(mUsers).toArray(new String[0]);
                //mSearchView.setSuggestions(suggestions);
            }
        });
    }

    /*public void subscribeFavorites() {
        baseViewModel.getFavorites().observe(baseActivity.get(), usersFavorites -> {
            if (usersFavorites == null || usersFavorites.size() <= 0) {
                recyclerviewHorizontal.setVisibility(View.GONE);
                favTitle.setVisibility(View.GONE);
            } else {
                recyclerviewHorizontal.setVisibility(View.VISIBLE);
                favTitle.setVisibility(View.VISIBLE);
                mUsersFavorites = usersFavorites;
                mAdapterFavorites.reset();
                mAdapterFavorites.addAll(mUsersFavorites);
            }
        });
    }*/

    private void setUpList() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(baseActivity.get(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new LigaAdapter(baseActivity.get(), this, mLigas);
        mRecyclerView.setAdapter(mAdapter);
    }

    /*private void setUpGrid() {
        GridLayoutManager layoutManager = new GridLayoutManager(baseActivity.get(), Constants.GRID_SPAN_COUNT);
        mRecyclerView.setLayoutManager(layoutManager);
        EndlessRecyclerViewScrollListener mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                baseViewModel.requestDataToServer(page);
            }
        };
        mRecyclerView.addOnScrollListener(mScrollListener);
        mAdapter = new UserAdapter(baseActivity.get(), this, mUsers);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setUpFavoritesGrid() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(baseActivity.get(), LinearLayoutManager.HORIZONTAL, false);
        recyclerviewHorizontal.setLayoutManager(layoutManager);
        mAdapterFavorites = new UserAdapter(baseActivity.get(), this, mUsersFavorites);
        recyclerviewHorizontal.setAdapter(mAdapterFavorites);
    }*/

    private void setUpSearchView() {
        mSearchView.setEllipsize(true);
        mSearchView.setOnQueryTextListener(this);
    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
    }

    /*private void openUserDetail(User user) {
        Intent intent = new Intent(baseActivity.get(), UserDetailActivity.class);
        intent.putExtra(Constants.ARG_USER, Parcels.wrap(user));
        baseActivity.get().startActivity(intent);
    }*/

    @Override
    public boolean onQueryTextSubmit(String query) {
        //baseViewModel.getUserBySuggestion(query).observe(baseActivity.get(), this::openUserDetail);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
