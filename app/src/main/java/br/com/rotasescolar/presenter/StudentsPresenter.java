package br.com.rotasescolar.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.ui.adapter.StudentsAdapter;
import br.com.rotasescolar.ui.fragment.StudentsFragment;
import br.com.rotasescolar.viewModel.StudentsViewModel;

/**
 * Created by gustavosantorio on 14/08/17.
 */

public class StudentsPresenter extends BasePresenter {

    private StudentsFragment studentsFragment;
    private StudentsViewModel model;

    public StudentsPresenter(StudentsFragment studentsFragment){
        super((AppCompatActivity)studentsFragment.getActivity(), StudentsViewModel.class);
        this.studentsFragment = studentsFragment ;
        this.model = getViewModel();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(){
        registerStudentsObserver();
    }

    @Override
    public void onResume() {
        super.onResume();
        model.updateStudents();
    }

    private void registerStudentsObserver(){
        model.students.observe(studentsFragment, stringListMap -> studentsFragment.setAdapter(new StudentsAdapter(studentsFragment.getActivity(), stringListMap)));
    }
}
