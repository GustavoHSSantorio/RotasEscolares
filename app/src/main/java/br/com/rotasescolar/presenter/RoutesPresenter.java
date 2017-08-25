package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.ui.activity.RegisterRouteActivity;
import br.com.rotasescolar.ui.adapter.RoutesAdapter;
import br.com.rotasescolar.ui.fragment.RoutesFragment;
import br.com.rotasescolar.utils.Constants;
import br.com.rotasescolar.viewModel.RoutesViewModel;

/**
 * Created by gustavosantorio on 17/07/17.
 */

public class RoutesPresenter extends BasePresenter {
    private RoutesFragment routesFragment;
    private RoutesViewModel model;
    private RoutesAdapter adapter;

    public RoutesPresenter(RoutesFragment routesFragment){
        super((AppCompatActivity) routesFragment.getActivity(), RoutesViewModel.class);
        this.routesFragment = routesFragment;
        this.model = getViewModel();
    }


    @Override
    public void onResume(){
        super.onResume();
        registerRoutesObserver();
        model.updateRoutes();
    }

    private void registerRoutesObserver(){
        model.routes.observe(routesFragment, new Observer<List<Route>>() {
            @Override
            public void onChanged(@Nullable List<Route> routes) {
                if(adapter != null)
                    adapter.updateDataSet(routes);
                else
                    routesFragment.setAdapter(adapter = new RoutesAdapter(routesFragment.getActivity(), routes));
            }
        });
    }

    public void onItemRouteClick(int position){
        forwardaToRegisterRoute(adapter.getRoutes().get(position).getId());
    }

    public void onClickAdd(){
        forwardaToRegisterRoute(null);
    }

    private void forwardaToRegisterRoute(Integer routeId){
        Intent intent = new Intent(routesFragment.getActivity(), RegisterRouteActivity.class);
        if(routeId != null)
            intent.putExtra(Constants.RoutesConstants.ROUTE_ID ,routeId);
        routesFragment.getActivity().startActivity(intent);
    }

}
