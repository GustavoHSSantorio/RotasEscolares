package br.com.rotasescolar.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import br.com.rotasescolar.controller.RoutesController;
import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.vo.RoutesVO;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class RoutesViewModel extends BaseViewModel {

    private RoutesController routesController;
    public MutableLiveData<List<Route>> routes = new MutableLiveData<>();

    public RoutesViewModel(){
        routesController = new RoutesController();
    }

    public void updateRoutes(){
        routesController.getRoutes();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRoutesLoaded(RoutesVO routes){
        this.routes.setValue(routes.getRoutes());
    }

}
