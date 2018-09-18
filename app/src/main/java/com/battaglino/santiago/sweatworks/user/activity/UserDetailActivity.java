package com.battaglino.santiago.sweatworks.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.activity.BaseActivity;
import com.battaglino.santiago.sweatworks.db.entities.User;
import com.battaglino.santiago.sweatworks.global.Constants;
import com.battaglino.santiago.sweatworks.user.fragment.UserDetailFragment;
import com.battaglino.santiago.sweatworks.user.mvvm.view.UserDetailView;

import org.parceler.Parcels;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserDetailActivity extends BaseActivity {

    @Inject
    UserDetailView view;

    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_user_detail);
        user = Parcels.unwrap(getIntent().getParcelableExtra(Constants.ARG_USER));
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(Constants.ARG_USER,
                    Parcels.wrap(user));
            UserDetailFragment fragment = new UserDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void injectThis() {
        AndroidInjection.inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, UserGridActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public User getUser() {
        return user;
    }
}
