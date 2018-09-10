package com.battaglino.santiago.sweatworks.user.mvvm.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.mvvm.view.BaseView;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.user.activity.UserGridActivity;
import com.battaglino.santiago.sweatworks.user.adapter.SimpleItemRecyclerViewAdapter;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Santiago Battaglino.
 */
public class UserGridView extends BaseView<UserGridActivity, UserGridViewModel> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

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
        baseViewModel.getUsers().observe(baseActivity.get(), users -> {
            if (users == null || users.size() <= 0) {
                baseViewModel.fetchFromServer();
            } else {
                mUsers = users;
                showDataInUi();
            }
        });
    }

    @Override
    protected void showDataInUi() {
        mRecyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(baseActivity.get(), mUsers, mTwoPane));
    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
    }
}
