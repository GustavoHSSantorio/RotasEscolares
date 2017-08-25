package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import br.com.rotasescolar.viewModel.BaseViewModel;

/**
 * Created by gustavosantorio on 06/07/17.
 */

class BasePresenter implements LifecycleObserver {

    private BaseViewModel model;

    BasePresenter(){
    }

    BasePresenter(@NonNull AppCompatActivity appCompatActivity, @NonNull Class modelClass){
        model = (BaseViewModel) ViewModelProviders.of(appCompatActivity).get(modelClass);
    }

    protected <E extends ViewModel> E getViewModel(){
        return (E) model;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        try {
            model.registerBus();
        }catch (Exception e){

        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(){
        try {
            model.unregisterBus();
        }catch (Exception e){

        }
    }
}
