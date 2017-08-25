package br.com.rotasescolar.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.com.rotasescolar.controller.RegisterStudentController;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 07/08/17.
 */

public class RegisterStudentViewModel extends BaseViewModel {

    private RegisterStudentController controller ;
    public MutableLiveData<Student> student = new MutableLiveData<>();

    public RegisterStudentViewModel(){
        controller = new RegisterStudentController();
    }

    public void updateStudent(int studentId) {
        controller.getStudent(studentId);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onStudentsLoaded(StudentVO studentVO){
        if(studentVO.getStudents() != null)
            this.student.setValue(studentVO.getStudents().get(0));
    }
}
