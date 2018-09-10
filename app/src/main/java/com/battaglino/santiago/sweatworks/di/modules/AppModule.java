package com.battaglino.santiago.sweatworks.di.modules;

import android.app.Application;

import com.battaglino.santiago.sweatworks.global.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Santiago Battaglino.
 */
@Module(includes = {RetrofitModule.class})
public class AppModule {

    @Provides
    @Singleton
    Application provideContext(App application) {
        return application;
    }
}
