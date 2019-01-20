package com.battaglino.santiago.sweatworks.di.modules;

import com.battaglino.santiago.sweatworks.liga.activity.LigaActivity;
import com.battaglino.santiago.sweatworks.liga.mvvm.view.LigaView;
import com.battaglino.santiago.sweatworks.liga.mvvm.viewmodel.LigaViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Santiago Battaglino.
 */
@Module
public class LigaActivityModule {

    @Provides
    LigaView provideLigaView(LigaActivity activity, LigaViewModel viewModel) {
        return new LigaView(activity, viewModel);
    }
}
