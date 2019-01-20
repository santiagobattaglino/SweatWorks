package com.battaglino.santiago.sweatworks.di.modules;

import com.battaglino.santiago.sweatworks.liga.activity.LigaActivity;
import com.battaglino.santiago.sweatworks.user.activity.UserDetailActivity;
import com.battaglino.santiago.sweatworks.user.activity.UserGridActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Santiago Battaglino.
 * <p>
 * This is a given module to dagger. We map all our activities here.
 * And Dagger know our activities in compile time.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {LigaActivityModule.class})
    abstract LigaActivity bindLigaActivity();

    @ContributesAndroidInjector(modules = {UserGridActivityModule.class})
    abstract UserGridActivity bindUserGridActivity();

    @ContributesAndroidInjector(modules = {UserDetailActivityModule.class})
    abstract UserDetailActivity bindUserDetailActivity();
}
