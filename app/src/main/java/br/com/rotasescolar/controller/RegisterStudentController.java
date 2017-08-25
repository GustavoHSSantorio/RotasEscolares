package br.com.rotasescolar.controller;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import br.com.rotasescolar.model.Student;
import br.com.rotasescolar.vo.StudentVO;

/**
 * Created by gustavosantorio on 07/08/17.
 */

public class RegisterStudentController {


    public void getStudent(int studentId){
        Student student = new Student();
        student.setId(String.valueOf(1));
        student.setName("Gustavo Santorio");
        student.setLat(-35);
        student.setLng(135);
        student.setAddress("Rua Antonio Cassano");
        student.setAddressNumber("101");
        student.setZipcode("03974040");
        List<Student> students = new ArrayList<Student>();
        students.add(student);
        StudentVO vo = new StudentVO();
        vo.setStudents(students);
        EventBus.getDefault().post(vo);
    }
}
