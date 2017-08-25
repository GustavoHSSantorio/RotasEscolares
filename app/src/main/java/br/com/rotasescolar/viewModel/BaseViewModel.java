package br.com.rotasescolar.viewModel;

import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by gustavosantorio on 26/07/17.
 */

public class BaseViewModel extends ViewModel {

    public void registerBus(){
        EventBus.getDefault().register(this);
    }

    public void unregisterBus(){
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        EventBus.getDefault().unregister(this);
    }
}
