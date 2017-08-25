package br.com.rotasescolar.vo;

import java.io.Serializable;
import java.util.List;

import br.com.rotasescolar.model.Student;

/**
 * Created by gustavosantorio on 19/07/17.
 */

public class StudentVO implements Serializable {

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
