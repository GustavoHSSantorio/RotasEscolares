package br.com.rotasescolar.ui.activity;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by gustavosantorio on 26/07/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Override
    public abstract LifecycleRegistry getLifecycle();

    public <E extends View> E viewById(int id){
        return (E) findViewById(id);
    }
}
