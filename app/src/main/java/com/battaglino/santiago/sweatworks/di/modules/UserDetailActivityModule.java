package com.battaglino.santiago.sweatworks.di.modules;

import com.battaglino.santiago.sweatworks.user.activity.UserDetailActivity;
import com.battaglino.santiago.sweatworks.user.mvvm.view.UserDetailView;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Santiago Battaglino.
 */
@Module
public class UserDetailActivityModule {

    @Provides
    UserDetailView provideUserDetailView(UserDetailActivity activity, UserGridViewModel viewModel) {
        return new UserDetailView(activity, viewModel);
    }
}
