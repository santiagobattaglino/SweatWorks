package com.battaglino.santiago.sweatworks.user.mvvm.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.mvvm.view.BaseView;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.user.activity.UserGridActivity;
import com.battaglino.santiago.sweatworks.user.adapter.UserAdapter;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;

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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        GridLayoutManager mLayoutManager = new GridLayoutManager(baseActivity.get(), 5);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //MovimientoAdapter adapter = new MovimientoAdapter(baseActivity.get(), this, mMovimientos);
        UserAdapter adapter = new UserAdapter(baseActivity.get(), this, mUsers);

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, int position, User item) {
            /*DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }*/
    }

    private void setUpNavigation(Toolbar toolbar) {
        baseActivity.get().setSupportActionBar(toolbar);
    }
}
