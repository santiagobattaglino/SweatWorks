package com.battaglino.santiago.sweatworks.user.mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.mvvm.view.BaseView;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.global.Constants;
import com.battaglino.santiago.sweatworks.user.activity.ItemDetailActivity;
import com.battaglino.santiago.sweatworks.user.activity.UserGridActivity;
import com.battaglino.santiago.sweatworks.user.adapter.UserAdapter;
import com.battaglino.santiago.sweatworks.user.fragment.ItemDetailFragment;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class UserGridView extends BaseView<UserGridActivity, UserGridViewModel> implements UserAdapter.OnViewHolderClick {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private EndlessRecyclerViewScrollListener mScrollListener;

    private boolean mTwoPane;

    private List<User> mUsers;

    public UserGridView(UserGridActivity activity, UserGridViewModel viewModel) {
        super(activity, viewModel);
        ButterKnife.bind(this, activity);

        setUpNavigation(toolbar);

        if (baseActivity.get().findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }
    }

    @Override
    protected void subscribeUiToLiveData() {
        subscribeUsers();
    }

    @Override
    protected void showDataInUi() {

    }

    @Override
    public void onClick(View view, int position, User user) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ItemDetailFragment.ARG_USER, Parcels.wrap(user));
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            baseActivity.get().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(baseActivity.get(), ItemDetailActivity.class);
            intent.putExtra(ItemDetailFragment.ARG_USER, Parcels.wrap(user));
            baseActivity.get().startActivity(intent);
        }
    }

    private void subscribeUsers() {
        baseViewModel.getUsers().observe(baseActivity.get(), users -> {
            if (users == null || users.size() <= 0) {
                baseViewModel.fetchUsersFromServer(1);
            } else {
                mUsers = users;
                setUpGrid();
            }
        });
    }

    private void setUpGrid() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager layoutManager = new GridLayoutManager(baseActivity.get(), Constants.GRID_SPAN_COUNT);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new UserAdapter(baseActivity.get(), this, mUsers));

        mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                baseViewModel.fetchUsersFromServer(page);
            }
        };

        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
    }
}
