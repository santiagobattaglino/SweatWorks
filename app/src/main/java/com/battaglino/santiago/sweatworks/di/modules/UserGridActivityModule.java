package com.battaglino.santiago.sweatworks.di.modules;

import com.battaglino.santiago.sweatworks.user.activity.UserGridActivity;
import com.battaglino.santiago.sweatworks.user.mvvm.view.UserGridView;
import com.battaglino.santiago.sweatworks.user.mvvm.viewmodel.UserGridViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Santiago Battaglino.
 */
@Module
public class UserGridActivityModule {

    @Provides
    UserGridView provideSyncView(UserGridActivity activity, UserGridViewModel viewModel) {
        return new UserGridView(activity, viewModel);
    }
}
