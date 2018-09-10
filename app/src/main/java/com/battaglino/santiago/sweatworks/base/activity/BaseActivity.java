package com.battaglino.santiago.sweatworks.base.activity;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Santiago Battaglino.
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {

    /*
      The next two commented lines are an example of how to inject the view into the activity using dagger 2.11
      Dagger 2.11 must be used to resolve this dependency in order to have our view instanciated.

      @Inject
      BaseView baseView;
    */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        injectThis();
        super.onCreate(savedInstanceState);
    }

    /* Implement this  method to allow injection into this activity
     *  e.g: implement with 'AndroidInjection.inject(this);'
     *
     */
    protected abstract void injectThis();
}
