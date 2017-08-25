package br.com.rotasescolar.viewModel;

import android.arch.lifecycle.MutableLiveData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.com.rotasescolar.controller.StudentsController;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.model.StudentGroup;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 14/08/17.
 */

public class StudentsViewModel extends BaseViewModel {

    private StudentsController controller;
    public MutableLiveData<List<StudentGroup>> students = new MutableLiveData<>();

    public StudentsViewModel(){
        controller = new StudentsController();
    }

    public void updateStudents(){
        controller.getStudents();
    }

    private void sortStudents(List<Student> students){

        List<StudentGroup> studentGroups = new ArrayList<>();
        TreeMap<Character, List<Student>> sortedByNameStudents = new TreeMap<>();

        for(Student student: students) {
            Character key = student.getName().charAt(0);
            if(!sortedByNameStudents.containsKey(key)) {
                sortedByNameStudents.put(key, new ArrayList<>());
            }
            sortedByNameStudents.get(key).add(student);
        }

        for(Character key : sortedByNameStudents.keySet()) {
            StudentGroup sg = new StudentGroup(key.toString(), sortedByNameStudents.get(key));
            studentGroups.add(sg);
            Collections.sort(sg.students,(Student t1, Student t2) -> t1.getName().compareTo(t2.getName()));
        }
        this.students.setValue(studentGroups);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onStudentsLoaded(StudentVO student){
        sortStudents(student.getStudents());
    }

}
