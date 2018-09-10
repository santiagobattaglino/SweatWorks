package com.battaglino.santiago.sweatworks.user.activity;

import android.os.Bundle;

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
}
