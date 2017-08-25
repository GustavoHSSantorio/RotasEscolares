package br.com.rotasescolar.controller;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Route;
import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.RoutesVO;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 19/07/17.
 */

public class RegisterRouteController {


    public void getStudents(int routeId){
//        Implementar chamada
        List<Student> students = new ArrayList<>();
        for(int i = 0; i <= 10; i++){
            Student student = new Student();
            student.setId(String.valueOf(i));
            student.setName("Estudante " + i);
            student.setLat(-35);
            student.setLng(135);
            student.setAddress("Rua Antonio Cassano, 101");
            students.add(student);
        }
        StudentVO vo = new StudentVO();
        vo.setStudents(students);
        EventBus.getDefault().post(vo);
    }

}
