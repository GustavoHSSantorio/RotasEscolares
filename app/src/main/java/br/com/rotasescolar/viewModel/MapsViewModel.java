package br.com.rotasescolar.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.controller.MapsController;
import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.RoutesVO;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 06/07/17.
 */

public class MapsViewModel extends BaseViewModel {

    private MapsController mapsController;
    public MutableLiveData<List<LatLng>> latLngs = new MutableLiveData<>();
    public MutableLiveData<List<Route>> routes = new MutableLiveData<>();

    public MapsViewModel(){
        mapsController = new MapsController();
    }

    public void updateLatLngByRouteId(int routeId){
        mapsController.getLatLongListByRouteId(routeId);
    }

    public void updateRoutes(){
        mapsController.getRoutes();
    }


    private List<LatLng> getStudentsLatLng(List<Student> students){
        List<LatLng> latLngs = new ArrayList<>();
        for (Student student : students){
            latLngs.add(new LatLng(student.getLat(), student.getLng()));
        }
        return latLngs;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLatLngLoaded(StudentVO studentVO){
        if(studentVO != null)
            this.latLngs.setValue(getStudentsLatLng(studentVO.getStudents()));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onRoutesLoaded(RoutesVO routesVO){
        if (routesVO != null)
            this.routes.setValue(routesVO.getRoutes());
    }
}
