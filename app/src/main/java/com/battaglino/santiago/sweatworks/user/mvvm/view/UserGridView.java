package com.battaglino.santiago.sweatworks.user.mvvm.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.mvvm.view.BaseView;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.global.Constants;
import com.battaglino.santiago.sweatworks.user.activity.UserDetailActivity;
import com.battaglino.santiago.sweatworks.user.activity.UserGridActivity;
import com.battaglino.santiago.sweatworks.user.adapter.UserAdapter;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class UserGridView extends BaseView<UserGridActivity, UserGridViewModel>
        implements UserAdapter.OnViewHolderClick, MaterialSearchView.OnQueryTextListener {

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

    private UserAdapter mAdapter;
    private UserAdapter mAdapterFavorites;

    private List<User> mUsers = new ArrayList<>();
    private List<User> mUsersFavorites = new ArrayList<>();

    public UserGridView(UserGridActivity activity, UserGridViewModel viewModel) {
        super(activity, viewModel);
        ButterKnife.bind(this, activity);
        setUpNavigation(toolbar);
        setUpSearchView();
        setUpFavoritesGrid();
        setUpGrid();
    }

    @Override
    protected void subscribeUiToLiveData() {
        subscribeUsers();
        subscribeFavorites();
    }

    @Override
    protected void showDataInUi() {

    }

    @Override
    public void onClick(View view, int position, User user) {
        openUserDetail(user);
    }

    private void subscribeUsers() {
        baseViewModel.getUsers().observe(baseActivity.get(), users -> {
            if (users == null || users.size() <= 0) {
                baseViewModel.fetchUsersFromServer(1);
            } else {
                mUsers = users;
                mAdapter.addAll(mUsers);
                String[] suggestions = User.getDataSource(mUsers).toArray(new String[0]);
                mSearchView.setSuggestions(suggestions);
            }
        });
    }

    public void subscribeFavorites() {
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
    }

    private void setUpGrid() {
        GridLayoutManager layoutManager = new GridLayoutManager(baseActivity.get(), Constants.GRID_SPAN_COUNT);
        mRecyclerView.setLayoutManager(layoutManager);
        EndlessRecyclerViewScrollListener mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                baseViewModel.fetchUsersFromServer(page);
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
    }

    private void setUpSearchView() {
        mSearchView.setEllipsize(true);
        mSearchView.setOnQueryTextListener(this);
    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
    }

    private void openUserDetail(User user) {
        Intent intent = new Intent(baseActivity.get(), UserDetailActivity.class);
        intent.putExtra(Constants.ARG_USER, Parcels.wrap(user));
        baseActivity.get().startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        baseViewModel.getUserBySuggestion(query).observe(baseActivity.get(), this::openUserDetail);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
