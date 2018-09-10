package com.battaglino.santiago.sweatworks.di.components;

import com.battaglino.santiago.sweatworks.di.modules.ActivityBuilder;
import com.battaglino.santiago.sweatworks.di.modules.AppModule;
import com.battaglino.santiago.sweatworks.global.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Santiago Battaglino.
 * <p>
 * Android apps have one application class. That is why we have one application component.
 * This component is responsible for providing application scope instances (eg. OkHttp, Database, SharedPrefs.).
 * This Component is root of our dagger graph. Application component is providing 4 module in our app.
 * <p>
 * AndroidInjectionModule : We didn’t create this. It is an internal class in Dagger.
 * Provides our activities and fragments with given module.
 * <p>
 * ActivityBuilder and FragmentBuilder: We created this module. This is a given module to dagger.
 * We map all our activities and fragments here. And Dagger know our activities and fragments in compile time.
 * <p>
 * AppModule: We provide retrofit client, okhttp, UseCaseRepository, etc here.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(App app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(App application);

        AppComponent build();
    }

}
