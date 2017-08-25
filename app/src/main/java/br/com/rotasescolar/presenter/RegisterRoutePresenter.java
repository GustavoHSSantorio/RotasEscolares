package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import br.com.rotasescolar.controller.RegisterRouteController;
import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.ui.activity.RegisterRouteActivity;
import br.com.rotasescolar.ui.adapter.RoutesAdapter;
import br.com.rotasescolar.ui.adapter.RoutesStudentPreviewAdapter;
import br.com.rotasescolar.viewModel.RegisterRouteViewModel;

/**
 * Created by gustavosantorio on 19/07/17.
 */

public class RegisterRoutePresenter extends BasePresenter{

    private RegisterRouteActivity registerRouteActivity;
    private RegisterRouteViewModel model;


    public RegisterRoutePresenter(RegisterRouteActivity registerRouteActivity){
        super(registerRouteActivity, RegisterRouteViewModel.class);
        this.registerRouteActivity = registerRouteActivity;
        model = getViewModel();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        registerRoutesObserver();
    }

    @Override
    public void onResume(){
        super.onResume();

        if(registerRouteActivity.routeId != null)
            updateStudents(registerRouteActivity.routeId);
    }

    private void updateStudents(int routeId){
        model.updateStudents(routeId);
    }

    private void registerRoutesObserver(){
        model.students.observe(registerRouteActivity, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                registerRouteActivity.setAdapters(new RoutesStudentPreviewAdapter(registerRouteActivity, students));
            }
        });
    }

    public void save(){
        model.save(fillRoute());
        registerRouteActivity.finish();
    }

    private Route fillRoute(){
        Route route = new Route();
        route.setName(registerRouteActivity.etRouteName.getText().toString());
        return route;
    }
}
