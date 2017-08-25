package br.com.rotasescolar.controller;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 14/08/17.
 */

public class StudentsController {

    public void getStudents(){
//        Implementar chamada
        List<Student> students = new ArrayList<>();
        for(int i = 0; i <= 15; i++){
            Student student = new Student();
            student.setId(String.valueOf(i));
            student.setName("Estudante " + i);
            student.setLat(-35);
            student.setLng(135);
            student.setAddress("Rua Antonio Cassano, 101");
            students.add(student);
        }

        students.get(0).setName("ABEL");
        students.get(1).setName("ALBERT");
        students.get(2).setName("CLAUDIO");
        students.get(3).setName("CANDIDA");
        students.get(4).setName("ESLOGANO");
        students.get(5).setName("ESLOGANES");
        students.get(6).setName("GUSTAVO");
        students.get(7).setName("GUILHERME");
        students.get(8).setName("INGRID");
        students.get(9).setName("INGRID");
        students.get(10).setName("INGRID");
        students.get(11).setName("INGRID");
        students.get(12).setName("ABELARDO");
        students.get(13).setName("ALESSANDRA");
        students.get(14).setName("ALEXANDRE");

        StudentVO vo = new StudentVO();
        vo.setStudents(students);
        EventBus.getDefault().post(vo);
    }
}
