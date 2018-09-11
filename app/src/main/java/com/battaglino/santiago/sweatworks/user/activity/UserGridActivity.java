package com.battaglino.santiago.sweatworks.user.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.battaglino.santiago.sweatworks.R;
import com.battaglino.santiago.sweatworks.base.activity.BaseActivity;
import com.battaglino.santiago.sweatworks.user.mvvm.view.UserGridView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class UserGridActivity extends BaseActivity {

    @Inject
    UserGridView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_item_list);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectThis() {
        AndroidInjection.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        view.mSearchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (view.mSearchView.isSearchOpen()) {
            view.mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
