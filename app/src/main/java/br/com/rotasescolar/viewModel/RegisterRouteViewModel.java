package br.com.rotasescolar.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import br.com.rotasescolar.controller.RegisterRouteController;
import br.com.rotasescolar.controller.RoutesController;
import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.RoutesVO;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 19/07/17.
 */

public class RegisterRouteViewModel extends BaseViewModel {

    private RegisterRouteController registerRouteController;
    public MutableLiveData<List<Student>> students = new MutableLiveData<>();

    public RegisterRouteViewModel(){
        registerRouteController = new RegisterRouteController();
    }

    public void updateStudents(int routeId){
        registerRouteController.getStudents(routeId);
    }

    public void save(Route route){

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onStudentsLoaded(StudentVO studentVO){
        this.students.setValue(studentVO.getStudents());
    }
}
