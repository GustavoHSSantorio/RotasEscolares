package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;

import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.ui.activity.RegisterStudentActivity;
import br.com.rotasescolar.viewModel.RegisterStudentViewModel;

/**
 * Created by gustavosantorio on 07/08/17.
 */

public class RegisterStudentPresenter extends BasePresenter{

    private RegisterStudentActivity studentActivity;
    private RegisterStudentViewModel model;

    public RegisterStudentPresenter(RegisterStudentActivity studentActivity){
        super(studentActivity, RegisterStudentViewModel.class);
        this.studentActivity = studentActivity;
        model = super.getViewModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerStudentObserver();
        applyRule();
    }

    private void applyRule(){
        if(studentActivity.studentId != null)
        model.updateStudent(studentActivity.studentId);
        else
            studentActivity.setDefaultMode();
    }

    private void registerStudentObserver(){
        model.student.observe(studentActivity, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                if(student != null)
                    studentActivity.setEditMode(student);
            }
        });
    }

}
